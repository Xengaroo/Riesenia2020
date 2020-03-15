//package com.company;

import java.util.ArrayList;

public class Suciny {
    public static long[] suciny(long[] a) {
        long[] b = new long[a.length];
        long prev = 1L, tmp, i, j;
        for (i = 0; i < a.length; i++) {
            b[(int)i] = prev;
            prev *= a[(int)i];
        }
        prev = 1L;
        for (i = a.length-1; i >= 0; i--) {
            b[(int)i] *= prev;
            prev *= a[(int)i];
        }
        return b;
    }
}
