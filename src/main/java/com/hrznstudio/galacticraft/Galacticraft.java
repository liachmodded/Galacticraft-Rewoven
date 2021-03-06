package com.hrznstudio.galacticraft;

import com.hrznstudio.galacticraft.blocks.GalacticraftBlocks;
import com.hrznstudio.galacticraft.config.ConfigHandler;
import com.hrznstudio.galacticraft.container.GalacticraftContainers;
import com.hrznstudio.galacticraft.energy.GalacticraftEnergy;
import com.hrznstudio.galacticraft.entity.GalacticraftBlockEntities;
import com.hrznstudio.galacticraft.fluids.GalacticraftFluids;
import com.hrznstudio.galacticraft.items.GalacticraftItems;
import com.hrznstudio.galacticraft.misc.Capes;
import com.hrznstudio.galacticraft.recipes.GalacticraftRecipes;
import com.hrznstudio.galacticraft.sounds.GalacticraftSounds;
import com.hrznstudio.galacticraft.world.biome.GalacticraftBiomes;
import com.hrznstudio.galacticraft.world.biome.source.GalacticraftBiomeSourceTypes;
import com.hrznstudio.galacticraft.world.dimension.GalacticraftDimensions;
import com.hrznstudio.galacticraft.world.gen.WorldGenerator;
import com.hrznstudio.galacticraft.world.gen.chunk.GalacticraftChunkGeneratorTypes;
import com.hrznstudio.galacticraft.world.gen.decorator.GalacticraftDecorators;
import com.hrznstudio.galacticraft.world.gen.feature.GalacticraftFeatures;
import com.hrznstudio.galacticraft.world.gen.surfacebuilder.GalacticraftSurfaceBuilders;
import io.github.teamgalacticraft.tgcutils.api.updatechecker.ModUpdateChecker;
import io.github.teamgalacticraft.tgcutils.api.updatechecker.ModUpdateListener;
import io.github.teamgalacticraft.tgcutils.api.updatechecker.UpdateInfo;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class Galacticraft implements ModInitializer, ModUpdateListener {

    public static Logger logger = LogManager.getLogger("Galacticraft-Rewoven");
    private static final Marker GALACTICRAFT = MarkerManager.getMarker("Galacticraft");

    public static ConfigHandler configHandler = new ConfigHandler();
    private ModUpdateChecker modUpdateChecker = new ModUpdateChecker(
            Constants.MOD_ID,
            "https://raw.githubusercontent.com/StellarHorizons/Galacticraft-Rewoven/master/updates.json",
            true
    );

    @Override
    public void onInitialize() {
        logger.info(GALACTICRAFT, "[Galacticraft] Initializing...");
        GalacticraftFluids.register();
        GalacticraftBlocks.register();
        GalacticraftItems.register();
        GalacticraftRecipes.register();
        GalacticraftSounds.register();
        GalacticraftEnergy.register();
        GalacticraftContainers.register();
        GalacticraftBlockEntities.init();
        GalacticraftCommands.register();
        GalacticraftChunkGeneratorTypes.init();
        GalacticraftFeatures.init();
        GalacticraftDecorators.init();
        GalacticraftBiomes.init();
        GalacticraftBiomeSourceTypes.init();
        GalacticraftDimensions.init();
        GalacticraftSurfaceBuilders.init();
        WorldGenerator.register();
        Capes.updateCapeList();

        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            try {
                Class<?> clazz = Class.forName("io.github.prospector.modmenu.api.ModMenuApi");
                Method method = clazz.getMethod("addConfigOverride", String.class, Runnable.class);
                method.invoke(null, Constants.MOD_ID, (Runnable) () -> configHandler.openConfigScreen());
            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                logger.error("[Galacticraft] Failed to add modmenu config override. {1}", e);
            }
        }

    }

    @Override
    public void onUpdate(UpdateInfo updateInfo) {
        if (updateInfo.getStatus() == UpdateInfo.VersionStatus.OUTDATED) {
            logger.info("Galacticraft: Rewoven is outdated.");
        }
    }
}
