package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityDiggingFX;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.WorldServer;

public class PD_EntityParticleDeco extends EntityLiving {

    public ItemStack glowstone;
    public PD_Inventory inventory = new PD_Inventory(this);
    public ItemStack particleDye;

	public PD_EntityParticleDeco(World world) {
		this(world, 0, 64, 0, 0);
	}

	public PD_EntityParticleDeco(World world, int i, int j, int k, int l) {
		super(world);
		this.dataWatcher.addObject(10, (short)l); // type
		this.dataWatcher.addObject(11, (short)100); // particleHeight
		this.dataWatcher.addObject(12, (short)-1); // color
		this.dataWatcher.addObject(13, (short)0); // boolean shit
		this.dataWatcher.addObject(14, (short)0); // direction
		this.height = 0.4F;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);
        this.setPosition(i + 0.5D, j, k + 0.5D);
        this.health = 1;
        this.texture = "/SanAndreasP/mods/ParticleDecoMod/images/pd_base.png";
        this.setActive(true);
	}

	public boolean canBeColorized() {
		return this.getType() == 0 || this.getType() == 1;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	protected void entityInit() {}

	@Override
	public int getBrightnessForRender(float f) {
        if(this.isGlow() && this.isGlowItself() && this.isActive()) return 0x0f00f0;
        return super.getBrightnessForRender(f);
	}
	
	public int getColor() {
		return this.dataWatcher.getWatchableObjectShort(12);
	}

	public float[] getColorFromDye() {
		float[] color = new float[] {1F, 1F, 1F};
		switch(this.getColor()) {
			case 0: color = new float[] {0F, 0F, 0F}; break;
			case 1: color = new float[] {1F, 0F, 0F}; break;
			case 2: color = new float[] {0.29F, 0.43F, 0.1F}; break;
			case 3: color = new float[] {0.49F, 0.29F, 0.17F}; break;
			case 4: color = new float[] {0F, 0F, 1F}; break;
			case 5: color = new float[] {0.66F, 0.34F, 0.82F}; break;
			case 6: color = new float[] {0.25F, 0.59F, 0.73F}; break;
			case 7: color = new float[] {0.6F, 0.6F, 0.7F}; break;
			case 8: color = new float[] {0.3F, 0.3F, 0.4F}; break;
			case 9: color = new float[] {0.96F, 0.68F, 0.82F}; break;
			case 10: color = new float[] {0F, 1F, 0F}; break;
			case 11: color = new float[] {1F, 1F, 0F}; break;
			case 12: color = new float[] {0.51F, 0.71F, 1F}; break;
			case 13: color = new float[] {0.84F, 0.44F, 0.81F}; break;
			case 14: color = new float[] {1F, 0.5F, 0F}; break;
		}
		return color;
	}
	
	@Override
	protected String getDeathSound() {
		return "step.stone";
	}

	public int getDirection() {
		return this.dataWatcher.getWatchableObjectShort(14);
	}
	
	@Override
	protected String getHurtSound() {
		return "";
	}

	@Override
	public int getMaxHealth() {
		return 1;
	}
	
	private float[] getMotionFromDir() {
		float[] dir = new float[] {0F, 1.1F, 0F, 0F, 0.2F, 0F};
		switch(this.getDirection()) {
			case 1: dir = new float[] {1.1F, 0F, 0F, 0.2F, 0.25F, 0F}; break;
			case 2: dir = new float[] {1.5F, 0F, 0F, -0.2F, 0.25F, 0F}; break;
			case 3: dir = new float[] {0F, 0F, 1.1F, 0F, 0.25F, 0.2F}; break;
			case 4: dir = new float[] {0F, 0F, 1.5F, 0F, 0.25F, -0.2F}; break;
		}
		return dir;
	}

	public int getParticleHeight() {
		return this.dataWatcher.getWatchableObjectShort(11);
	}
	
	@Override
	public String getTexture() {
		if(this.isCamouflaged()) return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_stealth.png";
		switch(this.getType()) {
			case 0: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_p.png";
			case 1: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_o.png";
			case 2: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_b.png";
			case 3: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_f.png";
			case 4: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_c.png";
			case 5: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_h.png";
			case 6: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_e.png";
			case 7: return "/SanAndreasP/mods/ParticleDecoMod/images/pd_base_x.png";
			default: return super.getTexture();
		}
	}

	public int getType() {
		return this.dataWatcher.getWatchableObjectShort(10);
	}
	
	public boolean hasParticleNC() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 8) == 8;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		entityplayer.openGui(PD_ModRegistry.instance, 0, this.worldObj, this.entityId, 0, 0);
		return true;
	}
	
	public boolean isActive() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 2) == 2;
	}

	public boolean isCamouflaged() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 16) == 16;
	}
	
	public boolean isFireFXSetFire() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 64) == 64;
	}

	public boolean isGlow() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 1) == 1;
	}
	
	public boolean isGlowItself() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 32) == 32;
	}

	public boolean isRSActive() {
		return (this.dataWatcher.getWatchableObjectShort(13) & 4) == 4;
	}
	
	@Override
	public void moveEntity(double d, double d1, double d2) {}

	private void onPDUpdate() {

		if(this.isEntityAlive() && this.isEntityInsideOpaqueBlock())
	    {
	        if(!this.attackEntityFrom(DamageSource.inWall, 1));
	    }
	    this.prevCameraPitch = this.cameraPitch;
	    if(this.attackTime > 0)
	    {
	        this.attackTime--;
	    }
	    if(this.hurtTime > 0)
	    {
	        this.hurtTime--;
	    }
	    if(this.health / 2 > 0)
	    {
	        this.health -= 2;
	    }
	    if(this.health <= 0)
	    {
	    	this.onDeathUpdate();
	    }
	    if(this.newPosRotationIncrements > 0)
	    {
	    	this.newPosRotationIncrements--;
	    } else
	    {
	    	this.attackingPlayer = null;
	    }
	    this.ticksExisted++;
	}

	@Override
	public void onUpdate() {
		if(!this.worldObj.isRemote) {
			if(this.particleDye != null && this.particleDye.itemID == Item.dyePowder.shiftedIndex && this.getColor() != this.particleDye.getItemDamage()) {
				this.setColor(this.particleDye.getItemDamage());
			} else if(this.particleDye == null || (this.particleDye != null && this.particleDye.itemID != Item.dyePowder.shiftedIndex)) {
				this.setColor(-1);
			}

			if(this.glowstone != null && this.glowstone.itemID == Item.lightStoneDust.shiftedIndex) {
				this.setGlow(true);
			} else {
				this.setGlow(false);
			}
		}

		float[] f = this.getColorFromDye();
		float[] f1 = this.getMotionFromDir();

		if(!(this.worldObj instanceof WorldServer)) {
			boolean b = PD_ModRegistry.proxy.getClientPlayer().getDistanceToEntity(this) < 128D;
			if(this.isActive() && b) {
				for(int i = 0; i < 1; i++) {
					switch(this.getType()) {
					/** Dust FX **/
						case 0:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityDustFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], f[0], f[1], f[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
					/** Potion FX **/
						case 1:
							if(i == 0 && this.ticksExisted % 2 == 0) PD_ModRegistry.proxy.spawnParticle(new PD_EntityPotionFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], f[0], f[1], f[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
					/** Special FX **/
						case 2:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityBubbleFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
						case 3:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityFlameFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.hasParticleNC(), this.isFireFXSetFire(), this.getParticleHeight(), this.getDirection()));
							break;
						case 4:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityCritFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
						case 5:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityHeartFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
						case 6:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityEnchFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
						case 7:
							if(i == 0 && this.ticksExisted % 2 == 0) PD_ModRegistry.proxy.spawnParticle(new PD_EntityXmasFX(PD_ModRegistry.proxy.getClientWorld(), this.posX+f1[3], this.posY+f1[4], this.posZ+f1[5], f1[0], f1[1], f1[2], this.isGlow(), this.hasParticleNC(), this.getParticleHeight(), this.getDirection()));
							break;
						default:
							PD_ModRegistry.proxy.spawnParticle(new PD_EntityDustFX(PD_ModRegistry.proxy.getClientWorld(), this.posX, this.posY+0.2D, this.posZ, 0F, 1.1F, 0F, 0F, 0F, 0F, false, false, 100, 0));
					}
				}
			}

			if( this.health < 1 ) {
				for(int i = 0; i < 10 && PD_ModRegistry.proxy.isClient(); i++) PD_ModRegistry.proxy.spawnParticle(new EntityDiggingFX(PD_ModRegistry.proxy.getClientWorld(), this.posX, this.posY, this.posZ, 0.0F, 0.5F, 0.0F,Block.stone, 0, 0));
				this.setDead();
			}
		} else {
			if(this.ticksExisted % 2 == 0 && this.isActive()) this.worldObj.playSoundAtEntity(this, "particledeco.static", 0.03F, this.getParticleHeight() / 200F);
		}

		if( this.health < 1 ) {
			if(!this.worldObj.isRemote) {
				this.entityDropItem(new ItemStack(PD_ModRegistry.partdeco, 1, this.dataWatcher.getWatchableObjectShort(10)), 0F);
	        	if(this.glowstone != null && this.glowstone.itemID == Item.lightStoneDust.shiftedIndex) this.entityDropItem(this.glowstone, 0F);
	        	if(this.particleDye != null && this.particleDye.itemID == Item.dyePowder.shiftedIndex) this.entityDropItem(this.particleDye, 0F);
			}
			this.setDead();
		}

    	if(this.isRSActive()) {
			int cPosX = (int) Math.floor(this.posX);
	    	int cPosY = (int) Math.floor(this.posY);
	    	int cPosZ = (int) Math.floor(this.posZ);

	    	this.setActive(this.worldObj.isBlockGettingPowered(cPosX, cPosY-1, cPosZ) || this.worldObj.isBlockGettingPowered(cPosX, cPosY, cPosZ));
    	}

		this.onPDUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		if(nbttagcompound.hasKey("PD_pType"))
			this.setType(nbttagcompound.getInteger("PD_pType"));
		if(nbttagcompound.hasKey("PD_ParticleHgt"))
			this.setParticleHeight(nbttagcompound.getInteger("PD_ParticleHgt"));
		if(nbttagcompound.hasKey("PD_PTdirection"))
			this.setDirection(nbttagcompound.getInteger("PD_PTdirection"));
		if(nbttagcompound.hasKey("PD_PTcolor")) {
			this.setColor(nbttagcompound.getInteger("PD_PTcolor"));
			if(this.getColor() >= 0)
				this.particleDye = new ItemStack(Item.dyePowder.shiftedIndex, 1, this.getColor());
		}
		if(nbttagcompound.hasKey("PD_PTglowing")) {
			this.setGlow(nbttagcompound.getBoolean("PD_PTglowing"));
			if(this.isGlow())
				this.glowstone = new ItemStack(Item.lightStoneDust.shiftedIndex, 1, 0);
		}
		if(nbttagcompound.hasKey("PD_Active"))
			this.setActive(nbttagcompound.getBoolean("PD_Active"));
		if(nbttagcompound.hasKey("PD_isRSActive"))
			this.setRSActive(nbttagcompound.getBoolean("PD_isRSActive"));
		if(nbttagcompound.hasKey("PD_camouflaged"))
			this.setCamouflaged(nbttagcompound.getBoolean("PD_camouflaged"));
		if(nbttagcompound.hasKey("PD_glowItself"))
			this.setGlowItself(nbttagcompound.getBoolean("PD_glowItself"));
		if(nbttagcompound.hasKey("PD_fireFXSetOnFire"))
			this.setIsFireFXSetFire(nbttagcompound.getBoolean("PD_fireFXSetOnFire"));
		if(nbttagcompound.hasKey("PD_hasParticleNC"))
			this.setParticleNC(nbttagcompound.getBoolean("PD_hasParticleNC"));
	}

	public void setActive(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 2;
		else
			prevDW &= ~2;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setCamouflaged(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 16;
		else
			prevDW &= ~16;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setColor(int color) {
		this.dataWatcher.updateObject(12, (short)color);
	}


	public void setDirection(int dir) {
		this.dataWatcher.updateObject(14, (short)dir);
	}

	public void setGlow(boolean glow) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(glow)
			prevDW |= 1;
		else
			prevDW &= ~1;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setGlowItself(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 32;
		else
			prevDW &= ~32;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setIsFireFXSetFire(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 64;
		else
			prevDW &= ~64;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setParticleHeight(int ptHeight) {
		this.dataWatcher.updateObject(11, (short)ptHeight);
	}

	public void setParticleNC(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 8;
		else
			prevDW &= ~8;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setRSActive(boolean active) {
		short prevDW = this.dataWatcher.getWatchableObjectShort(13);
		if(active)
			prevDW |= 4;
		else
			prevDW &= ~4;

		this.dataWatcher.updateObject(13, prevDW);
	}

	public void setType(int type) {
		this.dataWatcher.updateObject(10, (short)type);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("PD_pType", this.getType());
		nbttagcompound.setInteger("PD_ParticleHgt", this.getParticleHeight());
		nbttagcompound.setInteger("PD_PTdirection", this.getDirection());
		nbttagcompound.setInteger("PD_PTcolor", this.getColor());
		nbttagcompound.setBoolean("PD_PTglowing", this.isGlow());
		nbttagcompound.setBoolean("PD_Active", this.isActive());
		nbttagcompound.setBoolean("PD_isRSActive", this.isRSActive());
		nbttagcompound.setBoolean("PD_camouflaged", this.isCamouflaged());
		nbttagcompound.setBoolean("PD_glowItself", this.isGlowItself());
		nbttagcompound.setBoolean("PD_hasParticleNC", this.hasParticleNC());
		nbttagcompound.setBoolean("PD_fireFXSetOnFire", this.isFireFXSetFire());
	}
}
