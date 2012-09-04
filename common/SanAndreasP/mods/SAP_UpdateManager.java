package SanAndreasP.mods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Formatter;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class SAP_UpdateManager {
	private boolean checkedForUpdate = false;
	private int majNmbr, minNmbr, revNmbr;
	private String updateURL, modName, modURL;
	
	public SAP_UpdateManager(String md, int mj, int mn, int rev, String updURL, String mdURL) {
		this.modName = md;
		this.majNmbr = mj;
		this.minNmbr = mn;
		this.revNmbr = rev;
		this.updateURL = updURL;
		this.modURL = mdURL;
	}
	
	public String getFormattedVersion() {
		Formatter form = new Formatter();
		String ver = form.format("%1$d.%2$02d_%3$02d", this.majNmbr, this.minNmbr, this.revNmbr).toString();
		form.close();
	    return ver;
	}
	
	private void addMessage(String s) {
		if(FMLCommonHandler.instance().getSide().isClient()) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(s);
		} else {
			MinecraftServer.getServer().logWarningMessage(s.replaceAll("\247.", ""));
		}
	}
	
	public void checkForUpdate() {
		if(!this.checkedForUpdate) {
			try {
			    URL url = new URL(this.updateURL);
			    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			    String str = in.readLine();
			    
			    in.close();
			    
			    if(str == null) return;
			    
			    int[] webVer = new int[] {
			    	    Integer.valueOf(str.substring(6, 7)),
			    	    Integer.valueOf(str.substring(14, 16)),
			    	    Integer.valueOf(str.substring(26, 28))
			    };
			    
				Formatter form = new Formatter();
				String newVer = form.format("%1$d.%2$02d_%3$02d", webVer[0], webVer[1], webVer[2]).toString();
				form.close();
			    			    
			    if(webVer[0] > this.majNmbr) {
			    	addMessage("\247eNew major update (" + newVer + ") for \2476" + modName + " \247eis out!");
			    	addMessage("\247ePlease update ASAP at:\247F");
			    	addMessage(this.modURL);
			    } else if(webVer[1] > this.minNmbr) {
			    	addMessage("\247eNew feature update (" + newVer + ") for \2476" + modName + " \247eis out!");
			    	addMessage("\247ePlease update to get the new features at:\247F");
			    	addMessage(this.modURL);
			    } else if(webVer[2] > this.revNmbr) {
			    	addMessage("\247eNew bugfix update (" + newVer + ") for \2476" + modName + " \247eis out!");
			    	addMessage("\247ePlease update to get less bugs at:\247F");
			    	addMessage(this.modURL);
			    }
			    
		    } catch (MalformedURLException e) {
		    } catch (IOException e) {
		    }
			
			this.checkedForUpdate = true;
		}
	}
}