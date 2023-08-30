package lol.koblizek.minemmo.api.rarity;

import lol.koblizek.minemmo.util.Config;
import lol.koblizek.minemmo.util.NonVoidConsumer;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class Rarity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2183236723L;

    /**
     * Called when player damages entity with item having the
     * rarity of this class
     *
     * @param event The event involved in the damaging
     * @return whether event should be cancelled
     */
    public boolean onEntityDamage(EntityDamageByEntityEvent event) {
        return false;
    }

    /**
     * Sets the default color of this rarity
     * @return the color of this rarity
     */
    public TextColor getColor() {
        return NamedTextColor.WHITE;
    }

    /**
     * @return the name of the rarity
     */
    public String getName() {
        return Config.getString("rarity.no_name");
    }

    /**
     * Sets the decorations of rarity displaying
     * All the decorations will be directly applied to the name of the rarity
     * except the {@link TextDecoration#OBFUSCATED obfuscated tag}, which
     * will be applied as following:
     * <p>
     *     {@link TextDecoration#OBFUSCATED [tag]} {@link Rarity#getName() [rarity name]} {@link TextDecoration#OBFUSCATED [tag]}
     *     <br>where all other decorations will be applied to the obfuscated tag as well
     * </p>
     * @return decorations associated with the rarity displaying
     */
    public TextDecoration[] getDecorations() {
        return new TextDecoration[] {};
    }

    /**
     * Overwrites everything used for displaying and uses this component instead
     *
     * @return component used instead of other text modifiers
     */
    public Component asComponent() {
        return null;
    }

    /**
     * @return the multiplier of damage
     */
    public double damageModifier() {
        return 1;
    }

    public static RarityBuilder builder() {
        return new RarityBuilder();
    }

    public static class RarityBuilder {
        private NonVoidConsumer<EntityDamageByEntityEvent, Boolean> onEntityDamage = event -> false;
        private TextColor textColor = NamedTextColor.WHITE;
        private String name = Config.getString("rarity.no_name");
        private TextDecoration[] decorated = new TextDecoration[] {};
        private Component component;

        public RarityBuilder onEntityDamage(NonVoidConsumer<EntityDamageByEntityEvent, Boolean> onEntityDamage) {
            this.onEntityDamage = onEntityDamage;
            return this;
        }

        public RarityBuilder textColor(TextColor textColor) {
            this.textColor = textColor;
            return this;
        }

        public RarityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RarityBuilder decorated(TextDecoration... decorated) {
            this.decorated = decorated;
            return this;
        }

        public RarityBuilder component(Component component) {
            this.component = component;
            return this;
        }

        public Rarity build() {
            return new Rarity() {
                @Override
                public boolean onEntityDamage(EntityDamageByEntityEvent event) {
                    return onEntityDamage.apply(event);
                }

                @Override
                public TextColor getColor() {
                    return textColor;
                }

                @Override
                public String getName() {
                    return name;
                }

                @Override
                public TextDecoration[] getDecorations() {
                    return decorated;
                }

                @Override
                public Component asComponent() {
                    return component;
                }
            };
        }
    }
}
