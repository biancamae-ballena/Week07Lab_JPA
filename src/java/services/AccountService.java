/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Note;

/**
 *
 * @author 785284
 */
public class AccountService {

    public void login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService us = new UserService();
        try {
            User user = us.get(username);
            if (user.getPassword().equals(password)) {
                session.setAttribute("validated", user);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("ERROR: Unknown user " + username);
        }
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public void logout(HttpServletRequest request, HttpServletRequest response) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
