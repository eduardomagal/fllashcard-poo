package br.com.unipar.flashcar.service;

import br.com.unipar.flashcar.database.repository.ModeloRepository;
import br.com.unipar.flashcar.exception.DescricaoInvalidaException;
import br.com.unipar.flashcar.model.Modelo;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloService {
    private void valida(Modelo modelo) throws DescricaoInvalidaException {
        
        if (modelo.getDescricao() == null) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getDescricao().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getDescricao().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getAno()== 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getPortas()== 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getCor_id()== 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getCombustivel()== null) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getCombustivel().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getCombustivel().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
    }
    
    public void insert(Modelo modelo) throws DescricaoInvalidaException, 
                                       SQLException {
        
        valida(modelo);
        
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.insert(modelo);
        
    }
    
    public ArrayList<Modelo> findAll() throws SQLException{
        ModeloRepository modeloRepository = new ModeloRepository();
        return modeloRepository.findAll();
    }
    
    public void delete(Modelo modelo) throws SQLException {
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.delete(modelo);
    }
    
    public void update(Modelo modelo) throws SQLException, DescricaoInvalidaException {
        valida(modelo);
        
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.update(modelo);
    }
    
    public Modelo findById(int id,int cor_id) throws SQLException {
        ModeloRepository modeloRepository = new ModeloRepository();
        return modeloRepository.findById(id,cor_id);
    }
}