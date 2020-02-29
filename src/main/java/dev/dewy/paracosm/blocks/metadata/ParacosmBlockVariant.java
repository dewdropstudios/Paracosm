package dev.dewy.paracosm.blocks.metadata;

import java.util.Objects;

public class ParacosmBlockVariant implements Comparable<ParacosmBlockVariant>
{
    private final int metadata;
    private final String name;

    public ParacosmBlockVariant(int metadata, String name)
    {
        this.metadata = metadata;
        this.name = name;
    }

    public int getMetadata()
    {
        return this.metadata;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public int compareTo(ParacosmBlockVariant blockVariant)
    {
        return (this.metadata < blockVariant.metadata) ? -1 : ((this.metadata == blockVariant.metadata) ? 0 : 1);
    }

    @Override
    public String toString()
    {
        return this.getName();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass())
        {
            return false;
        }

        ParacosmBlockVariant that = (ParacosmBlockVariant) obj;

        return this.metadata == that.metadata && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode()
    {
        int hash = 1;

        hash = 31 * hash + this.name.hashCode();
        hash = 31 * hash + this.metadata;

        return hash;
    }
}
