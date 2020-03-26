/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NoteDB;
import dataaccess.NoteDBException;
import java.util.Date;
import java.util.List;
import models.Note;

/**
 *
 * @author 785284
 */
public class NoteService {
    
    private NoteDB noteDB;
    
    public NoteService() {
        noteDB = new NoteDB();
    }
    
    public Note get(int noteId) {
        Note note = noteDB.get(noteId);
        return note;
    }
    
    public List<Note> getAll() {
        List<Note> noteList = noteDB.getAll();
        return noteList;
    }
    
    public int update(int noteid, String title, String contents) throws Exception { 
        Note note = get(noteid);
        note.setDatecreated(new Date());
        note.setTitle(title);
        note.setContents(contents);
        return noteDB.update(note);
    }
    
    public int delete(int noteid) throws Exception {
        Note note = noteDB.get(noteid);
        return noteDB.delete(note);
    }
    
    public int insert(String contents, String title) throws Exception {
        Note addNote = new Note();
        addNote.setDatecreated(new Date());
        addNote.setTitle(title);
        addNote.setContents(contents);
        return noteDB.update(addNote);
    }
}
