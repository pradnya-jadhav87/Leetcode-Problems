import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int totalOne = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '1') {
                totalOne++;
            }
        }

        int[] start = new int[n];
        int[] length = new int[n];
        int[] group = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == '0') {
                if (i > 0 && arr[i - 1] == '0') {
                    length[count - 1]++;
                } else {
                    start[count] = i;
                    length[count] = 1;
                    count++;
                }
            }
            group[i] = count - 1;
        }

        int q = queries.length;
        List<Integer> result = new ArrayList<>(q);

        if (count == 0) {
            Integer value = totalOne;
            for (int i = 0; i < q; i++) {
                result.add(value);
            }
            return result;
        }

        int[][] table = null;

        if (count > 1) {
            int size = count - 1;
            int[] base = new int[size];

            for (int i = 0; i < size; i++) {
                base[i] = length[i] + length[i + 1];
            }

            int level = 32 - Integer.numberOfLeadingZeros(size);
            table = new int[level][];
            table[0] = base;

            for (int i = 1; i < level; i++) {
                int jump = 1 << (i - 1);
                int len = size - (1 << i) + 1;
                int[] prev = table[i - 1];
                int[] curr = new int[len];

                for (int j = 0; j < len; j++) {
                    curr[j] = Math.max(prev[j], prev[j + jump]);
                }

                table[i] = curr;
            }
        }

        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];

            int leftGroup = group[left];
            int rightGroup = group[right];

            int leftPart = (leftGroup == -1) ? -1 : (length[leftGroup] - (left - start[leftGroup]));
            int rightPart = (rightGroup == -1) ? -1 : (right - start[rightGroup] + 1);

            int endGroup = (arr[right] == '1') ? rightGroup : rightGroup - 1;
            int first = leftGroup + 1;
            int last = endGroup - 1;

            int answer = totalOne;

            if (arr[left] == '0' && arr[right] == '0' && leftGroup + 1 == rightGroup) {
                answer = Math.max(answer, totalOne + leftPart + rightPart);
            } else if (first <= last) {
                int k = 31 - Integer.numberOfLeadingZeros(last - first + 1);
                int[] row = table[k];
                int best = Math.max(row[first], row[last - (1 << k) + 1]);
                answer = Math.max(answer, totalOne + best);
            }

            if (arr[left] == '0' && leftGroup + 1 <= endGroup) {
                answer = Math.max(answer, totalOne + leftPart + length[leftGroup + 1]);
            }

            if (arr[right] == '0' && leftGroup < rightGroup - 1) {
                answer = Math.max(answer, totalOne + rightPart + length[rightGroup - 1]);
            }

            result.add(answer);
        }

        return result;
    }
}
