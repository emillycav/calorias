package br.com.calorias.pattern.observer;

import br.com.calorias.model.Registro;
import br.com.calorias.pattern.singleton.AuditoriaLog;
import org.springframework.stereotype.Component;

/** Observer que grava no log de auditoria (Singleton). */
@Component
public class AuditoriaObserver implements RegistroObserver {

    @Override
    public void aoAdicionar(Registro r) {
        AuditoriaLog.getInstance().registrar("Adicionado: " + r.getAlimento().getNome()
                + " " + r.getGramas() + "g (" + r.getCalorias() + " kcal) em " + r.getData());
    }

    @Override
    public void aoRemover(Registro r) {
        AuditoriaLog.getInstance().registrar("Removido registro #" + r.getId()
                + " (" + r.getAlimento().getNome() + ")");
    }
}
