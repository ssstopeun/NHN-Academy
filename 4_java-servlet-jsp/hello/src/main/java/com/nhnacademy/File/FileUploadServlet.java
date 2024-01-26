package com.nhnacademy.File;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        //location 위치는 적절히 변경합니다.
        location = "C:\\Users\\User\\Desktop\\pre_project\\NHN-Academy\\3_java-dev-setting\\hello\\upload"
)
@WebServlet(name = "fileUploadServlet", urlPatterns = "/file/fileUpload")
@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\pre_project\\NHN-Academy\\3_java-dev-setting\\hello\\upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()){
            String contentDispostion = part.getHeader(CONTENT_DISPOSITION);

            if(contentDispostion.contains("filename=")){
                String fileName = extractFileName(contentDispostion);

                if(part.getSize()>0){
                    part.write(UPLOAD_DIR + File.separator+fileName);
                    part.delete();
                }
            }else{
                String formValue = req.getParameter(part.getName());
                log.error("{}={}",part.getName(),formValue);
            }
        }
        resp.sendRedirect("/fileUpload.html");
    }

    private String extractFileName(String contentDispostion) {
        log.error("contentDisposition:{}",contentDispostion);
        for(String token : contentDispostion.split(";")){
            if(token.trim().startsWith("filename")){
                return token.substring(token.indexOf("=")+2,token.length()-1);
            }
        }
        return null;
    }
}
