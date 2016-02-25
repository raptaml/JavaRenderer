/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.raptaml.renderer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
        
        Obj obj = new Obj();
        ArrayList<String> vertStrings = new ArrayList<>();
        ArrayList<String> faceStrings = new ArrayList<>();
        String line;
        try {
            System.out.println(objFile.getEncoding());
            while ((line = reader.readLine()) != null)  {
                //System.out.println(line.charAt(2));
                if ((line.length() > 0) && line.charAt(0) == 'v' && line.charAt(1) == ' ') {
                    String[] splittedLine = line.split(" ");
                    for (int i = 1; i < splittedLine.length; i++)
                        vertStrings.add(splittedLine[i]);
                } else if ((line.length() > 0) && line.charAt(0) == 'f' && line.charAt(1) == ' ') {
                    
                }
                
                        
            }
            System.out.println(vertStrings);
            
        } catch (IOException ex) {
            Logger.getLogger(ObjParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public class Obj {
        public Obj() {
            
        }
    
        float vert[];
        int faces[][];
    }
}

