public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
        for(int i = 0; i < a.length; i++) {
            int totalSum = 0;
            if (a[i] < 0 )
                continue;
             else {
                 int upperLimit = i + n;
                for(int j = i; (j <= (i + n)  && j < a.length); j++){
                    totalSum = totalSum + a[j];
                }
                a[i] = totalSum;
            }
        }
    }
//    good link to remember concatenation between strings and numbers:
//    https://stackoverflow.com/questions/12028779/concatenating-string-and-numbers-java
    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);
        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
