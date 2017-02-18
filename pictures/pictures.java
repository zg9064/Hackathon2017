import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
@SuppressWarnings("all")
public class pictures {
	public static void main(String[]args) throws IOException{
		BufferedImage image = ImageIO.read(new File(INPUT));
		int width=image.getWidth();
		int height=image.getHeight();
		int[] hues=new int[360];
		int black=0;
		int white=0;
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				int rgb=image.getRGB(x,y);
				float hsb[] = new float[3];
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >>  8) & 0xFF;
				int b = (rgb      ) & 0xFF;
				Color.RGBtoHSB(r, g, b, hsb);
				if      (hsb[1] < 0.1 && hsb[2] > 0.9) white++;
				else if (hsb[2] < 0.1                ) black++;
				else                                   hues[(int) (hsb[0]*360)]++;
			}
		}
    /*
		String s="[";
		for(int i=0; i<360;i++)
			if(i==359)
				s+=hues[i]+"]";
			else
				s+=hues[i]+", ";
		System.out.println("black: "+black+" white: "+white+" hues: "+s);
    */		int[] colors=new int[5];
		int mc1;
		int mc2;
		int mc3;
		int mc4;
		for(int i=0;i<5;i++){
			int mostCommon=0;
			for(int j=0;j<360;j++){
				boolean run=true;
				if(mc1!=null)
					if(mc1<=15){
						j=31;
						run=false;
					}
					else if(j==mc1-15){
						j=j+31;
						run=false;
					}
				else if(mc2!=null)
					if(mc2<=15){
						j=31;
						run=false;
					}
					else if(j==mc2-15){
						j=j+31;
						run=false;
					}
				else if(mc3!=null)
					if(mc3<=15){
						j=31;
						run=false;
					}
					else if(j==mc3-15){
						j=j+31;
						run=false;
					}
				else if(mc4!=null)
					if(mc4<=15){
						j=31;
						run=false;
					}
					else if(j==mc4-15){
						j=j+31;
						run=false;
					}
				if(run&&hues[i]>hues[mostCommon]) mostCommon=i;
			}
			if(mc1==null) mc1=mostCommon;
			else if(mc2==null) mc2=mostCommon;
			else if(mc3==null) mc3=mostCommon;
			else if(mc4==null) mc4==mostCommon;
			int[i]=mostCommon;
	}
}
