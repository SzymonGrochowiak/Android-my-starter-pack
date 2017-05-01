package com.szymongrochowiak.androidstarterpack.test.utils;

import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

/**
 * @author Szymon Grochowiak
 */

public class TestUtils {

    // no instance of this class
    private TestUtils() {
    }

    public static VerificationMode onlyOnce() {
        return Mockito.times(1);
    }
}
