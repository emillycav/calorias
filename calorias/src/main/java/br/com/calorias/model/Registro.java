package br.com.calorias.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Alimento alimento;

    private Double gramas;

    @Enumerated(EnumType.STRING)
    private TipoRefeicao tipo;

    private LocalDate data;

    private Double calorias;

    public Registro() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Alimento getAlimento() { return alimento; }
    public void setAlimento(Alimento alimento) { this.alimento = alimento; }
    public Double getGramas() { return gramas; }
    public void setGramas(Double gramas) { this.gramas = gramas; }
    public TipoRefeicao getTipo() { return tipo; }
    public void setTipo(TipoRefeicao tipo) { this.tipo = tipo; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Double getCalorias() { return calorias; }
    public void setCalorias(Double calorias) { this.calorias = calorias; }
}
