/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    /**
     * Get the Collator for Turkey Turkish and set its strength to PRIMARY(String sıralamada kullanılıyor.)
     * trCollator.setStrength(Collator.PRIMARY);  metodu da gerekirse kullanılabilir.
     */
    public static final Collator trCollator = Collator.getInstance(new Locale("tr"));
    /**
     * Formatters
     */
    public static final SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
   /**
    * Email
    */
    public static final String PROJECT_EMAIL_ADDRESS = "";
    public static final String EXCEPTION_SUBJECT_TEXT = "Exception Occured";
}