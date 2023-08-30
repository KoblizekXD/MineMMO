package lol.koblizek.minemmo;

import lol.koblizek.minemmo.core.commands.TestRarityCommand;
import lol.koblizek.minemmo.core.events.PlayerEvents;
import lol.koblizek.minemmo.util.Config;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineMMO extends JavaPlugin {
    private static MineMMO mineMmo;

    @Override
    public void onEnable() {
        mineMmo = this;
        registerCommand("testrarity", new TestRarityCommand());
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        Config.initialize();
    }
    private void registerCommand(String name, CommandExecutor executor) {
        getServer().getPluginCommand(name).setExecutor(executor);
    }

    @Override
    public void onDisable() {
        mineMmo = null;
    }

    public static MineMMO getInstance() {
        return mineMmo;
    }
}
