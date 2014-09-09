package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;


import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import SanAndreasP.mods.SAP_ConfigManager;
import net.minecraftforge.client.MinecraftForgeClient;

@Mod(name="Particle Decoration Mod",modid="ParticleDeco",version="1.3")
@NetworkMod(clientSideRequired=true,serverSideRequired=false,channels={"ParticleDeco"},packetHandler=PD_PacketHandler.class)
public class PD_ModRegistry {
	
	public static Item partdeco;
	public int pd_id = 9000;
	
	@SidedProxy(clientSide="SanAndreasP.mods.ParticleDecoMod.PD_ClientProxy", serverSide="SanAndreasP.mods.ParticleDecoMod.PD_CommonProxy")
	public static PD_CommonProxy proxy;
	
	@Instance
	public static PD_ModRegistry instance;
	
	public static HashMap<Integer, PD_ContainerParticleDeco> currItemContainer = new HashMap<Integer, PD_ContainerParticleDeco>();

	public static final String cfgver = "1.3_01";
	public static final String modname = "ParticleDeco";
	public PD_ModRegistry() {
		
	}
	
	public void AddRecipes() {
		ItemStack[] is = new ItemStack[] {
			new ItemStack(partdeco, 1, 0), new ItemStack(Item.redstone, 1),
			new ItemStack(partdeco, 1, 1), new ItemStack(Item.blazePowder, 1),
			new ItemStack(partdeco, 1, 2), new ItemStack(Item.bucketWater, 1),
			new ItemStack(partdeco, 1, 3), new ItemStack(Item.bucketLava, 1),
			new ItemStack(partdeco, 1, 4), new ItemStack(Item.arrow, 1),
			new ItemStack(partdeco, 1, 5), new ItemStack(Block.plantRed, 1),
			new ItemStack(partdeco, 1, 6), new ItemStack(Item.book, 1),
			new ItemStack(partdeco, 1, 7), new ItemStack(Item.sugar, 1)
		};
		
		for(int i = 0; i < is.length; i+=2) {
			GameRegistry.addRecipe(is[i],
					"S S", "SCS", "SRS",
					Character.valueOf('S'), Block.cobblestone,
					Character.valueOf('C'), is[i+1],
					Character.valueOf('R'), Item.redstone
			);
		}
	}
	
	public void setConfig() {
		SAP_ConfigManager cfg = new SAP_ConfigManager(modname, cfgver);
		
		String itm = "ParticleDecoGenerator";
		
		cfg.setVar_IN_O(itm, pd_id, cfg.getGroupIdFromName("Item IDs"));
		
		cfg.loadConfig();
		
		pd_id = cfg.getVar_IN_O(itm);
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt) {
		setConfig();
		proxy.registerEventBusses();
	}
	
	@Init
	public void load(FMLInitializationEvent evt) {
		proxy.registerRenderStuff();
		partdeco = (new PD_ItemParticleDeco(pd_id - 256));
		partdeco.setTextureFile("/SanAndreasP/mods/ParticleDecoMod/images/pditems.png");
		NetworkRegistry.instance().registerGuiHandler(this, new PD_HandlerCollection());
		
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 0), "en_US", "Dust Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 1), "en_US", "Potion Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 2), "en_US", "Bubble Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 3), "en_US", "Flame Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 4), "en_US", "Critical Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 5), "en_US", "Heart Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 6), "en_US", "Enchant Particles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(partdeco, 1, 7), "en_US", "X-mas Particles");
		
		proxy.registerEntity(PD_EntityParticleDeco.class, "ParticleDeco", 0, this, 128, 1, false);
		
		AddRecipes();
	}
}
