/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;

/**
 *
 * @author ThigoYure
 * @author AlexaLins
 */
@Named
public class UsuarioDao implements Serializable{
    
    public boolean cadastroUsuario(Usuario usuario) throws ClassNotFoundException {
        try (Connection con = ConFactory.getConnection()) {
            int retorno;
            String sql = "INSERT INTO usuario (email, nome, senha, telefone, fotoPerfil) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getFotoPerfil());
            retorno = stmt.executeUpdate();
            return retorno>0;
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Usuario readByEmail(String email) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            st.setString(1, email);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Usuario user = new Usuario();
                user.setEmail(r.getString("email"));
                user.setFotoPerfil(r.getString("fotoPerfil"));
                user.setNome(r.getString("nome"));
                user.setTelefone(r.getString("telefone"));
                user.setTipo(r.getString("tipo"));
                user.setSenha(r.getString("senha"));
                return user;
            }
            st.close();
            con.close();
            return null;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object readAll() {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM usuario where tipo is null");
            ResultSet r = st.executeQuery();
            ArrayList<Usuario> retorno = new ArrayList<>();
            while (r.next()) {
                Usuario user = new Usuario();
                user.setEmail(r.getString("email"));
                user.setFotoPerfil(r.getString("fotoPerfil"));
                user.setNome(r.getString("nome"));
                user.setTelefone(r.getString("telefone"));
                retorno.add(user);
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
