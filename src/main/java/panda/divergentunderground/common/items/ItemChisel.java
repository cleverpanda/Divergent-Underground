package panda.divergentunderground.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChisel extends Item{
	public ItemChisel() {
		this.setMaxStackSize(1);
		this.setMaxDamage(251);
		this.setContainerItem(this);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(this, 1, itemStack.getItemDamage() + 1);
	}
}
