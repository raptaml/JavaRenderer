/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.raptaml.renderer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lemmer
 */
public class ObjParser {
    
    FileReader objFile;
    BufferedReader reader;
    

    public ObjParser() {
        try {
            objFile = new FileReader("african_head.txt");
            reader = new BufferedReader(objFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjParser.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    void parseObj() {
        
        Obj obj;
        ArrayList<String> vertStrings = new ArrayList<>();
        ArrayList<String> faceStrings = new ArrayList<>();
        int totalVerts =0;
        int totalFaces = 0;
        String line;
        
        long then = System.currentTimeMillis();
        try {
            System.out.println(objFile.getEncoding());
            while ((line = reader.readLine()) != null)  {
                if ((line.length() > 0) && line.charAt(0) == 'v' && line.charAt(1) == ' ') {
                    String[] splittedLine = line.split(" ");
                    for (int i = 1; i < splittedLine.length; i++)
                        vertStrings.add(splittedLine[i]);
                } else if ((line.length() > 0) && line.charAt(0) == 'f' && line.charAt(1) == ' ') {
                    String[] splittedLine = line.split("f");
                    faceStrings.add(splittedLine[1].trim());
                } else if ((line.length() > 0) && (line.startsWith("#")) && line.contains("vertices")) {
                    if (!line.contains("texture"))
                        totalVerts = Integer.valueOf(line.split(" ")[1]);           
                } else if ((line.length() > 0) && (line.startsWith("#")) && line.contains("faces")) {
                    totalFaces = Integer.valueOf(line.split(" ")[1]);        
                }      
            }
            
            long now = System.currentTimeMillis();

            System.out.println(now - then);
          
            //build vert and face array from splitted strs
            obj = new Obj(totalVerts, totalFaces);
            int n = 0;
            for (float[] vert : obj.verts) {
                for (int j = 0; j < 3; j++) {
                    vert[j] = Float.valueOf(vertStrings.get(n));  
                    n++;
                }
            }
            n= 0;
            System.out.println(obj.faces.length +" "+faceStrings.size());
            for (int[] face : obj.faces) {
                for (String faceString :faceStrings.get(n).split(" ")) {
                    for (int j = 0; j < 3; j++) {
                        face[j] = Integer.valueOf(faceString.split("/")[j]);
                    //face[j] = Integer.valueOf(faceStrings.get(n).split("/")[j*3].trim());  
                    }
                n++;
                if (n==2493)
                    System.out.println(n);
                }
            }
            //DBG
            for (int[] face : obj.faces) {
                System.out.println(Arrays.toString(face));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ObjParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public class Obj {
        
        float verts[][];
        int faces[][];
        
        public Obj(int vertCount, int faceCount) {
            this.verts = new float[vertCount][3];
            this.faces = new int[faceCount][3];
        }
    

    }
}

