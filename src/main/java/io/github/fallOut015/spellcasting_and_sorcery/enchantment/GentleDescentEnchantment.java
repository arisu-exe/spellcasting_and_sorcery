package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class GentleDescentEnchantment extends Enchantment {
    public GentleDescentEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentTypeSpellcastingAndSorcery.HERMES_HELMET, slots);
    }
}