package SanAndreasP.mods.ParticleDecoMod;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet;
import net.minecraft.src.World;

public class PD_CommonProxy {
	
	public void registerRenderStuff() {  }
	
	public void registerEntity(Class<? extends Entity> entity, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerGlobalEntityID(entity, entityName, EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(entity, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
	public void registerEventBusses() {  }
	
	public World getClientWorld() { 
		return null;
	}
	
	public EntityPlayer getClientPlayer() { 
		return null;
	}
	
	public void sendToServer(Packet packet) {  }
	
	public void spawnParticle(Object entityFX) {  }
	
	public boolean isClient() {
		return false;
	}
}
