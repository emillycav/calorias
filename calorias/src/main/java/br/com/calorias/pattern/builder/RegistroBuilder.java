package br.com.calorias.pattern.builder;

import br.com.calorias.model.Alimento;
import br.com.calorias.model.Registro;
import br.com.calorias.model.TipoRefeicao;

import java.time.LocalDate;

/** PADRÃO 3 - BUILDER: monta um Registro e calcula as calorias ao final. */
public class RegistroBuilder {

    private Alimento alimento;
    private Double gramas;
    private TipoRefeicao tipo = TipoRefeicao.LANCHE;
    private LocalDate data = LocalDate.now();

    public RegistroBuilder alimento(Alimento alimento) { this.alimento = alimento; return this; }
    public RegistroBuilder gramas(Double gramas) { this.gramas = gramas; return this; }
    public RegistroBuilder tipo(TipoRefeicao tipo) { if (tipo != null) this.tipo = tipo; return this; }
    public RegistroBuilder data(LocalDate data) { if (data != null) this.data = data; return this; }

    public Registro build() {
        if (alimento == null || gramas == null || gramas <= 0) {
            throw new IllegalStateException("Alimento e gramas (> 0) são obrigatórios");
        }
        Registro r = new Registro();
        r.setAlimento(alimento);
        r.setGramas(gramas);
        r.setTipo(tipo);
        r.setData(data);
        r.setCalorias(Math.round(alimento.getCaloriasPor100g() * gramas) / 100.0);
        return r;
    }
}
