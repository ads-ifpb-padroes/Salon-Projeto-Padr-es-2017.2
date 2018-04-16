/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Avaliacao;
import com.mycompany.salon.persistencia.AvaliacaoDao;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ThigoYure
 */
public class BuscaAvaliacaoAtendente extends SimpleTagSupport {

    private String atendente;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     *
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        AvaliacaoDao dao = new AvaliacaoDao();
        int tempAtend = 0;
        int qualiAtend = 0;
        int qualiAmbi = 0;
        int qualiServ = 0;
        for (Avaliacao avaliacao : dao.readByAtendente(atendente)) {
            tempAtend += avaliacao.getTempoAtendimento();
            qualiAtend += avaliacao.getQualidadeAtendimento();
            qualiAmbi += avaliacao.getQualidadeAmbiente();
            qualiServ += avaliacao.getQualidadeServico();
        }
        getJspContext().setAttribute("tempAtend", tempAtend / dao.readByAtendente(atendente).size());
        getJspContext().setAttribute("qualiAtend", qualiAtend / dao.readByAtendente(atendente).size());
        getJspContext().setAttribute("qualiAmbi", qualiAmbi / dao.readByAtendente(atendente).size());
        getJspContext().setAttribute("qualiServ", qualiServ / dao.readByAtendente(atendente).size());
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

}
