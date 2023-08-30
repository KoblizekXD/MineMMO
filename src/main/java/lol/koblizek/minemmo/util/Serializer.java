package lol.koblizek.minemmo.util;

import lol.koblizek.minemmo.api.rarity.Rarity;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

public class Serializer {
    public static String toBase64(Rarity rarity) {
        return Base64.getEncoder().encodeToString(toBytes(rarity));
    }
    public static byte[] toBytes(Rarity rarity) {
        var baos = new ByteArrayOutputStream();
        try (var oos = new BukkitObjectOutputStream(baos)) {
            oos.writeObject(rarity);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return baos.toByteArray();
    }
    public static Rarity fromBytes(byte[] bytes) {
        var bais = new ByteArrayInputStream(bytes);
        try (var ois = new BukkitObjectInputStream(bais)) {
            return (Rarity) ois.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
