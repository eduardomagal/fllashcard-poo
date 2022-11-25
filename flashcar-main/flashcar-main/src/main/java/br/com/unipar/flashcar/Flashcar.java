package br.com.unipar.flashcar;

import br.com.unipar.flashcar.exception.DescricaoInvalidaException;
import br.com.unipar.flashcar.model.Cor;
import br.com.unipar.flashcar.model.Marca;
import br.com.unipar.flashcar.model.Modelo;
import br.com.unipar.flashcar.service.CorService;
import br.com.unipar.flashcar.service.MarcaService;
import br.com.unipar.flashcar.service.ModeloService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Flashcar {

    public static void main(String[] args) {
        
        try {
            
            Cor azul = new Cor();
            azul.setDescricao("roxo");
            
            CorService corService = new CorService();
            corService.insert(azul);
            
            System.out.println("----------------MODELO------------------");
            Modelo modelo = new Modelo();
            modelo.setAno(2003);
            modelo.setCombustivel("Gasolina");
            modelo.setDescricao("teste");
            modelo.setCor_id(1);
            modelo.setPortas(4);
            
            ModeloService modeloService = new ModeloService();
            modeloService.insert(modelo);
            
            ArrayList<Modelo> listaModelo = modeloService.findAll();
            System.out.println(listaModelo.toString());
            
            System.out.println("----------------UPDATE------------------");
            
            modelo.setAno(2004);
            modelo.setDescricao("teste muito diferente update");
            modeloService.update(modelo);
            
            listaModelo = modeloService.findAll();
            System.out.println(listaModelo.toString());
            
            System.out.println("----------------FIND BY ID------------------");
            
            modelo = modeloService.findById(4,1);
            System.out.println(modelo.toString());
            
            System.out.println("----------------MARCA------------------");
            Marca marca = new Marca();
            marca.setDescricao("teste2");
            marca.setModelo_id(1);
            
            MarcaService marcaService = new MarcaService();
            marcaService.insert(marca);
            ArrayList<Marca> listaMarca = marcaService.findAll();
            System.out.println(listaMarca.toString());
            
            System.out.println("----------------UPDATE------------------");
            
            marca.setDescricao("teste update");
            marcaService.update(marca);
            
            listaMarca = marcaService.findAll();
            System.out.println(listaMarca.toString());
            
            System.out.println("----------------FIND BY ID------------------");
            
            marca = marcaService.findById(4,1);
            System.out.println(marca.toString());
            
        } catch (DescricaoInvalidaException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
}
