//package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Schody {
    private static Map<Integer,Integer> memo = new HashMap<>();
    static {
        memo.put(0,1);
    }

    public static int pocet12(int n){
        return pocet(n, new int[]{1,2});
    }

    public static int pocet123(int n){
        return pocet(n, new int[]{1,2,3});
    }

    public static int pocetMem(int n, int[] kroky) {
        if (n < 0){
            return 0;
        }
        // n<0?0:memo.computeIfAbsent(n, m -> Arrays.stream(kroky).map(x -> pocet(m-x, kroky)).reduce(0, (prt, cur) -> prt + cur))
        Integer res = memo.get(n);
        if (res == null){
            res = Arrays.stream(kroky).map(x -> pocetMem(n-x, kroky)).reduce(0, (prt, cur) -> prt + cur);
            memo.put(n, res);
        }
        return res;
    }

    public static int pocet(int n, int[] kroky){
        int res = pocetMem(n, kroky);
        memo.clear();
        memo.put(0,1);
        return res;
    }
}
