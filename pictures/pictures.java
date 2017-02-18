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
		String s="[";
		for(int i=0; i<360;i++)
			if(i==359)
				s+=hues[i]+"]";
			else
				s+=hues[i]+", ";
		System.out.println("black: "+black+" white: "+white+" hues: "+s);
		int[] colors=new int[5];
		int mc1=-1;
		int mc2=-1;
		int mc3=-1;
		int mc4=-1;
		boolean rb=true;
		boolean rw=true;
		for(int i=0;i<5;i++){
			int mostCommon=0;
			for(int j=0;j<360;j++){
				boolean run=true;
				boolean r1=true;
				boolean r2=true;
				boolean r3=true;
				boolean r4=true;
				if(mc1<360&&r1&&mc1>=0){
					if(mc1<=15&&j<31){
						j=31;
						run=false;
						r1=false;
					}
					else{
						for(int t=-15;t<16;t++){
							if(j==mc1+t){
								j=mc1+16;
								run=false;
								r1=false;
							}
						}
					}
				}
				if(mc2<360&&r2&&mc2>=0){
					if(mc2<=15&&j<31){
						j=31;
						run=false;
						r2=false;
					}
					else{
						for(int t=-15;t<16;t++){
							if(j==mc2+t){
								j=mc2+16;
								run=false;
								r2=false;
							}
						}
					}
				}
				if(mc3<360&&r3&&mc3>=0){
					if(mc3<=15&&j<31){
						j=31;
						run=false;
						r3=false;
					}
					else{
						for(int t=-15;t<16;t++){
							if(j==mc3+t){
								j=mc3+16;
								run=false;
								r3=false;
							}
						}
					}
				}
				if(mc4<360&&r4&&mc4>=0){
					if(mc4<=15&&j<31){
						j=31;
						run=false;
						r4=false;
					}
					else{
						for(int t=-15;t<16;t++){
							if(j==mc4+t){
								j=mc4+16;
								run=false;
								r4=false;
							}
						}
					}
				}
				if(run&&hues[j]>hues[mostCommon]) mostCommon=j;
			}
			boolean rt=true;
			if(rb&&black>hues[mostCommon]){
				mostCommon=360;
				if(mc1<0) mc1=360;
				else if(mc2<0) mc2=360;
				else if(mc3<0) mc3=360;
				else if(mc4<0) mc4=360;
				rt=false;
				rb=false;
			}
			try{
				if(rw&&white>hues[mostCommon]){
					mostCommon=361;
					if(mc1<0) mc1=361;
					else if(mc2<0) mc2=361;
					else if(mc3<0) mc3=361;
					else if(mc4<0) mc4=361;
					rt=false;
					rw=false;
				}
			}
			catch(Exception e){
				if(white>black){
					rb=true;
					mostCommon=361;
					if(mc1<0||mc1==360) mc1=361;
					else if(mc2<0||mc2==360) mc2=361;
					else if(mc3<0||mc3==360) mc3=361;
					else if(mc4<0||mc4==360) mc4=361;
					rt=false;
					rw=false;
				}
			}
			if(rt){
				if(mc1<0) mc1=mostCommon;
				else if(mc2<0) mc2=mostCommon;
				else if(mc3<0) mc3=mostCommon;
				else if(mc4<0) mc4=mostCommon;
			}
			colors[i]=mostCommon;

		}
		int c1,c2,c3,c4,c5;
		for(int i=0;i<5;i++){
			if(colors[i]==361){
				if(i==0) c1=Color.HSBtoRGB(0, 0, 1);
				else if(i==1) c2=Color.HSBtoRGB(0, 0, 1);
				else if(i==2) c3=Color.HSBtoRGB(0, 0, 1);
				else if(i==3) c4=Color.HSBtoRGB(0, 0, 1);
				else c5=Color.HSBtoRGB(0, 0, 1);
			}
			else if(colors[i]==360){
				if(i==0) c1=Color.HSBtoRGB(0, 0, 0);
				else if(i==1) c2=Color.HSBtoRGB(0, 0, 0);
				else if(i==2) c3=Color.HSBtoRGB(0, 0, 0);
				else if(i==3) c4=Color.HSBtoRGB(0, 0, 0);
				else c5=Color.HSBtoRGB(0, 0, 0);
			}
			else{
				if(i==0) c1=Color.HSBtoRGB(new Float(colors[i]), new Float(.5), new Float(.5));
				else if(i==1) c2=Color.HSBtoRGB(new Float(colors[i]), new Float(.5), new Float(.5));
				else if(i==2) c3=Color.HSBtoRGB(new Float(colors[i]), new Float(.5), new Float(.5));
				else if(i==3) c4=Color.HSBtoRGB(new Float(colors[i]), new Float(.5), new Float(.5));
				else c5=Color.HSBtoRGB(new Float(colors[i]), new Float(.5), new Float(.5));
			}
		}
		String str="[";
		for(int i=0;i<5;i++){
			if(i==4) str+=colors[i]+"]";
			else str+=colors[i]+", ";
		}
		System.out.println(str);
	}
}
