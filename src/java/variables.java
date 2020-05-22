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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class variables extends HttpServlet {

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    public int Wvs = 0;
    public int Npdtv = 0;
    public int Ncdtv = 0;
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

        Map<String, Integer> primitive = new LinkedHashMap<String, Integer>();

        Map<String, Integer> composite = new LinkedHashMap<String, Integer>();
//        
//        ArrayList<Integer> operators = new ArrayList<Integer>();
//        
//        ArrayList<Integer> stringLiterals = new ArrayList<Integer>();
        int j = 0;

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

                String filePath = savePath + j + ".txt";
                FileWriter writer = new FileWriter(filePath);

                while ((line = br.readLine()) != null) {
                    sb.append(line);      //appends line to string buffer 

                    writer.write(line);

                    writer.append("\n");

                    Wvs = calculateLineNumericScope(line);

                    Npdtv = calculateLineNumericPrimitive(line);

                    Ncdtv = calculateLineNumericNonPrimitive(line);

                    keywords.put(line, Wvs);

                    primitive.put(line, Npdtv);

                    composite.put(line, Ncdtv);

//                    identifiers.add(nid);
//                    
//                    operators.add(nop);
//                    
//                    stringLiterals.add(nsl);
                    sb.append("\n");     //line feed   
                }
                writer.close();
                System.out.println(filePath);

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

        request.setAttribute("variable", keywords);
        request.setAttribute("primitive", primitive);
        request.setAttribute("composite", composite);

        getServletContext().getRequestDispatcher("/variableMessage.jsp").forward(
                request, response);
    }

    public static int calculateLineNumericScope(String line) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        String str = line;

        if (m.find()) {

            if (!line.matches("(.*)\\{(.*)")) {
                score++;
            }

            return score;

        }

        return score;

    }

    public static int calculateLineNumericPrimitive(String line) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        Pattern p = Pattern.compile("\\d+");

        String[] nop = {"int", "long", "double", "float"};

        if (!line.matches("(.*)\\{(.*)") && (!line.matches("(.*)\\((.*)"))) {

            for (int i = 0; i < nop.length; i++) {
                if (line.contains(nop[i])) {
                    score++;
                }
            }

        }

        return score;

    }

    public static int calculateLineNumericNonPrimitive(String line) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        String[] nop = {"int", "long", "double", "float"};

        String str = line;

        if (!line.matches("(.*)\\{(.*)") && (!line.matches("(.*)\\((.*)"))) {

            if (line.matches("\\[")) {

                score = score + 2;

            }

        }

        return score;

    }

    //calculateLineNumericPrimitive
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
