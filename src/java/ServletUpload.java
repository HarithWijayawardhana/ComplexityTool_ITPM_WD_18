/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harith
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ServletUpload extends HttpServlet {

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    public int wkw = 0;
    public int wkwe = 0;
    public int nid = 0;
    public int nop = 0;
    public int Nnv = 0;
    public int nsl = 0;

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

        Map<String, Integer> keywords = new LinkedHashMap<String, Integer>();

        Map<String, Integer> identifiers = new LinkedHashMap<String, Integer>();

        Map<String, Integer> operators = new LinkedHashMap<String, Integer>();

        Map<String, Integer> numerics = new LinkedHashMap<String, Integer>();

        Map<String, Integer> stringLiterals = new LinkedHashMap<String, Integer>();

        int j = 0;

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);

            System.out.println(fileName);

            // Start reading file
            try {
                FileReader fr = new FileReader(savePath + File.separator + fileName);   //reads the file  
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
                String line;
                // Read line by line

                String filePath = savePath + j + ".txt";
                FileWriter writer = new FileWriter(filePath);

                while ((line = br.readLine()) != null) {
                    sb.append(line);      //appends line to string buffer 

                    writer.write(line);
                    
                    writer.append("\n");

                    wkw = calculateLineKeywords(line);

                    nid = calculateLineIdentifiers(line);

                    nop = calculateLineOperators(line);

                    Nnv = calculateLineNumeric(line);

                    nsl = calculateLineStrings(line);

                    keywords.put(line, wkw);

                    identifiers.put(line, nid);

                    operators.put(line, nop);

                    numerics.put(line, Nnv);

                    stringLiterals.put(line, nsl);

                    sb.append("\n");     //line feed  

                    

                }
                writer.close();
                System.out.println(filePath);
                j++;

//                for (String i : keywords.keySet()) {
//                    System.out.println("key: " + i + " value: " + keywords.get(i));
//                }
                fr.close();    //closes the stream and release the resources  
//                System.out.println("Contents of File: ");  
//                System.out.println(sb.toString());   //returns a string that textually represents the object  

                

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("message", keywords);
        request.setAttribute("identifiers", identifiers);
        request.setAttribute("operators", operators);
        request.setAttribute("numerics", numerics);
        request.setAttribute("strings", stringLiterals);

        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }

    public static int calculateLineKeywords(String line) {
        int score = 0;
        String[] wkw = {"private", "public", "static", "class", "void", "request", "protected", "extends", "implements"};

        for (int i = 0; i < wkw.length; i++) {
            if (line.contains(wkw[i])) {
                score++;
            }
        }

        return score;
    }

    public static int calculateLineIdentifiers(String line) {
        int score = 0;

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        String str = line;

        if (line.matches("(.*)for(.*)")) {
            score = score + 4;
        } else if (line.matches("(.*)System.out(.*)")) {
            score = score + 3;
        } else if (m.find()) {
            score++;
        }

        return score;
    }

    public static int calculateLineOperators(String line) {
        int score = 0;
        String[] nop = {"+", "-", "*", "/", "%", "++", "--", "==", "!=", ">", "<", ">=", "<=", "&&", "||", "!"};

        String str = line;

        for (int i = 0; i < nop.length; i++) {
            if (line.contains(nop[i])) {
                if (!str.startsWith("import")) {
                    score++;
                }
            }
        }
        return score;
    }

    public static int calculateLineNumeric(String line) {
        int score = 0;

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        while (m.find()) {
            score++;
        }

        return score;
    }

    public static int calculateLineStrings(String line) {
        int score = 0;
        String[] nop = {"\""};

        for (int i = 0; i < nop.length; i++) {
            if (line.contains(nop[i])) {
                score++;
            }
        }
        return score;
    }

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
