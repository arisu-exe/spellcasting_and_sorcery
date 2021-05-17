package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class VitalityThiefEnchantment extends Enchantment {
    protected VitalityThiefEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentTypeSpellcastingAndSorcery.BLOOD_BLADE, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}