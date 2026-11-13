package br.com.calorias.controller;

import br.com.calorias.model.Meta;
import br.com.calorias.model.NivelAtividade;
import br.com.calorias.model.Sexo;
import br.com.calorias.pattern.factory.TmbStrategyFactory;
import br.com.calorias.pattern.singleton.AuditoriaLog;
import br.com.calorias.pattern.strategy.TmbStrategy;
import br.com.calorias.repository.MetaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PerfilController {

    private final MetaRepository metaRepository;

    public PerfilController(MetaRepository metaRepository) { this.metaRepository = metaRepository; }

    @GetMapping("/meta")
    public Meta meta() { return metaRepository.findById(1L).orElseGet(() -> metaRepository.save(new Meta())); }

    @PutMapping("/meta")
    public Meta atualizarMeta(@RequestBody Meta nova) {
        Meta m = meta();
        if (nova.getCaloriasDiarias() == null || nova.getCaloriasDiarias() <= 0) {
            throw new IllegalArgumentException("caloriasDiarias deve ser maior que zero");
        }
        m.setCaloriasDiarias(nova.getCaloriasDiarias());
        return metaRepository.save(m);
    }

    /** Calcula TMB e gasto diário usando Factory + Strategy. */
    @GetMapping("/tmb")
    public Map<String, Object> tmb(@RequestParam double peso, @RequestParam double altura,
                                   @RequestParam int idade, @RequestParam Sexo sexo,
                                   @RequestParam(defaultValue = "MIFFLIN") String formula,
                                   @RequestParam(defaultValue = "SEDENTARIO") NivelAtividade atividade) {
        if (peso <= 0 || altura <= 0 || idade <= 0) {
            throw new IllegalArgumentException("peso, altura e idade devem ser maiores que zero");
        }
        TmbStrategy strategy = TmbStrategyFactory.criar(formula);
        double tmb = strategy.calcular(peso, altura, idade, sexo);
        return Map.of(
                "formula", strategy.nome(),
                "tmb", Math.round(tmb),
                "gastoDiario", Math.round(tmb * atividade.getFator()));
    }

    @GetMapping("/auditoria")
    public List<String> auditoria() { return AuditoriaLog.getInstance().listar(); }
}
