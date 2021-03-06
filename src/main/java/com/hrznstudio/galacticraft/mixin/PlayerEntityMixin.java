package com.hrznstudio.galacticraft.mixin;

import alexiil.mc.lib.attributes.item.impl.SimpleFixedItemInv;
import com.hrznstudio.galacticraft.accessor.GCPlayerAccessor;
import com.hrznstudio.galacticraft.container.PlayerInventoryGCContainer;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements GCPlayerAccessor {
    @Shadow
    @Final
    public PlayerInventory inventory;

    private PlayerInventoryGCContainer gcContainer;
    private SimpleFixedItemInv gearInventory;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Override
    public PlayerInventoryGCContainer getGCContainer() {
        return gcContainer;
    }

    @Override
    public SimpleFixedItemInv getGearInventory() {
        return gearInventory;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(World world_1, GameProfile gameProfile_1, CallbackInfo info) {
        this.gcContainer = new PlayerInventoryGCContainer(this.inventory, !world.isClient, (PlayerEntity) (Object) this);
        this.gearInventory = new SimpleFixedItemInv(12);
    }
}