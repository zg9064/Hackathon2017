import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
@SuppressWarnings("all")
public class pictures extends JComponent {
	public static void main(String[]args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 400, 200);
		window.getContentPane().add(new pictures());
		window.setVisible(true);
	}
	public void paint(Graphics gr){
		BufferedReader b;
		try {
			b = new BufferedReader(new FileReader("URLStrings.txt"));
		} catch (FileNotFoundException e2) {
			return;
		}
		int c1=0,c2=0,c3=0,c4=0,c5=0,white=0,black=0;
		int[] hues=new int[360];
		float[] sat=new float[360];
		float[] sn=new float[360];
		float[] br=new float[360];
		float[] bn=new float[360];
		try {
			while(b.ready()){
				String URL;
				try {
					URL = b.readLine();
				} catch (IOException e) {
					break;
				}
				BufferedImage image;
				try {
					image = ImageIO.read(new File(URL));
				} catch (IOException e1) {
					return;
				}
				int width=image.getWidth();
				int height=image.getHeight();
				for(int y=0;y<height;y+=3){
					for(int x=0;x<width;x+=3){
						float[] hsb= MostCommon.getColor(image,x,y);
						if      (hsb[1] < 0.1 && hsb[2] > 0.9) white++;
						else if (hsb[2] < 0.1                ) black++;
						else{
							hues[(int)(hsb[0])]++;
							sat[(int)(hsb[0])]=(float)(sn[(int)(hsb[0])]*sat[(int)(hsb[0])]+hsb[1])/(sn[(int)(hsb[0])]+1);
							br[(int)(hsb[0])]=(float)(br[(int)(hsb[0])]*bn[(int)(hsb[0])]+hsb[2])/(bn[(int)(hsb[0])]+1);
							sn[(int)(hsb[0])]++;
							bn[(int)(hsb[0])]++;
						}
					}
				}
			}
		} catch (IOException e1) {
			return;
		}
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
					if(mc1<=30&&j<61){
						j=61;
						run=false;
						r1=false;
					}
					else{
						for(int t=-30;t<31;t++){
							if(j==mc1+t){
								j=mc1+31;
								run=false;
								r1=false;
							}
						}
					}
				}
				if(mc2<360&&r2&&mc2>=0){
					if(mc2<=30&&j<61){
						j=61;
						run=false;
						r2=false;
					}
					else{
						for(int t=-30;t<31;t++){
							if(j==mc2+t){
								j=mc2+31;
								run=false;
								r2=false;
							}
						}
					}
				}
				if(mc3<360&&r3&&mc3>=0){
					if(mc3<=30&&j<61){
						j=61;
						run=false;
						r3=false;
					}
					else{
						for(int t=-30;t<31;t++){
							if(j==mc3+t){
								j=mc3+31;
								run=false;
								r3=false;
							}
						}
					}
				}
				if(mc4<360&&r4&&mc4>=0){
					if(mc4<=30&&j<61){
						j=61;
						run=false;
						r4=false;
					}
					else{
						for(int t=-30;t<31;t++){
							if(j==mc4+t){
								j=mc4+31;
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
		c1=(colors[0]==361)?Color.HSBtoRGB(0,0,1):(colors[0]==360)?Color.HSBtoRGB(0,0,0):Color.HSBtoRGB(new Float(((float)colors[0])/360), new Float(sat[colors[0]]), new Float(br[colors[0]]));
		c2=(colors[1]==361)?Color.HSBtoRGB(0,0,1):(colors[1]==360)?Color.HSBtoRGB(0,0,0):Color.HSBtoRGB(new Float(((float)colors[1])/360), new Float(sat[colors[1]]), new Float(br[colors[1]]));
		c3=(colors[2]==361)?Color.HSBtoRGB(0,0,1):(colors[2]==360)?Color.HSBtoRGB(0,0,0):Color.HSBtoRGB(new Float(((float)colors[2])/360), new Float(sat[colors[2]]), new Float(br[colors[2]]));
		c4=(colors[3]==361)?Color.HSBtoRGB(0,0,1):(colors[3]==360)?Color.HSBtoRGB(0,0,0):Color.HSBtoRGB(new Float(((float)colors[3])/360), new Float(sat[colors[3]]), new Float(br[colors[3]]));
		c5=(colors[4]==361)?Color.HSBtoRGB(0,0,1):(colors[4]==360)?Color.HSBtoRGB(0,0,0):Color.HSBtoRGB(new Float(((float)colors[4])/360), new Float(sat[colors[4]]), new Float(br[colors[4]]));
		gr.setColor(new Color(c1));
		gr.fillRect(20,20,50,50);
		gr.setColor(new Color(c2));
		gr.fillRect(90,20,50,50);
		gr.setColor(new Color(c3));
		gr.fillRect(160,20,50,50);
		gr.setColor(new Color(c4));
		gr.fillRect(230,20,50,50);
		gr.setColor(new Color(c5));
		gr.fillRect(300,20,50,50);

	}
}
