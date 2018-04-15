/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThigoYure
 */
public class SairController implements Command,Serializable{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.invalidate();
        try {
            res.sendRedirect("index.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SairController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
