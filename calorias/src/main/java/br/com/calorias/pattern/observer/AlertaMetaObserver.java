package br.com.calorias.pattern.observer;

import br.com.calorias.model.Registro;
import br.com.calorias.pattern.singleton.AuditoriaLog;
import br.com.calorias.repository.MetaRepository;
import br.com.calorias.repository.RegistroRepository;
import org.springframework.stereotype.Component;

/** Observer que avisa quando o total do dia ultrapassa a meta calórica. */
@Component
public class AlertaMetaObserver implements RegistroObserver {

    private final RegistroRepository registroRepository;
    private final MetaRepository metaRepository;

    public AlertaMetaObserver(RegistroRepository registroRepository, MetaRepository metaRepository) {
        this.registroRepository = registroRepository;
        this.metaRepository = metaRepository;
    }

    @Override
    public void aoAdicionar(Registro r) {
        double total = registroRepository.somaCalorias(r.getData());
        int meta = metaRepository.findById(1L).map(m -> m.getCaloriasDiarias()).orElse(2000);
        if (total > meta) {
            AuditoriaLog.getInstance().registrar("ALERTA: meta de " + meta + " kcal ultrapassada em "
                    + r.getData() + " (total " + total + " kcal)");
        }
    }

    @Override
    public void aoRemover(Registro r) { }
}
