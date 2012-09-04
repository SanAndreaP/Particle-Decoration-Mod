package SanAndreasP.mods.ParticleDecoMod;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityFX;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;

public class PD_ClientProxy extends PD_CommonProxy {
		
	@Override
	public void registerRenderStuff() {
		// textures
		MinecraftForgeClient.preloadTexture("/SanAndreasP/mods/ParticleDecoMod/images/pditems.png");
		
		// entity renderers
		RenderingRegistry.registerEntityRenderingHandler(PD_EntityParticleDeco.class, new PD_RenderParticleDeco(new PD_ModelParticleDeco(), 0.0F));
	}
	
	@Override
	public void registerEventBusses() {
		MinecraftForge.EVENT_BUS.register(new PD_SoundHandler());
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	@Override
	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
	}
	
	@Override
	public void spawnParticle(Object entityFX) {
		Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX)entityFX);
	}
	
	@Override
	public boolean isClient() {
		return true;
	}

	@Override
	public EntityPlayer getClientPlayer() { 
		return Minecraft.getMinecraft().thePlayer;
	}
}
