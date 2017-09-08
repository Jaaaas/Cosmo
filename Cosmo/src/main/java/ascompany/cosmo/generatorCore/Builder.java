package ascompany.cosmo.generatorCore;

import ascompany.cosmo.configuration.ConfigName;
import ascompany.cosmo.configuration.QueryType;
import static ascompany.cosmo.utility.Utility.removeLastChar;
import java.util.ArrayList;

/**
 *
 * @author m.castano
 */
public class Builder 
{
    /**
     * Query che dovrà essere ritornata
     */
    String finalQuery = "";
    
    /**
     * Nome del metodo che deve essere creato
     */
    String nomeFunzione = "";
    
    /**
     * Nome dell'ultima tabella da cui si stanno ricavando i dati
     */
    String lastSelectTable = "";
    
    /**
     * Stringa della select
     */
    String select = "";
    
    /**
     * Stringa del from
     */
    String from = "";
    
    /**
     * Lista delle tabelle che vengono utilizzate
     */
    ArrayList<String> tableUsed = new ArrayList<>();
    
    
    
    /**
     * Tipo di query che deve essere creata
     */
    QueryType queryType;
    
    /**
     * Costruttore
     */
    public Builder() 
    {
    
    }
    
    public Builder(QueryType queryType) 
    {
        this.select = queryType.toString() + " ";
    }
    
    
    /**
     * Funzione che setta il nome della funzione
     * @param nomeFunzione il nome della funzione
     * @return oggetto d'istanza
     */
    public Builder withName(String nomeFunzione)
    {
        this.nomeFunzione = nomeFunzione;
        return this;
    }
    
    /**
     * Funzione che aiuta a creare la select
     * @param c classe della tabella corrispondente
     * @return 
     */
    public Builder from(Class c)
    {
        this.lastSelectTable = c.getSimpleName();
        this.tableUsed.add(c.getSimpleName());
        
        return this;
    }
    
    /**
     * Funzione che aiuta a creare la select
     * @param list lista di stringhe della select
     * @return 
     */
    public Builder extract(String... list)
    {
        for(String s : list)
        {
            this.select += this.lastSelectTable +"."+ s +",";
        }
        return this;
    }
    
    /**
     * Funzione che ritorna la stringa finale
     * @return 
     */
    public String generate()
    {
        this.from = ConfigName.FROM  +" ";
        for(String s : this.tableUsed)
        {
            this.from += s +",";
        }
        
        this.finalQuery = removeLastChar(this.select) +" "+ removeLastChar(this.from);
        
        return this.finalQuery;
    }
}
