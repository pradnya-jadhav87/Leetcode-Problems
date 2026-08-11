class Solution {
    public int missingInteger(int[] nums) {
        int length = nums.length;

        boolean[] present = new boolean[1276];

        int sequentialSum = nums[0];

        // Populating the presence array
        for (int index = 0; index < length; index++) {
            present[nums[index]] = true;
        }

        // Finding the sum of the longest sequential prefix
        for (int index = 1;
             index < length && nums[index] == nums[index - 1] + 1;
             sequentialSum += nums[index++]);

        // Finding the smallest missing number
        while (present[sequentialSum]) {
            sequentialSum++;
        }

        return sequentialSum;
    }
}
