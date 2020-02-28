package dev.dewy.paracosm.items;

import net.minecraft.item.Item;

import java.util.function.Function;

/**
 * Ya basic. A very basic item with very basic properties.
 *
 * @author Dewy
 */
public class BasicItem extends Item
{
    public BasicItem(Function<Properties, Properties> properties)
    {
        super(properties.apply(new Properties()));
    }
}
