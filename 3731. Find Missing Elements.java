class Solution {
    public List<Integer> findMissingElements(int[] numbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        int minimumValue = Integer.MAX_VALUE;
        int maximumValue = Integer.MIN_VALUE;

        for (int currentNumber : numbers) {
            uniqueNumbers.add(currentNumber);
            minimumValue = Math.min(minimumValue, currentNumber);
            maximumValue = Math.max(maximumValue, currentNumber);
        }

        List<Integer> missingNumbers = new ArrayList<>();

        for (int value = minimumValue; value <= maximumValue; value++) {
            if (!uniqueNumbers.contains(value)) {
                missingNumbers.add(value);
            }
        }

        return missingNumbers;
    }
}
