/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascompany.cosmo.preprocessore;

import ascompany.cosmo.annotazioni.CosmoGen;
import ascompany.cosmo.annotazioni.CreateQuery;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

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
            if(roundEnv.getElementsAnnotatedWith(CosmoGen.class).size() <= 1)
            {
                for (Element elem : roundEnv.getElementsAnnotatedWith(CosmoGen.class)) 
                {
                    CosmoGen cosmoGen = elem.getAnnotation(CosmoGen.class);
                    String basePath = cosmoGen.path();
                    
                    // Compile source file.
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    File root = new File("/java");
                    File sourceFile = new File("C:\\Users\\m.castano\\Documents\\NetBeansProjects\\Cosmo\\CosmoTest\\src\\CosmoQuery\\CosmoGenerator.java");
                    compiler.run(null, null, null, sourceFile.getPath());

                    // Load and instantiate compiled class.
                    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
                    Class<?> cls = Class.forName("CosmoQuery.CosmoGenerator", true, classLoader); 
                    Object instance = cls.newInstance();
                    System.out.println(instance);
                    
                    
                    Class<?> classAnnotated = Class.forName(elem.getSimpleName().toString());
                    System.out.println("classe annotata");
                    System.out.println(classAnnotated);
                    final List<Method> methodsDeclared = new ArrayList<Method>(Arrays.asList(classAnnotated.getDeclaredMethods()));
                    
                    for(Method method : methodsDeclared)
                    {
                        System.out.println(method.getName());
                        if(method.isAnnotationPresent(CreateQuery.class))
                        {
                            System.out.println("Metodo trovato con annotazione: ");
                            System.out.println(method.getName());
                        }
                        else
                        {
                            throw new Exception("Method " +method.getName()+ " has not CreateQuery annotation.");
                        }
                    }
                    
                }
            }
            else
            {
                throw new Exception("Multiple CosmoGen annotation found.");
            }
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return true;
    }
}
