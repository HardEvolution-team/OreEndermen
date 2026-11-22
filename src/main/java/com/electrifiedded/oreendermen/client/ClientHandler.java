package com.electrifiedded.oreendermen.client;

import com.electrifiedded.oreendermen.EntityOreEnderman;
import com.electrifiedded.oreendermen.OreEndermenMod;
import com.electrifiedded.oreendermen.Variant;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = OreEndermenMod.MOD_ID, value = Side.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void models(ModelRegistryEvent e) {
        for (Variant v : Variant.values()) {
            ModelLoader.setCustomModelResourceLocation(OreEndermenMod.pearls.get(v), 0,
                    new ModelResourceLocation(new ResourceLocation(OreEndermenMod.MOD_ID, v.pearlName()), "inventory"));
            final ResourceLocation tex = new ResourceLocation(OreEndermenMod.MOD_ID, "textures/mobs/" + v.id + "enderman.png");
            RenderingRegistry.registerEntityRenderingHandler(OreEndermenMod.entityClass(v),
                    (RenderManager m) -> new RenderOreEnderman(m, tex));
        }
    }

    static class RenderOreEnderman extends RenderLiving<EntityOreEnderman> {
        private final ResourceLocation texture;

        RenderOreEnderman(RenderManager m, ResourceLocation texture) {
            super(m, new OreEndermanModel(), 0.8F);
            this.texture = texture;
        }

        @Override
        protected ResourceLocation getEntityTexture(EntityOreEnderman entity) {
            return texture;
        }
    }
}
