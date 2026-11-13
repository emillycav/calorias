package br.com.calorias.service;

import br.com.calorias.model.Alimento;
import br.com.calorias.repository.AlimentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlimentoService {

    private final AlimentoRepository repository;

    public AlimentoService(AlimentoRepository repository) {
        this.repository = repository;
    }

    public List<Alimento> listar() { return repository.findAll(); }

    public Alimento buscar(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado: " + id));
    }

    public Alimento salvar(Alimento a) { return repository.save(a); }

    public Alimento atualizar(Long id, Alimento dados) {
        Alimento a = buscar(id);
        a.setNome(dados.getNome());
        a.setCaloriasPor100g(dados.getCaloriasPor100g());
        a.setCategoria(dados.getCategoria());
        return repository.save(a);
    }

    public void remover(Long id) {
        try {
            repository.delete(buscar(id));
            repository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Alimento possui registros de consumo e não pode ser removido");
        }
    }
}
