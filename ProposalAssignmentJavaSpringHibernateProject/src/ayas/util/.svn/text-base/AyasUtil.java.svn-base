/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abdullah
 */
public class AyasUtil {

    public static boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("sessionData") == null) {
            return false;
        }

        if (!((SessionData) session.getAttribute("sessionData")).getSessionID().equals(request.getSession().getId())) {
            return false;
        }

        return true;
    }

    public static SessionData getSessionData(HttpServletRequest request) {
        return (SessionData) request.getSession().getAttribute("sessionData");
    }

    public static List arrayToList(Object[] panelistArray) {
        List list = new ArrayList();

        for (int i = 0; i < panelistArray.length; i++) {
            list.add(panelistArray[i]);
        }

        return list;
    }

    public static Set arrayToSet(Object[] panelistArray) {
        Set set = new HashSet();

        for (int i = 0; i < panelistArray.length; i++) {
            set.add(panelistArray[i]);
        }

        return set;
    }

    public static List setToList(Set set) {
        List list = new ArrayList();

        for (Iterator it = set.iterator(); it.hasNext();) {
            Object object = it.next();
            list.add(object);
        }

        return list;
    }

    public static List fillList(Object[] array, List list) {

        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        return list;
    }

    public static String getStringValue(String value) {
        return value != null ? value : "";
    }

    public static Double getDoubleZeroIfNull(Double value) {
        return value != null ? value : 0.0;
    }

    public static String getEmptyStringIfNull(String str) {
        if (str == null) {
            return "";
        }

        return str;
    }
}
