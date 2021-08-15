import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Arrays;

public class Data {
    // read the Data
    static void readData(String fileName) throws ParseException {

        try {
            Scanner fileIn = new Scanner(new File(fileName));
            float array[] = new float[fileIn.nextInt()]; // get the size of the file
            int index = 0;
            while (fileIn.hasNextLine()) {
                String str = fileIn.nextLine();
                String number = str.substring(str.indexOf(' ') + 1, str.length()).trim();
                float t = floatFromStringOrZero(str.split(" ")[0]);
                index = (int) t;
                array[index] = floatFromStringOrZero(number);
                // index++;
            }
            fileIn.close();

            for (int i = 0; i < array.length; i++) {
                System.out.println(Arrays.toString(array));
            }

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

    static Float floatFromStringOrZero(String s) {
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

    public static void main(String[] args) {
        try {
            readData("sampleInput/sampleInput100.txt");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}