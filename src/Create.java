import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
public class Create{
    public static void main(String[] args) {
        try{
            FileWriter fileW = new FileWriter(args[0]);
            PrintWriter pW = new PrintWriter(fileW);
            //Random ob = new Random();
            //System.out.println(String.format("%.6f",nextFloats(0.0f, 5.0f)));
            pW.println((int)Math.pow(10, 8));
            
            for(int i =0 ;i<(int)Math.pow(10, 8);i++){
               pW.println(i+" "+String.format("%.6f",nextFloats(0.0f, 5.0f))); 
            }
            pW.close();
        }
        catch(IOException e){
            System.out.println("Unable to open the file "+args[0]);
            e.printStackTrace();
        }
    }
    public static float nextFloats(float min, float max) {
        return (float) (Math.random() * (max - min)) + min;
    }

}