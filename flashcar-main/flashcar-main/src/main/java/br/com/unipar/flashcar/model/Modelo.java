package br.com.unipar.flashcar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modelo {
    private int id;
    private String descricao;
    private int ano;
    private int cor_id;
    private int portas;
    private String combustivel;
}