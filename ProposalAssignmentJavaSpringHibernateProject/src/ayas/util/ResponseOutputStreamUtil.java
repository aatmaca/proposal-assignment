/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import ayas.exception.ErrorReporter;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class ResponseOutputStreamUtil {

    public static ModelAndView writeByteArrayToResponse(HttpServletResponse response, byte[] byteArray, String filename, String fileExtension, String contentDisposition) {
        response.addHeader("Content-Disposition", contentDisposition + "; filename=" + filename + "." + fileExtension);
//        response.addHeader("Content-Disposition", "attachment; filename=" + filename + "." + fileExtension);
//        response.addHeader("Content-Disposition", "inline; filename=" + filename + "." + fileExtension);
        response.setContentLength(byteArray.length);
        response.setContentType("application/" + fileExtension);
        response.setDateHeader("Expires", 0);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        try {
        response.getOutputStream().write(byteArray);
        response.getOutputStream().flush();
        } catch (IOException e) {
            return ErrorReporter.showError("Sistemde hata oluştu.", true);
        }

        return null;
    }

    public static ModelAndView writeByteArrayToResponse(HttpServletResponse response, byte[] byteArray, String fileFullName, String contentDisposition) {

        int indexOfPoint = fileFullName.lastIndexOf(".");
        String fileNameWithoutExtension = fileFullName.substring(0, indexOfPoint);
        String fileExtension = fileFullName.substring(indexOfPoint + 1, fileFullName.length());

        return writeByteArrayToResponse(response, byteArray, fileNameWithoutExtension, fileExtension, contentDisposition);
    }
}