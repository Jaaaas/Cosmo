package ascompany.cosmo.annotazioni;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author m.castano
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CosmoGen 
{
    /**
     * 
     * @return percorso dove verranno creati i metodi
     */
    String pkgToConfig();
    String basePath();
    String fileName();
}
