/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.raptaml.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lemmer
 */
public class Main {
    static int width = 1920;
    static int height = 1080;
     
    public static void main(String[] args) {
                
        BufferedImage img = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        
        //draw line between 0/0 and 10/10
        
       long start = System.nanoTime();
        xline(0,0,width,height,g,Color.red);
       long end = System.nanoTime();
       
        System.out.println("spent "+ (double)(end-start)/1000l + " millis");

        try {
            System.out.println(ImageIO.write(img, "BMP", new File("d:\\test2.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     
    static void line(int x0, int y0, int x1, int y1, Graphics g, Color color) {
        g.translate(0, height);
        for (int x=x0; x <=x1; x++) {
             float t = (x-x0)/(float)(x1-x0);
             int y = (int) (y0 * (1f-t) + y1 * t);
             g.drawRect(x, -y, 0, 0);
        }
    }
    
    static void xline(int x0, int y0, int x1, int y1, Graphics g, Color color) {
        boolean steep = false;
        //origin bottom left for LLO
        g.translate(0, height);
        g.setColor(color);
        if(Math.abs(x0-x1) < Math.abs(y0-y1)) {
            //swap
            int tmp = x0;
            x0 = y0;
            y0 = tmp;
            tmp = x1;
            x1 = y1;
            y1 = tmp;
            steep = true;
        }
        
        if(x0 > x1) {
            //swap
            int tmp = x0;
            x0 = x1;
            x1 = tmp;
            tmp = y0;
            y0 = y1;
            y1 = tmp;
        }
        
        int dx = x1-x0;
        int dy = y1-y0;
        float derror = Math.abs((float)dy / (float)dx);
        float error = 0;
        int y = y0;
        for (int x=x0; x<=x1; x++) {
            //allways draw negative y to translate to LLO coodrdinates
            if (steep) {
                g.drawRect(y, -x, 0,0);
            } else {
                g.drawRect(x, -y, 0,0);
            }
            error += derror;
            if (error>.5f) {
                y += (y1>y0?1:-1);
                error -= 1.;
            }
        }
    }
    
}
