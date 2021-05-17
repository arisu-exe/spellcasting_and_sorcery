package io.github.fallOut015.spellcasting_and_sorcery.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.fallOut015.spellcasting_and_sorcery.enchantment.EnchantmentsSpellcastingAndSorcery;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BanisherItem extends Item implements IVanishable {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> modifiers;

    public BanisherItem(Properties properties) {
        super(properties);
        this.attackDamage = 0f;
        float attackSpeedIn = -3.5f;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.modifiers = builder.build();
    }

    @Override
    public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, livingEntityIn -> livingEntityIn.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        return true;
    }

    @SuppressWarnings("deprecation")
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.modifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(entity instanceof LivingEntity) {
            stack.hurtEnemy((LivingEntity) entity, player);
            if((player).getAttackStrengthScale(0.5f) == 1f) {
                ((LivingEntity) entity).knockback(2 * (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsSpellcastingAndSorcery.DISTANCE.get(), stack) + 1), -(entity.xOld - player.xOld), -(entity.zOld - player.zOld));
                entity.level.addParticle(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(), entity.getZ(), -(entity.xOld - player.xOld), -0.1, -(entity.zOld - player.zOld));
                stack.hurtAndBreak(4, player, livingEntityIn -> livingEntityIn.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                return true;
            }
        }
        return false;
    }
}