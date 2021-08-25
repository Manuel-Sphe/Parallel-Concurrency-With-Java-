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
    public float array[];
    public int size;
    public int width; // filter width

    // reading the data
    public void readData(String fileName) throws ParseException {

        try {
            Scanner fileIn = new Scanner(new File(fileName));
            array = new float[fileIn.nextInt()]; // get the size of the file
            int index = 0;
            while (fileIn.hasNextLine()) {
                String str = fileIn.nextLine();
                String number = str.substring(str.indexOf(' ') + 1, str.length()).trim();// getting the actual float value

                float t = floatFromStringOrZero(str.split(" ")[0]); // getting the line no. and using it as an index of float array[]
                index = (int) t;
                if(index<array.length)
                    array[index] = floatFromStringOrZero(number); // writing the floats into the array
                else
                    break;

            }
            size = index+1;
            fileIn.close();

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

    //Writing the data 
    public void writeData(String fileName){
        try{
            FileWriter fileW = new FileWriter(fileName);
            PrintWriter pWriter = new PrintWriter(fileW);
            pWriter.println(array.length);
            for(int i = 0;i<array.length;i++){
                pWriter.println(i+" "+String.format("%.5f",array[i]));
            }
            pWriter.close();
        }
        catch(IOException e){
            System.out.println("Unable to open the file "+fileName);
            e.printStackTrace();
        }
    }

    //For converting String floats into floats "ab,cd"-->(ab.cd) 
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

    // returns an array of indices exclude indices in the window
    public void testArray(float [] data) {
        
        int mid = getWidth()/ 2;
        
    
        for (int j = mid; j < data.length - mid; j++) {
            
            float arr [] = new float[getWidth()] ;
            int starting = j - mid;
            
            for(int i = 0;i<arr.length;i++)
                arr[i] = data[starting++];
                
            Arrays.sort(arr);
            data[j] = arr[mid];   
        } 
    }
    // getter
    public int getWidth() {
        return this.width;
    }

    // setter
    public void setWidth(int w) {
        this.width = w;
    }
}