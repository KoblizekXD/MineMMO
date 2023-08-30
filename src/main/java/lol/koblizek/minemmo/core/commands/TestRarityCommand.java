package lol.koblizek.minemmo.core.commands;

import lol.koblizek.minemmo.api.rarity.Rarity;
import lol.koblizek.minemmo.core.item.ItemModifier;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
                    .rarity(Rarity.builder()
                            .name("NIGGER")
                            .decorated(TextDecoration.BOLD)
                            .textColor(NamedTextColor.AQUA).build());
        }
        return true;
    }
}
