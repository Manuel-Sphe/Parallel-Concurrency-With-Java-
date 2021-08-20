import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;

public class Data {
    // read the Data
    public float array[];
    public int width;

    public void readData(String fileName) throws ParseException {

        try {
            Scanner fileIn = new Scanner(new File(fileName));
            array = new float[fileIn.nextInt()]; // get the size of the file
            int index = 0;
            while (fileIn.hasNextLine()) {
                String str = fileIn.nextLine();
                String number = str.substring(str.indexOf(' ') + 1, str.length()).trim();

                float t = floatFromStringOrZero(str.split(" ")[0]);
                index = (int) t;
                array[index] = floatFromStringOrZero(number);

            }
            fileIn.close();
            // indices = testArray(indices, 3);

        }

        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(0);
        }

        catch (java.util.InputMismatchException e) {
            System.out.println("Malformed input file: " + fileName);
            e.printStackTrace();
        }

    }
    public void writeData(String fileName){
        try{
            FileWriter fileW = new FileWriter(fileName);
            PrintWriter pWriter = new PrintWriter(fileW);
            pWriter.println(array.length);
            for(int i = 0;i<array.length;i++){
                pWriter.println(i+" "+array[i]);
            }
            pWriter.close();
        }
        catch(IOException e){
            System.out.println("Unable to open the file "+fileName);
            e.printStackTrace();
        }
    }

    public Float floatFromStringOrZero(String s) {
        Float val = Float.valueOf(0);
        try {
            val = Float.valueOf(s);
        } catch (NumberFormatException ex) {
            DecimalFormat df = new DecimalFormat();
            Number n = null;
            try {
                n = df.parse(s);
            } catch (ParseException ex2) {
            }
            if (n != null)
                val = n.floatValue();
        }
        return val;
    }

    public int[] testArray(int wid) {
        setWidth(wid);
        int i = 0;
        int mid = wid / 2;
        int ind[] = new int[array.length - 2 * mid];
        for (int j = mid; j < array.length - mid; j++) {
            ind[i] = j;
            i++;
        }

        return ind;
    }

    public void midFilter(int n[]) {
        float cal[] = new float[getWidth()];

        for (int i = 0; i < n.length; i++) {
            cal = subArray(n[i]);

            array[n[i]] = sortMed(cal);
            // System.out.println(Arrays.toString(cal));

        }
        // System.out.println(Arrays.toString(a));

    }

    public float[] subArray(int index) {
        int mid = getWidth() / 2;
        float arr[] = new float[getWidth()];

        // copy from left to right
        int j = -1;
        for (int i = index - mid; i < index; i++) {
            arr[++j] = array[i];

        }

        // copy of from the mid to the right side
        int p = mid;
        for (int k = index; k < index + mid + 1; k++) {
            arr[p++] = array[k];
        }

        return arr;
    }

    public float sortMed(float a[]) {
        Arrays.sort(a);
        float med = 0;
        int n = a.length;
        if (n % 2 == 1) {
            med = a[(n + 1) / 2 - 1];
        } else {
            med = (a[n / 2 - 1] + a[n / 2]) / 2;
        }
        return med;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int w) {
        this.width = w;
    }
}