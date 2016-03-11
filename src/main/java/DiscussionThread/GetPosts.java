/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiscussionThread;

import java.io.File;
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
@WebServlet(name = "GetPosts", urlPatterns = {"/GetPosts"})
public class GetPosts extends HttpServlet {

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
        
        try
        {
            //http://stackoverflow.com/questions/4340653/file-path-to-resource-in-our-war-web-inf-folder
            ServletContext context = this.getServletContext();
            
            //Get the correct context for the json path.
            String fullPath = context.getRealPath(path);            
            
            //File-ize it!
            File jsonFile = new File(fullPath);
            //Dump it into a string
            String jsonFileContents = new Scanner(jsonFile).useDelimiter("\\Z").next();       

            //Make a JSONObject from our string to be parsed.  I realize I'm going from json to string to json... but it works and I struggled with this for a long stinkin' time.
            JSONObject mainObject = new JSONObject(jsonFileContents);
            JSONArray jPosts = mainObject.getJSONArray("posts");
            
            int count = 1;
            
            //I didn't feel like properly passing all this stuff to a jsp, so I just did it here :-)
            out.print("<!DOCTYPE html><html><head><title>View Posts</title></head><body><h1>View all the posts!</h1>");
            out.print("<table border=\"1\"><tr><td>#</td><td>User:</td><td>Message</td></tr>");

            //Iterate through the JSON object...
            for (Object key : jPosts) {
                
                JSONArray jKey = (JSONArray)key;
                
                //Iterate through the sub array...
                for (Object subKey : jKey) {                    
                    JSONObject entry = (JSONObject)subKey;
                    out.print("<tr><td>" + count++ + "</td><td>" + entry.get("username").toString() + "</td><td>" + entry.get("text").toString() + "</td></tr>");                    
                    System.out.print(entry.get("username") + ": " + entry.get("text"));
                }     
            }
            
            out.print("<p><a href=\"newpost.jsp\">Make a new post!</a></p></table></body></html>");
        }
        catch (Exception ex)
        {
            out.println("Ruh roh");
            ex.printStackTrace();
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
