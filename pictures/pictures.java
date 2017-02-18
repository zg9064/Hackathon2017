import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
public static void main(String[]args){
  BufferedImage img = ImageIO.read(new File(IMAGE NAME, FIND OUT HOW TO PULL IT INTO THE FILE));
  int width=image.getWidth();
  int height=image.getHeight();
  int[] hues=new int[361];
  int black=0;
  int white=0;
  for(int r=0;r<height;r++){
    for(int c=0;c<width;c++){
      int rgb=img.getRGB(c,r);
      float hsb[] = new float[3];
      int r = (rgb >> 16) & 0xFF;
      int g = (rgb >>  8) & 0xFF;
      int b = (rgb      ) & 0xFF;
      Color.RGBtoHSB(r, g, b, hsb);
      if      (hsb[1] < 0.1 && hsb[2] > 0.9) white++;
      else if (hsb[2] < 0.1                ) black++;
      else                                   hues[hsb[0]*360]++;
    }
  }
}
