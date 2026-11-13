package br.com.calorias.pattern.singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** PADRÃO 4 - SINGLETON: instância única do log de auditoria (initialization-on-demand holder, thread-safe). */
public final class AuditoriaLog {

    private static final int LIMITE = 100;
    private final List<String> eventos = Collections.synchronizedList(new ArrayList<>());

    private AuditoriaLog() {}

    private static class Holder {
        private static final AuditoriaLog INSTANCIA = new AuditoriaLog();
    }

    public static AuditoriaLog getInstance() {
        return Holder.INSTANCIA;
    }

    public void registrar(String mensagem) {
        eventos.add(0, LocalDateTime.now() + " - " + mensagem);
        while (eventos.size() > LIMITE) {
            eventos.remove(eventos.size() - 1);
        }
    }

    public List<String> listar() {
        synchronized (eventos) {
            return new ArrayList<>(eventos);
        }
    }
}
