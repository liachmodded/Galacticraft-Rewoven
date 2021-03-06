package com.hrznstudio.galacticraft.recipes.rei;

import me.shedaniel.rei.api.RecipeDisplay;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public interface DefaultCompressingDisplay<T> extends RecipeDisplay<Recipe> {
    default Identifier getRecipeCategory() {
        return GalacticraftREIPlugin.COMPRESSING;
    }

    default int getWidth() {
        return 3;
    }

    default int getHeight() {
        return 3;
    }
}
