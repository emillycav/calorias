package br.com.calorias.service;

import br.com.calorias.dto.RegistroRequest;
import br.com.calorias.dto.ResumoDia;
import br.com.calorias.model.Registro;
import br.com.calorias.pattern.builder.RegistroBuilder;
import br.com.calorias.pattern.observer.RegistroSubject;
import br.com.calorias.repository.MetaRepository;
import br.com.calorias.repository.RegistroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroService {

    private final RegistroRepository repository;
    private final MetaRepository metaRepository;
    private final AlimentoService alimentoService;
    private final RegistroSubject subject;

    public RegistroService(RegistroRepository repository, MetaRepository metaRepository,
                           AlimentoService alimentoService, RegistroSubject subject) {
        this.repository = repository;
        this.metaRepository = metaRepository;
        this.alimentoService = alimentoService;
        this.subject = subject;
    }

    public List<Registro> listarPorData(LocalDate data) {
        return repository.findByDataOrderByIdAsc(data != null ? data : LocalDate.now());
    }

    public Registro adicionar(RegistroRequest req) {
        Registro r = new RegistroBuilder()                       // Builder
                .alimento(alimentoService.buscar(req.alimentoId()))
                .gramas(req.gramas())
                .tipo(req.tipo())
                .data(req.data())
                .build();
        r = repository.save(r);
        subject.notificarAdicao(r);                              // Observer
        return r;
    }

    public void remover(Long id) {
        Registro r = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado: " + id));
        repository.delete(r);
        subject.notificarRemocao(r);
    }

    public ResumoDia resumo(LocalDate data) {
        LocalDate d = data != null ? data : LocalDate.now();
        double total = repository.somaCalorias(d);
        int meta = metaRepository.findById(1L).map(m -> m.getCaloriasDiarias()).orElse(2000);
        return new ResumoDia(d, total, meta, meta - total, total > meta);
    }
}
