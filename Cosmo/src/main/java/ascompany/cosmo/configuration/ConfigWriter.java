/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.configuration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author marcocastano
 */
public class ConfigWriter
{
    public static JsonObject ConfigWriter()
    {
        JsonObject configJson = new JsonObject();
        
        JsonArray listaQuery = new JsonArray();
        
        JsonObject query = new JsonObject();
        query.addProperty(ConfigName.PATH_TO_CLASS, "");
        query.addProperty(ConfigName.METHOD_NAME, "");
        query.addProperty(ConfigName.QUERY, "");
        
        
        listaQuery.add(query);
        
        configJson.add(ConfigName.TO_CREATE, listaQuery);
        
        return configJson;
    }
}
