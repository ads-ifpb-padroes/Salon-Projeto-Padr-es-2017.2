/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.persistencia.AtendimentoDao;
import com.mycompany.salon.persistencia.AvaliacaoDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricarte
 */
public class AvaliarController implements Command, Serializable {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        AtendimentoDao dao = new AtendimentoDao();
        Atendimento atendimento = dao.readAtendimentoById(Integer.parseInt(req.getParameter("atendimento")));
        AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
        if (avaliacaoDao.createAvaliacao(Integer.parseInt(req.getParameter("tempoAtendimento")), Integer.parseInt(req.getParameter("qualidadeAtendimento")), Integer.parseInt(req.getParameter("qualidadeEstabelecimento")), Integer.parseInt(req.getParameter("qualidadeServico")), atendimento.getAtendente())) {
            try {
                res.sendRedirect("sucesso.html");
            } catch (IOException ex) {
                Logger.getLogger(AvaliarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
