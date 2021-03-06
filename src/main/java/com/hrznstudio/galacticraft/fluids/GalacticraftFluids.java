package com.hrznstudio.galacticraft.fluids;

import com.hrznstudio.galacticraft.Constants;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class GalacticraftFluids {

    public static final BaseFluid FLOWING_CRUDE_OIL = new CrudeOilFluid.Flowing();
    public static final BaseFluid STILL_CRUDE_OIL = new CrudeOilFluid.Still();
    public static final BaseFluid FLOWING_FUEL = new FuelFluid.Flowing();
    public static final BaseFluid STILL_FUEL = new FuelFluid.Still();

    public static void register() {
        Registry.register(Registry.FLUID, new Identifier(Constants.MOD_ID, Constants.Fluids.CRUDE_OIL_FLUID_FLOWING), FLOWING_CRUDE_OIL);
        Registry.register(Registry.FLUID, new Identifier(Constants.MOD_ID, Constants.Fluids.CRUDE_OIL_FLUID_STILL), STILL_CRUDE_OIL);
        Registry.register(Registry.FLUID, new Identifier(Constants.MOD_ID, Constants.Fluids.FUEL_FLUID_FLOWING), FLOWING_FUEL);
        Registry.register(Registry.FLUID, new Identifier(Constants.MOD_ID, Constants.Fluids.FUEL_FLUID_STILL), STILL_FUEL);
    }
}
