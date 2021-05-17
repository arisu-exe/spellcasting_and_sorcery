package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class BoundingEnchantment extends Enchantment {
    public BoundingEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentTypeSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}