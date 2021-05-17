package io.github.fallOut015.spellcasting_and_sorcery.util;

import io.github.fallOut015.spellcasting_and_sorcery.MainSpellcastingAndSorcery;
import net.minecraft.data.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventsSpellcastingAndSorcery {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MainSpellcastingAndSorcery.MODID);



    public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_DOUBLE_JUMP_BOOTS = SOUND_EVENTS.register("item_armor_equip_double_jump_boots", () -> new SoundEvent(new ResourceLocation("two", "item.armor.equip_double_jump_boots"))); // also used for power up sound
    public static final RegistryObject<SoundEvent> ITEM_ARMOR_OFF_DOUBLE_JUMP_BOOTS = SOUND_EVENTS.register("item_armor_off_double_jump_boots", () -> new SoundEvent(new ResourceLocation("two", "item.armor.off_double_jump_boots"))); // used for when the double jump boots run out of double jumps
    public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_BEAMING_BOOTS = SOUND_EVENTS.register("item_armor_equip_beaming_boots", () -> new SoundEvent(new ResourceLocation("two", "item.armor.equip_beaming_boots")));
    public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_HERMES_HELMET = SOUND_EVENTS.register("item_armor_equip_hermes_helmet", () -> new SoundEvent(new ResourceLocation("two", "item.armor.equip_hermes_helmet")));



    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}