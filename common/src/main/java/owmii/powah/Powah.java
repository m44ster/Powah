package owmii.powah;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import owmii.powah.config.v2.PowahConfig;
import owmii.powah.network.Network;
import owmii.powah.api.PowahAPI;
import owmii.powah.block.Blcks;
import owmii.powah.block.Tiles;
import owmii.powah.entity.Entities;
import owmii.powah.inventory.Containers;
import owmii.powah.item.Itms;
import owmii.powah.recipe.Recipes;

public class Powah {
    public static final String MOD_ID = "powah";
    private static final ConfigHolder<PowahConfig> CONFIG = AutoConfig.register(PowahConfig.class, JanksonConfigSerializer::new);
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static PowahConfig config() {
        return CONFIG.getConfig();
    }

    public static void init() {
        Blcks.DR.register();
        Tiles.DR.register();
        EnvHandler.INSTANCE.setupBlockItems();
        Itms.DR.register();
        Containers.DR.register();
        Entities.DR.register();
        Recipes.DR_SERIALIZER.register();
        Recipes.DR_TYPE.register();

        EnvHandler.INSTANCE.registerWorldgen();
        EnvHandler.INSTANCE.registerTransfer();
        Network.register();

        EnvHandler.INSTANCE.scheduleCommonSetup(() -> {
            PowahAPI.registerSolidCoolant(Blocks.SNOW_BLOCK, 48, -3);
            PowahAPI.registerSolidCoolant(Items.SNOWBALL, 12, -3);
            PowahAPI.registerSolidCoolant(Blocks.ICE, 48, -5);
            PowahAPI.registerSolidCoolant(Blocks.PACKED_ICE, 192, -8);
            PowahAPI.registerSolidCoolant(Blocks.BLUE_ICE, 568, -17);
            PowahAPI.registerSolidCoolant(Blcks.DRY_ICE.get(), 712, -32);
        });

        // TODO ARCH
        // Configs.register();
        // Configs.ALL.forEach(IConfig::reload);
        // ConfigHandler.post();

        /* TODO ARCH
        if (FMLEnvironment.dist.isClient()) {
            try {
                var clientClass = Class.forName("owmii.powah.client.PowahClient");
                clientClass.getMethod("init").invoke(null);
            } catch (Exception exception) {
                throw new RuntimeException("Failed Powah client-side setup", exception);
            }
        }
         */
    }
}