package lol.koblizek.minemmo.core.commands;

import lol.koblizek.minemmo.core.item.ItemModifier;
import lol.koblizek.minemmo.core.registry.Registries;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestRarityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            var item = player.getInventory().getItemInMainHand();
            ItemModifier.create(item)
                    .rarity(Registries.RARITIES.get("testrarity"));
        }
        return true;
    }
}
