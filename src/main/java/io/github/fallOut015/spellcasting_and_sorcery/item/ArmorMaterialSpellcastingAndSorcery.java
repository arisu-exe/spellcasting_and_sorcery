package io.github.fallOut015.spellcasting_and_sorcery.item;

import io.github.fallOut015.spellcasting_and_sorcery.util.SoundEventsSpellcastingAndSorcery;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ArmorMaterialSpellcastingAndSorcery implements IArmorMaterial {
    DOUBLE_JUMP_BOOTS("double_jump_boots", 25, new int[] {2, 0, 0, 0}, 5, SoundEventsSpellcastingAndSorcery.ITEM_ARMOR_EQUIP_DOUBLE_JUMP_BOOTS, 0.0f, 0, () -> {
        return Ingredient.of(Items.FEATHER);
    }),
    BEAMING_BOOTS("beaming_boots", 5, new int[] {2, 0, 0, 0}, 5, SoundEventsSpellcastingAndSorcery.ITEM_ARMOR_EQUIP_BEAMING_BOOTS, 0.0f, 0, () -> {
        return Ingredient.of(Items.CHORUS_FRUIT); // TODO idk
    }),
    HERMES_HELMET("hermes_helmet", 5, new int[] {0, 0, 0, 2}, 5, SoundEventsSpellcastingAndSorcery.ITEM_ARMOR_EQUIP_HERMES_HELMET, 0.0f, 0, () -> {
        return Ingredient.of(Items.FEATHER);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final LazyValue<SoundEvent> soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    ArmorMaterialSpellcastingAndSorcery(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, Supplier<SoundEvent> equipSoundIn, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterialSupplier) {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountsIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = new LazyValue<>(equipSoundIn);
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }
    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }
    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent.get();
    }
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}