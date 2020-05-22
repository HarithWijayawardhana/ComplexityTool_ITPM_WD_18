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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harith
 */
@WebServlet(urlPatterns = {"/readTextFileToEditMethod"})
public class readTextFileToEditMethod extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
    int j = 0;
    public int Wmrt = 0;
    public int Npdtp = 0;
    public int Ncdtp;

    private static final String SAVE_DIR = "uploadFiles";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        int rtnGlobal = Integer.parseInt(request.getParameter("Keyword"));
        int rtnLocal = Integer.parseInt(request.getParameter("Identifier"));
        int rtnvoid = Integer.parseInt(request.getParameter("Operator"));
        int prmPrimitive = Integer.parseInt(request.getParameter("Numericalvalue"));
        int prmComposite = Integer.parseInt(request.getParameter("Stringliteral"));

        Map<String, Integer> methodReturnType = new LinkedHashMap<String, Integer>();

        Map<String, Integer> primitiveParameter = new LinkedHashMap<String, Integer>();

        Map<String, Integer> compositeParameter = new LinkedHashMap<String, Integer>();

        String fileName = savePath + j + ".txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
        String line;

        try {

            while ((line = br.readLine()) != null) {
                sb.append(line);      //appends line to string buffer 

                Wmrt = calculateLineMethods(line, rtnGlobal);

                Npdtp = calculateLineMethodsPrimitiveParameters(line, prmPrimitive);

                Ncdtp = calculateLineMethodsCompositeParameters(line, prmComposite);

                methodReturnType.put(line, Wmrt);

                primitiveParameter.put(line, Npdtp);

                compositeParameter.put(line, Ncdtp);

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

        request.setAttribute("methodReturnType", methodReturnType);
        request.setAttribute("primitiveParameter", primitiveParameter);
        request.setAttribute("compositeParameter", compositeParameter);

        getServletContext().getRequestDispatcher("/methodsMessage.jsp").forward(
                request, response);

    }

    public static int calculateLineMethods(String line, int rutnGlobal) {
        int score = 0;

//        String[] nop = {"public int", "public long", "public double", "public float", "private int", "private long", "private float",};
        String str = line;

        String[] nop = {"int", "long", "double", "float"};

        if (line.matches("(.*)\\((.*)") && line.matches("(.*)\\{(.*)")) {

            for (int i = 0; i < nop.length; i++) {
                if (line.contains(nop[i])) {
                    score = score + 2;
                }
            }

        } else if (line.matches("(.*)\\((.*)") && !line.matches("(.*)void(.*)") && line.matches("(.*)\\{(.*)")) {
            score++;
        }

        return score;
    }

    public static int calculateLineMethodsPrimitiveParameters(String line, int paramPrimitive) {
        int score = 0;

        String[] nop = {"int", "long", "double", "float"};
        String str = line;

        if (line.matches("(.*)\\((.*)") && line.matches("(.*)\\{(.*)")) {

            for (int i = 0; i < nop.length; i++) {
                if (str.contains(nop[i])) {
                    score = score + paramPrimitive;
                }
            }
        }

//        } else if (line.matches("(.*)\\((.*)")) {
//            for (int i = 0; i < nop.length; i++) {
//                if (!line.contains(nop[i])) {
//                    score = score + 2;
//                    System.out.println("Mama awa");
//                }
//            }
        return score;
    }

    public static int calculateLineMethodsCompositeParameters(String line, int paramComposite) {
        int score = 0;

        String[] nop = {"int", "long", "double", "float"};

        if (line.matches("(.*)()(.*)") && (line.matches("(.*)\\{(.*)"))) {

            for (int i = 0; i < nop.length; i++) {
                if (!line.contains(nop[i])) {
                    score = paramComposite;
                }
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
