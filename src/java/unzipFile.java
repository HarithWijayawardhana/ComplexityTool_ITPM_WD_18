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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
@WebServlet(urlPatterns = {"/unzipFile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class unzipFile extends HttpServlet {

    private static final String SAVE_DIR = "upload";

    String temp = "";

    File newFile;

    ArrayList<String> cars = new ArrayList<String>();

    ArrayList<String> toPrint = new ArrayList<String>();

    Map<String, Integer> keywords = new LinkedHashMap<String, Integer>();

    Map<String, Integer> identifiers = new LinkedHashMap<String, Integer>();

    Map<String, Integer> operators = new LinkedHashMap<String, Integer>();

    Map<String, Integer> numerics = new LinkedHashMap<String, Integer>();

    Map<String, Integer> stringLiterals = new LinkedHashMap<String, Integer>();

    public int wkw = 0;
    public int wkwe = 0;
    public int nid = 0;
    public int nop = 0;
    public int Nnv = 0;
    public int nsl = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        String fileName = null;

        String name = null;

        System.out.println(appPath);

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
        request.setAttribute("message", keywords);
        request.setAttribute("identifiers", identifiers);
        request.setAttribute("operators", operators);
        request.setAttribute("numerics", numerics);
        request.setAttribute("strings", stringLiterals);

        getServletContext().getRequestDispatcher("/zipFileMessage.jsp").forward(
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
    	if(!directory.exists()){

           System.out.println("Directory does not exist.");
           System.exit(0);

        }else{

           try{

               delete(directory);

           }catch(IOException e){
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

    public void readContent(Path filePath) throws IOException {
        System.out.println("read file " + filePath);
        List<String> fileList = Files.readAllLines(filePath);
        System.out.println("" + fileList);
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
