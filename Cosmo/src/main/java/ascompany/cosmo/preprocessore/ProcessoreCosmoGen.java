package ascompany.cosmo.preprocessore;

import ascompany.cosmo.annotazioni.CosmoGen;
import ascompany.cosmo.configuration.ConfigWriter;
import ascompany.cosmo.generatorCore.GenerateQuery;
import ascompany.cosmo.utility.Utility;
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

                File file = new File(cosmoGen.basePath() + cosmoGen.pkgToConfig()+ cosmoGen.fileName()+".json");
                if(file.exists() && !file.isDirectory()) 
                {
                    GenerateQuery.Create(cosmoGen.basePath(), Utility.convertFileToJson(file.getAbsolutePath()), cosmoGen.staticMethods());
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
