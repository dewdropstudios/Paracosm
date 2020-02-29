package dev.dewy.paracosm.blocks.metadata;

import dev.dewy.paracosm.utils.ParacosmException;
import net.minecraft.state.IProperty;

import java.util.*;

public class ParacosmPropertyVariant implements IProperty<ParacosmBlockVariant>
{
    private final String name;
    private final ParacosmBlockVariant[] mappings;
    private final HashMap<String, ParacosmBlockVariant> entries;
    private final List<ParacosmBlockVariant> values = new ArrayList<>();

    protected ParacosmPropertyVariant(String name, ParacosmBlockVariant... variants)
    {
        if (variants.length <= 0)
        {
            throw new ParacosmException("[Paracosm Variants]: No property variants for you, unless you can give me at least one block variant.");
        }

        this.name = name;
        this.mappings = variants;
        this.entries = new HashMap<>();

        for (ParacosmBlockVariant variant : variants)
        {
            this.entries.put(variant.getName(), variant);
            this.values.add(variant);
        }
    }

    public static ParacosmPropertyVariant create(String name, ParacosmBlockVariant... variants)
    {
        return new ParacosmPropertyVariant(name, variants);
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public Collection<ParacosmBlockVariant> getAllowedValues()
    {
        return this.entries.values();
    }

    @Override
    public Class<ParacosmBlockVariant> getValueClass()
    {
        return ParacosmBlockVariant.class;
    }

    @Override
    public Optional<ParacosmBlockVariant> parseValue(String value)
    {
        return Optional.ofNullable(this.entries.get(value));
    }

    @Override
    public String getName(ParacosmBlockVariant value)
    {
        return value.getName();
    }

    public ParacosmBlockVariant fromMetadata(int metadata)
    {
        if (metadata < 0 || metadata > this.mappings.length)
        {
            return this.mappings[0];
        }

        return this.mappings[metadata];
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        else if (other instanceof ParacosmPropertyVariant && super.equals(other))
        {
            ParacosmPropertyVariant otherProperty = (ParacosmPropertyVariant) other;

            return this.values.equals(otherProperty.values) && this.entries.equals(otherProperty.entries);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        int i = super.hashCode();

        i = 31 * i + this.values.hashCode();
        i = 31 * i + this.entries.hashCode();

        return i;
    }
}
