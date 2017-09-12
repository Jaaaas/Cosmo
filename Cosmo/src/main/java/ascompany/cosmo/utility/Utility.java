package ascompany.cosmo.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
    
    /**
     * Funzione utilizzata per creare il file .java
     * 
     * @param nomeClasse nome del file da dover creare
     * @param source stringa contenente il codice sorgente
     * @param path percorso di creazioen
     * @throws Exception 
     */
    public static void creaFileJava(String nomeClasse, String source, String path) throws Exception
    {
        File root = new File(path);
        File sourceFile = new File(root, "/"+nomeClasse+".java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Funzione che Capitalizza la prima lettera di una parola
     * 
     * @param input stringa di cui bisgona capitalizzare la prima lettera
     * @return stringa capitalizzata
     */
    public static String capitalizeFirstLetter(String input)
    {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    
}
