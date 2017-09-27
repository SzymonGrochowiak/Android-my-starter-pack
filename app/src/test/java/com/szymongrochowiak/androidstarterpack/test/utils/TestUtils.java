package com.szymongrochowiak.androidstarterpack.test.utils;

import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

/**
 * @author Szymon Grochowiak
 */
public class TestUtils {

    public static final String TEST_URL = "http://pokeapi.co/api/v2/";

    private TestUtils() {
    }

    public static VerificationMode onlyOnce() {
        return Mockito.times(1);
    }
}
