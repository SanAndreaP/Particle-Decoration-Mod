package SanAndreasP.mods;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;


/**SanAndreasP's Config Manager v. 1.2 for Minecraft Modifications*/
public class SAP_ConfigManager {
	private List props = new ArrayList();
	private List groups = new ArrayList();
	private String modname, cfgver, cfgpath, cfgname = "config.txt";
	
	enum dt {LONG, BOOLEAN, DOUBLE, STRING};
	
//	public
	public SAP_ConfigManager(String modificationname, String configversion) {
		initCfgMan(modificationname, configversion, "/"+modificationname+"/");
	}
	
	public SAP_ConfigManager(String modificationname, String configversion, String configname) {
		cfgname = configname;
		initCfgMan(modificationname, configversion, "/"+modificationname+"/");
	}
	
	public SAP_ConfigManager(String modificationname, String configversion, String configname, String mainFolder) {
		cfgname = configname;
		initCfgMan(modificationname, configversion, "/"+mainFolder+"/"+modificationname+"/");
	}
	
	public SAP_ConfigManager(String modificationname, String configversion, String configname, String mainFolder, String subFolder) {
		cfgname = configname;
		initCfgMan(modificationname, configversion, "/"+mainFolder+"/"+modificationname+"/"+subFolder+"/");
	}
	
	private File getModDir(String path) {
		if(FMLCommonHandler.instance().getSide().isClient())
			return Minecraft.getAppDir(path);
		else
			return MinecraftServer.getServer().getFile(path);
	}
	
	private void initCfgMan(String modnm, String cfgvers, String path) {
		modname = modnm;
		cfgver = cfgvers;
		
		groups.add(new Object[] { "Block IDs", 0 });
		groups.add(new Object[] { "Item IDs", 0 });
		groups.add(new Object[] { "Achievement IDs", 0 });
		
		try {
			if(FMLCommonHandler.instance().getSide().isClient()) {
				cfgpath = "minecraft/mods"+path;
//				dir = 
//						Minecraft.getAppDir(cfgpath);
			} else {
				cfgpath = "mods"+path;
//				dir = 
//						MinecraftServer.getServer().getFile(cfgpath);
			}
			File dir = getModDir(cfgpath);
			File file = new File(dir, cfgname);
			if(!(dir.exists()))
				dir.mkdirs();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

	////// MANAGING GROUPS //////
	
	/**
	 * Adds a group called {@code groupname}.
	 * <br>
	 * If this function detects that the group name already exists, then 
	 * it throws an exception.
	 * <p>
	 * Note that the group names {@code "Block IDs"}, {@code "Item IDs"} and {@code "Achievement IDs"} already exists
	                          
	@param  groupname  {@code (String)} the absolute name of the new config group, e. g. "Item Names"
	 */
	public void addGroup(String groupname, int datatype) {
		if(getGroupIdFromName(groupname) == -1)
			groups.add( new Object[] { groupname, datatype } );
		else
			throw new RuntimeException("SAP Config Manager: Group "+ groupname +" already exists! [ in function - addGroup - ]");
	}
	
	public int getGroupIdFromName(String groupname) {
		for(int i = 0; i < groups.size(); i++) {
			Object obj[] = (Object[]) groups.get(i);
			if(((String)obj[0]).equals(groupname))
				return i;
		}
		return -1;
	}
	
	private boolean groupExist_I(int i) {
		return groups.size() > i;
	}
	
	private boolean groupExist_S(String s) {
		return getGroupIdFromName(s) != -1;
	}
	
	private boolean groupHasValues(int group) {
		for(int i = 0; i < props.size(); i++) {
			Object[] prop = (Object[]) props.get(i);
			Integer i1 =  (Integer) prop[2];
			if(i1.intValue() == group) {
				return true;
			}
		}
		return false;
	}

	////// MANAGING CONFIG VALUES //////
	
	private int getValueIdFromName(String s) {
		for(int i = 0; i < props.size(); i++) {
			Object[] prop = (Object[]) props.get(i);
			if(((String)prop[1]).equals(s))
				return i;
		}
		return -1;
	}
	
	/* SETTING AND GETTING CONFIG VALUES */

// for all dt
	private void setConfigValues_A(String[] valuenames, Object[] values, int group) {
		if(valuenames.length != values.length) {
			props.clear();
			throw new RuntimeException("SAP Config Manager: Quantity of value names and values doesn't fit [ in function <setConfigValues_A> ]");
		}
		
		if(!groupExist_I(group)) {
			props.clear();
			throw new RuntimeException("SAP Config Manager: Group-# "+ group +" doesn't exist [ in function <setConfigValues_A> ]");
		}

		for(int i = 0; i < values.length; i++) {
			Object[] obj = new Object[] {values[i], valuenames[i], group};
			if(valuenames[i].equals("") || valuenames[i].equals(null)) {
				props.clear();
				throw new RuntimeException("SAP Config Manager: Valuename on index " + i + " cannot be null [ in function function <setConfigValues_A> ]");
			}
			if(getValueIdFromName(valuenames[i]) != -1) {
				props.clear();
				throw new RuntimeException("SAP Config Manager: Valuename on index " + i + " already exist [ in function function <setConfigValues_A> ]");
			}

			props.add(obj);
		}
	}

	private Object[] getConfigValues_A(String[] valuenames, int datatype) {
		Object[] obj = new Object[valuenames.length];
		int ind = 0;
		for(int i1 = 0; i1 < valuenames.length; i1++) {
			for(int i2 = 0; i2 < props.size(); i2++) {
				Object[] obj1 = (Object[]) props.get(i2);
				if(((String)obj1[1]).equals(valuenames[i1])) {					
					Object obj2[] = (Object[])groups.get((Integer)obj1[2]);
					if(((Integer)obj2[1]) != datatype)
						throw new RuntimeException("SAP Config Manager: Config Value has wrong datatype [ in function - setConfigValues_A - ]");
					obj[ind++] = obj1[0];
					break;
				}
			}
		}
		return obj;
	}

// for long dt
	public void setVar_LO_A(String[] valuenames, long[] values, int group) {
		Object obj[] = new Object[values.length];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.LONG).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected LONG type [ in method <setVar_LO_A> ]");
		
		for(int i = 0; i < values.length; i++) {
			obj[i] = (Object)values[i];
		}
		
		setConfigValues_A(valuenames, obj, group);
	}
	
	public void setVar_LO_O(String valuename, long value, int group) {
		String[] vn = new String[] {valuename};
		Object obj[] = new Object[1];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.LONG).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected LONG type [ in function <setVar_LO_O> ]");
		
		obj[0] = (Object)value;
				
		setConfigValues_A(vn, obj, group);
	}
	
