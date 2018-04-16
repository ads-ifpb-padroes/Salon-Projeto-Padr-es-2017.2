/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Agendador;
import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricarte
 */
public class LoginController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        UsuarioDao dao;
        dao = new UsuarioDao();
        Usuario user = dao.readByEmail(email);
        if (user == null) {
            try {
                res.sendRedirect("index.jsp?msg='Não há nenhum usuario cadastrado com esse email e senha.'");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                System.out.println(session.getAttribute("user"));
                try {
                    if (user.getTipo() == null) {
                        res.sendRedirect("inicialClient.jsp");
                    } else {
                        Timer timer = new Timer();
                        Agendador agendador = new Agendador();
                        timer.schedule(agendador, new Date(), 60000);
                        res.sendRedirect("inicialAdmin.jsp");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    res.sendRedirect("index.jsp?msg='Dados incorretos...'");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
