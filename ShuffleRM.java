
import java.util.*;
import java.io.*; 

public class ShuffleRM {
    static int[][] memo;
    static int references = 0;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java ShuffleRM <X> <Y> <Z> [<debug level>]");
            return;
        }
        
        String X = args[0];
        String Y = args[1];
        String Z = args[2];
        int debugLevel = (args.length == 4) ? Integer.parseInt(args[3]) : 0;
        
        if (X.length() + Y.length() != Z.length()) {
            System.out.println("Incompatible lengths of X, Y, and Z.");
            return;
        }

        memo = new int[X.length() + 1][Y.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);  // Initialize all table values to -1
        }

        boolean result = isShuffle(X, Y, Z, X.length(), Y.length());

        if (debugLevel == 1) {
            try {
                File ShuffleRMTable = new File("ShuffleRM-Table.txt"); 
                FileWriter myWriter = new FileWriter(ShuffleRMTable);  
                myWriter.write(printTable());
                myWriter.close(); 
            } catch (IOException e) {
                System.out.println("An error occured."); 
                e.printStackTrace(); 
            }
        }

        System.out.println("Result: " + (result ? "yes" : "no"));
        System.out.println("Number of table references: " + references);
        

    }


    public static boolean isShuffle(String X, String Y, String Z, int i, int j) {
        references++;
        // If already computed, return stored value
        if (memo[i][j] == 0) {
            return false; 
        }

        if (i == 0 && j == 0) {
            memo[i][j] = 1; 
            return true; 
        } 
        else if (i > 0 && j == 0) {
            if (X.charAt(i - 1) == Z.charAt(i - 1) && isShuffle(X, Y, Z, i - 1, j)) {
                memo[i][j] = 1;
                return true; 
            }
        } 
        else if (i == 0 && j > 0) {
            if (Y.charAt(j - 1) == Z.charAt(j - 1) && isShuffle(X, Y, Z, i, j - 1)) {
                memo[i][j] = 1;
                return true; 
            }
        } 
        else if (i > 0 && j > 0) {
            if ((X.charAt(i - 1) == Z.charAt(i + j - 1) && isShuffle(X, Y, Z, i - 1, j)) || (Y.charAt(j - 1) == Z.charAt(i + j - 1) && isShuffle(X, Y, Z, i, j - 1))) {
                memo[i][j] = 1;
                return true; 
            }
        }

        memo[i][j] = 0;
        return false; 
        
    }

    /** 
     * Prints either the optimal value table or the decision table
     */
    private static String printTable() {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                sb.append(memo[i][j]).append(" "); 
            }
            sb.append("\n"); 
        }
        return sb.toString(); 
    }
}
