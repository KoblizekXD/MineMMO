package lol.koblizek.minemmo.api.rarity;

import lol.koblizek.minemmo.util.Serializer;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class RarityPersistenceDataType implements PersistentDataType<byte[], Rarity> {
    public static final RarityPersistenceDataType TYPE
            = new RarityPersistenceDataType();

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<Rarity> getComplexType() {
        return Rarity.class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull Rarity complex, @NotNull PersistentDataAdapterContext context) {
        return Serializer.toBytes(complex);
    }

    @Override
    public @NotNull Rarity fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext context) {
        return Serializer.fromBytes(primitive);
    }
}
