package ca.lukegrahamlandry.critterpedia.content.data;

import ca.lukegrahamlandry.critterpedia.content.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipesGen extends RecipeProvider {
    public ModRecipesGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        for (ResourceLocation key : ItemInit.cookedFish.keySet()){
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 200).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(p_176532_, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_furnace"));
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(p_176532_, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_smoker"));
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(p_176532_, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_campfire"));
        }
    }
}