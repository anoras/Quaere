package org.quaere.alias.test;

import static org.quaere.alias.CompareType.SMALLER;
import static org.quaere.alias.ListProvider.alias;
import static org.quaere.alias.ListProvider.from;
import static org.quaere.alias.ListProvider.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.quaere.alias.ListProvider.*;

public class NonPublicFieldTest {
    
    @Test
    public void testStaticAlias() {
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        List<Integer> lowNums = from(numbers, A)
            .where(test(A, SMALLER, 5))
            .select();
        String result = "";
        for (Integer t : lowNums) {
            result += t + ";";
        }
        Assert.assertEquals(result, "4;1;3;2;0;");
    }

    @Test
    public void testNonPublicClass() {
        class Test {
            Integer x;
        }
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        Integer n = alias(numbers);
        Test x = template(Test.class);
        List<Test> lowNums = from(n)
            .where(test(n, SMALLER, 5))
            .select(x, set(x.x, n));
        String result = "";
        for (Test t : lowNums) {
            result += t.x + ";";
        }
        Assert.assertEquals(result, "4;1;3;2;0;");
    }
    
}
