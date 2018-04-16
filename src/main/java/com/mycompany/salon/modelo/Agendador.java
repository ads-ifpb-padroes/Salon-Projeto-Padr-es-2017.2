/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import com.mycompany.salon.persistencia.AtendimentoDao;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Ricarte
 */
public class Agendador extends TimerTask {

    @Override
    public void run() {
        AtendimentoDao dao = new AtendimentoDao();
        ArrayList<Atendimento> avaliacoesPendentes = dao.readAtendimentosComAvaliacoesPendentes();
        if (avaliacoesPendentes != null) {
            for (Atendimento avaliacaoPendente : avaliacoesPendentes) {
                HtmlEmail email = new HtmlEmail();
                email.setHostName("smtp.gmail.com");
                try {
                    email.addTo(avaliacaoPendente.getCliente().getEmail(), avaliacaoPendente.getCliente().getNome());
                    email.setFrom("thigoyure@gmail.com", "Admin from Salon");
                    email.setSubject("Avaliação de Atendimento");
                    email.setHtmlMsg("<html><p>Data do atendimento:" + avaliacaoPendente.getData() + "</p>"
                            + "<p>Serviço:" + avaliacaoPendente.getServico().getNome() + "</p>"
                            + "<p>Atendente:" + avaliacaoPendente.getAtendente().getNome() + "</p>"
                            + "<p>Clique no link para avaliar o atendimento:<a href='http://localhost:8084/Salon/avaliacao.jsp?atendimento=" + avaliacaoPendente.getId() + "'>Clique aqui!</a></p></html>");
                    email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
                    email.setSmtpPort(465);
                    email.setAuthentication("thigoyure@gmail.com", "senha");
                    email.setSSLOnConnect(true);
                    System.out.println("ola");
                    email.send();
                } catch (EmailException ex) {
                    Logger.getLogger(Agendador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
