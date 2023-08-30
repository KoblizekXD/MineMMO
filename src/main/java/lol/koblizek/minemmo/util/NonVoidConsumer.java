package lol.koblizek.minemmo.util;

@FunctionalInterface
public interface NonVoidConsumer<T, R> {
    R apply(T t);
}
