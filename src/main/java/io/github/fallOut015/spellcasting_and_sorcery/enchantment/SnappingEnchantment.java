package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SnappingEnchantment extends Enchantment {
    public SnappingEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentTypeSpellcastingAndSorcery.EVOCATION_STAFF, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}