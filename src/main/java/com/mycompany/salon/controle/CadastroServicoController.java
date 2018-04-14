package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.ServicoDao;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexalins
 */
public class CadastroServicoController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse res) {
        PrintWriter out;
        try {
            out = res.getWriter();

            String nome = request.getParameter("nome");
            Float preco = Float.parseFloat(request.getParameter("preco"));
            int tempoMedio = Integer.parseInt(request.getParameter("tempoMedio"));

            ServicoDao dao = new ServicoDao();
            Servico servico = new Servico(nome, tempoMedio, preco);
            if (nome.equals("")) {
                res.sendRedirect("cadastroServico.jsp?msg=Preencha os campos vazios.");
            } else {
                if (dao.readByNome(nome)==null) {
                    if (dao.cadastroServico(servico)) {
                        res.sendRedirect("cadastroServico.jsp?msg=Cadastro de servco realizado com sucesso.");
                    } else {
                        res.sendRedirect("cadastroServico.jsp?msg=Cadastro de servco falhou.");
                    }
                }else{
                    res.sendRedirect("cadastroServico.jsp?msg=Ja existe um serviço com esse nome no sistema.");
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
