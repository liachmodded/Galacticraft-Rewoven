package com.hrznstudio.galacticraft.world.biome;

import com.hrznstudio.galacticraft.world.gen.decorator.GalacticraftDecorators;
import com.hrznstudio.galacticraft.world.gen.surfacebuilder.GalacticraftSurfaceBuilders;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public final class MoonBiome extends Biome {

    public static final String ok = "ok Sarinsa";

    public MoonBiome() {
        super(new Settings()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, GalacticraftSurfaceBuilders.MOON_CONFIG)
                .precipitation(Precipitation.NONE)
                .category(Category.NONE)
                .depth(0.075F)
                .scale(0.075F)
                .temperature(0.0F)
                .downfall(0.003F)
                .waterColor(9937330)
                .waterFogColor(11253183)
                .parent(null));
        this.flowerFeatures.clear();
        this.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, GalacticraftDecorators.CRATER_CONF);
    }

    @Override
    public Precipitation getPrecipitation() {
        return Precipitation.NONE;
    }

    @Override
    public float getTemperature(BlockPos blockPos) {
        return 0;
    }

    @Override
    public String getTranslationKey() {
        return "biome.galacticraft-rewoven.moon";
    }

    @Override
    public int getSkyColor(float f) {
        return 0;
    }

    @Override
    public int getFoliageColorAt(BlockPos blockPos_1) {
        return waterFogColor;
    }

    @Override
    public int getGrassColorAt(BlockPos blockPos_1) {
        return waterColor;
    }
}
