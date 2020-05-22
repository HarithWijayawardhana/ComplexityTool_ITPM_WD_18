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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harith
 */
@WebServlet(urlPatterns = {"/zipFileReadVariable"})
public class zipFileReadVariable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SAVE_DIR = "upload";

    int j;

    public int Wvs = 0;
    public int Npdtv = 0;
    public int Ncdtv = 0;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        int scopeglobal = Integer.parseInt(request.getParameter("Keyword"));
        int scopelocal = Integer.parseInt(request.getParameter("Identifier"));
        int primitiveNumeric = Integer.parseInt(request.getParameter("Operator"));
        int compositeNumeric = Integer.parseInt(request.getParameter("Numericalvalue"));

        Map<String, Integer> keywords = new LinkedHashMap<String, Integer>();

        Map<String, Integer> primitive = new LinkedHashMap<String, Integer>();

        Map<String, Integer> composite = new LinkedHashMap<String, Integer>();

        j = 0;

        String fileName = savePath + j + ".txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
        String line;

        try {

            while ((line = br.readLine()) != null) {
                sb.append(line);      //appends line to string buffer 

                Wvs = calculateLineNumericScope(line, scopeglobal);

                Npdtv = calculateLineNumericPrimitive(line, primitiveNumeric);

                Ncdtv = calculateLineNumericNonPrimitive(line, compositeNumeric);

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

            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("variable", keywords);
        request.setAttribute("primitive", primitive);
        request.setAttribute("composite", composite);

        getServletContext().getRequestDispatcher("/variableMessage.jsp").forward(
                request, response);

    }

    public static int calculateLineNumericScope(String line, int scp) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        String str = line;

        if (m.find()) {

            if (!line.matches("(.*)\\{(.*)")) {
                score = score + scp;
            }

            return score;

        }

        return score;

    }

    public static int calculateLineNumericPrimitive(String line, int pri) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        Pattern p = Pattern.compile("\\d+");

        String[] nop = {"int", "long", "double", "float"};

        if (!line.matches("(.*)\\{(.*)") && (!line.matches("(.*)\\((.*)"))) {

            for (int i = 0; i < nop.length; i++) {
                if (line.contains(nop[i])) {
                    score = score + pri;
                }
            }

        }

        return score;

    }

    public static int calculateLineNumericNonPrimitive(String line, int cmp) {
        int score = 0;
//        String[] Wvsg = {"public int", "public String", "public double", "public float"};
//        
//        String[] Wvsl = {"int", "String", "double" , "float"};

        String[] nop = {"int", "long", "double", "float"};

        String str = line;

        if (!line.matches("(.*)\\{(.*)") && (!line.matches("(.*)\\((.*)"))) {

            if (line.matches("\\[")) {

                score = score + cmp;

            }

        }

        return score;

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
