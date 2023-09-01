package lol.koblizek.minemmo.core.item;

import com.jeff_media.morepersistentdatatypes.DataType;
import lol.koblizek.minemmo.api.rarity.Rarity;
import lol.koblizek.minemmo.api.stats.ItemStats;
import lol.koblizek.minemmo.core.registry.Registries;
import lol.koblizek.minemmo.util.EventUtils;
import lol.koblizek.minemmo.util.Pair;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.xml.crypto.Data;
import java.util.*;

public final class ItemModifier extends EventUtils {
    private final ItemStack stack;

    private ItemModifier(ItemStack stack) {
        this.stack = stack;
    }

    public static ItemModifier create(ItemStack stack) {
         return new ItemModifier(stack);
    }
    public ItemModifier rarity(Rarity rarity) {
        dataContainer(stack)
                .add("item_rarity", PersistentDataType.STRING, Registries.RARITIES.findIdentifier(rarity));
        var meta = stack.getItemMeta();
        if (rarity.asComponent() != null) {
            if (meta.hasLore()) {
                var lore = meta.lore();
                lore.add(rarity.asComponent());
                meta.lore(lore);
            } else {
                var list = new ArrayList<Component>();
                list.add(rarity.asComponent());
                meta.lore(list);
            }
        } else {
            var list = Arrays.stream(rarity.getDecorations()).toList();
            Component decoration;
            if (list.contains(TextDecoration.OBFUSCATED)) {
                list.remove(TextDecoration.OBFUSCATED);
                decoration = MiniMessage.miniMessage().deserialize("<obf>K</obf>")
                        .appendSpace()
                        .append(Component.text(rarity.getName()).color(rarity.getColor())
                                .appendSpace()
                                .append(MiniMessage.miniMessage().deserialize("<obf>K</obf>")))
                        .decoration(TextDecoration.ITALIC, false)
                        .decorate(list.toArray(TextDecoration[]::new));
            } else {
                decoration = Component.text(rarity.getName()).color(rarity.getColor())
                        .decoration(TextDecoration.ITALIC, false)
                        .decorate(list.toArray(TextDecoration[]::new));
            }
            if (meta.hasLore()) {
                var lore = meta.lore();
                lore.add(decoration);
                meta.lore(lore);
            } else {
                var lore = new ArrayList<Component>();
                lore.add(decoration);
                meta.lore(lore);
            }
        }
        stack.setItemMeta(meta);
        return this;
    }
    public <E extends Entity> ItemModifier itemStats(E entity, Pair<ItemStats<E>, Double>... values) {
        var data = dataContainer(stack);
        var lore = stack.lore();
        if (lore == null) lore = new ArrayList<>();

        Map<String, Double> addedData = new HashMap<>();
        List<Component> addedLore = new ArrayList<>();
        for (Pair<ItemStats<E>, Double> itemStat : values) {
            addedLore.add(itemStat.getK().toComponent(itemStat.getV(), entity));
        }
        lore.addAll(0, addedLore);
        data.add("item_stats", DataType.asMap(DataType.STRING, DataType.DOUBLE), addedData);
        stack.lore(lore);
        return this;
    }
}
