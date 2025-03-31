import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class KnapsackRM {


    static Boolean[][] memo; 

    int i = 0; 
    int j = 0; 

    public static void main(String[] args) { 
        if (args.length < 4) {
            System.out.println("Requires at least 4 arguments: java KnapsackRM <n> <W> <w.txt> <v.txt> [<debug level>]");
            return; 
        }

        int numItems = Integer.parseInt(args[0]); 
        int maxWeight = Integer.parseInt(args[1]);

        int[] weights = new int[numItems];
        int[] values = new int[numItems]; 

        try {
            File weightFile = new File(args[2]); 
            Scanner weightScan = new Scanner(weightFile);

            for (int m = 0; m < numItems; m++) {
                if (weightScan.hasNextInt()) {
                    weights[m] = weightScan.nextInt(); 
                }
            }

            weightScan.close(); 

            File valueFile = new File(args[3]); 
            Scanner valueScan = new Scanner(valueFile);

            for (int n = 0; n < numItems; n++) {
                if (valueScan.hasNextInt()) {
                    values[n] = valueScan.nextInt(); 
                }
            }

            valueScan.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found."); 
            return; 
        }
        
         
        int debug = Integer.parseInt(args[4]); 

    }

    public static int knapsack(int numItems, int maxWeight, int[][] weights, int[][] values, int i, int j) {
        // i represents number of items you are considering from 1 to n
        // j represents remaining capacity of the knapsack from 0 to W 

        // base case - number of items is 0, max weight is 0, return 0
        if (numItems == 0 && maxWeight == 0) {
            return 0; 
        }

        if (memo[i][j] != null) {
            return memo[i][j]; 
        }

        if ()

        return 0;  
    }
}