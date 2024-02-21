package gh2;

 import deque.ArrayDeque;
 import deque.Deque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //create buffer with capacity sr / freq, cast result into an int
        int capacity = (int) Math.round(SR / frequency);
        //create deque of size capacity, ensuring that all default elements are 0;
        buffer = new ArrayDeque<>();
        //iterate through each el in the items array and set all the values to 0
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int originalSize = buffer.size();
        while (buffer.size() >= 0 ) {
            buffer.removeFirst();
        }
        //now add all the random values back to the buffer
        int count = 0;
        while (count <= originalSize){
            double r = Math.random() - 0.5;
            buffer.addLast(r);
            count++;
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return 0;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
