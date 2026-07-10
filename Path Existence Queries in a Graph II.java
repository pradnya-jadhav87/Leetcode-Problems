import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair up values with original indices and sort
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = nums[i]; // value
            sorted[i][1] = i;       // original index
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map from original index to sorted index position
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sorted[i][1]] = i;
        }
        
        // Step 2: Use sliding window to find farthest 1-step jump for each sorted position
        int LOG = 18; // 2^17 = 131,072 > 10^5, enough for binary lifting
        int[][] st = new int[n][LOG];
        
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && sorted[right][0] - sorted[left][0] <= maxDiff) {
                right++;
            }
            st[left][0] = right - 1; // Farthest valid index reachable in 1 hop
        }
        
        // Step 3: Populate binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                st[i][j] = st[st[i][j - 1]][j - 1];
            }
        }
        
        // Step 4: Process queries
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            if (u == v) {
                answer[i] = 0;
                continue;
            }
            
            // Convert original indices to sorted track positions
            int a = pos[u];
            int b = pos[v];
            if (a > b) { // Ensure a is always the smaller position
                int temp = a;
                a = b;
                b = temp;
            }
            
            int steps = 0;
            int curr = a;
            
            // Greedily make largest power-of-2 jumps possible without passing b
            for (int j = LOG - 1; j >= 0; j--) {
                if (st[curr][j] < b) {
                    curr = st[curr][j];
                    steps += (1 << j);
                }
            }
            
            // Check if one final single-hop can reach or cross b
            if (st[curr][0] >= b) {
                answer[i] = steps + 1;
            } else {
                answer[i] = -1; // Unreachable
            }
        }
        
        return answer;
    }
}
