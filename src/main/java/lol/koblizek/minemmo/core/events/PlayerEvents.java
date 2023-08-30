package lol.koblizek.minemmo.core.events;

import lol.koblizek.minemmo.api.rarity.Rarity;
import lol.koblizek.minemmo.core.item.ItemModifier;
import lol.koblizek.minemmo.util.EventUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerEvents extends EventUtils implements Listener {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            var pdc = dataContainer(mainHand);
            if (pdc.has("item_rarity")) {
                int id = pdc.get("item_rarity", PersistentDataType.INTEGER);
                Rarity itemRarity = ItemModifier.RARITIES.get(id);
                event.setDamage(event.getDamage() * itemRarity.damageModifier());
                if (itemRarity.onEntityDamage(event)) event.setCancelled(true);
            }
        }
    }
}
