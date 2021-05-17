package io.github.fallOut015.spellcasting_and_sorcery.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class BeamingBootsItem extends ArmorItem {
    public BeamingBootsItem(Properties builder) {
        super(ArmorMaterialSpellcastingAndSorcery.BEAMING_BOOTS, EquipmentSlotType.FEET, builder);
    }
}