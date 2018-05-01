package panda.divergentunderground.common.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;

public class FakeRecipe
  implements IRecipe
{
  private IRecipe realRecipe;
  
  public FakeRecipe(IRecipe realRecipe)
  {
    this.realRecipe = realRecipe;
  }
  
  @Nullable
  public ResourceLocation getRegistryName()
  {
    return this.realRecipe.getRegistryName();
  }
  
  @Nullable
  public IRecipe setRegistryName(ResourceLocation name)
  {
    return this.realRecipe.setRegistryName(name);
  }
  
  public Class<IRecipe> getRegistryType()
  {
    return this.realRecipe.getRegistryType();
  }
  
  public boolean canFit(int width, int height)
  {
    return false;
  }
  
  public boolean matches(InventoryCrafting inv, World worldIn)
  {
    return false;
  }
  
  public ItemStack getCraftingResult(InventoryCrafting inv)
  {
    return ItemStack.EMPTY;
  }
  
  public ItemStack getRecipeOutput()
  {
    return ItemStack.EMPTY;
  }
  
  @Override
  public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
  {
    return NonNullList.create();
  }
  

}