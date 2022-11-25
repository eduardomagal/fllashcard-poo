package br.com.unipar.flashcar.database.repository;

import br.com.unipar.flashcar.database.DatabaseConnection;
import br.com.unipar.flashcar.model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaRepository {

    private String INSERT
            = "insert into MARCA(descricao,modelo_id) values(?,?);";
    private String UPDATE
            = "update MARCA set descricao = ? where id = ? and modelo_id = ?;";
    private String DELETE
            = "delete MARCA where id = ? and modelo_id = ?;";
    private String FIND_BY_ID
            = "select id, descricao,modelo_id  from MARCA where ID = ? and MODELO_ID = ?;";
    private String FIND_ALL
            = "select id, descricao,modelo_id from MARCA;";

    public void insert(Marca marca) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(INSERT);
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getModelo_id());
            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public ArrayList<Marca> findAll() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Marca> listaResultado = new ArrayList<>();
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Marca marca = new Marca();
                marca.setDescricao(rs.getString("descricao"));
                marca.setId(rs.getInt(1));
                marca.setModelo_id(rs.getInt(3));
                
                listaResultado.add(marca);
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
    
    public void delete(Marca marca) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, marca.getId());
            ps.setInt(2, marca.getModelo_id());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public void update(Marca marca) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getId());
            ps.setInt(3, marca.getModelo_id());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
    
    public Marca findById(int id,int modelo_id) throws SQLException {   

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Marca resultado = new Marca();
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            ps.setInt(2, modelo_id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                resultado.setDescricao(rs.getString("descricao"));
                resultado.setId(rs.getInt("id"));
                resultado.setModelo_id(rs.getInt("modelo_id"));
                
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
