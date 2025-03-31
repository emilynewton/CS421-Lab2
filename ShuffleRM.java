public class ShuffleRM {
    
    int i = 0; 
    int j = 0; 
    //* */
    static Boolean[][] memo; 

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Requires at least 3 arguments: java ShuffleDP <X> <Y> <Z> [<debug level>]"); 
            return; 
        }

        String X = args[0]; 
        String Y = args[1]; 
        String Z = args[2]; 

        if (args[3] != null) {
            int debug = Integer.parseInt(args[3]); 
        }
        
        if (X.length() + Y.length() != Z.length()) {
            System.out.println("Z must be the length of X and Y combined."); 
            return; 
        }

        memo = new Boolean[X.length() + 1][Y.length() + 1]; 

        boolean result = isShuffleRM(X, Y, Z, 0, 0);  
        System.out.println(result ? "yes" : "no"); 

    }


    public static boolean isShuffleRM(String X, String Y, String Z, int i, int j) {
    
        // base case, X and Y are either empty strings or are exhausted 
        if (X.length() == i && Y.length() == j) {
            return true; 
        }

        if (memo[i][j] != null) {
            return memo[i][j]; 
        }

        // comparing X and Z if X is nonempty 
        if (i < X.length() && X.charAt(i) == Z.charAt(i + j)) {
            // recursion using next character in X 
            if (isShuffleRM(X, Y, Z, i + 1, j)) {
                return memo[i][j] = true; 
            }
        }
        // comparing Y and Z if Y is nonempty 
        if (j < Y.length() && Y.charAt(j) == Z.charAt(i + j)) {
           if (isShuffleRM(X, Y, Z, i, j + 1)) {
                return memo[i][j] = true; 
           }
        } 

        return memo[i][j] = false; 
    }
}
