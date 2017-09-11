/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.preprocessore;

import ascompany.cosmo.annotazioni.CosmoGen;
import ascompany.cosmo.configuration.ConfigName;
import ascompany.cosmo.configuration.ConfigWriter;
import ascompany.cosmo.generatorCore.GenerateQuery;
import ascompany.cosmo.utility.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author m.castano
 */
@SupportedAnnotationTypes("ascompany.cosmo.annotazioni.CosmoGen")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ProcessoreCosmoGen extends AbstractProcessor
{
    /**
     * Costruttore
     */
    public ProcessoreCosmoGen() 
    {
        super();
    }
    
    /**
     * 
     * @param set
     * @param re
     * @return 
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) 
    {   
        try
        {
            for (Element elem : roundEnv.getElementsAnnotatedWith(CosmoGen.class)) 
            {
                CosmoGen cosmoGen = elem.getAnnotation(CosmoGen.class);

                File file = new File(cosmoGen.path() + ConfigName.JSON_NAME);
                if(file.exists() && !file.isDirectory()) 
                {
                    GenerateQuery.Create(Utility.convertFileToJson(file.getAbsolutePath()));
                    
                }
                else
                {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    Utility.writeJsonToFs(ConfigWriter.ConfigWriter(),file.getAbsolutePath());
                }
            }   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        
        return true;
    }
}
