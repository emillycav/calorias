package br.com.calorias.model;

import jakarta.persistence.*;

/** Meta calórica diária do usuário (registro único, id = 1). */
@Entity
public class Meta {

    @Id
    private Long id = 1L;

    private Integer caloriasDiarias = 2000;

    public Meta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getCaloriasDiarias() { return caloriasDiarias; }
    public void setCaloriasDiarias(Integer c) { this.caloriasDiarias = c; }
}
