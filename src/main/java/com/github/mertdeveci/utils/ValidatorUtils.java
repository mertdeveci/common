package com.github.mertdeveci.utils;

import java.util.function.Supplier;

public class ValidatorUtils {

    public static <E extends Exception> void throwIf(boolean valid, Supplier<E> e) throws E {
        if(valid) {
            throw e.get();
        }
    }

    public static <E extends Exception> void throwIfNot(boolean valid, Supplier<E> e) throws E {
        if(!valid) {
            throw e.get();
        }
    }

}
