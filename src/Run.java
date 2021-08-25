import java.util.concurrent.ForkJoinPool;
import java.util.Arrays;
import java.text.ParseException;
public class Run{

    static long startTime = 0;
    static ForkJoinPool fjPool = new ForkJoinPool();
    public static void main(String[] args) {
        try {
            Data c = new  Data();
            c.readData(args[0]);
            int width = Integer.parseInt(args[1]);
            c.setWidth(width);
            int mid = width/2;

            int ind [] = new int[c.size -2*mid];
            
            int n =0;
            for(int i = mid ;i<c.size - mid;i++){
                ind[n] = i;
                n++;
            }

 
            float [] data = Arrays.copyOfRange(c.array,0,c.size);
        
            float d[] = Arrays.copyOfRange(c.array,0,c.size);
           
            if(width<3 || width>21){
                System.out.print("Invalid filter width!!!\n");
                System.exit(0);
            }
            float ans1 = 0;
            for (int k = 0; k < 20; k++) {
                tick();
                c.testArray(data);
                float t = tock();
                ans1 += t;
                
            }
            System.gc();

            System.out.println("The average time " + ans1 / 20 + " seconds in series\n");

            //System.out.println(Arrays.toString(d));
       
            float ans = 0;
            for (int i = 0; i < 20; i++) {
                tick();
                midfil1(d, ind,width);
                float t1 = tock();
                ans += t1;
            }
            System.gc();
            //System.out.println(Arrays.toString(d));
            System.out.println("The average time " + ans / 20 + " seconds in Parallel");
            
            System.out.println("Speed-up :"+ans1/ans);
           
            

            c.testArray(c.array);
           
            c.writeData(args[2]); // writing the results into a given file 
            

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    static void midfil1(float [] p,int []index,int w) {
        fjPool.invoke(new MyThread(0,index.length,p,index,w));
    }

    private static void tick() {
        startTime = System.nanoTime();
    }

    /*private static float tock() {
        return (System.nanoTime() - startTime) / 1000000000.0f;
    }*/
    private static float tock() {
		return (System.nanoTime() - startTime) / 1000000000.0f;
	}
}