package com.electrifiedded.oreendermen;

import com.electrifiedded.archaiclib.original.OriginalModAssets;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.EnumMap;
import java.util.Map;

@Mod(modid = OreEndermenMod.MOD_ID, name = "Ore Endermen", version = "1.0.0", dependencies = "required-after:archaiclib@[1.25.0,)")
@Mod.EventBusSubscriber(modid = OreEndermenMod.MOD_ID)
public class OreEndermenMod {
    public static final String MOD_ID = "oreendermen";
    public static final String ORIGINAL_NAME = "ore_endermen_mod_1.7.10.jar (Ore Endermen by ShinyMew)";
    public static final String ORIGINAL_URL = "https://www.curseforge.com/minecraft/mc-mods/ore-endermen";

    @Mod.Instance(MOD_ID)
    public static OreEndermenMod instance;

    public static final Map<Variant, Item> pearls = new EnumMap<>(Variant.class);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        OriginalModAssets.require(MOD_ID, MOD_ID, ORIGINAL_NAME, ORIGINAL_URL, MOD_ID);
        int id = 0;
        for (Variant v : Variant.values()) {
            Class<? extends Entity> cls = entityClass(v);
            EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, v.entityName()), cls, MOD_ID + "." + v.entityName(),
                    id++, this, 80, 3, true, Variant.EGG_PRIMARY, v.eggSecondary);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        for (Variant v : Variant.values()) {
            EntityRegistry.addSpawn(entityClass(v).asSubclass(EntityLiving.class), 4, 4, 4,
                    EnumCreatureType.MONSTER, Biomes.SKY);
        }
        smelt(Variant.COAL, new ItemStack(Items.COAL, Variant.COAL.smeltCount));
        smelt(Variant.REDSTONE, new ItemStack(Items.REDSTONE, Variant.REDSTONE.smeltCount));
        smelt(Variant.LAPIS, new ItemStack(Items.DYE, Variant.LAPIS.smeltCount, 4));
        smelt(Variant.IRON, new ItemStack(Items.IRON_INGOT, Variant.IRON.smeltCount));
        smelt(Variant.GOLD, new ItemStack(Items.GOLD_INGOT, Variant.GOLD.smeltCount));
        smelt(Variant.DIAMOND, new ItemStack(Items.DIAMOND, Variant.DIAMOND.smeltCount));
        smelt(Variant.EMERALD, new ItemStack(Items.EMERALD, Variant.EMERALD.smeltCount));
    }

    private static void smelt(Variant v, ItemStack out) {
        GameRegistry.addSmelting(pearls.get(v), out, v.xp);
    }

    public static Class<? extends EntityOreEnderman> entityClass(Variant v) {
        switch (v) {
            case COAL: return EntityOreEnderman.Coal.class;
            case REDSTONE: return EntityOreEnderman.Redstone.class;
            case IRON: return EntityOreEnderman.Iron.class;
            case GOLD: return EntityOreEnderman.Gold.class;
            case LAPIS: return EntityOreEnderman.Lapis.class;
            case DIAMOND: return EntityOreEnderman.Diamond.class;
            default: return EntityOreEnderman.Emerald.class;
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        for (Variant v : Variant.values()) {
            Item item = new Item();
            item.setRegistryName(MOD_ID, v.pearlName());
            item.setTranslationKey(MOD_ID + "." + v.pearlName());
            item.setCreativeTab(CreativeTabs.MATERIALS);
            pearls.put(v, item);
            e.getRegistry().register(item);
        }
    }
}
