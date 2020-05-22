/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
@WebServlet(urlPatterns = {"/readTextFileToEdit"})
public class readTextFileToEdit extends HttpServlet {

    private static final String SAVE_DIR = "web";

    int j;
    public int wkw = 0;
    public int wkwe = 0;
    public int nid = 0;
    public int nop = 0;
    public int Nnv = 0;
    public int nsl = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int Keyword = Integer.parseInt(request.getParameter("Keyword"));
        int Identifier = Integer.parseInt(request.getParameter("Identifier"));
        int Operator = Integer.parseInt(request.getParameter("Operator"));
        int Numericalvalue = Integer.parseInt(request.getParameter("Numericalvalue"));
        int Stringliteral = Integer.parseInt(request.getParameter("Stringliteral"));

        Map<String, Integer> keywords = new LinkedHashMap<String, Integer>();

        Map<String, Integer> identifiers = new LinkedHashMap<String, Integer>();

        Map<String, Integer> operators = new LinkedHashMap<String, Integer>();

        Map<String, Integer> numerics = new LinkedHashMap<String, Integer>();

        Map<String, Integer> stringLiterals = new LinkedHashMap<String, Integer>();

        String fileName = "uploadFiles" + j + ".txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
        String line;

        try {

            while ((line = br.readLine()) != null) {
                //process the line
                wkw = calculateLineKeywords(line, Keyword);

                nid = calculateLineIdentifiers(line, Identifier);

                nop = calculateLineOperators(line, Operator);

                Nnv = calculateLineNumeric(line, Numericalvalue);

                nsl = calculateLineStrings(line, Stringliteral);

                keywords.put(line, wkw);

                identifiers.put(line, nid);

                operators.put(line, nop);

                numerics.put(line, Nnv);

                stringLiterals.put(line, nsl);

                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("message", keywords);
        request.setAttribute("identifiers", identifiers);
        request.setAttribute("operators", operators);
        request.setAttribute("numerics", numerics);
        request.setAttribute("strings", stringLiterals);

        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);

    }

    public static int calculateLineKeywords(String line, int key) {
        int score = 0;
        String[] wkw = {"private", "public", "static", "class", "void", "request", "protected", "extends", "implements"};

        for (int i = 0; i < wkw.length; i++) {
            if (line.contains(wkw[i])) {
                score = score + key;
            }
        }

        return score;
    }

    public static int calculateLineIdentifiers(String line, int idt) {
        int score = 0;

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        String str = line;

        if (line.matches("(.*)for(.*)")) {
            score = score + 4;
        } else if (line.matches("(.*)System.out(.*)")) {
            score = score + 3;
        } else if (m.find()) {
            score = score + idt;
        }

        return score;
    }

    public static int calculateLineOperators(String line, int op) {
        int score = 0;
        String[] nop = {"+", "-", "*", "/", "%", "++", "--", "==", "!=", ">", "<", ">=", "<=", "&&", "||", "!"};

        String str = line;

        for (int i = 0; i < nop.length; i++) {
            if (line.contains(nop[i])) {
                if (!str.startsWith("import")) {
                    score = score + op;
                }
            }
        }
        return score;
    }

    public static int calculateLineNumeric(String line, int num) {
        int score = 0;

        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(line);

        while (m.find()) {
            score = score + num;
        }

        return score;
    }

    public static int calculateLineStrings(String line, int sLit) {
        int score = 0;
        String[] nop = {"\""};

        for (int i = 0; i < nop.length; i++) {
            if (line.contains(nop[i])) {
                score = score + sLit;
            }
        }
        return score;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
