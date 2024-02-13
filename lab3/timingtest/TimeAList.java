package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
       //instantiate an array of size according to the table
        int [] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 120000};
        //instantiate 3 Array lists to pass into print timing table function
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        //iterate through the sizes array with for in loop
         for(Integer size : sizes) {
             //add the current size to the ns and the opCounts list
             Ns.addLast(size);
             opCounts.addLast(size);
             //need to calculate the time it takes for the AList functionality to work
             //instanstiate a dummy AList for the sole purpose of calculating how long it takes to complete add last function
             AList<Integer> dummyList = new AList<>();
             //create a loop to iterate from 1 to current size to add
             //initialize counter variable
             int counter = 1;
             //declare stopwatch variable and assign it the location of a stopwatch instance prior to the start of the loop
             Stopwatch sw = new Stopwatch();
             while (counter <= size) {
                 dummyList.addLast(counter);
                 counter++;
             }
             //declare double variable time in seconds to capture how much time has passed after calling the function
             double timeInSeconds = sw.elapsedTime();
             //add the timeInSeconds variable to the times AList
             times.addLast(timeInSeconds);
         }
            // call the print function passing in the three ALists
        printTimingTable(Ns, times, opCounts);
    }
}
