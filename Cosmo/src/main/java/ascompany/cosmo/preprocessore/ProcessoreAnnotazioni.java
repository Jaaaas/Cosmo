/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.preprocessore;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author m.castano
 */
@SupportedAnnotationTypes("ascompany.cosmo.annotazioni.CreateQuery")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ProcessoreAnnotazioni extends AbstractProcessor
{
    /**
     * Costruttore
     */
    public ProcessoreAnnotazioni() 
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
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment re) 
    {
        return true;
    }
    
}
