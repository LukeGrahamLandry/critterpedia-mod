package ca.lukegrahamlandry.critterpedia.content.data;

import ca.lukegrahamlandry.critterpedia.content.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> generator) {
        buildFishRecipes(generator);
    }

    private void buildFishRecipes(Consumer<FinishedRecipe> generator) {
        // live fish -> bucket of fish
        for (ResourceLocation key : ItemInit.liveFish.keySet()){
            if (ItemInit.smallFishBuckets.containsKey(key)){
                Item bucket = ItemInit.smallFishBuckets.get(key).get();
                Item live = ItemInit.liveFish.get(key).get();
                ShapelessRecipeBuilder.shapeless(bucket, 1).requires(live).requires(Items.WATER_BUCKET).unlockedBy("has_" + key.getPath(), has(live)).save(generator);
            }
        }

        // raw fish -> live fish
        for (ResourceLocation key : ItemInit.cookedFish.keySet()){
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 200).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(generator, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_furnace"));
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(generator, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_smoker"));
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.rawFish.get(key).get()), ItemInit.cookedFish.get(key).get(), 0.5F, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_" + key.getPath(), has(ItemInit.rawFish.get(key).get())).save(generator, new ResourceLocation(key.getNamespace(), key.getPath() + "_from_campfire"));
        }
    }
}