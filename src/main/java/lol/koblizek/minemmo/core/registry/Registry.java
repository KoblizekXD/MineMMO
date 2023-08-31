package lol.koblizek.minemmo.core.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Registry<T> {
    private final Map<RegistryKey<T>, T> data = new HashMap<>();

    public static <E> RegistryKey<E> register(Registry<E> registry, String key, E data) {
        return registry.registerNew(data, key);
    }
    private RegistryKey<T> registerNew(T object, String stringedKey) {
        RegistryKey<T> key = new RegistryKey<>(this, stringedKey, object);
        data.put(key, object);
        return key;
    }
    public T get(String key) {
        AtomicReference<T> toReturn = new AtomicReference<>(null);
        data.forEach((reg, obj) -> {
            if (reg.getKey().equals(key))
                toReturn.set(obj);
        });
        return toReturn.get();
    }
    public String findIdentifier(T object) {
        AtomicReference<String> ret = new AtomicReference<>(null);
        data.forEach((reg, obj) -> {
            if (obj.equals(object))
                ret.set(reg.getKey());
        });
        return ret.get();
    }
    private T get(RegistryKey<T> key) {
        return data.get(key);
    }
}
