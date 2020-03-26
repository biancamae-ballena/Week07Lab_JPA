/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;
import services.NoteService;

/**
 *
 * @author 785284
 */
public class NoteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteService ns = new NoteService();
        List<Note> noteList = ns.getAll();
        request.setAttribute("noteList", noteList);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
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
        request.setAttribute("mode", "view");
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
        NoteService ns = new NoteService();
        List<Note> noteList = ns.getAll();
        request.setAttribute("noteList", noteList);
        
        //Edting the notes.
        if(request.getParameter("edit") != null) {
            int editNote = Integer.parseInt(request.getParameter("noteId"));
            Note note = ns.get(editNote);
            request.setAttribute("selectedNote", note.getNoteid());
            request.setAttribute("title", note.getTitle());
            request.setAttribute("content", note.getContents());
        }
        
        //Saving the note
        if(request.getParameter("btnSave") != null) {
            int saveNote = Integer.parseInt(request.getParameter("selectedNote"));
            
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            
            try {
                ns.update(saveNote, title, content);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("mode", "view");
            
        }
        
        //Adding a note.
        if(request.getParameter("btnAdd") != null) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            
            try {
                ns.insert(title, content);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("mode", "view");
        } 
            
        //Deleting a note
        if(request.getParameter("btnDelete") != null) {
            int deleteNote = Integer.parseInt(request.getParameter("selectedNote"));
            
            try {
                ns.delete(deleteNote);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("mode", "view");
        }
        processRequest(request, response);
//        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
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
