import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSucty {

    @org.junit.Test
    public void sucty() {
        assertTrue("Test 1", equals(
                List.of(4, 5, 6, 7, 8, 9, 10, 11, 12),
                Sucty.sucty(List.of(1,2,3,4,5), List.of(3,4,5,6,7))));
        assertTrue("Test 2", equals(
                List.of(4),
                Sucty.sucty(List.of(1), List.of(3))));
        assertTrue("Test 3", equals(
                List.of(4,5,6,7),
                Sucty.sucty(List.of(1,2), List.of(3,5))));

        List<Integer> lst100 = // 1..100
                IntStream.range(1,101).boxed().collect(Collectors.toList());
        List<Integer> lst200 = // 2..200
                IntStream.range(2,201).boxed().collect(Collectors.toList());
        assertTrue("Test 4", equals(
                lst200,
                Sucty.sucty(lst100, lst100)));
        List<Integer> lst300 = // 3..300
                IntStream.range(3,301).boxed().collect(Collectors.toList());
        assertTrue("Test 5", equals(
                lst300,
                Sucty.sucty(lst100, lst200)));
        assertTrue("Test 6", equals(
                lst300,
                Sucty.sucty(lst200, lst100)));
        List<Integer> lst100Even = // 2..100
                IntStream
                    .range(1,101)
                    .filter(n->n%2==0)
                    .boxed()
                    .collect(Collectors.toList());
        List<Integer> lst100Odd = // 1,3,5..99
                IntStream
                        .range(1,101)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());
        List<Integer> lst200Even = // 4..200
                IntStream
                    .range(4,201)
                    .filter(n->n%2==0)
                    .boxed()
                    .collect(Collectors.toList());

        List<Integer> temp =  Sucty.sucty(lst100Even, lst100Even);


        assertTrue("Test 7", equals(
                lst200Even,
                Sucty.sucty(lst100Even, lst100Even)));
        List<Integer> lst200Even_ = // 2..198
                IntStream
                        .range(4,201)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());
        assertTrue("Test 8", equals(
                lst200Even_,
                Sucty.sucty(lst100Odd, lst100Odd)));
    }

    @org.junit.Test
    public void suctyVela() {
        assertTrue("Test 1", equals(
                List.of(3,4,5,7),
                Sucty.sucty(List.of(List.of(3,4,5,7)))));

        assertTrue("Test 1", equals(
                List.of(4, 5, 6, 7, 8, 9, 10, 11, 12),
                Sucty.sucty(List.of(List.of(1,2,3,4,5), List.of(3,4,5,6,7)))));
        assertTrue("Test 2", equals(
                List.of(10),
                Sucty.sucty(List.of(List.of(1), List.of(3), List.of(3), List.of(3)))));
        assertTrue("Test 3", equals(
                List.of(6,7,8,9),
                Sucty.sucty(List.of(List.of(1,2), List.of(2), List.of(3,5)))));
        List<Integer> lst100 = // 1..100
                IntStream.range(1,101).boxed().collect(Collectors.toList());
        List<Integer> lst200 = // 2..200
                IntStream.range(2,201).boxed().collect(Collectors.toList());
        assertTrue("Test 4", equals(
                lst200,
                Sucty.sucty(List.of(lst100, lst100))));
        List<Integer> lst300 = // 3..300
                IntStream.range(3,301).boxed().collect(Collectors.toList());

        assertTrue("Test 5", equals(
                lst300,
                Sucty.sucty(List.of(lst100, lst200))));
        assertTrue("Test 6", equals(
                lst300,
                Sucty.sucty(List.of(lst200, lst100))));
        List<Integer> lst100Even = // 2..100
                IntStream
                        .range(1,101)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());
        List<Integer> lst100Odd = // 1,3,5..99
                IntStream
                        .range(1,101)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());
        List<Integer> lst200Even = // 4..200
                IntStream
                        .range(4,201)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());

        assertTrue("Test 7", equals(
                lst200Even,
                Sucty.sucty(List.of(lst100Even, lst100Even))));
        List<Integer> lst200Even_ = // 2..198
                IntStream
                        .range(4,201)
                        .filter(n->n%2==0)
                        .boxed()
                        .collect(Collectors.toList());
        assertTrue("Test 8", equals(
                lst200Even_,
                Sucty.sucty(List.of(lst100Odd, lst100Odd))));

        List<Integer> lst10k = // 100..100*100
                IntStream.range(100, 10001).boxed().collect(Collectors.toList());
        System.out.println(Sucty.sucty(
                IntStream.range(0,100).mapToObj(i -> lst100).collect(Collectors.toList())
        ));
        assertTrue("Test 10", equals(
                lst10k,
                Sucty.sucty(
                        IntStream.range(0,100).mapToObj(i -> lst100).collect(Collectors.toList())
                )));


    }
    private static boolean equals(List<Integer> xs, List<Integer> ys) {
        if (xs == ys)
            return true;
        else if (xs == null || ys == null)
            return false;
        else if (xs.size() != ys.size())
            return false;
        else {
            for(int i = 0; i<xs.size(); i++) {
                if (!xs.get(i).equals(ys.get(i)))
                    return false;
            }
            return true;
        }
    }
}