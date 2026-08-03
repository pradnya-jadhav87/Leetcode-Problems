class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        // Start from the last stone
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;
            dp[i] = Integer.MIN_VALUE;

            // Alice can take 1, 2, or 3 stones
            for (int j = i; j < Math.min(i + 3, n); j++) {
                sum += stoneValue[j];
                dp[i] = Math.max(dp[i], sum - dp[j + 1]);
            }
        }

        if (dp[0] > 0)
            return "Alice";
        else if (dp[0] < 0)
            return "Bob";
        else
            return "Tie";
    }
}
