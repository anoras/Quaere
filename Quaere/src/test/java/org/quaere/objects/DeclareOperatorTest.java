package org.quaere.objects;

import static org.quaere.DSL.*;
import org.junit.Test;
import junit.framework.Assert;

public class DeclareOperatorTest {
    @Test
    public void canUseDeclareToDeclareVariable() {
        String[] words = {"cherry", "apple", "blueberry"};
        Iterable<Integer> wordLengths =
                from("w").in(words).
                        declare("wordLength").as("w.length()").
                        select("wordLength");

        int index = 0;
        for (int wordLength : wordLengths) {
            Assert.assertEquals(words[index++].length(), wordLength);
        }

    }
}
