/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.raptaml.renderer;

import de.raptaml.renderer.ObjParser.Obj;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
        //origin bottom left for LLO
        g.translate(0, height);
        //draw line between 0/0 and 10/10
        
       long start = System.nanoTime();
       for(int i = 1; i < 3000000; i++)
            line(0,0,100,50,g,Color.red);
            //xline(30,50,60,20,g,Color.blue);
            //xline(60,20,0,0,g,Color.yellow);
       long end = System.nanoTime();
       
        System.out.println("spent "+ (double)(end-start)/1000000l + " millis");
        System.out.println(-0);

        try {
            System.out.println("BITMAP WRITTEN " + ImageIO.write(img, "BMP", new File("d:\\test2.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //parse objfile
        ObjParser objParser = new ObjParser("african_head.txt");
        Obj obj = objParser.parseObjFile();
        
        // Draw Obj
        

    }
     
    static void line(int x0, int y0, int x1, int y1, Graphics g, Color color) {
        g.setColor(color);
        g.drawLine(x0, -y0, x1, -y1);
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
            //allways draw negative y to translate to LLO coodrdinates
            if (steep) {
                g.drawRect(y, -x, 0,0);
                //g.drawLine(y,-x,y,-x);
            } else {
                g.drawRect(x, -y, 0,0);
                //g.drawLine(x, -y, x, -y);
            }
            error += derror;
            if (error>.5f) {
                y += (y1>y0?1:-1);
                error -= 1.;
            }
        }
    }
    
}
