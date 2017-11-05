package ayas.exception;

import ayas.util.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public class ErrorReporter {

    public ErrorReporter() {
    }

    public static ModelAndView showError(String errorText) {
        return showError(errorText, false);
    }

    public static ModelAndView showError(String errorText, boolean closePage) {
        Map map = new HashMap();
        map.put("error", errorText);
        if (closePage) {
            map.put("closePage", "closePage");
        }
        return new ModelAndView("error/Error", map);
    }

    public Exception reportError(HttpServletRequest request) {

        Exception ex = (Exception) request.getAttribute("exception");

        String userName = null;

        SessionData sessionData = AyasUtil.getSessionData(request);
        if (sessionData != null && sessionData.getSessionUser() != null) {
            userName = sessionData.getSessionUser().getFullName();
        }



        String text = "Kullanıcı: " + userName + "\n\n";
        text += "Hata kaynağı (IP): " + request.getRemoteAddr() + "\n\n";
        text += "Root Hata Mesajı: " + getExceptionRootCause(ex) + "\n\n";
        text += "Hata Mesajı: " + ex.getMessage() + "\n\n";
        text += "Hata Detayı: \n\n";

        StackTraceElement[] ste = ex.getStackTrace();
        String stackTraceString = "";
        for (int i = 0; i < ste.length; i++) {
            stackTraceString += ste[i].toString() + "\n";
        }
        text += stackTraceString;

        EmailUtil.sendMail(Constants.PROJECT_EMAIL_ADDRESS, Constants.PROJECT_EMAIL_ADDRESS,
                Constants.EXCEPTION_SUBJECT_TEXT, text);

        return ex;
    }

    public static Throwable getExceptionRootCause(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause == null) {
            return ex;
        }
        return getExceptionRootCause(cause);
    }
}