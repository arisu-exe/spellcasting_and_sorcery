package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class DistanceEnchantment extends Enchantment {
    public DistanceEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentTypeSpellcastingAndSorcery.BANISHER, slots);
    }
}