package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    /* compares which string comes earlier in the alphabet */
    public class strComparator implements Comparator<String> {
        //returns < 0 if str1 has less char
        // > 0
        @Override
        public int compare (String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
    /*compares which str contains the most chars*/
    public class strLengthComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            int len1 = str1.length();
            int len2 = str2.length();
            if (len1 - len2 > 0) {
                return 1;
            } else if (len1 - len2 < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
   /* compares which int is the biggest*/
    public class intComparator implements Comparator<Integer> {
        @Override
       public int compare(Integer num1, Integer num2) {
            return num1 - num2;
        }
   }

    @Test
    public void testComparator () {
        String [] testArr = {"Howdy", "Hiya", "Hello", "Greetings", "Sup", "Good Morning"};
        //instantiate instance of strComparator
        Comparator<String> comp = new strComparator();
        //create new MaxDeque
        MaxArrayDeque<String> testDeque = new MaxArrayDeque<>(comp);
        for (String word : testArr) {
            testDeque.addLast(word);
        }
        System.out.println(testDeque.get(testDeque.size - 1));
        String first = testDeque.get(0);
        String last = testDeque.get(testDeque.size - 1);

        System.out.println(testDeque.comp.compare(first, last)); // "h" is 1 times greater than G, aka comes later in the alphabet
    }
    @Test
    public void maxArrayDequeMax () {
        String [] testArr = {"Howdy", "Hiya", "Hello", "Greetings", "Sup", "Good Morning"};
        //instantiate instance of strLengthComparator
        Comparator<String> comp = new strLengthComparator();
        //create new MaxDeque
        MaxArrayDeque<String> testDeque = new MaxArrayDeque<>(comp);
        for (String word : testArr) {
            testDeque.addLast(word);
        }
        String maxStr = testDeque.max();
        System.out.println("Max str is "+ maxStr);
        assertEquals(maxStr, testDeque.removeLast());
    }
    @Test
    public void maxArrayDequeRandomized() {
        Comparator<Integer> comp = new intComparator();
        MaxArrayDeque<Integer> testD = new MaxArrayDeque<>(comp);
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNum = StdRandom.uniform(0, 4);
            if (operationNum == 0) {
                int randVal = StdRandom.uniform(0, 500);
                testD.addLast(randVal);
            } else if (operationNum == 1) {
                int randVal = StdRandom.uniform(0, 500);
                testD.addFirst(randVal);
            } else if (operationNum == 2) {
                if(testD.size() > 0) {
                    testD.removeFirst();
                }
            } else {
                if(testD.size() > 0) {
                    testD.removeLast();
                }
            }
        }
        int maxVal = testD.max(comp);
        System.out.println();
        System.out.println("max val in the testD is " + maxVal);
    }
}
