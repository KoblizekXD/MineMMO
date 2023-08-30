package lol.koblizek.minemmo.util;

import lol.koblizek.minemmo.api.rarity.RarityPersistenceDataType;
import lol.koblizek.minemmo.core.item.ItemDataContainer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class EventUtils {
    public static PersistentDataContainer pdc(ItemStack stack) {
        return stack.getItemMeta().getPersistentDataContainer();
    }
    public static ItemDataContainer dataContainer(ItemStack stack) {
        return new ItemDataContainer(pdc(stack));
    }
    public static RarityPersistenceDataType rarityPersistence() {
        return RarityPersistenceDataType.TYPE;
    }
}
