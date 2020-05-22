/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author Harith
 */
@WebServlet(urlPatterns = {"/unzipFileMethod"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class unzipFileMethod extends HttpServlet {

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

    File newFile;

    public int Wmrt = 0;
    public int Npdtp = 0;
    public int Ncdtp;

    ArrayList<String> cars = new ArrayList<String>();

    ArrayList<String> toPrint = new ArrayList<String>();

    Map<String, Integer> methodReturnType = new LinkedHashMap<String, Integer>();

    Map<String, Integer> primitiveParameter = new LinkedHashMap<String, Integer>();

    Map<String, Integer> compositeParameter = new LinkedHashMap<String, Integer>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        String fileName = null;

        String name = null;

        System.out.println(savePath);

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);

            name = savePath + File.separator + fileName;

            System.out.println(name);

        }

        unzip(name, savePath);

        request.setAttribute("Hello", cars);
        request.setAttribute("methodReturnType", methodReturnType);
        request.setAttribute("primitiveParameter", primitiveParameter);
        request.setAttribute("compositeParameter", compositeParameter);

        getServletContext().getRequestDispatcher("/zipFileMessageMethod.jsp").forward(
                request, response);

    }

    private void unzip(String zipFilePath, String destDir) {

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

            String fileName;

            while (ze != null) {
                fileName = ze.getName();
                newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());

                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    ze = zis.getNextEntry();
                    toPrint.add(fileName);
//                File[] listOfFiles = newFile.listFiles();
//
//                for (File file : listOfFiles) {
//                    if (file.isFile()) {
//                        System.out.println("Hello");
//                        System.out.println(file.getName());
//                    }
//                }
                }

                // Creates a new File instance by converting the given pathname string
                // into an abstract pathname
                //close this ZipEntry
            }

            zis.closeEntry();
            zis.close();

            System.out.println("done");

            String[] pathnames;

            File f = new File(newFile.getParent());

            // Populates the array with names of files and directories
            pathnames = f.list();

            // For each pathname in the pathnames array
            for (String pathname : pathnames) {
                // Print the names of files and directories
                cars.add(pathname);

            }

            System.out.println(cars.size());

            int j = 0;

            for (int i = 0; i < cars.size(); i++) {

                System.out.println("Hello");

                FileReader fr = new FileReader(destDir + File.separator + toPrint.get(j));   //reads the file  
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  
                String line;

                String filePath = destDir + j + ".txt";
                FileWriter writer = new FileWriter(filePath);

                while ((line = br.readLine()) != null) {

                    Wmrt = calculateLineMethods(line);

                    Npdtp = calculateLineMethodsPrimitiveParameters(line);

                    Ncdtp = calculateLineMethodsCompositeParameters(line);

                    methodReturnType.put(line, Wmrt);

                    primitiveParameter.put(line, Npdtp);

                    compositeParameter.put(line, Ncdtp);

                    sb.append(line);      //appends line to string buffer 

                    writer.write(line);

                    writer.append("\n");

                    sb.append("\n");

                }

                writer.close();
                System.out.println(filePath);
                j++;
                fr.close();

            }
            //close last ZipEntry
            fis.close();

            File directory = new File(newFile.getParent());

            //make sure directory exists
            if (!directory.exists()) {

                System.out.println("Directory does not exist.");
                System.exit(0);

            } else {

                try {

                    delete(directory);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    public static int calculateLineMethods(String line) {
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

    public static int calculateLineMethodsPrimitiveParameters(String line) {
        int score = 0;

        String[] nop = {"int", "long", "double", "float"};
        String str = line;

        if (line.matches("(.*)\\((.*)") && line.matches("(.*)\\{(.*)")) {

            for (int i = 0; i < nop.length; i++) {
                if (str.contains(nop[i])) {
                    score++;
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

    public static int calculateLineMethodsCompositeParameters(String line) {
        int score = 0;

        String[] nop = {"int", "long", "double", "float"};

        if (line.matches("(.*)()(.*)") && (line.matches("(.*)\\{(.*)"))) {

            for (int i = 0; i < nop.length; i++) {
                if (!line.contains(nop[i])) {
                    score = 1;
                }
            }

        }

        return score;

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
