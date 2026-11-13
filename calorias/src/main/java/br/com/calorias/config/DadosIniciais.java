package br.com.calorias.config;

import br.com.calorias.model.Alimento;
import br.com.calorias.repository.AlimentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** Popula o banco H2 com alimentos comuns na primeira execução. */
@Configuration
public class DadosIniciais {

    @Bean
    CommandLineRunner seed(AlimentoRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.saveAll(List.of(
                        new Alimento("Arroz branco cozido", 130.0, "Cereais"),
                        new Alimento("Feijão carioca cozido", 76.0, "Leguminosas"),
                        new Alimento("Peito de frango grelhado", 159.0, "Carnes"),
                        new Alimento("Ovo cozido", 146.0, "Ovos"),
                        new Alimento("Banana", 89.0, "Frutas"),
                        new Alimento("Maçã", 52.0, "Frutas"),
                        new Alimento("Pão francês", 300.0, "Pães"),
                        new Alimento("Leite integral", 61.0, "Laticínios"),
                        new Alimento("Batata doce cozida", 77.0, "Tubérculos"),
                        new Alimento("Alface", 15.0, "Verduras")));
            }
        };
    }
}
