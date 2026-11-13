package br.com.calorias.pattern.strategy;

import br.com.calorias.model.Sexo;

/** PADRÃO 1 - STRATEGY: família de algoritmos para calcular a Taxa Metabólica Basal. */
public interface TmbStrategy {
    String nome();
    double calcular(double pesoKg, double alturaCm, int idade, Sexo sexo);
}
