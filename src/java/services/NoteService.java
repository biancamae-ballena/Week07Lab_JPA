package services;

import dataaccess.NoteDB;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import models.Note;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(Integer noteid) throws Exception {
        Note note = noteDB.get(noteid);
        return note;
    }

    public List<Note> getAllNote() throws Exception {
        List<Note> notes = noteDB.getAll();
        return notes;
    }

    public int update(Integer noteid, String title, String contents) throws Exception {
        Note note = get(noteid);
        note.setDatecreated(new Date());
        note.setTitle(title);
        note.setContents(contents);
        return noteDB.update(note);
    }

    public int delete(Integer noteid) throws Exception {
        Note note = noteDB.get(noteid);
        return noteDB.delete(note);
    }

    public int insert(String title, String contents) throws Exception {
        Note note = new Note();
        note.setDatecreated(new Date());
        note.setTitle(title);
        note.setContents(contents);
        return noteDB.update(note);
    }
}
