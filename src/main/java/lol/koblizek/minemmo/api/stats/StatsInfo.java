package lol.koblizek.minemmo.api.stats;

import lol.koblizek.minemmo.util.Config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface StatsInfo {
    String name() default "";
}
