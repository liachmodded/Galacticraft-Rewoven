package com.hrznstudio.galacticraft.blocks.machines.energystoragemodule;

import alexiil.mc.lib.attributes.item.impl.SimpleFixedItemInv;
import com.hrznstudio.galacticraft.blocks.machines.MachineBlockEntity;
import com.hrznstudio.galacticraft.energy.GalacticraftEnergy;
import com.hrznstudio.galacticraft.entity.GalacticraftBlockEntities;
import io.github.cottonmc.energy.impl.SimpleEnergyAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class EnergyStorageModuleBlockEntity extends MachineBlockEntity implements Tickable {
    public static int MAX_ENERGY = 60000;
    public static int CHARGE_BATTERY_SLOT = 0;
    public static int DRAIN_BATTERY_SLOT = 1;
    SimpleFixedItemInv inventory = new SimpleFixedItemInv(2);
    private SimpleEnergyAttribute energy = new SimpleEnergyAttribute(MAX_ENERGY, GalacticraftEnergy.GALACTICRAFT_JOULES);
    private int powerToChargePerTick = 5;

    public EnergyStorageModuleBlockEntity() {
        super(GalacticraftBlockEntities.ENERGY_STORAGE_MODULE_TYPE);
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    protected int getInvSize() {
        return 2;
    }

    @Override
    public void tick() {
        // Charge the battery, drain internal energy buffer.
        ItemStack batteryToCharge = getInventory().getInvStack(CHARGE_BATTERY_SLOT);
        if (GalacticraftEnergy.isEnergyItem(batteryToCharge) && this.getEnergy().getCurrentEnergy() > 0) {
            int currentBatteryCharge = GalacticraftEnergy.getBatteryEnergy(batteryToCharge);
            int maxBatteryCharge = GalacticraftEnergy.getMaxBatteryEnergy(batteryToCharge);
            int chargeRoom = maxBatteryCharge - currentBatteryCharge;

            int chargeToAdd = Math.min(powerToChargePerTick, chargeRoom);
            chargeToAdd = Math.min(chargeToAdd, this.getEnergy().getCurrentEnergy());

            this.getEnergy().setCurrentEnergy(getEnergy().getCurrentEnergy() - chargeToAdd);

            GalacticraftEnergy.incrementEnergy(batteryToCharge, chargeToAdd);
        }

        // Drain the battery, charge internal energy buffer.
        ItemStack batteryToDrain = getInventory().getInvStack(DRAIN_BATTERY_SLOT);
        if (GalacticraftEnergy.isEnergyItem(batteryToDrain) && this.getEnergy().getCurrentEnergy() < this.getEnergy().getMaxEnergy()) {
            int currentBatteryCharge = GalacticraftEnergy.getBatteryEnergy(batteryToDrain);

            int chargeToRemove = Math.min(powerToChargePerTick, currentBatteryCharge);
            int newInternalBuffer = getEnergy().getCurrentEnergy() + chargeToRemove;

            this.getEnergy().setCurrentEnergy(newInternalBuffer);
            GalacticraftEnergy.decrementEnergy(batteryToDrain, chargeToRemove);
        }
    }
}