	public long[] getVar_LO_A(String[] valuenames) {
		Object obj[] = new Object[valuenames.length];
		long l[] = new long[valuenames.length];
		
		obj = getConfigValues_A(valuenames, 0);
		
		for(int i1 = 0; i1 < valuenames.length; i1++) {
			if(obj[i1] != null) {
				l[i1] = Long.valueOf(obj[i1].toString());
			}
		}
		
		return l;
	}
	
	public long getVar_LO_O(String valuename) {
		
		String[] vn = new String[] {valuename};
		
		Object obj[] = new Object[1];
		obj = getConfigValues_A(vn, 0);
		
		if(obj[0] != null) {
			long L = Long.valueOf(obj[0].toString());
			return L;
		}
		return -1L;
	}

	
// for integer dt (long)
	public void setVar_IN_A(String[] valuenames, int[] values, int group) {
		long[] l = new long[values.length];
		for(int i = 0; i < l.length; i++) {
			l[i] = (long)values[i];
		}
		setVar_LO_A(valuenames, l, group);
	}
	
	public int[] getVar_IN_A(String[] valuenames) {
		long l[] = getVar_LO_A(valuenames);
		int i[] = new int[l.length];
		for(int j = 0; j < i.length; j++) {
			i[j] = Integer.valueOf(((Long)l[j]).toString());
		}
		return i;
	}
	
	public void setVar_IN_O(String valuename, int value, int group) {
		setVar_LO_O(valuename, (long)value, group);
	}
	
	public int getVar_IN_O(String valuename) {
		return Integer.valueOf(((Long)getVar_LO_O(valuename)).toString());
	}
	
// for short dt (long)
	public void setVar_SH_A(String[] valuenames, short[] values, int group) {
		long[] l = new long[values.length];
		for(int i = 0; i < l.length; i++) {
			l[i] = (long)values[i];
		}
		setVar_LO_A(valuenames, l, group);
	}
	
