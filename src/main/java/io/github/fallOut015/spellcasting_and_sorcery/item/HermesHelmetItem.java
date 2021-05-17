package io.github.fallOut015.spellcasting_and_sorcery.item;

import io.github.fallOut015.spellcasting_and_sorcery.enchantment.EnchantmentsSpellcastingAndSorcery;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class HermesHelmetItem extends ArmorItem {
    public HermesHelmetItem(Properties builder) {
        super(ArmorMaterialSpellcastingAndSorcery.HERMES_HELMET, EquipmentSlotType.HEAD, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        int amplifierIn = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsSpellcastingAndSorcery.GENTLE_DESCENT.get(), stack);

        if(player.getEffect(Effects.SLOW_FALLING) == null) {
            player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 1, amplifierIn, true, false));
        } else {
            player.getEffect(Effects.SLOW_FALLING).update(new EffectInstance(Effects.SLOW_FALLING, 1, amplifierIn, true, false));
        }
        super.onArmorTick(stack, world, player);
    }
}