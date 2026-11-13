package br.com.calorias.pattern.observer;

import br.com.calorias.model.Registro;

/** PADRÃO 5 - OBSERVER: observadores reagem a eventos de registro de consumo. */
public interface RegistroObserver {
    void aoAdicionar(Registro registro);
    void aoRemover(Registro registro);
}
