package io.github.fallOut015.spellcasting_and_sorcery.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public abstract class BloodBladeItem extends SwordItem {
    public BloodBladeItem(Properties builder) {
        super(ItemTierSpellcastingAndSorcery.BLOOD_BLADE, 3, -2.4f, builder);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        float xp = stack.getTag().getFloat("xp");
        int level = stack.getTag().getInt("level");

        tooltip.add(new StringTextComponent("XP: " + xp + " / " + Math.pow(1.45, stack.getTag().getInt("level"))));
        tooltip.add(new StringTextComponent("Level: " + level));
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(playerIn.getActiveItemStack().hasTag()) {
            playerIn.getActiveItemStack().getTag().putFloat("xp", 0.1f);
            playerIn.getActiveItemStack().getTag().putInt("level", 10);
        } else {
            System.out.println("No tag!");
        }

        // TODO

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(entity instanceof LivingEntity) {
            stack.hitEntity((LivingEntity) entity, player);
            if((player).getCooledAttackStrength(0.5f) == 1f) {
                this.bonusEffect(stack, (LivingEntity) entity, player);

                if(EnchantmentHelper.getEnchantmentLevel(EnchantmentsTwo.VITALITY_THIEF.get(), stack) > 0) {
                    player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, EnchantmentHelper.getEnchantmentLevel(EnchantmentsTwo.VITALITY_THIEF.get(), stack), false, false));
                }
            }

            CompoundNBT nbt;
            if(player.getActiveItemStack().hasTag()) {
                nbt = player.getActiveItemStack().getTag();
            } else {
                nbt = new CompoundNBT();
                nbt.putFloat("xp", 0.1f);
                nbt.putInt("level", 0);
            }

            nbt.putFloat("xp", (float) (nbt.getFloat("xp") + Math.sqrt(stack.getDamage())));
            if(nbt.getFloat("xp") >= Math.pow(1.45, nbt.getInt("level"))) {
                nbt.putInt("level", nbt.getInt("level") + 1);
                nbt.putFloat("xp", 0);
            }

            player.getActiveItemStack().setTag(nbt);
        }
        return false;
    }

    public abstract void bonusEffect(ItemStack stack, LivingEntity target, LivingEntity attacker);
}