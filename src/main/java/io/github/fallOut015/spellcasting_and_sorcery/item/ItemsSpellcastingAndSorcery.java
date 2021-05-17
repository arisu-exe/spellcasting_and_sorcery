package io.github.fallOut015.spellcasting_and_sorcery.item;

import io.github.fallOut015.spellcasting_and_sorcery.MainSpellcastingAndSorcery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsSpellcastingAndSorcery {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainSpellcastingAndSorcery.MODID);



    public static final RegistryObject<Item> BLOOD_WITHER_BLADE = ITEMS.register("blood_wither_blade", () -> new BloodWitherBladeItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BLOOD_FLAME_BLADE = ITEMS.register("blood_flame_blade", () -> new BloodFlameBladeItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BLOOD_VENOM_BLADE = ITEMS.register("blood_venom_blade", () -> new BloodVenomBladeItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BANISHER = ITEMS.register("banisher", () -> new BanisherItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).rarity(Rarity.RARE).defaultDurability(1280)));
    public static final RegistryObject<Item> EVOCATION_STAFF = ITEMS.register("evocation_staff", () -> new EvocationStaffItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).rarity(Rarity.EPIC).defaultDurability(1280)));
    public static final RegistryObject<Item> CONJURATION_STAFF = ITEMS.register("conjuration_staff", () -> new ConjurationStaffItem(new Item.Properties()/*.tab(ItemGroup.COMBAT)*/.stacksTo(1).rarity(Rarity.EPIC).defaultDurability(1280)));
    public static final RegistryObject<Item> DOUBLE_JUMP_BOOTS = ITEMS.register("double_jump_boots", () -> new DoubleJumpBootsItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BEAMING_BOOTS = ITEMS.register("beaming_boots", () -> new BeamingBootsItem(new Item.Properties()/*.tab(ItemGroup.COMBAT)*/.rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HERMES_HELMET = ITEMS.register("hermes_helmet", () -> new HermesHelmetItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.RARE)));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
