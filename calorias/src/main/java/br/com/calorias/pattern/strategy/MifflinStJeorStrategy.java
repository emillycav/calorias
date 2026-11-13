package br.com.calorias.pattern.strategy;

import br.com.calorias.model.Sexo;

public class MifflinStJeorStrategy implements TmbStrategy {

    @Override
    public String nome() { return "MIFFLIN"; }

    @Override
    public double calcular(double pesoKg, double alturaCm, int idade, Sexo sexo) {
        double base = 10 * pesoKg + 6.25 * alturaCm - 5 * idade;
        return sexo == Sexo.MASCULINO ? base + 5 : base - 161;
    }
}
