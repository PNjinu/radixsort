import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RadixSort {
    public static void main(String[] args) throws FileNotFoundException {
        // Loading the desserts file.
        File file = new File("D:\\RadixSort\\src\\Favorite_desserts.txt");
        Scanner scan = new Scanner(file);

        ArrayList linesList = new ArrayList();
        while (scan.hasNextLine()) {
            //System.out.println(scan.nextLine());
            linesList.add(scan.nextLine());
        }
        System.out.println(linesList);

        String[] str = GetStringArray(linesList);
        for (int i=0; i<str.length; ++i) {
            str[i] = str[i].toLowerCase();
            // Stripped the space for less complex sorting.
            str[i] = str[i].replace(" ", "");
        }
        // Print Array elements
        System.out.println("String Array[]: " + Arrays.toString(str));
        //System.out.println("Sorted: ");
        radixSort(str,'a','z');
    }

    //Convert the ArrayList into an Array
    public static String[] GetStringArray(ArrayList<String> arr) {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    // 27 buckets, 26 for the alphabets and the 27th for space.
    // 20 passes (Counting sorts are the passes).

    public static void CountingSort(String[] str, int index, char lower,char upper) {
        //take first character from the lines.
        // To use Radix, go to the next character after.
        int[] countArray = new int[(upper-lower)+2];
        String[] tempArray = new String[str.length];
        Arrays.fill(countArray,0);

        //increase count for char at index
        for(int i=0;i<str.length;i++){
            int charIndex = (str[i].length()-1 < index) ? 0 : ((str[i].charAt(index) - lower)+1);
            countArray[charIndex]++;
        }

        //sum up countArray; countArray will hold last index for the char at each string's index
        for(int i=1;i<countArray.length;i++){
            countArray[i] += countArray[i-1];
        }

        for(int i=str.length-1;i>=0;i--){
            int charIndex = (str[i].length()-1 < index) ? 0 : (str[i].charAt(index) - lower)+1;
            tempArray[countArray[charIndex]-1] = str[i];
            countArray[charIndex]--;
        }

        for(int i=0;i<tempArray.length;i++){
            str[i] = tempArray[i];
        }


        for(int i=0;i<str.length;i++){
            System.out.println(str[i]);
        }

    }

    public static void radixSort(String[] arr, char lower,char upper){
        int maxIndex = 19;
        for(int i=maxIndex; i>=0; i--) {
            System.out.println("Iterating on index: "+ i);
            CountingSort(arr, i, lower, upper);
        }

        System.out.println("Done...");
    }
}