	public short[] getVar_SH_A(String[] valuenames) {
		long l[] = getVar_LO_A(valuenames);
		short sh[] = new short[l.length];
		for(int j = 0; j < sh.length; j++) {
			sh[j] = (short)l[j];
		}
		return sh;
	}
	
	public void setVar_SH_O(String valuename, short value, int group) {
		setVar_LO_O(valuename, (long)value, group);
	}
	
	public short getVar_SH_O(String valuename) {
		return (short)getVar_LO_O(valuename);
	}

//for byte dt (long)
	public void setVar_BY_A(String[] valuenames, byte[] values, int group) {
		long[] l = new long[values.length];
		for(int i = 0; i < l.length; i++) {
			l[i] = (long)values[i];
		}
		setVar_LO_A(valuenames, l, group);
	}
	
	public byte[] getVar_BY_A(String[] valuenames) {
		long l[] = getVar_LO_A(valuenames);
		byte by[] = new byte[l.length];
		for(int j = 0; j < by.length; j++) {
			by[j] = (byte)l[j];
		}
		return by;
	}
	
	public void setVar_BY_O(String valuename, byte value, int group) {
		setVar_LO_O(valuename, (long)value, group);
	}
	
	public byte getVar_BY_O(String valuename) {
		return (byte)getVar_LO_O(valuename);
	}
	
// for bool dt
	public void setVar_BO_A(String[] valuenames, boolean[] values, int group) {
		Object obj[] = new Object[values.length];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.BOOLEAN).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected BOOL type [ in function <setVar_BO_A> ]");
		
		for(int i = 0; i < values.length; i++) {
			obj[i] = (Object)values[i];
		}
		
		setConfigValues_A(valuenames, obj, group);
	}
	
	public void setVar_BO_O(String valuename, boolean value, int group) {
		String[] vn = new String[] {valuename};
		Object obj[] = new Object[1];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.BOOLEAN).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected BOOL type [ in function <setVar_BO_O> ]");
		
		obj[0] = (Object)value;
				
		setConfigValues_A(vn, obj, group);
	}
	
	public boolean[] getVar_BO_A(String[] valuenames) {
		Object obj[] = new Object[valuenames.length];
		boolean b[] = new boolean[valuenames.length];
		
		obj = getConfigValues_A(valuenames, 1);
		
		for(int i1 = 0; i1 < valuenames.length; i1++) {
			if(obj[i1] != null)
				b[i1] = (Boolean)obj[i1];
		}
		
		return b;
	}
	
	public boolean getVar_BO_O(String valuename) {
		
		String[] vn = new String[] {valuename};

		Object obj[] = new Object[1];
		obj = getConfigValues_A(vn, 1);
		
		if(obj[0] != null)		
			return (Boolean)obj[0];
		return false;
	}
	
// for double dt
	public void setVar_DO_A(String[] valuenames, double[] values, int group) {
		Object obj[] = new Object[values.length];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.DOUBLE).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected DOUBLE type [ in function <setVar_DO_A> ]");
		
		for(int i = 0; i < values.length; i++) {
			obj[i] = (Object)values[i];
		}
		
		setConfigValues_A(valuenames, obj, group);
	}
	
	public void setVar_DO_O(String valuename, double value, int group) {
		String[] vn = new String[] {valuename};
		Object obj[] = new Object[1];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.DOUBLE).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected DOUBLE type [ in function <setVar_DO_O> ]");
		
		obj[0] = (Object)value;
				
		setConfigValues_A(vn, obj, group);
	}
	
	public double[] getVar_DO_A(String[] valuenames) {
		Object obj[] = new Object[valuenames.length];
		double d[] = new double[valuenames.length];
		
		obj = getConfigValues_A(valuenames, 1);
		
		for(int i1 = 0; i1 < valuenames.length; i1++) {
			if(obj[i1] != null)
				d[i1] = (Double)obj[i1];
		}
		
		return d;
	}
	
	public double getVar_DO_O(String valuename) {
		
		String[] vn = new String[] {valuename};
	
		Object obj[] = new Object[1];
		obj = getConfigValues_A(vn, 3);
		
		if(obj[0] != null)		
			return (Double)obj[0];
		return -1D;
	}

	//for float dt (double)
		public void setVar_FL_A(String[] valuenames, float[] values, int group) {
			double[] d = new double[values.length];
			for(int i = 0; i < d.length; i++) {
				d[i] = (double)values[i];
			}
			setVar_DO_A(valuenames, d, group);
		}
		
		public float[] getVar_FL_A(String[] valuenames) {
			double l[] = getVar_DO_A(valuenames);
			float fl[] = new float[l.length];
			for(int j = 0; j < fl.length; j++) {
				fl[j] = (float)l[j];
			}
			return fl;
		}
		
		public void setVar_FL_O(String valuename, float value, int group) {
			setVar_DO_O(valuename, (double)value, group);
		}
		
		public float getVar_FL_O(String valuename) {
			return (float)getVar_DO_O(valuename);
		}
	
