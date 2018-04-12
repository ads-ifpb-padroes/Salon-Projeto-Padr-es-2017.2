package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.persistencia.AtendenteDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author alexalins
 */
public class CadastroAtendenteController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse res) {
        PrintWriter out;
        try {
            out = res.getWriter();

            String nome = request.getParameter("nome");
            LocalTime horaInicio = LocalTime.parse(request.getParameter("horaInicio"));
            LocalTime horaFim = LocalTime.parse(request.getParameter("horaFim"));
            
            AtendenteDao dao = new AtendenteDao();
            Atendente atendente = new Atendente(nome, horaInicio, horaFim);
            dao.cadastroAtendente(atendente);

            res.sendRedirect("index.jsp");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
