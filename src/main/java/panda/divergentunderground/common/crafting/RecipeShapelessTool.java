package panda.divergentunderground.common.crafting;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;
import panda.divergentunderground.DivergentUnderground;


public class RecipeShapelessTool implements IRecipeFactory{

	@Override
	public IRecipe parse(JsonContext context, JsonObject json) {

	        NonNullList<Ingredient> ings = NonNullList.create();
	        for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
	            ings.add(CraftingHelper.getIngredient(ele, context));

	        if (ings.isEmpty())
	            throw new JsonParseException("No ingredients for shapeless recipe");

	        ItemStack itemstack = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
	        return new ShapelessToolRecipe(itemstack, ings);

    }

    public static class ShapelessToolRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

        private final ItemStack recipeOutput;
        public final NonNullList<Ingredient> recipeItems;
        private final String group;

        public ShapelessToolRecipe(ItemStack output, NonNullList<Ingredient> nonNullList)
        {
            this.group = DivergentUnderground.MODID;
            this.recipeOutput = output;
            this.recipeItems = nonNullList;
        }

        @Override
        public String getGroup()
        {
            return this.group;
        }

        public ItemStack getRecipeOutput()
        {
            return this.recipeOutput;
        }
        
        @Override
        public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
        {
            NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

            for (int i = 0; i < nonnulllist.size(); ++i)
            {
                ItemStack itemstack = inv.getStackInSlot(i);
               
                if(!itemstack.isEmpty()){
                	for(String name : OreDictionary.getOreNames()){
                		if(!name.startsWith("tool")) continue;
                		
                		for(ItemStack orestack : OreDictionary.getOres(name)){
                			if(orestack.getItem() == itemstack.getItem()){
                				ItemStack toolcopy = itemstack.copy();
                				nonnulllist.set(i, toolcopy.getItem().getContainerItem(itemstack));	
                    		}
                		}
                	}
                }else{
                	 nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
                } 
            }

            return nonnulllist;
        }

        @Override
        public NonNullList<Ingredient> getIngredients()
        {
            return this.recipeItems;
        }


        public boolean matches(InventoryCrafting inv, World worldIn)
        {
            List<Ingredient> list = Lists.newArrayList(this.recipeItems);

            for (int i = 0; i < inv.getHeight(); ++i)
            {
                for (int j = 0; j < inv.getWidth(); ++j)
                {
                    ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                    if (!itemstack.isEmpty())
                    {
                        boolean flag = false;

                        for (Ingredient ingredient : list)
                        {
                            if (ingredient.apply(itemstack))
                            {
                                flag = true;
                                list.remove(ingredient);
                                break;
                            }
                        }
                        if (!flag)
                        {
                            return false;
                        }
                    }
                }
            }
            return list.isEmpty();
        }

        public ItemStack getCraftingResult(InventoryCrafting inv)
        {
            return this.recipeOutput.copy();
        }

        public static ShapelessRecipes deserialize(JsonObject json)
        {
            String s = JsonUtils.getString(json, "group", "");
            NonNullList<Ingredient> nonnulllist = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));

            if (nonnulllist.isEmpty())
            {
                throw new JsonParseException("No ingredients for shapeless recipe");
            }
            else if (nonnulllist.size() > 9)
            {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            }
            else
            {
                ItemStack itemstack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
                return new ShapelessRecipes(s, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> deserializeIngredients(JsonArray array)
        {
            NonNullList<Ingredient> nonnulllist = NonNullList.<Ingredient>create();

            for (int i = 0; i < array.size(); ++i)
            {
                Ingredient ingredient = ShapedRecipes.deserializeIngredient(array.get(i));

                if (ingredient != Ingredient.EMPTY)
                {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        public boolean canFit(int width, int height)
        {
            return width * height >= this.recipeItems.size();
        }
    } 
}
