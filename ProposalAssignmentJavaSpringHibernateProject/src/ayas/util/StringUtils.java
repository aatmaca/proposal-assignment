/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import java.util.Locale;

public class StringUtils {

    public static String toUpperCase(String str, Locale l) {
        return str.toUpperCase(l);
    }

    public static String convertTurkishCharToEnglish(String str) {
        StringBuffer strBuffer = new StringBuffer(str);
        for (int i = 0; i < str.length(); i++) {
            if (strBuffer.charAt(i) == 'ı') {
                strBuffer.setCharAt(i, 'i');
            } else if (strBuffer.charAt(i) == 'İ') {
                strBuffer.setCharAt(i, 'I');
            } else if (strBuffer.charAt(i) == 'ş') {
                strBuffer.setCharAt(i, 's');
            } else if (strBuffer.charAt(i) == 'Ş') {
                strBuffer.setCharAt(i, 'S');
            } else if (strBuffer.charAt(i) == 'ü') {
                strBuffer.setCharAt(i, 'u');
            } else if (strBuffer.charAt(i) == 'Ü') {
                strBuffer.setCharAt(i, 'U');
            } else if (strBuffer.charAt(i) == 'ö') {
                strBuffer.setCharAt(i, 'o');
            } else if (strBuffer.charAt(i) == 'Ö') {
                strBuffer.setCharAt(i, 'O');
            } else if (strBuffer.charAt(i) == 'ğ') {
                strBuffer.setCharAt(i, 'g');
            } else if (strBuffer.charAt(i) == 'Ğ') {
                strBuffer.setCharAt(i, 'G');
            } else if (strBuffer.charAt(i) == 'ç') {
                strBuffer.setCharAt(i, 'c');
            } else if (strBuffer.charAt(i) == 'Ç') {
                strBuffer.setCharAt(i, 'C');
            }
        }
        return strBuffer.toString();
    }
}