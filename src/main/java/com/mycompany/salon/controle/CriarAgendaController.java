package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.modelo.AtendimentoBuilder;
import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.persistencia.AtendenteDao;
import com.mycompany.salon.persistencia.AtendimentoDao;
import com.mycompany.salon.persistencia.ServicoDao;
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
public class CriarAgendaController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            PrintWriter out = res.getWriter();
            AtendimentoDao dao = new AtendimentoDao();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            String nomeAtendente = req.getParameter("atendente");
            String nomeServico = req.getParameter("servico");
            
            Atendente atendente = atendenteDao.readByNome(nomeAtendente);
            
            Servico servico = servicoDao.readByNome(nomeServico);
            
            AtendimentoBuilder builder = new AtendimentoBuilder();
            builder.comAtendente(atendente);
            builder.comServico(servico);
                    
            if(nomeAtendente.equals("")||nomeServico.equals("")){
                res.sendRedirect("cadastroAgenda.jsp?msg='Preencha os campos vazios.'");
            }else{
                if(dao.readServicoAgendaByAtendente(nomeAtendente, nomeServico)==null){
                   if(dao.createAgenda(builder.getAtendimento())){
                      res.sendRedirect("cadastroAgenda.jsp?msg='Agenda criada com sucesso.'"); 
                   } else{
                       res.sendRedirect("cadastroAgenda.jsp?msg='Falha ao criar agenda.'");
                   }
                }else{
                    res.sendRedirect("cadastroAgenda.jsp?msg='Já existe esse serviço na agenda desse atendente.'");
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(CriarAgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
