/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.Avaliacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricarte
 */
public class AvaliacaoDao {
    
    public boolean createAvaliacao(int tempoAtend,int qualiAtend,int qualiAmbi,int qualiServ,Atendente atendente) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO Avaliacao (tempoatendimento,qualidadeatendimento,qualidadeambiente,qualidadeservico,atendente"
                        + ") VALUES(?,?,?,?,?)");
                st.setInt(1, tempoAtend);
                st.setInt(2, qualiAtend);
                st.setInt(3, qualiAmbi);
                st.setInt(4, qualiServ);
                st.setString(5, atendente.getNome());
                retorno = st.executeUpdate();
                st.close();
                con.close();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public ArrayList<Avaliacao> readAll() {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM avaliacao");
            ResultSet r = st.executeQuery();
            ArrayList<Avaliacao> retorno = new ArrayList<>();
            AtendenteDao dao = new AtendenteDao();
            while (r.next()) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setAtendente(dao.readByNome(r.getString("atendente")));
                avaliacao.setTempoAtendimento(r.getInt("tempoatendimento"));
                avaliacao.setId(r.getInt("id"));
                avaliacao.setQualidadeServico(r.getInt("qualidadeservico"));
                avaliacao.setQualidadeAtendimento(r.getInt("qualidadeatendimento"));
                avaliacao.setQualidadeAmbiente(r.getInt("qualidadeambiente"));
                retorno.add(avaliacao);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public ArrayList<Avaliacao> readByAtendente(String atendente) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM avaliacao where atendente = ?");
            st.setString(1, atendente);
            ResultSet r = st.executeQuery();
            ArrayList<Avaliacao> retorno = new ArrayList<>();
            AtendenteDao dao = new AtendenteDao();
            while (r.next()) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setAtendente(dao.readByNome(r.getString("atendente")));
                avaliacao.setTempoAtendimento(r.getInt("tempoatendimento"));
                avaliacao.setId(r.getInt("id"));
                avaliacao.setQualidadeServico(r.getInt("qualidadeservico"));
                avaliacao.setQualidadeAtendimento(r.getInt("qualidadeatendimento"));
                avaliacao.setQualidadeAmbiente(r.getInt("qualidadeambiente"));
                retorno.add(avaliacao);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
}
