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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lemmer
 */
public class ObjParser {
    
    FileReader objFile;
    BufferedReader reader;
    Vector v;
    

    public ObjParser() {
        try {
            objFile = new FileReader("african_head.obj");
            reader = new BufferedReader(objFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjParser.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    void parseObj() {
        
        String line;
        try {
            System.out.println(objFile.getEncoding());
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                if (line.charAt(1) == 'v' && line.charAt(3) == ' ')
                    System.out.println(line);
            }
            
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

