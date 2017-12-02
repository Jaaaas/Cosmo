package ascompany.cosmo.generatorCore;

import ascompany.cosmo.configuration.ConfigName;
import static ascompany.cosmo.utility.Utility.capitalizeFirstLetter;
import static ascompany.cosmo.utility.Utility.creaFileJava;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcocastano
 */
public class GenerateQuery
{
    public static void Create(String basePath, JsonObject cosmoQuery, boolean staticMethods) throws Exception
    {
        HashMap<String,ArrayList<JsonObject>> hm = new HashMap<>();
        JsonArray listQuery = cosmoQuery.get(ConfigName.TO_CREATE).getAsJsonArray();
        
        if(listQuery.size() > 0)
        {
            for(JsonElement je : listQuery)
            {
                JsonObject jo = je.getAsJsonObject();
                
                if(!jo.get(ConfigName.PKG_TO_CLASS).getAsString().equals(""))
                {
                    if(!hm.containsKey(jo.get(ConfigName.PKG_TO_CLASS).getAsString()))
                    {
                        hm.put(jo.get(ConfigName.PKG_TO_CLASS).getAsString(), new ArrayList<JsonObject>(Arrays.asList(jo)));
                    }
                    else
                    {
                        ArrayList<JsonObject> list = hm.get(jo.get(ConfigName.PKG_TO_CLASS).getAsString());
                        list.add(jo);
                    }
                }
                
            }
            
            if(hm.isEmpty())
            {
                System.out.println("No query to create");
            }
            else
            {
                for(Map.Entry<String, ArrayList<JsonObject>> map : hm.entrySet()) 
                {
                    String pckToClass = map.getKey();
                    
                    String className = pckToClass.substring(pckToClass.lastIndexOf('/') + 1).trim();
                    
                    int lastIndexOf = pckToClass.lastIndexOf('/');
                    String pkg = pckToClass.substring(0,lastIndexOf);
                    
                    ArrayList<JsonObject> listaQuery = map.getValue();
                    ArrayList<Method> listaMetodi = new ArrayList<>();
                    
                    for(JsonObject jo : listaQuery)
                    {
                        listaMetodi.add(new Method(ConfigName.PUBLIC + (staticMethods ? " "+ConfigName.STATIC : ""),ConfigName.STRING,jo.get(ConfigName.METHOD_NAME).getAsString(),"return \""+ jo.get(ConfigName.QUERY).getAsString() +"\";"));
                    }
                    
                    if(className.indexOf('.') == -1)
                    {
                        throw new Exception("Mission path to 'File.java' in 'packageToClass'");
                    }
                    ModelHelper m = new ModelHelper()
                        .toPackage(pkg.replace('/','.'))
                        .withAccessMode(ConfigName.PUBLIC)
                        .withKey(ConfigName.CLASS)
                        .className(capitalizeFirstLetter(className.substring(0, className.indexOf('.'))))
                        .addMethods(listaMetodi)
                        .terminate();
                    
                    File fileToRecreate = new File(basePath + pkg);
                    fileToRecreate.delete();
                    
                    creaFileJava(className.substring(0, className.indexOf('.')), m.classString , fileToRecreate.getAbsolutePath());
                    
                }
            }
        }
        else
        {
            System.out.println("Empty CosmoGen 'toCreate' ");
        }
    }
}
