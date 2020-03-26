/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Note;

/**
 *
 * @author 785284
 */
public class NoteDB {
    
    public int insert(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(note);
            trans.commit();
            return 1;
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public int update(Note note) throws NoteDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception e) {
            trans.rollback();
            throw new NoteDBException("Error updating note.");
        } finally {
            em.close();
        }
    }
    
    public List<Note> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Note> note = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return note;                
        } finally {
            em.close();
        }
    }
    
    public Note get(int noteid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteid);
            return note;
        } finally {
            em.close();
        }
    }
    
    public int delete(Note note) throws NoteDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            // deleting notes
            em.remove(em.merge(note));
            trans.commit();
            return 1;
        } catch (Exception e) {
            trans.rollback();
            throw new NoteDBException("Error deleting note.");
        } finally {
            em.close();
        }
    }
    
}
