// import java.util.HashMap; 
// public class ShuffleRM {
    
//     int i = 0; 
//     int j = 0; 
//     //* */
//     static Boolean[][] memoarray; 
//     private static HashMap<Pair<Integer, Integer>, Boolean> memo = new HashMap<>(); 
//     private static int referenceCount = 0; 


//     public static void main(String[] args) {
//         if (args.length < 3) {
//             System.out.println("Requires at least 3 arguments: java ShuffleDP <X> <Y> <Z> [<debug level>]"); 
//             return; 
//         }

//         String X = args[0]; 
//         String Y = args[1]; 
//         String Z = args[2]; 

//         if (args.length > 3) {
//             int debug = Integer.parseInt(args[3]); 
//         }
        
//         if (X.length() + Y.length() != Z.length()) {
//             System.out.println("Z must be the length of X and Y combined."); 
//             return; 
//         }

//         boolean result = isShuffleRM(X, Y, Z, 0, 0); 
//         System.out.println(result ? "yes" : "no"); 
//         System.out.println("number of recursive calls: " + referenceCount); 

//     }


//     public static boolean isShuffleRM(String X, String Y, String Z, int i, int j) {
//         referenceCount++;
//         // // base case, X and Y are either empty strings or are exhausted 
//         // if (X.length() == i && Y.length() == j) {
//         //     return true; 
//         // }

//         // Pair<Integer, Integer> key = new Pair<>(i, j); 
//         // if (memo.containsKey(key)) {
//         //     return memo.getKey(); 
//         // }

//         // boolean result = false; 

//         // // comparing X and Z if X is nonempty 
//         // if (i < X.length() && X.charAt(i) == Z.charAt(i + j)) {
//         //     result |= isShuffleRM(X, Y, Z, i + 1, j);
//         // }
//         // // comparing Y and Z if Y is nonempty 
//         // if (j < Y.length() && Y.charAt(j) == Z.charAt(i + j)) {
//         //    result |= isShuffleRM(X, Y, Z, i, j + 1); 
//         // } 

//         // memo.put(key, result); 
//         // return result; 

//         Pair<Integer, Integer> pair = new Pair<>(i, j); 
//         if (memo.containsKey(pair)) {
//             return memo.get(pair); 
//         }

//         if (i == 0 && j == 0) {
//             return table.put(pair, 1); 
//         }

//         if (i > 0 && X.charAt(i - 1) == Z.charAt(i + j - 1)) {
//             if (isShuffleRM(i - 1, j) == 1) {
//                 return memo.put(pair, 1); 
//             }
//         }

//         if (j > 0 && Y.charAt(j - 1) == Z.charAt(i + j - 1)) {
//             if (isShuffleRm(i, j - 1) == 1) {
//                 return memo.put(pair, 1); 
//             }
//         }

//         return memo.put(pair, 0); 
//     }
// }
import java.util.*;

public class ShuffleRM {
    static int[][] memo;
    static int tableReferences = 0;

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

        boolean result = isShuffle(X, Y, Z, 0, 0);
        System.out.println("Result: " + (result ? "Yes" : "No"));
        System.out.println("Number of table references: " + tableReferences);
        
        if (debugLevel == 1) {
            printTable();
        }
    }

    public static boolean isShuffle(String X, String Y, String Z, int i, int j) {
        tableReferences++;
    
        // If already computed, return stored value
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
    
        // Base case: if both strings are fully used
        if (i == X.length() && j == Y.length()) {
            memo[i][j] = 1;
            return true;
        }
    
        boolean result = false;
    
        // Try taking from X if possible
        if (i < X.length() && X.charAt(i) == Z.charAt(i + j)) {
            result |= isShuffle(X, Y, Z, i + 1, j);
        }
    
        // Try taking from Y if possible
        if (j < Y.length() && Y.charAt(j) == Z.charAt(i + j)) {
            result |= isShuffle(X, Y, Z, i, j + 1);
        }
    
        // Store result in memo table (1 for success, 0 for failure)
        memo[i][j] = result ? 1 : 0;
    
        return result;
    }
    

    /** 
     * Prints either the optimal value table or the decision table
     */
    private static void printTable() {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }
    }
}
