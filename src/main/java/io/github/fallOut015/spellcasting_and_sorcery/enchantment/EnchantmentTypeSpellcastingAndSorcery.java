package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import io.github.fallOut015.spellcasting_and_sorcery.item.BloodBladeItem;
import io.github.fallOut015.spellcasting_and_sorcery.item.ItemsSpellcastingAndSorcery;
import net.minecraft.enchantment.EnchantmentType;

public class EnchantmentTypeSpellcastingAndSorcery {
    public static final EnchantmentType DOUBLE_JUMP_BOOTS = EnchantmentType.create("double_jump_boots", item -> item.asItem() == ItemsSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS.get());
    public static final EnchantmentType BLOOD_BLADE = EnchantmentType.create("blood_blade", item -> item.asItem() instanceof BloodBladeItem);
    public static final EnchantmentType EVOCATION_STAFF = EnchantmentType.create("evocation_staff", item -> item.asItem() == ItemsSpellcastingAndSorcery.EVOCATION_STAFF.get());
    public static final EnchantmentType CONJURATION_STAFF = EnchantmentType.create("conjuration_staff", item -> item.asItem() == ItemsSpellcastingAndSorcery.CONJURATION_STAFF.get());
    public static final EnchantmentType BANISHER = EnchantmentType.create("banisher", item -> item.asItem() == ItemsSpellcastingAndSorcery.BANISHER.get());
    public static final EnchantmentType HERMES_HELMET = EnchantmentType.create("hermes_helmet", item -> item.asItem() == ItemsSpellcastingAndSorcery.HERMES_HELMET.get());
}