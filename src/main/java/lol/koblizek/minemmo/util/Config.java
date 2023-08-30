package lol.koblizek.minemmo.util;

import lol.koblizek.minemmo.MineMMO;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public final class Config {
    private static FileConfiguration configuration;
    private Config() {}

    public static void initialize() {
        configuration = MineMMO.getInstance().getConfig();
        // Savable data
        configuration.addDefault("rarity.no_name", "[Rarity Unspecified]");
        configuration.addDefault("stats.no_name", "Unknown stat");
        configuration.options().copyDefaults(true);
        MineMMO.getInstance().saveConfig();
    }
    public static File getPluginFile(String fileName) {
        return new File(MineMMO.getInstance().getDataFolder(), fileName);
    }
    public static String getString(String path) {
        return configuration.getString(path);
    }
    public static Object get(String path) {
        return configuration.get(path);
    }
}
