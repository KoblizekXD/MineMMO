package lol.koblizek.minemmo.core.registry;

import lombok.Getter;

@Getter
public final class RegistryKey<T> {
    private final Registry<T> registry;
    private final String key;
    private final T object;

    RegistryKey(Registry<T> registry, String key, T object) {
        this.registry = registry;
        this.key = key;
        this.object = object;
    }
}
