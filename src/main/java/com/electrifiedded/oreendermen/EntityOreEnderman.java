package com.electrifiedded.oreendermen;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/** An enderman that drops ore pearls instead of the vanilla loot. */
public abstract class EntityOreEnderman extends EntityEnderman {
    public EntityOreEnderman(World world) {
        super(world);
    }

    public abstract Variant variant();

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(variant().health);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0);
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && super.getCanSpawnHere();
    }

    @Override
    protected ResourceLocation getLootTable() {
        return null; // use dropFewItems
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        // the 1.7.10 original dropped nextInt(1 + looting) pearls (usually none); we guarantee at least one
        int n = 1 + rand.nextInt(1 + looting);
        for (int i = 0; i < n; i++) {
            entityDropItem(new ItemStack(OreEndermenMod.pearls.get(variant())), 0.0F);
        }
    }

    public static class Coal extends EntityOreEnderman {
        public Coal(World w) { super(w); }
        @Override public Variant variant() { return Variant.COAL; }
    }
    public static class Redstone extends EntityOreEnderman {
        public Redstone(World w) { super(w); }
        @Override public Variant variant() { return Variant.REDSTONE; }
    }
    public static class Iron extends EntityOreEnderman {
        public Iron(World w) { super(w); }
        @Override public Variant variant() { return Variant.IRON; }
    }
    public static class Gold extends EntityOreEnderman {
        public Gold(World w) { super(w); }
        @Override public Variant variant() { return Variant.GOLD; }
    }
    public static class Lapis extends EntityOreEnderman {
        public Lapis(World w) { super(w); }
        @Override public Variant variant() { return Variant.LAPIS; }
    }
    public static class Diamond extends EntityOreEnderman {
        public Diamond(World w) { super(w); }
        @Override public Variant variant() { return Variant.DIAMOND; }
    }
    public static class Emerald extends EntityOreEnderman {
        public Emerald(World w) { super(w); }
        @Override public Variant variant() { return Variant.EMERALD; }
    }
}
