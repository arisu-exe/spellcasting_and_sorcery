package io.github.fallOut015.spellcasting_and_sorcery.common;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    private static boolean pamTextureBloodBlade = true;

    public static boolean usePamTextureBloodBlade() {
        return pamTextureBloodBlade;
    }

    public static void bakeConfig() {
        pamTextureBloodBlade = CLIENT.PAM_TEXTURE_BLOOD_BLADE.get();
    }

    public static class ClientConfig {
        // Cosmetic
        public final ForgeConfigSpec.BooleanValue PAM_TEXTURE_BLOOD_BLADE;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Cosmetic");
            this.PAM_TEXTURE_BLOOD_BLADE = builder.comment("Use Pam's texture for the Blood Venom Blade.").translation("spellcasting_and_sorcery.config.pamTextureBloodBlade").define("PAM_TEXTURE_BLOOD_BLADE", true);
            builder.pop();
        }
    }
}