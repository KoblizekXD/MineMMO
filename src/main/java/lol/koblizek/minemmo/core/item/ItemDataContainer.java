package lol.koblizek.minemmo.core.item;

import lol.koblizek.minemmo.MineMMO;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemDataContainer {
    private final PersistentDataContainer dataContainer;
    private final MineMMO instance;

    public ItemDataContainer(PersistentDataContainer container) {
        this.dataContainer = container;
        this.instance = MineMMO.getInstance();
    }

    public PersistentDataContainer getDataContainer() {
        return dataContainer;
    }

    public MineMMO getInstance() {
        return instance;
    }

    public boolean has(String key) {
        return dataContainer.has(new NamespacedKey(instance, key));
    }

    public <T, V> V get(String key, PersistentDataType<T, V> type) {
        return dataContainer
                .get(new NamespacedKey(instance, key), type);
    }
    public <T, V> void add(String key, PersistentDataType<T, V> type, V value) {
        dataContainer
                .set(new NamespacedKey(instance, key), type, value);
    }
}
