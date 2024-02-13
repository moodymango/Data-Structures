package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        //create an array of size
        int [] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        //create 3 A lists which  we will pass into our printTimingTable function
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        int opCount = 10000;
        AList<Integer> opCounts = new AList<>();
        //iterate through the sizes array
        for (Integer size : sizes) {
            //add each size to Ns
            Ns.addLast(size);
            //add 10000 to opCounts
            opCounts.addLast(opCount);
            //instantiate a new SLList
            SLList<Integer> dummyList = new SLList<>();
            int count = 1;
            while (count <= size) {
                //add n items to the s list
                dummyList.addLast(count);
                count++;
            }
            System.out.println("s list size is " + dummyList.size());
            //start the timer by instantiating new timer instance
            Stopwatch sw = new Stopwatch();
            //add total time in seconds to the times list
            int newCount = 0;
            //need to envelop getLast function within the number of function calls, because this is how long the function ultimately takes to complete
            while(newCount <= opCount) {
                dummyList.getLast();
                newCount++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        System.out.println("sizes of the arrayLists are " + Ns.size() + " " + opCounts.size() + " " + times.getLast());
        printTimingTable(Ns, times, opCounts);
    }

}
