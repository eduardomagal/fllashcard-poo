package br.com.unipar.flashcar.database.repository;

import br.com.unipar.flashcar.database.DatabaseConnection;
import br.com.unipar.flashcar.model.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloRepository {

    private String INSERT
            = "insert into MODELO(descricao,ano,cor_id,portas,combustivel) values(?,?,?,?,?);";
    private String UPDATE
            = "update MODELO set descricao = ?,ano = ?,portas = ?,combustivel = ? where id = ? and cor_id = ?;";
    private String DELETE
            = "delete MODELO where id = ? and cor_id = ?;";
    private String FIND_BY_ID
            = "select id, descricao, ano, cor_id, portas, combustivel  from MODELO where ID = ? and COR_ID = ?;";
    private String FIND_ALL
            = "select id, descricao, ano, cor_id, portas, combustivel from MODELO;";

    public void insert(Modelo modelo) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(INSERT);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getAno());
            ps.setInt(3, modelo.getCor_id());
            ps.setInt(4, modelo.getPortas());
            ps.setString(5, modelo.getCombustivel());
            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public ArrayList<Modelo> findAll() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Modelo> listaResultado = new ArrayList<>();
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt(1));
                modelo.setDescricao(rs.getString("descricao"));
                modelo.setAno(rs.getInt(3));
                modelo.setCor_id(rs.getInt(4));
                modelo.setPortas(rs.getInt(5));
                modelo.setCombustivel(rs.getString("combustivel"));
                
                listaResultado.add(modelo);
            }

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return listaResultado;

    }
    
    public void delete(Modelo modelo) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, modelo.getId());
            ps.setInt(2, modelo.getCor_id());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public void update(Modelo modelo) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getAno());
            ps.setInt(3, modelo.getPortas());
            ps.setString(4, modelo.getCombustivel());
            ps.setInt(5, modelo.getId());
            ps.setInt(6, modelo.getCor_id());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public Modelo findById(int id,int cor_id) throws SQLException {   

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Modelo resultado = new Modelo();
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            ps.setInt(2, cor_id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                resultado.setDescricao(rs.getString("descricao"));
                resultado.setAno(rs.getInt("ano"));
                resultado.setPortas(rs.getInt("portas"));
                resultado.setCombustivel(rs.getString("combustivel"));
                resultado.setId(rs.getInt("id"));
                resultado.setCor_id(rs.getInt("cor_id"));
                
            }

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return resultado;

    }

}
