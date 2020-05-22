/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static ServletUpload.calculateLineIdentifiers;
//import static ServletUpload.calculateLineKeywords;
//import static ServletUpload.calculateLineNumeric;
//import static ServletUpload.calculateLineOperators;
//import static ServletUpload.calculateLineStrings;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Harith
 */
@WebServlet(urlPatterns = {"/multipleFileUpload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class multipleFileUpload extends HttpServlet {

    private static final String SAVE_eDIR = "upload";

    final int BUFFER_SIZE = 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_eDIR;
        
        
        unzip(savePath, appPath);

        Enumeration entries;
        ZipFile zipFile;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();

        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);

            // Start reading file
            try {
                FileReader fr = new FileReader(savePath + File.separator + fileName);   //reads the file  
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
                String line;

//                zipFile = new ZipFile(savePath + File.separator + fileName);

//                entries = zipFile.entries();

                System.out.println("Hello");

//                while (entries.hasMoreElements()) {
//
//                    ZipEntry entry = (ZipEntry) entries.nextElement();
//
//                    if (entry.isDirectory()) {
//                        // Assume directories are stored parents first then children.
//                        System.out.println("Extracting directory: " + entry.getName());
//                        // This is not robust, just for demonstration purposes.
//                        (new File(entry.getName())).mkdir();
//                        continue;
//                    }
//
//                    System.out.println("Extracting file: " + entry.getName());
//
////                while ((line = br.readLine()) != null) {
////                    sb.append(line);      //appends line to string buffer 
////
////                    wkw = calculateLineKeywords(line);
////
////                    nid = calculateLineIdentifiers(line);
////
////                    nop = calculateLineOperators(line);
////
////                    Nnv = calculateLineNumeric(line);
////
////                    nsl = calculateLineStrings(line);
////
////                    keywords.put(line, wkw);
////
////                    identifiers.put(line, nid);
////
////                    operators.put(line, nop);
////
////                    numerics.put(line, Nnv);
////
////                    stringLiterals.put(line, nsl);
////
////                    sb.append("\n");     //line feed   
////                }
////                for (String i : keywords.keySet()) {
////                    System.out.println("key: " + i + " value: " + keywords.get(i));
////                }
//                    fr.close();
////closes the stream and release the resources  
////                System.out.println("Contents of File: ");  
////                System.out.println(sb.toString());   //returns a string that textually represents the object  
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //get the zip file content
    }// </editor-fold>

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
