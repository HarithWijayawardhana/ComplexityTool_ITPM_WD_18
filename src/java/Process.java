/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author MUNZIR
 */
@WebServlet(name = "Process", urlPatterns = {"/Process"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB



public class Process extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";
    public int var = 0;
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String appPath = request.getServletContext().getRealPath("");
        
        String savePath = appPath + File.separator + SAVE_DIR;
        
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Map<String, Integer> score = new LinkedHashMap<String, Integer>();

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
                // Read line by line
                while ((line = br.readLine()) != null) {
                    sb.append(line);      //appends line to string buffer 

                    var = calculateWightControlS(line);

                    score.put(line, var);

                    sb.append("\n");     //line feed   
                }

                for (String i : score.keySet()) {
                    System.out.println("key: " + i + " value: " + score.get(i));
                }

                fr.close();    //closes the stream and release the resources  
//                System.out.println("Contents of File: ");  
//                System.out.println(sb.toString());   //returns a string that textually represents the object  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("pass", score);
        
        getServletContext().getRequestDispatcher("/pass.jsp").forward(
                request, response);
    }

   /* public static int calculateLineScore(String line) {
        int score = 0;
        String[] wkw = {"private", "public", "static", "class", "void", "request", "protected"};

        for (int i = 0; i < wkw.length; i++) {
            if (line.contains(wkw[i])) {
                score++;
            }
        }
        return score;  
    }
*/
    public static int calculateWightControlS(String line) {
        int score = 0;
        String[] Wtcs = {"if", "if-else","else", "while", "do-while", "switch", "case"};

        for (int i = 0; i < Wtcs.length; i++) {
            if (line.contains(Wtcs[i])) {
                score++;
            }
        }
        return score;  
    }
    
     public static int calculateComplex(String line) {
        int c = 0;
        String[] Ccs = {};

        for (int i = 0; i < Ccs.length; i++) {
            if (line.contains(Ccs[i])) {
                c++;
            }
        }
        return c;  
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
