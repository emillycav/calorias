package br.com.calorias.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Calorias por 100g é obrigatório")
    @PositiveOrZero(message = "Calorias não pode ser negativa")
    private Double caloriasPor100g;

    private String categoria;

    public Alimento() {}

    public Alimento(String nome, Double caloriasPor100g, String categoria) {
        this.nome = nome;
        this.caloriasPor100g = caloriasPor100g;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getCaloriasPor100g() { return caloriasPor100g; }
    public void setCaloriasPor100g(Double c) { this.caloriasPor100g = c; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
