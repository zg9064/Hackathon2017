import java.awt.Color;
import java.awt.image.BufferedImage;

public class MostCommon {
	public static float[] getColor(BufferedImage i, int x, int y){
		float aH=0;//avg hue
		float aS=0;//avg sat
		float aB=0;//avg bright
		for(int r=x;r<x+3;r++){
			for(int c=y;c<y+3;c++){
				try{
					Color t=new Color(i.getRGB(r,c));
					int r1 = t.getRed();
					int g = t.getGreen();
					int b = t.getBlue();
					float[] hsb= Color.RGBtoHSB(r1, g, b, null);
					aH+=hsb[0]*360;
					aS+=hsb[1];
					aB+=hsb[2];
				}
				catch(Exception e){}
			}
		}
		aB/=9;
		aS/=9;
		aH/=9;
		float[] val={aH,aS,aB};
		return val;
	}
}
