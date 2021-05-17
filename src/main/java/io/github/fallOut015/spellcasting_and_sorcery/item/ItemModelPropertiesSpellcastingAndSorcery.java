package io.github.fallOut015.spellcasting_and_sorcery.item;

import io.github.fallOut015.spellcasting_and_sorcery.MainSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.common.Config;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ItemModelPropertiesSpellcastingAndSorcery {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ItemModelsProperties.register(ItemsSpellcastingAndSorcery.BLOOD_VENOM_BLADE.get(), new ResourceLocation(MainSpellcastingAndSorcery.MODID, "pam_texture"), (itemStack, clientWorld, livingEntity) -> Config.usePamTextureBloodBlade() ? 1F : 0F);
    }
}