package br.com.calorias.controller;

import br.com.calorias.dto.RegistroRequest;
import br.com.calorias.dto.ResumoDia;
import br.com.calorias.model.Registro;
import br.com.calorias.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) { this.service = service; }

    @GetMapping("/registros")
    public List<Registro> listar(@RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return service.listarPorData(data);
    }

    @PostMapping("/registros")
    @ResponseStatus(HttpStatus.CREATED)
    public Registro adicionar(@Valid @RequestBody RegistroRequest req) { return service.adicionar(req); }

    @DeleteMapping("/registros/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) { service.remover(id); }

    @GetMapping("/resumo")
    public ResumoDia resumo(@RequestParam(required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return service.resumo(data);
    }
}
