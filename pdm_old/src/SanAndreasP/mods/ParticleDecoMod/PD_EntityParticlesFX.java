package SanAndreasP.mods.ParticleDecoMod;
import net.minecraft.src.*;

public abstract class PD_EntityParticlesFX extends EntityFX {

	protected boolean glow = false;
	protected int ptheight = 100;
	protected int direction = 0;

    public PD_EntityParticlesFX(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, boolean b, boolean b1, int i, int j)
    {
        super(world, d, d1, d2, d3, d4, d5);
        glow = b;
        ptheight = i;
        direction = j;
        noClip=b1;
        initParticle(world, d, d1, d2, d3, d4, d5, 1.1F, f, f1, f2);
    }
    
    public abstract void initParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, float f, float f1, float f2, float f3);

    public int getBrightnessForRender(float f)
    {
        if(glow) return 0x0f00f0;
        return super.getBrightnessForRender(f);
    }
    
    protected void moveParticle() {
	    switch(direction) {
	    	case 0: motionY += 0.002D; break;
	    	case 1: motionX += 0.002D; break;
	    	case 2: motionX -= 0.002D; break;
	    	case 3: motionZ += 0.002D; break;
	    	case 4: motionZ -= 0.002D; break;
	    }
	    
	    moveEntity(motionX, motionY, motionZ);
	      
	    double factor = 0.00111111111111111111111111111111D;
	      
	    switch(direction) {
		    case 0:
		    	motionX *= 0.85000002384185791D;
		    	motionY *= 0.73888888888888888888888888888889D + factor * (double)ptheight;
		    	motionZ *= 0.85000002384185791D;
		    	break;
		    case 1:
		    	motionY *= 0.85000002384185791D;
		    	motionX *= 0.73888888888888888888888888888889D + factor * (double)ptheight;
		    	motionZ *= 0.85000002384185791D;
		    	break;
		    case 2:
		    	motionY *= 0.85000002384185791D;
		    	motionX *= ((motionX > 0.000000000000000000000F) ? -1 : 1)*(0.73888888888888888888888888888889D + factor * (double)ptheight);
		    	motionZ *= 0.85000002384185791D;
		    	break;
		    case 3:
		    	motionY *= 0.85000002384185791D;
		    	motionZ *= 0.73888888888888888888888888888889D + factor * (double)ptheight;
		    	motionX *= 0.85000002384185791D;
		    	break;
		    case 4:
		    	motionY *= 0.85000002384185791D;
		    	motionZ *= ((motionZ > 0.000000000000000000000F) ? -1 : 1)*(0.73888888888888888888888888888889D + factor * (double)ptheight);
		    	motionX *= 0.85000002384185791D;
		    	break;
	    }
	  
		if(onGround && !noClip)
		{
			motionX *= 0.69999998807907104D;
			motionZ *= 0.69999998807907104D;
		}
    }
}
