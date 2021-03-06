/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.AtendimentoDao;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ThigoYure
 * @author Ricarte
 */
@Named
public class AgendarAtendimentoControllerAdmin implements Command, Serializable {

    @Inject private AtendimentoDao atendimentoDao;
    @Inject private UsuarioDao userDao;
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        String opcao = req.getParameter("opcao");
        String[] atributos = opcao.split("&");
        int idAtendimento = Integer.parseInt(atributos[0]);
        Atendimento atendimento = atendimentoDao.readAgendaById(idAtendimento);
        Usuario user = userDao.readByEmail(req.getParameter("cliente"));
        atendimento.setCliente(user);
        atendimento.setConfirmado(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        atendimento.setData(LocalDate.parse(req.getParameter("data"), formatter));
        LocalTime horaInicio = LocalTime.parse(atributos[1]);
        LocalTime horaFim = LocalTime.parse(atributos[2]);
        atendimento.setHoraInicio(horaInicio);
        atendimento.setHoraFim(horaFim);
        if (req.getParameter("opcao").equals("") || req.getParameter("cliente").equals("") || req.getParameter("data").equals("")) {
            try {
                res.sendRedirect("agendamentoDeHorarioAdmin.jsp?msg=Preencha os campos vazios.");
            } catch (IOException ex) {
                Logger.getLogger(AgendarAtendimentoControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (atendimentoDao.readByDataHorarioAtendente(atendimento.getData(),atendimento.getHoraInicio(),atendimento.getAtendente().getNome()) != null) {
            try {
                res.sendRedirect("agendamentoDeHorarioAdmin.jsp?msg= Ja existe um atendimento marcado para esse horario e data com esse atendente.");
            } catch (IOException ex) {
                Logger.getLogger(AgendarAtendimentoControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (atendimentoDao.createAtendimento(atendimento)) {
                try {
                    if (atendimento.getData().isAfter(LocalDate.now())) {
                        SimpleEmail email = new SimpleEmail();
                        System.out.println("oi");
                        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
                        email.addTo(atendimento.getCliente().getEmail(), atendimento.getCliente().getNome()); //destinatário
                        email.setFrom("thigoyure@gmail.com", "Admin from Salon"); // remetente
                        email.setSubject("Mensagem de Confirmação"); // assunto do e-mail
                        email.setMsg("Agendamento de horário no Salon para o dia "+atendimento.getData()+" foi feito com sucesso. \nNão esqueça. :D\nAtendente: "
                                + atendimento.getAtendente().getNome()+"\nServiço: "+atendimento.getServico().getNome()); //conteudo do e-mail
                        email.setSmtpPort(465);
                        email.setAuthentication("thigoyure@gmail.com", "senha");
                        email.setSSLOnConnect(true);
                        email.send(); //envia o e-mail
                    }
                    res.sendRedirect("agendamentoDeHorarioAdmin.jsp?msg=Horario marcado com sucesso.");
                } catch (IOException | EmailException ex) {
                    Logger.getLogger(AgendarAtendimentoControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    res.sendRedirect("agendamentoDeHorarioAdmin.jsp?msg=Nao foi possivel marcar o horario.");
                } catch (IOException ex) {
                    Logger.getLogger(AgendarAtendimentoControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
