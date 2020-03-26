/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

/**
 *
 * @author 785284
 */
public class NoteDBException extends Exception {

    /**
     * Sends message from superclass Exception when there is an exception
     *
     * @param message
     */
    public NoteDBException(String message) {
        super(message);
    }
}
