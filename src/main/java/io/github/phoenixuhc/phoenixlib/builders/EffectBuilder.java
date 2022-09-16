package io.github.phoenixuhc.phoenixlib.builders;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Utility builder class to create potion effects.
 */
public class EffectBuilder {
    private final PotionEffectType type;
    private int duration = Integer.MAX_VALUE;
    private int level = 0;

    private byte mask = 0;

    /**
     * @param type the type for this potion effect.
     */
    public EffectBuilder(PotionEffectType type) {
        this.type = type;
    }

    /**
     * @param duration the duration of this effect, defaults to an infinite duration.
     * @return this
     */
    public EffectBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * @param level the level for this potion effect, defaults to 0.
     * @return this
     */
    public EffectBuilder level(int level) {
        this.level = level;
        return this;
    }

    /**
     * Whether the ambient is enabled for this potion effect.
     *
     * @return this
     */
    public EffectBuilder showAmbient() {
        this.mask = (byte) (this.mask | 0x01);
        return this;
    }

    /**
     * Whether the particles are enabled for this potion effect
     *
     * @return this
     */
    public EffectBuilder showParticles() {
        this.mask = (byte) (this.mask | 0x02);
        return this;
    }

    /**
     * Builds a potion effect from the specified parameters.
     *
     * @return the potion effect.
     */
    public PotionEffect build() {
        return new PotionEffect(type, duration, level, (this.mask & 0x01) == 0x01, (this.mask & 0x02) == 0x02);
    }
}

