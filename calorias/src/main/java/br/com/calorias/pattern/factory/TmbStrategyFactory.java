package br.com.calorias.pattern.factory;

import br.com.calorias.pattern.strategy.HarrisBenedictStrategy;
import br.com.calorias.pattern.strategy.MifflinStJeorStrategy;
import br.com.calorias.pattern.strategy.TmbStrategy;

/** PADRÃO 2 - FACTORY METHOD: cria a Strategy correta a partir do nome da fórmula. */
public final class TmbStrategyFactory {

    private TmbStrategyFactory() {}

    public static TmbStrategy criar(String formula) {
        if (formula == null) {
            return new MifflinStJeorStrategy();
        }
        return switch (formula.toUpperCase()) {
            case "MIFFLIN" -> new MifflinStJeorStrategy();
            case "HARRIS_BENEDICT", "HARRIS" -> new HarrisBenedictStrategy();
            default -> throw new IllegalArgumentException(
                    "Fórmula desconhecida: " + formula + " (use MIFFLIN ou HARRIS_BENEDICT)");
        };
    }
}
