package SanAndreasP.mods.ParticleDecoMod;

import net.minecraft.src.SoundManager;
import net.minecraft.src.SoundPoolEntry;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.client.event.sound.SoundResultEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;


public class PD_SoundHandler {
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		System.out.println("sound!");
		
        event.manager.soundPoolSounds.addSound("particledeco/static.ogg", PD_ModRegistry.class.getResource("/SanAndreasP/mods/ParticleDecoMod/sound/static.ogg"));
    }
}
