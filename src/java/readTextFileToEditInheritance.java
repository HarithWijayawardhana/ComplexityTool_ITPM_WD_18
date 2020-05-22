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
@WebServlet(urlPatterns = {"/readTextFileToEditInheritance"})
public class readTextFileToEditInheritance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    int j = 0;
    private static final String SAVE_DIR = "uploadFiles";
    public int var = 0;

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

        int rtnGlobal = Integer.parseInt(request.getParameter("Keyword"));
        int rtnLocal = Integer.parseInt(request.getParameter("Identifier"));
        int rtnvoid = Integer.parseInt(request.getParameter("Operator"));
        int prmPrimitive = Integer.parseInt(request.getParameter("Numericalvalue"));
        int prmComposite = Integer.parseInt(request.getParameter("Stringliteral"));

        Map<String, Integer> score = new LinkedHashMap<String, Integer>();

        String fileName = savePath + j + ".txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
        String line;

        try {

            while ((line = br.readLine()) != null) {
                sb.append(line);      //appends line to string buffer 

                var = calculateDirectInScore(line, rtnGlobal);

                score.put(line, var);

                sb.append("\n");     //line feed   
            }

            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setAttribute("message", score);
//       request.setAttribute("fileName", fileName);

        getServletContext().getRequestDispatcher("/inheritanceMessage.jsp").forward(
                request, response);

    }

    public static int calculateDirectInScore(String line, int b) {

        int score = 0;
        String[] wkw = {"extends"};

        for (int i = 0; i < wkw.length; i++) {
            if (line.contains(wkw[i])) {
                score = score + b;
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
