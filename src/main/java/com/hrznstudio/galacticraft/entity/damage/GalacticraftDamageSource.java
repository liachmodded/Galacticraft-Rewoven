package com.hrznstudio.galacticraft.entity.damage;

import net.minecraft.entity.damage.DamageSource;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class GalacticraftDamageSource extends DamageSource {
    public static final DamageSource VINE_POISON = new GalacticraftDamageSource("vine_poison");
    public static final DamageSource SUFFOCATION = new GalacticraftDamageSource("suffocation");

    private GalacticraftDamageSource(String string_1) {
        super(string_1);
    }
}
