package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.AtendimentoDao;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author alexalins
 */
public class CadastroUsuarioController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse res) {
        PrintWriter out;
        try {
            out = res.getWriter();
            Part part = request.getPart("fotoPerfil");

            String email = request.getParameter("email");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");

            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = new Usuario(email, nome, telefone, email);
            dao.cadastroUsuario(usuario);

            res.sendRedirect("inicialClient.jsp");
        } catch (IOException | ServletException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
