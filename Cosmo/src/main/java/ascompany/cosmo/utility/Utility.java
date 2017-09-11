/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author m.castano
 */
public class Utility 
{
    
    public static void writeJsonToFs(JsonObject j, String path) throws IOException
    {
        FileWriter fileWriter = null;
        try
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(j);
            fileWriter = new FileWriter(path);
            fileWriter.write(jsonString);
        }
        finally
        {
            if(fileWriter != null)
            {
                fileWriter.close();
            }
        }
    }
    
    public static JsonObject convertFileToJson(String filename)
    {   
        try
        {            
            BufferedReader br = new BufferedReader(new FileReader(filename));     
            if (br.readLine() == null) 
            {
                return null;
            }
            return new JsonParser().parse(new FileReader(filename)).getAsJsonObject();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public static String removeLastChar(String s)
    {
        return s.substring(0, s.length() - 1);
    }
}
