package ascompany.cosmo.generatorCore;

import java.util.ArrayList;

/**
 *
 * @author m.castano
 */
public class ModelHelper 
{
    /**
     * Nome della tabella corrispondente alla classe
     */
    public String nomeTabella = "";
    
    /**
     * Codice sorgente della classe
     */
    public String classString = "";
    
    /**
     * Codice sorgente del package
     */
    public String packageString = "";
    
    /**
     * Codice sorgente degli import
     */
    public String importString = "";
    
    /**
     * Codice sorgente del modificatore d'accesso
     */
    public String accessModeString = "";
    
    /**
     * Codice sorgente delle chiavi
     */
    public String withKeyString = "";
    
    /**
     * Codice sorgente del tipo di ritorno
     */
    public String returningString = "";
    
    /**
     * Codice sorgente del nome della classe
     */
    public String classNameString = "";
    
    /**
     * Codice sorgente della lista dei camp
     */
    public String parametersString = "";
    
    /**
     * Codice sorgente dei getter
     */
    public String getterString = "";
    
    /**
     * Codice sorgente dei setter
     */
    public String setterString = "";
    
    /*
    * Codice sorgente dei getter
    */
    public String getter = "";
    
    /*
    * Codice sorgente dei setter
    */
    public String setter = "";
    
    /**
     * Codice sorgente dei metodi aggiunti
     */
    public String methods = "";
        
    /**
     * 
     * @param pkg package della classe
     * @return Istanza di classe
     */
    public ModelHelper toPackage(String pkg)
    {
        this.packageString = "package " + pkg +";";
       //addDependencies();
        return this;
    }
    
    /**
     * 
     * @param mode modificatore d'accesso della classe
     * @return Istanza di classe
     */
    public ModelHelper withAccessMode(String mode)
    {
        this.accessModeString += mode + " ";
        return this;
    }
    
    /**
     * 
     * @param key lista di chiavi
     * @return Istanza di classe
     */
    public ModelHelper withKey(String ...key)
    {
        for(String k : key)
        {
            this.withKeyString += k + " ";
        }       
        return this;
    }
    
    /**
     * 
     * @param type tipo di ritorno
     * @return Istanza di classe
     */
    public ModelHelper returning(String type)
    {
        this.returningString += type + " ";
        return this;
    }
    
    /**
     * 
     * @param name nome della classe
     * @return Istanza di classe
     */
    public ModelHelper className(String name)
    {
        this.nomeTabella = name;
        this.classNameString += name + newLine(1) + openBracket();
        return this;
    }
    
    
    /**
     * 
     * @return Istanza di classe
     */
    public ModelHelper addGetter()
    {
        this.getterString += this.getter;
        return this;
    }
    
    /**
     * 
     * @return Istanza di classe
     */
    public ModelHelper addSetter()
    {
        this.setterString += this.setter;
        return this;
    }
    
    /**
     * 
     * @return Istanza di classe
     */
    public ModelHelper addGetterAndSetter()
    {
        addGetter();
        addSetter();
        return this;
    }
    
    public ModelHelper addMethods(ArrayList<Method> methods)
    {
        for(Method m : methods)
        {
            this.methods += 
                this.tab(1) + m.access +" static "+ m.returning +" "+ m.name+"()" + 
                newLine(1)  +
                this.tab(1) + openBracket()+
                this.tab(2) + m.body +
                newLine(1)  +
                this.tab(1) + closeBracket()+
                newLine(1);
        }
        
        return this;
    }
    
    /**
     * 
     * @return Istanza di classe
     * @throws Exception 
     */
    public ModelHelper terminate() throws Exception
    {
        this.classString = 
        this.packageString + newLine(2) +
        this.importString + newLine(1) +
                
        this.accessModeString + this.withKeyString + this.returningString + this.classNameString +
        
        this.parametersString +
        this.getterString +
        this.setterString +
        this.newLine(1) +
        this.methods +
                
        this.classString + newLine(1) + closeBracket();
        
        //creaFileJava(nomeTabella, classString, path);
        return this;
    }
    
    /**
     * 
     * @return parentesi aperta
     */
    public String openBracket()
    {
        return "{\n";
    }
    
    /**
     * 
     * @return parentesi chiusa
     */
    public String closeBracket()
    {
        return "}\n";
    }
    
    /**
     * 
     * @param n numero di nuove linee
     * @return nuova linea
     */
    public String newLine(int n)
    {
        String s = "";
        for(int i = 0; i < n; i++)
        {
            s += "\n";
        }
        return s;
    }
    
    /**
     * 
     * @param n numero di tab
     * @return tab
     */
    public String tab(int n)
    {
        String s = "";
        for(int i = 0; i < n; i++)
        {
            s += "    ";
        }
        return s;
    }
    
    
    
}
