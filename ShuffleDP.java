import java.util.*;

public class ShuffleDP {
    static int tableReferences = 0;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java ShuffleDP <X> <Y> <Z> [<debug level>]");
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

        // Initialize DP table where dp[i][j] represents whether X[0..i-1] and Y[0..j-1] can form Z[0..i+j-1]
        int[][] dp = new int[X.length() + 1][Y.length() + 1];
        dp[0][0] = 1; 

        // Fill the DP table row by row (bottom-up approach)
        for (int i = 0; i <= X.length(); i++) {
            for (int j = 0; j <= Y.length(); j++) {
                //tableReferences++;  // Increment table reference count

                // Skip if this subproblem has already been solved
                if (i == 0 && j == 0) continue;

                // If we're looking at dp[i][j], check if we can form Z[i+j-1]
                if (i > 0 && X.charAt(i - 1) == Z.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i - 1][j];
                    tableReferences++; 
                }

                if (j > 0 && Y.charAt(j - 1) == Z.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i][j - 1];
                    tableReferences++; 
                }
            }
        }
        // The final result is in dp[X.length()][Y.length()]
        int result = dp[X.length()][Y.length()];

        // Output the result and the number of table references
        System.out.println("Result: " + (result == 1 ? "Yes" : "No"));
        System.out.println("Number of table references: " + tableReferences);
        
        // Debug output (print the DP table if debug level 1 is set)
        if (debugLevel == 1) {
            
            try {
                File ShuffleDPTable = new File("ShuffleDP-Table.txt"); 
                FileWriter myWriter = new FileWriter(ShuffleDPTable);  
                myWriter.write(printTable(dp));
                myWriter.close(); 
            } catch (IOException e) {
                System.out.println("An error occured."); 
                e.printStackTrace(); 
            }
        }
    }


    private static String printTable(int[][] dp) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                sb.append(dp[i][j]).append(" "); 
            }
            sb.append("\n"); 
        }
        return sb.toString(); 
    }
}
