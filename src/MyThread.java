import java.text.ParseException;
import java.util.concurrent.RecursiveAction;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

class MyThread extends RecursiveAction {
    
    static final int SEQUENTIAL_CUTOFF = 5000;
    protected int lo;
    protected int hi; // Fieds for communicating input
    protected float array[];
    protected int index []; 
    protected int filter;
    
    static Data c = new Data();

    public MyThread(int l, int h,float [] data,int [] d,int w) {
        this.lo = l;
        this.hi = h;
        this.array = data;
        this.index = d;
        this.filter =w;
    }

    public void compute() {
        if ((hi - lo) < SEQUENTIAL_CUTOFF) {
                int mid = filter/2;
                
                for(int i = lo; i<hi ;i++){
                    
                    int initial = index[i] - mid;
                    float arr [] = new float [filter];
                    for(int j = 0;j<arr.length;j++){
                        arr[j] = array[initial];
                        initial++;
                    }
                    Arrays.sort(arr);
                    
                    array[index[i]] = arr[mid];

                }
            
        }
        else {

            int m =(lo+hi)/2;
            MyThread left = new MyThread(lo,m,array,index,filter);
            MyThread right = new MyThread(m,hi,array,index,filter);
            left.fork(); // starting the thread
            right.compute(); // hand-optimization
            left.join(); // waiting for thread to finish
        }
    }

}