package br.com.calorias.pattern.observer;

import br.com.calorias.model.Registro;
import org.springframework.stereotype.Component;

import java.util.List;

/** Subject do Observer: notifica todos os observadores registrados (injetados pelo Spring). */
@Component
public class RegistroSubject {

    private final List<RegistroObserver> observers;

    public RegistroSubject(List<RegistroObserver> observers) {
        this.observers = observers;
    }

    public void notificarAdicao(Registro r) { observers.forEach(o -> o.aoAdicionar(r)); }

    public void notificarRemocao(Registro r) { observers.forEach(o -> o.aoRemover(r)); }
}
