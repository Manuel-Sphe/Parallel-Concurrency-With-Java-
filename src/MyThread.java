import java.text.ParseException;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class MyThread extends RecursiveAction {
    static long startTime = 0;
    static ForkJoinPool fjPool = new ForkJoinPool();
    static final int SEQUENTIAL_CUTOFF = 4000;
    int lo;
    int hi; // Fieds for communicating input
    int array[];
    static Data c = new Data();

    public MyThread(int Data[], int l, int h) {
        this.lo = l;
        this.hi = h;
        this.array = Data;
    }

    public void compute() {
        if ((hi - lo) <= SEQUENTIAL_CUTOFF) {
            c.midFilter(array);
        }
        else {
            MyThread left = new MyThread(array, lo, (lo + hi) / 2);
            MyThread right = new MyThread(array, (lo + hi) / 2, hi);
            left.fork(); // starting the thread
            right.compute();
            left.join(); // waiting for thread to finish

        }
    }

    public static void main(String[] args) {
        try {
            c.readData(args[0]);
            int width = Integer.parseInt(args[1]);

            float ans1 = 0;
            for (int k = 0; k < 20; k++) {
                tick();
                c.midFilter(c.testArray(width));
                float time2 = tock();
                ans1 += time2;
            }

            System.out.println("The average time " + ans1 / 20 + " seconds in series");

            float ans = 0;
            for (int i = 0; i < 20; i++) {
                tick();
                midfil1(c.testArray(width));
                float time = tock();
                ans += time;
            }
            System.gc();
            
            System.out.println("The average time " + ans / 20 + " seconds in Parallel");
            
            c.writeData(args[2]);
            

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    static void midfil1(int[] p) {
        fjPool.invoke(new MyThread(p, 0, p.length));
    }

    private static void tick() {
        startTime = System.nanoTime();
    }

    private static float tock() {
        return (System.nanoTime() - startTime) / 1000000000.0f;
    }
}