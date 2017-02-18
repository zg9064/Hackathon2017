import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
public static void main(String[]args){
  float hsb[] = new float[3];
  int r = (rgb >> 16) & 0xFF;
  int g = (rgb >>  8) & 0xFF;
  int b = (rgb      ) & 0xFF;
  Color.RGBtoHSB(r, g, b, hsb);
  if      (hsb[1] < 0.1 && hsb[2] > 0.9) nearlyWhite();
  else if (hsb[2] < 0.1) nearlyBlack();
  else {
    float deg = hsb[0]*360;
    if      (deg >=   0 && deg <  30) red();
    else if (deg >=  30 && deg <  90) yellow();
    else if (deg >=  90 && deg < 150) green();
    else if (deg >= 150 && deg < 210) cyan();
    else if (deg >= 210 && deg < 270) blue();
    else if (deg >= 270 && deg < 330) magenta();
    else red();
  }
}
