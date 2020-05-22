/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/InheritanceServletUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class InheritanceServletUpload extends HttpServlet {

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    public int var = 0;

    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        int j = 0;

        Map<String, Integer> score = new LinkedHashMap<String, Integer>();

         String fileName = "";
        
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
            System.out.println(fileName);
            request.setAttribute("name", fileName);

            String filePath = savePath + j + ".txt";
            FileWriter writer = new FileWriter(filePath);
            // Start reading file
            try {
                FileReader fr = new FileReader(savePath + File.separator + fileName);   //reads the file  
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
                String line;
                // Read line by line
                while ((line = br.readLine()) != null) {
                    sb.append(line);      //appends line to string buffer 

                    writer.write(line);

                    writer.append("\n");

                    var = calculateDirectInScore(line);

                    score.put(line, var);

                    sb.append("\n");     //line feed   
                }

//                for (String i : score.keySet()) {
//                   System.out.println("key: " + i + " value: " + score.get(i));
//                }
                fr.close();    //closes the stream and release the resources  

                writer.close();
                System.out.println(filePath);
//                System.out.println("Contents of File: ");  
//                System.out.println(sb.toString());   //returns a string that textually represents the object  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(savePath + File.separator + fileName);

        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
        
        

        request.setAttribute("message", score);
//       request.setAttribute("fileName", fileName);

        getServletContext().getRequestDispatcher("/inheritanceMessage.jsp").forward(
                request, response);
    }

//    public static int calculateClass(String line){
//        int classcnt = 0;
//        String[] wkw ={"class"};
//       for (int i = 0; i < wkw.length; i++) {
//            if (line.contains(wkw[i])) {
//                classcnt++;
//            }
//        }
//       if(classcnt == 0){
//           int totint = 0;
//           return totint;
//       }
//        return classcnt;
//    }
    public static int calculateDirectInScore(String line) {

        int score = 0;
        String[] wkw = {"extends"};

        for (int i = 0; i < wkw.length; i++) {
            if (line.contains(wkw[i])) {
                score++;
            }
        }

        if (line.matches("(.*)class(.*)") && !line.matches("(.*)extends(.*)")) {

            String[] arrOfStr = line.split("\\W+");

            System.out.println(arrOfStr[0]);

            for (String a : arrOfStr) {
                System.out.println(a);

            }

        }

        return score;
    }

//    public static int calculateLineScore(String line) {
//        int score = 0;
//        String[] wkw = { "extends"};
//
//        for (int i = 0; i < wkw.length; i++) {
//            if (line.contains(wkw[i])) {
//                score++;
//            }
//        }
//        return score;
//    }
    /**
     * Extracts file name from HTTP header content-disposition
     */
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
