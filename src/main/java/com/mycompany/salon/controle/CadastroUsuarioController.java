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
            String senha = request.getParameter("senha");
            String email = request.getParameter("email");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String caminhoUser = File.separator + request.getServletContext().getRealPath("/Users")+ File.separator + email;
            
            File pastaUser = new File(caminhoUser);
            if (!pastaUser.exists()) {
                pastaUser.mkdirs();
            }
            Part path = request.getPart("fotoPerfil");
            System.out.println(path);
            String cam = caminhoUser + File.separator + path.getSubmittedFileName();
            path.write(cam);
            //CAMINHO DO DIRETÓRIO PARA O BANCO
            String fotoPerfil = "usuarios/" + email + "/" + path.getSubmittedFileName();
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = new Usuario(email, senha, nome, telefone, fotoPerfil,"");
            if (email.equals("") || senha.equals("") || nome.equals("")) {
                res.sendRedirect("index.jsp?msg=Preencha os campos vazios.");
            } else {
                if (dao.readByEmail(email) == null) {
                    if (dao.cadastroUsuario(usuario)) {
                        res.sendRedirect("index.jsp");
                    } else {
                        res.sendRedirect("index.jsp?msg=Cadastro realizado com sucesso.");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | ServletException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
