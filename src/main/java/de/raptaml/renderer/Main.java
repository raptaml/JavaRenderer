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
    public static void main(String[] args) {
        
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        
        //draw line between 0/0 and 10/10
        
        //xline(13, 20, 80, 40, g, Color.red);
        //xline(20, 13, 40, 80, g, Color.green);
        //xline(80, 41, 13, 21, g, Color.yellow);
        xline(0,0,0,100,g,Color.blue);

        
        
        try {
            System.out.println(ImageIO.write(img, "BMP", new File("d:\\test2.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
    }
     
    static void line(int x0, int y0, int x1, int y1, Graphics g, Color color) {
        //g.translate(0, 100);
        for (int x=x0; x <=x1; x++) {
             float t = (x-x0)/(float)(x1-x0);
             int y = (int) (y0 * (1f-t) + y1 * t);
             g.drawRect(x, y, 0, 0);
        }
    }
    
    static void xline(int x0, int y0, int x1, int y1, Graphics g, Color color) {
        boolean steep = false;
        
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
            if (steep) {
                g.drawRect(y, x, 0,0);
            } else {
                g.drawRect(x, y, 0,0);
            }
            error += derror;
            if (error>.5f) {
                y += (y1>y0?1:-1);
                error -= 1.;
            }
        }
    }
    
}
