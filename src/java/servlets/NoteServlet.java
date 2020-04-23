package servlets;

import dataaccess.NoteDBException;
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

public class NoteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService ns = new NoteService();
        List<Note> note = ns.getAllNote();
        request.setAttribute("note", note);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "view");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NoteService ns = new NoteService();
        List<Note> note = ns.getAllNote();
        request.setAttribute("note", note);
        request.setAttribute("action", "edit");

        //Editing the note.
        if (request.getParameter("btnEdit") != null) {
            int editNote = Integer.parseInt(request.getParameter("noteId"));
            Note noteEdit = ns.get(editNote);
            request.setAttribute("selectedNote", noteEdit.getNoteid());
            request.setAttribute("title", noteEdit.getTitle());
            request.setAttribute("contents", noteEdit.getContents());
        }

        //Saving the note.
        if (request.getParameter("btnSave") != null) {
            int saveNote = Integer.parseInt(request.getParameter("saveID"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            try {
                ns.update(saveNote, title, content);
            } catch (NoteDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("action", "view");
        }

        //Adding a note.
        if (request.getParameter("btnAdd") != null) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            try {
                ns.insert(title, content);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("action", "view");
        }

        //Deleting a note.
        if (request.getParameter("btnDelete") != null) {
            int deleteNote = Integer.parseInt(request.getParameter("noteID"));
            try {
                ns.delete(deleteNote);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("action", "view");
        }
        processRequest(request, response);
    }
}
