package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.GasStack;
import mekanism.common.MekanismBlocks;
import mekanism.common.MekanismFluids;
import mekanism.common.MekanismItems;
import mekanism.common.Resource;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.util.StackUtils;

public class MekanismCompat {
	
	public static final Block ORE_BLOCK = MekanismBlocks.OreBlock;
	
	public static final Block HARD_OSMIUM = ModBlocks.simply(new BlockHardStone(ORE_BLOCK.getStateFromMeta(0),1),"mekanism_hard_osmium_ore");
	public static final Block HARD_COPPER = ModBlocks.simply(new BlockHardStone(ORE_BLOCK.getStateFromMeta(1),1),"mekanism_hard_copper_ore");
	public static final Block HARD_TIN = ModBlocks.simply(new BlockHardStone(ORE_BLOCK.getStateFromMeta(2),1),"mekanism_hard_tin_ore");

	public static final Item ORE_OSMIUM = ModItems.makeOre("mekanism_osmium");
	public static final Item ORE_COPPER = ModItems.makeOre("mekanism_copper");
	public static final Item ORE_TIN = ModItems.makeOre("mekanism_tin");


	public static void init(){
		RockRegistry.addRock(ORE_BLOCK,0, ModItems.ROCK_STONE);
		RockRegistry.addRock(ORE_BLOCK,1, ModItems.ROCK_STONE);
		RockRegistry.addRock(ORE_BLOCK,2, ModItems.ROCK_STONE);
		
		OreRegistry.addOre(ORE_BLOCK,0,  ORE_OSMIUM);	
		OreRegistry.addOre(ORE_BLOCK,1,  ORE_COPPER);
		OreRegistry.addOre(ORE_BLOCK,2,  ORE_TIN);

		GameRegistry.addSmelting(ORE_OSMIUM, new ItemStack(MekanismItems.Ingot, 1, 1), 1.0F);
		GameRegistry.addSmelting(ORE_COPPER, new ItemStack(MekanismItems.Ingot, 1, 5), 1.0F);
		GameRegistry.addSmelting(ORE_TIN, new ItemStack(MekanismItems.Ingot, 1, 6), 1.0F);
//dissolution chamber,purification chamber,chemical injection chamber,enrichment chamber
		
		RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(ORE_OSMIUM), new ItemStack(MekanismItems.Dust, 2, Resource.OSMIUM.ordinal()));
		RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(ORE_COPPER), new ItemStack(MekanismItems.Dust, 2, Resource.COPPER.ordinal()));
		RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(ORE_TIN), new ItemStack(MekanismItems.Dust, 2, Resource.TIN.ordinal()));
	
        RecipeHandler.addChemicalDissolutionChamberRecipe(new ItemStack(ORE_OSMIUM),new GasStack(GasRegistry.getGas("osmium"), 1000));
        RecipeHandler.addChemicalDissolutionChamberRecipe(new ItemStack(ORE_COPPER),new GasStack(GasRegistry.getGas("copper"), 1000));
        RecipeHandler.addChemicalDissolutionChamberRecipe(new ItemStack(ORE_TIN),new GasStack(GasRegistry.getGas("tin"), 1000));
        
        RecipeHandler.addPurificationChamberRecipe(new ItemStack(ORE_OSMIUM), new ItemStack(MekanismItems.Clump, 3, Resource.OSMIUM.ordinal()));
        RecipeHandler.addPurificationChamberRecipe(new ItemStack(ORE_COPPER), new ItemStack(MekanismItems.Clump, 3, Resource.COPPER.ordinal()));
        RecipeHandler.addPurificationChamberRecipe(new ItemStack(ORE_TIN), new ItemStack(MekanismItems.Clump, 3, Resource.TIN.ordinal()));
	
        RecipeHandler.addChemicalInjectionChamberRecipe(new ItemStack(ORE_OSMIUM), MekanismFluids.HydrogenChloride,new ItemStack(MekanismItems.Shard, 4, Resource.OSMIUM.ordinal()));
        RecipeHandler.addChemicalInjectionChamberRecipe(new ItemStack(ORE_COPPER), MekanismFluids.HydrogenChloride,new ItemStack(MekanismItems.Shard, 4, Resource.COPPER.ordinal()));
        RecipeHandler.addChemicalInjectionChamberRecipe(new ItemStack(ORE_TIN), MekanismFluids.HydrogenChloride,new ItemStack(MekanismItems.Shard, 4, Resource.TIN.ordinal()));
	}

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(HARD_OSMIUM);
		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);
	}

	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(ORE_OSMIUM);
		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);

		ModItems.registerItemBlock(registry, HARD_OSMIUM);
		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
	}

	public static void registerOreDicts(){
		doDicts("Copper",ORE_COPPER,HARD_COPPER);
		doDicts("Tin",ORE_TIN,HARD_TIN);
		doDicts("Osmium",ORE_OSMIUM,HARD_OSMIUM);
	}

	private static void doDicts(String type,Item ore, Block block){
		OreDictionary.registerOre("ore"+type, block);
		OreDictionary.registerOre("ore"+type, ore);
		OreDictionary.registerOre("rockOre"+type,ore);
	}

	public static void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_OSMIUM);
		ClientProxy.registerBlockModel(HARD_OSMIUM);
		ClientProxy.registerItemModel(ORE_COPPER);
		ClientProxy.registerBlockModel(HARD_COPPER);
		ClientProxy.registerItemModel(ORE_TIN);
		ClientProxy.registerBlockModel(HARD_TIN);
	}

}
