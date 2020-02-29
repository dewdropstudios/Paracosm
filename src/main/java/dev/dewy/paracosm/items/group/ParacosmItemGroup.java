package dev.dewy.paracosm.items.group;

import dev.dewy.paracosm.Paracosm;
import dev.dewy.paracosm.items.init.ParacosmItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParacosmItemGroup extends ItemGroup
{
    public ParacosmItemGroup()
    {
        super(Paracosm.MODID);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack createIcon()
    {
        return new ItemStack(ParacosmItems.PP_SMOL_ITEM.get());
    }
}
