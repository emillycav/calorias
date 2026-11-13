package br.com.calorias.controller;

import br.com.calorias.model.Alimento;
import br.com.calorias.service.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    private final AlimentoService service;

    public AlimentoController(AlimentoService service) { this.service = service; }

    @GetMapping
    public List<Alimento> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Alimento buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alimento criar(@Valid @RequestBody Alimento a) { return service.salvar(a); }

    @PutMapping("/{id}")
    public Alimento atualizar(@PathVariable Long id, @Valid @RequestBody Alimento a) {
        return service.atualizar(id, a);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) { service.remover(id); }
}
