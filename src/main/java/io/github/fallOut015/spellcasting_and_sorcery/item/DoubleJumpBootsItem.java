package io.github.fallOut015.spellcasting_and_sorcery.item;

import io.github.fallOut015.spellcasting_and_sorcery.enchantment.EnchantmentsSpellcastingAndSorcery;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DoubleJumpBootsItem extends ArmorItem {
    public DoubleJumpBootsItem(Properties builder) {
        super(ArmorMaterialSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS, EquipmentSlotType.FEET, builder);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.getOrCreateTag().putInt("jumps", 0);
    }

    /**
     *
     * @param ItemStack
     * @return the number of times you can do a midair jump (no enchantments is 3, Bounding I is 4, Bounding II is 5, and Bounding III is 6
     */
    public static int getJumpLimit(ItemStack stack) {
        return 3 + EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsSpellcastingAndSorcery.BOUNDING.get(), stack);
    }
}