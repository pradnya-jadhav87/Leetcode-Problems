class Solution {
    
    public int[] getNoZeroIntegers(int n) {
        // Iterate through possible values of the first integer starting from 1
        for (int firstInteger = 1; ; firstInteger++) {
            // Calculate the second integer as the difference
            int secondInteger = n - firstInteger;
          
            // Convert both integers to strings and concatenate them
            String concatenatedString = firstInteger + "" + secondInteger;
          
            // Check if the concatenated string contains the digit '0'
            // If it doesn't contain '0', both integers are non-zero integers
            if (!concatenatedString.contains("0")) {
                // Return the pair of non-zero integers
                return new int[] {firstInteger, secondInteger};
            }
        }
    }
}
