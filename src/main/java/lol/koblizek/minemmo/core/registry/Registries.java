package lol.koblizek.minemmo.core.registry;

import lol.koblizek.minemmo.api.rarity.Rarity;
import lol.koblizek.minemmo.api.stats.ItemStats;

public class Registries {
    private Registries() {}

    public static final Registry<Rarity> RARITIES = new Registry<>() {};
    public static final Registry<ItemStats<?>> ITEM_STATS = new Registry<>() {
    };
}
