package br.com.calorias.pattern.strategy;

import br.com.calorias.model.Sexo;

public class HarrisBenedictStrategy implements TmbStrategy {

    @Override
    public String nome() { return "HARRIS_BENEDICT"; }

    @Override
    public double calcular(double pesoKg, double alturaCm, int idade, Sexo sexo) {
        if (sexo == Sexo.MASCULINO) {
            return 88.362 + 13.397 * pesoKg + 4.799 * alturaCm - 5.677 * idade;
        }
        return 447.593 + 9.247 * pesoKg + 3.098 * alturaCm - 4.330 * idade;
    }
}
