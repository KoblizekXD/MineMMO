package lol.koblizek.minemmo;

import lol.koblizek.minemmo.api.rarity.Rarity;
import lol.koblizek.minemmo.core.commands.TestRarityCommand;
import lol.koblizek.minemmo.core.events.PlayerEvents;
import lol.koblizek.minemmo.core.events.RegistryEvent;
import lol.koblizek.minemmo.core.registry.Registries;
import lol.koblizek.minemmo.core.registry.Registry;
import lol.koblizek.minemmo.util.Config;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineMMO extends JavaPlugin {
    private static MineMMO mineMmo;

    @Override
    public void onEnable() {
        mineMmo = this;
        onRegistryInvocation();
        registerCommand("testrarity", new TestRarityCommand());
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        Config.initialize();
    }
    private void registerCommand(String name, CommandExecutor executor) {
        getServer().getPluginCommand(name).setExecutor(executor);
    }

    private void onRegistryInvocation() {
        RegistryEvent event = new RegistryEvent();
        if (event.callEvent()) {
            Registry.register(Registries.RARITIES, "testrarity", Rarity.builder()
                    .name("Legendary lmao")
                    .decorated(TextDecoration.BOLD)
                    .textColor(NamedTextColor.AQUA).build());
        }
    }

    @Override
    public void onDisable() {
        mineMmo = null;
    }

    public static MineMMO getInstance() {
        return mineMmo;
    }
}
