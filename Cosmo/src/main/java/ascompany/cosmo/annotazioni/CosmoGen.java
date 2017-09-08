/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    String path();
}
