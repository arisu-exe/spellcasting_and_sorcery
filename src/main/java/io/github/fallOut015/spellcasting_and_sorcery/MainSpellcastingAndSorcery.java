package io.github.fallOut015.spellcasting_and_sorcery;

import io.github.fallOut015.spellcasting_and_sorcery.common.Config;
import io.github.fallOut015.spellcasting_and_sorcery.enchantment.EnchantmentTypeSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.enchantment.EnchantmentsSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.item.ItemModelPropertiesSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.item.ItemsSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.server.JumpPacketHandler;
import io.github.fallOut015.spellcasting_and_sorcery.server.PacketHandler;
import io.github.fallOut015.spellcasting_and_sorcery.util.SoundEventsSpellcastingAndSorcery;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

@Mod(MainSpellcastingAndSorcery.MODID)
public class MainSpellcastingAndSorcery {
    public static final String MODID = "spellcasting_and_sorcery";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public MainSpellcastingAndSorcery() {
        ItemsSpellcastingAndSorcery.register(FMLJavaModLoadingContext.get().getModEventBus());
        EnchantmentsSpellcastingAndSorcery.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundEventsSpellcastingAndSorcery.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.setup(event);

        // TODO put somewhere iDK
        ItemGroup.TAB_COMBAT.setEnchantmentCategories(EnchantmentType.VANISHABLE, EnchantmentType.ARMOR, EnchantmentType.ARMOR_FEET, EnchantmentType.ARMOR_HEAD, EnchantmentType.ARMOR_LEGS, EnchantmentType.ARMOR_CHEST, EnchantmentType.BOW, EnchantmentType.WEAPON, EnchantmentType.WEARABLE, EnchantmentType.BREAKABLE, EnchantmentType.TRIDENT, EnchantmentType.CROSSBOW, EnchantmentTypeSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS, EnchantmentTypeSpellcastingAndSorcery.BLOOD_BLADE, EnchantmentTypeSpellcastingAndSorcery.BANISHER, EnchantmentTypeSpellcastingAndSorcery.HERMES_HELMET, EnchantmentTypeSpellcastingAndSorcery.EVOCATION_STAFF, EnchantmentTypeSpellcastingAndSorcery.CONJURATION_STAFF);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemModelPropertiesSpellcastingAndSorcery.doClientStuff(event);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        if(event.getConfig().getSpec() == Config.CLIENT_SPEC) {
            Config.bakeConfig();
        }
    }

    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onLivingFall(final LivingFallEvent livingFallEvent) {
            if(livingFallEvent.getEntityLiving() instanceof PlayerEntity) {
                if(livingFallEvent.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemsSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS.get()) {
                    livingFallEvent.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getOrCreateTag().putInt("jumps", 0);
                    // queue something to let the player know that the jumps are regained
                }
            }
        }
        @SubscribeEvent
        public static void onKeyInput(final InputEvent.KeyInputEvent inputEvent$KeyInputEvent) {
            if(!Minecraft.getInstance().isPaused() && inputEvent$KeyInputEvent.getKey() == GLFW.GLFW_KEY_SPACE && inputEvent$KeyInputEvent.getAction() == GLFW.GLFW_PRESS) {
                LOGGER.debug("Sending jump packet to server");
                PacketHandler.INSTANCE.sendToServer(new JumpPacketHandler());
            }
        }
    }
}