// for string dt
	public void setVar_ST_A(String[] valuenames, String[] values, int group) {
		Object obj[] = new Object[values.length];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.STRING).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected STRING type [ in function <setVar_S_A> ]");
		
		for(int i = 0; i < values.length; i++) {
			obj[i] = (Object)values[i];
		}
		
		setConfigValues_A(valuenames, obj, group);
	}
	
	public void setVar_ST_O(String valuename, String value, int group) {
		String[] vn = new String[] {valuename};
		Object obj[] = new Object[1];
		Object obj1[] = (Object[])groups.get(group);
		
		if(((Integer)obj1[1]) != (dt.STRING).ordinal())
			throw new RuntimeException("SAP Config Manager: Wrong function used for group-# " + group + " to set config values - expected STRING type [ in function <setVar_B_O> ]");
		
		obj[0] = (Object)value;
				
		setConfigValues_A(vn, obj, group);
	}
	
	public String[] getVar_ST_A(String[] valuenames) {
		Object obj[] = new Object[valuenames.length];
		String s[] = new String[valuenames.length];
		
		obj = getConfigValues_A(valuenames, 1);
		
		for(int i1 = 0; i1 < valuenames.length; i1++) {
			if(obj[i1] != null)
				s[i1] = (String)obj[i1];
		}
		
		return s;
	}
	
	public String getVar_ST_O(String valuename) {
		
		String[] vn = new String[] {valuename};

		Object obj[] = new Object[1];
		obj = getConfigValues_A(vn, 3);
		
		if(obj[0] != null)		
			return (String)obj[0];
		return "";
	}
	
