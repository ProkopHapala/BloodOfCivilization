
package BoC.utils;

@FunctionalInterface
public interface StringParser<T> {
    public T parse(String s);
}
