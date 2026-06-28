class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Sort the array in ascending order
        Arrays.sort(arr);
      
        // First element must be 1 according to problem constraints
        arr[0] = 1;
      
        // Initialize the maximum element found so far
        int maxElement = 1;
      
        // Iterate through the array starting from the second element
        for (int i = 1; i < arr.length; ++i) {
            // Calculate the difference between current and previous element minus 1
            // This ensures the absolute difference between adjacent elements is at most 1
            int excessDifference = Math.max(0, arr[i] - arr[i - 1] - 1);
          
            // Decrease current element to maintain the constraint
            // After this operation, arr[i] - arr[i-1] <= 1
            arr[i] -= excessDifference;
          
            // Update the maximum element found
            maxElement = Math.max(maxElement, arr[i]);
        }
      
        // Return the maximum element in the modified array
        return maxElement;
    }
}
