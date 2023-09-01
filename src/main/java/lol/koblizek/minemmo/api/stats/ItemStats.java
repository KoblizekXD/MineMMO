package lol.koblizek.minemmo.api.stats;

import lol.koblizek.minemmo.util.Config;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;

public abstract class ItemStats<E extends Entity> {


    /**
     * This will be only invoked when entity equips an armor or item
     *
     * @param entity entity the modifier will be used on
     * @return Modifier class instance
     */
    public abstract Modifier modifies(E entity);
    public String name() { return ""; }

    /**
     * @return the color of the name of the stat
     */
    public TextColor color() { return NamedTextColor.GRAY; }

    /**
     * @param value value used as multiplier
     * @return color of the value along with the modifier character
     */
    public TextColor coloredValue(double value) { return NamedTextColor.DARK_BLUE; }

    public final Component toComponent(double value, E entity) {
        String name;
        if (this.getClass().isAnnotationPresent(StatsInfo.class)) {
            StatsInfo info = this.getClass().getAnnotation(StatsInfo.class);
            if (info.name().isEmpty()) name = Config.getString("stats.no_one");
            else name = info.name();
        } else {
            if (name().isEmpty()) name = Config.getString("stats.no_one");
            else name = name();
        }
        return Component.text(name + ":")
                .color(color())
                .appendSpace()
                .append(Component.text(findCharacterBasedOnOperation(modifies(entity).getModifier().getOperation()) + value)
                        .color(coloredValue(value)));
    }
    private char findCharacterBasedOnOperation(AttributeModifier.Operation op) {
        if (op == AttributeModifier.Operation.MULTIPLY_SCALAR_1) return '*';
        else return '+';
    }

    @Getter
    public static class Modifier {
        private Attribute attribute;
        private AttributeModifier modifier;

        private Modifier(Attribute attribute) {
            this.attribute = attribute;
        }
        public Modifier multiplies(String name, double amount) {
            modifier = new AttributeModifier(name, amount, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
            return this;
        }
        public Modifier add(String name, double amount) {
            modifier = new AttributeModifier(name, amount, AttributeModifier.Operation.ADD_NUMBER);
            return this;
        }

        public static Modifier forAttribute(Attribute attribute) {
            return new Modifier(attribute);
        }
    }
}
