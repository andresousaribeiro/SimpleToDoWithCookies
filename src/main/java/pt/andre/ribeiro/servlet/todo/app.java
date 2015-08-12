/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.andre.ribeiro.servlet.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AndreRibeiro
 */
public class app extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (TaskDB.isEnable() == false) {
            initDabatase(request);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        InputStream url = getServletContext().getResourceAsStream("/WEB-INF/html/index.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url));
        String line;

        String fileHead = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head lang=\"en\">\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Task List Example</title>\n"
                + "</head>\n"
                + "<body>";
        
        String fileTail = "</body>\n"
                + "</html>";

        out.append(fileHead);
        
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        if (TaskDB.instance().getTasks().size() != 0) {
            out.append("<ul id=\"taskList\">");
            for (Entry<Integer, Task> entry : TaskDB.instance().getTasks().entrySet()) {
                Integer key = entry.getKey();
                Task value = entry.getValue();
                out.append("<li id='" + key + "'>" + value.getTaskDescription() +  "</li>");
            }
            out.append("</ul>");
        }

        out.append(fileTail);


        out.close();


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private void initDabatase(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (TaskDB.instance().getTasks().isEmpty()) {

            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("DB")) {
                    String[] tasks = cookies[i].getValue().split("&");
                    LinkedHashMap<Integer, Task> previousTasks = new LinkedHashMap<Integer, Task>();
                    for (int j = 0; j < tasks.length; j++) {
                        previousTasks.put(Integer.parseInt(tasks[j].split("#")[0]), new Task(tasks[j].split("#")[1]));
                    }
                    TaskDB.initDB(previousTasks);
                }
            }


        }else{
            TaskDB.instance();
        }



    }
}
