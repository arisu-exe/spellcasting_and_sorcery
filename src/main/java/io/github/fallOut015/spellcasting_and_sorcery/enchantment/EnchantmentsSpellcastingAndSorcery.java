package io.github.fallOut015.spellcasting_and_sorcery.enchantment;

import io.github.fallOut015.spellcasting_and_sorcery.MainSpellcastingAndSorcery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentsSpellcastingAndSorcery {
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MainSpellcastingAndSorcery.MODID);



    public static final RegistryObject<Enchantment> VITALITY_THIEF = ENCHANTMENTS.register("vitality_thief", () -> new VitalityThiefEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> SNAPPING = ENCHANTMENTS.register("snapping", () -> new SnappingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> DISTANCE = ENCHANTMENTS.register("distance", () -> new DistanceEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> BOUNDING = ENCHANTMENTS.register("bounding", () -> new BoundingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.FEET));
    public static final RegistryObject<Enchantment> GENTLE_DESCENT = ENCHANTMENTS.register("gentle_descent", () -> new GentleDescentEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.HEAD));
    // conjuring



    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
