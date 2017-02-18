import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
@SuppressWarnings("all")
public class picTest {
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
    */
	}
}
