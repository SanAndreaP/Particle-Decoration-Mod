package SanAndreasP.mods.ParticleDecoMod;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.WorldServer;

public class PD_PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		int packetID = (int)packet.data[0] & 255;
		
		if(packetID == 3) {
			Entity entity = ((WorldServer)((EntityPlayer)player).worldObj).getEntityByID(getEntityIdFromByteArray(packet.data));
			if(entity != null) {
				entity.attackEntityFrom(DamageSource.onFire, 3);
				entity.setFire(5);
			}
			return;
		}
			
		PD_EntityParticleDeco pdeco = ((PD_EntityParticleDeco)((WorldServer)((EntityPlayer)player).worldObj).getEntityByID(getEntityIdFromByteArray(packet.data)));
		
		if(packetID == 1) {
			pdeco.setParticleHeight(((int)packet.data[6]) & 255);
			pdeco.setDirection((int)packet.data[7] & 255);
		} else if(packetID == 2) {
			pdeco.setActive(packet.data[6] == 1);
			pdeco.setRSActive(packet.data[7] == 1);
			pdeco.setParticleNC(packet.data[8] == 1);
			pdeco.setCamouflaged(packet.data[9] == 1);
			pdeco.setGlowItself(packet.data[10] == 1);
			pdeco.setIsFireFXSetFire(packet.data[11] == 1);
		}
	}
	
	private int getEntityIdFromByteArray(byte... bt) {
		int id = 0;
		id |= (int)bt[1] & 255;
		id |= ((int)bt[2] & 255) << 8;
		id |= ((int)bt[3] & 255) << 16;
		id |= ((int)bt[4] & 255) << 24;
		return id;
	}

}
