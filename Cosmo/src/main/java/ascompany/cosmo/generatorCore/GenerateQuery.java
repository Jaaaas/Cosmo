/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.generatorCore;

import ascompany.cosmo.configuration.ConfigName;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcocastano
 */
public class GenerateQuery
{
    public static void Create(JsonObject cosmoQuery)
    {
        HashMap<String,JsonObject> hm = new HashMap<>();
        JsonArray listQuery = cosmoQuery.get(ConfigName.TO_CREATE).getAsJsonArray();
        
        if(listQuery.size() > 0)
        {
            for(JsonElement je : listQuery)
            {
                JsonObject jo = je.getAsJsonObject();
                
                if(!jo.get(ConfigName.PATH_TO_CLASS).getAsString().equals("") && !hm.containsKey(jo.get(ConfigName.PATH_TO_CLASS).getAsString()))
                {
                    hm.put(jo.get(ConfigName.PATH_TO_CLASS).getAsString(), jo);
                }
            }
            
            if(hm.isEmpty())
            {
                System.out.println("No query to create");
            }
            else
            {
                for(Map.Entry<String, JsonObject> map : hm.entrySet()) 
                {
                    File pathClasse = new File(map.getKey());
                    String pkg = pathClasse.getParent().substring(pathClasse.getParentFile().toString().lastIndexOf('/') + 1).trim();
                    System.out.println("Package");
                    System.out.println(pkg);
                    
                    //pathClasse.delete();
                    
                    System.out.println("parente");
                    System.out.println(pathClasse.getParent());
                }
            }
            
        }
        else
        {
            System.out.println("Empty CosmoGen 'toCreate' ");
        }
    }
}
