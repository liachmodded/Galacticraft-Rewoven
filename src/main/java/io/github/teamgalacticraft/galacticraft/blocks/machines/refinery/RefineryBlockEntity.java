package io.github.teamgalacticraft.galacticraft.blocks.machines.refinery;

import io.github.teamgalacticraft.galacticraft.blocks.machines.MachineBlockEntity;
import io.github.teamgalacticraft.galacticraft.entity.GalacticraftBlockEntities;
import io.github.teamgalacticraft.galacticraft.fluids.GalacticraftFluids;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class RefineryBlockEntity extends MachineBlockEntity {

    public static final int MAX_FLUID_CAPACITY = 25000;
    private int oilAmount = 0;
    private int fuelAmount = 0;

    public RefineryBlockEntity() {
        super(GalacticraftBlockEntities.REFINERY_TYPE);
    }

    @Override
    protected int getInvSize() {
        return 3;
    }


    public int getMaxFluidCapacity() {
        return MAX_FLUID_CAPACITY;
    }

    public boolean canInsertFluid(Direction direction, Fluid fluid) {
        return fluid.matchesType(GalacticraftFluids.STILL_CRUDE_OIL) && this.fuelAmount < MAX_FLUID_CAPACITY;
    }

    //TODO
    public boolean canExtractFluid() {
        return false;
    }

    public void insertOil() {
        this.oilAmount++;
    }

    public void extractFuel(Direction direction, Fluid fluid, int i) {
        this.fuelAmount--;
    }


    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("Energy", getEnergy().getCurrentEnergy());
        tag.put("Inventory", this.inventory.toTag());
        tag.putInt("Oil", this.oilAmount);
        tag.putInt("Fuel", this.fuelAmount);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        getEnergy().setCurrentEnergy(tag.getInt("Energy"));
        this.inventory.fromTag(tag.getCompound("Inventory"));
        this.oilAmount = tag.getInt("Oil");
        this.fuelAmount = tag.getInt("Fuel");
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.fromTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return this.toTag(tag);
    }
}
