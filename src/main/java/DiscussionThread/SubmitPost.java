/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiscussionThread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author kevin
 */
@WebServlet(name = "SubmitPost", urlPatterns = {"/SubmitPost"})
public class SubmitPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");                
        PrintWriter out = response.getWriter();

        String path = "/WEB-INF/posts.json";
        
        String text = request.getParameter("text");
        String user = request.getParameter("username");
        
        try
        {
            //http://stackoverflow.com/questions/4340653/file-path-to-resource-in-our-war-web-inf-folder
            ServletContext context = this.getServletContext();
            
            //Get the JSON context
            String fullPath = context.getRealPath(path);                                   
            File jsonFile = new File(fullPath);
            
            //Get the contents of the JSON
            String jsonFileContents = new Scanner(jsonFile).useDelimiter("\\Z").next();
         
            //Dump the contents into a JSON object
            JSONObject mainObject = new JSONObject(jsonFileContents);
            JSONObject valuesObject = new JSONObject();
          
            //This will help us add stuff to our json 
            JSONArray list = new JSONArray();
            
            valuesObject.put("username", user);
            valuesObject.put("text", text);
            
            list.put(valuesObject);
            mainObject.accumulate("posts", list);  //Put it in!            
            
            //Rewrite our json file.
            FileWriter fw = new FileWriter(jsonFile, false);
            fw.write(mainObject.toString());
            fw.close();
            
            //Onward!
            request.getSession().setAttribute("jsonobject", mainObject);
            request.getRequestDispatcher("GetPosts").forward(request, response);
        }
        catch (Exception ex)
        {
            out.println("Boo hoo");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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

}