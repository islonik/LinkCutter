package org.linkcutter.impl.utils;

import java.util.Random;

/**
 * @author Lipatov Nikita
 */
public class SymbolGenerator {
    private static final String ALL_AVAILABLE_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTYVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static char generateSymbol() {
        Random random = new Random();
        return ALL_AVAILABLE_SYMBOLS.charAt(random.nextInt(ALL_AVAILABLE_SYMBOLS.length()));
    }
}
