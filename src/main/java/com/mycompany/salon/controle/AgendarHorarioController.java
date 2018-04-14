package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.modelo.AtendimentoBuilder;
import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.persistencia.AtendimentoDao;
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
public class AgendarHorarioController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            PrintWriter out = res.getWriter();
            String nomeAtendente = req.getParameter("nomeAtendente");
            String nomeServico = req.getParameter("nomeServico");
            
            Atendente atendente = new Atendente();
            atendente.setNome(nomeServico);
            
            Servico servico = new Servico();
            servico.setNome(nomeAtendente);
            
            AtendimentoBuilder builder = new AtendimentoBuilder();
            builder.comAtendente(atendente);
            builder.comServico(servico);
                    
            AtendimentoDao dao = new AtendimentoDao();
            dao.create(builder.getAtendimento());
            
        } catch (IOException ex) {
            Logger.getLogger(AgendarHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
