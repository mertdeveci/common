package com.github.mertdeveci;

import com.github.mertdeveci.utils.ValidatorUtils;

public class Tester {

    public static void main(String[] args) {
        boolean valid = true;
            ValidatorUtils.throwIf(true, ()-> new UnsupportedOperationException(""));
            ValidatorUtils.throwIfNot(false, ()-> new UnsupportedOperationException(""));
    }
}
