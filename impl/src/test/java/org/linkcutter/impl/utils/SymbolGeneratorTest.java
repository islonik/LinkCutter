package org.linkcutter.impl.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lipatov Nikita
 */
public class SymbolGeneratorTest {

    @Test
    public void primitiveTest() {
        for (int i = 0; i < 100000; i++) {
            char symbol = SymbolGenerator.generateSymbol();
            Assert.assertNotNull(symbol);
        }
    }
}
