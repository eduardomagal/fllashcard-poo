package br.com.unipar.flashcar.service;

import br.com.unipar.flashcar.database.repository.MarcaRepository;
import br.com.unipar.flashcar.exception.DescricaoInvalidaException;
import br.com.unipar.flashcar.model.Marca;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaService {
    private void valida(Marca marca) throws DescricaoInvalidaException {
        
        if (marca.getDescricao() == null) {
            throw new DescricaoInvalidaException();
        }
        
        if (marca.getDescricao().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (marca.getDescricao().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
        
    }
    
    public void insert(Marca marca) throws DescricaoInvalidaException, 
                                       SQLException {
        
        valida(marca);
        
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.insert(marca);
        
    }
    
    public ArrayList<Marca> findAll() throws SQLException{
        MarcaRepository marcaRepository = new MarcaRepository();
        return marcaRepository.findAll();
    }
    
    public void delete(Marca marca) throws SQLException {
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.delete(marca);
    }
    
    public void update(Marca marca) throws SQLException, DescricaoInvalidaException {
        valida(marca);
        
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.update(marca);
    }
    
    public Marca findById(int id,int modelo_id) throws SQLException {
        MarcaRepository marcaRepository = new MarcaRepository();
        return marcaRepository.findById(id,modelo_id);
    }
}