package ascompany.cosmo.generatorCore;

/**
 *
 * @author m.castano
 */
public class Method 
{
    public String access = "";
    
    public String returning = "";
    
    public String name = "";
    
    public String body = "";

    public Method(String access, String returning, String name, String body) 
    {
        this.access = access;
        this.returning = returning;
        this.name = name;
        this.body = body;
    }
    
    public String getAccess() 
    {
        return access;
    }

    public void setAccess(String access) 
    {
        this.access = access;
    }

    public String getReturning() 
    {
        return returning;
    }

    public void setReturning(String returning)
    {
        this.returning = returning;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getBody() 
    {
        return body;
    }

    public void setBody(String body) 
    {
        this.body = body;
    }
      
}
