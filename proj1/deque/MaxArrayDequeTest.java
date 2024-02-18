package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    public class strComparator implements Comparator<String> {
      /* compares the size of the string*/
        //returns < 0 if str1 has less char
        // > 0
        public int compare (String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
//    public static class ageComparator implements Comparator<Object> {
//        public int compare (Object person1, Object person2) {
//            if (person1.age < person2.age) {
//                return -1;
//            } else if (person1.age == person2.age) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//    }
    @Test
    public void createMaxArrDeque () {
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
}