// for char dt (string)

	public void setVar_CH_A(String[] valuenames, char[] values, int group) {
		String[] s = new String[values.length];
		for(int i = 0; i < s.length; i++) {
			s[i] = ((Character)values[i]).toString();
		}
		setVar_ST_A(valuenames, s, group);
	}
	
	public char[] getVar_CH_A(String[] valuenames) {
		String l[] = getVar_ST_A(valuenames);
		char ch[] = new char[l.length];
		for(int j = 0; j < ch.length; j++) {
			ch[j] = l[j].charAt(0);
		}
		return ch;
	}
	
	public void setVar_CH_O(String valuename, char value, int group) {
		setVar_ST_O(valuename, ((Character)value).toString(), group);
	}
	
	public char getVar_CH_O(String valuename) {
		return getVar_ST_O(valuename).charAt(0);
	}
	
	
	/* OVERRIDING CONFIG VALUES */
	
	private void overrideConfigValue(String valuename, Object value, int datatype) {
		for(int i = 0; i < props.size(); i++) {
			Object[] obj = (Object[]) props.get(i);
			if(((String)obj[1]).equals(valuename)) {
				Object[] obj1 = (Object[])groups.get((Integer)obj[2]);
				if(((Integer)obj1[1]) != datatype)
					throw new RuntimeException("SAP Config Manager: Config Value has wrong datatype [ in function - setConfigValues_A - ]");
				obj[0] = value;
				props.set(i, obj);
				break;
			}			
		}		
	}
	
	public void overrideVar_L(String valuename, long value) {
		overrideConfigValue(valuename, value, 0);
	}
	
	public void overrideVar_B(String valuename, boolean value) {
		overrideConfigValue(valuename, value, 1);
	}
	
	public boolean configExistsAndIsLatest() {
		File dir = getModDir(cfgpath);
//				Minecraft.getAppDir(cfgpath);
		File file = new File(dir, cfgname);
		try {
			if (file.exists()) {
				FileReader fstream = new FileReader(file);
				BufferedReader in = new BufferedReader(fstream);
				if((in.readLine()).equals(new StringBuilder().append(modname.toUpperCase()).append(" "+cfgname.toUpperCase()+" VER ").append(cfgver).toString())) {
					in.close();
					return true;
				}
				in.close();
			}
		} catch(IOException e) {};
		return false;
	}
	
	private void saveConfig() {
		File dir = getModDir(cfgpath);
//				Minecraft.getAppDir(cfgpath);
		File file = new File(dir, cfgname);
		try {
				if(file.exists()) {					
					file.delete();
				}
				FileWriter fstream = new FileWriter(file,true);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(modname.toUpperCase()+" "+cfgname.toUpperCase()+" VER "+cfgver+"\n----------------\n");
				
				for(int i = 0; i < groups.size(); i++) {
					if( !groupHasValues(i) )
						continue;
					Object[] obj = (Object[])groups.get(i);
					out.write("[ " + (String)obj[0] + " ]\n");
					for(int j = 0; j < props.size(); j++) {
						Object[] prop = (Object[]) props.get(j);
						Integer i1 = (Integer) prop[2];
						if(i1.intValue() == i) {
							out.write("#   "+prop[1]+" = "+prop[0]+"\n");
						}
					}
				}
				
				out.close();
				System.out.println("config.txt successfully created.");
		} catch(IOException e) { System.out.println(e); }
	}

	public void loadConfig() {
		File dir = getModDir(cfgpath);
//				Minecraft.getAppDir(cfgpath);
		File file = new File(dir, cfgname);
		try {
			if(!file.exists()) {
				saveConfig();
				return;
			}
			FileReader fstream = new FileReader(file);
			BufferedReader in = new BufferedReader(fstream);
//			boolean latest = (in.readLine()).equals(new StringBuilder().append(modname.toUpperCase()).append(" CONFIG VER ").append(cfgver).toString());
			boolean latest = configExistsAndIsLatest();
				in.readLine();
				int groupID = -1;
				String s = null;
				do {
					s = in.readLine();
				
					String s1 = null;
					if(s.matches("[<|\\[] .*? [>|\\]]")) {
						s1 = s.replaceAll("< ", "");
						s1 = s1.replaceAll(" >", "");
						s1 = s1.replaceAll("\\[ ", "");
						s1 = s1.replaceAll(" \\]", "");
						for(int i = 0; i < groups.size(); i++) {
							Object obj[] = (Object[])groups.get(i);
							if(s1.equals((String)obj[0])) {
								groupID = i;
							}
						}
					} else if(s.matches(".*?=.*") && groupID != -1) {
						String name = s.replaceAll("[ ]{0,}=.*", "");
						name = name.replaceAll("[#]{0,}[ ]{0,}", "");
						String value = s.replaceAll(".*?=[ ]{0,}", "");
						
						for(int i = 0; i < props.size(); i++) {
							Object obj[] = (Object[])props.get(i);
							Object obj1[] = (Object[])groups.get((Integer)obj[2]);
							
							if(groupID == (Integer)obj[2] && ((String)obj[1]).equals(name)) {
								switch((Integer)obj1[1]) {
									case 0: obj[0] = Integer.valueOf(value); break;
									case 1: obj[0] = Boolean.valueOf(value); break;
								}
							}
							
							props.set(i, obj);
						}
					}
				} while(!((s == "") || (s == null)) && in.ready());
				in.close();
				
				System.out.println("config.txt successfully readed.");
			if(!latest) {
				System.out.println("current config.txt too old or corrupt, deleting old file and writing a new one.");
				in.close();
				saveConfig();
				return;
			}
		} catch(IOException e) { System.out.println(e); }
	}
	
	/** DEPRECATED!!! **/
	@Deprecated
	public void setConfigValue_O_INT(String cfgName, int value, int group) {
		setVar_IN_O(cfgName, value, group);
	}

	@Deprecated
	public int getConfigValues_O_INT(String cfgName) {
		return getVar_IN_O(cfgName);
	}
}