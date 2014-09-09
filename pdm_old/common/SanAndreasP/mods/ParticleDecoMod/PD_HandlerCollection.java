package SanAndreasP.mods.ParticleDecoMod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import net.minecraft.src.WorldServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.server.FMLServerHandler;

public class PD_HandlerCollection implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		PD_EntityParticleDeco entitySrv = null;
		if(world instanceof WorldServer) entitySrv = (PD_EntityParticleDeco)((WorldServer)world).getEntityByID(x);
//		return 
		if(entitySrv != null && ID == 0)
			return new PD_ContainerParticleDeco(player.inventory, entitySrv);
		else
			return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		PD_EntityParticleDeco entitySrv = null;
//		if(FMLServerHandler.instance().getServer() != null && !FMLServerHandler.instance().getServer().worlds.isEmpty())
//			entitySrv = (PD_EntityParticleDeco)FMLServerHandler.instance().getServer().worlds.get(player.dimension).getEntityByID(x);
//		PD_EntityParticleDeco entityClt = (PD_EntityParticleDeco)FMLClientHandler.instance().getClient().theWorld.getEntityByID(x);

		PD_EntityParticleDeco entityClt = null;
		if(world instanceof WorldClient) entityClt = (PD_EntityParticleDeco)((WorldClient)world).getEntityByID(x);
		if(entityClt != null)
			if(ID == 1)
				return new PD_GuiParticleDeco_T2(player, entityClt);
			else if(ID == 2)
				return new PD_GuiParticleDeco_T3(player, entityClt);
			else
				return new PD_GuiParticleDeco_T1(player, entityClt);
		else
			return null;
	}

}
