class Solution {
    public int[] sumAndMultiply(String str, int[][] ranges) {
        long modValue = 1_000_000_007;

        int length = str.length();

        long[] digitSumPrefix = new long[length + 1];
        long[] valuePrefix = new long[length + 1];
        int[] nonZeroPrefix = new int[length + 1];
        long[] tenPower = new long[length + 1];

        tenPower[0] = 1;

        for (int index = 0; index < length; index++) {
            tenPower[index + 1] = (tenPower[index] * 10) % modValue;

            int currentDigit = str.charAt(index) - '0';

            digitSumPrefix[index + 1] = digitSumPrefix[index] + currentDigit;

            if (currentDigit == 0) {
                valuePrefix[index + 1] = valuePrefix[index];
                nonZeroPrefix[index + 1] = nonZeroPrefix[index];
            } else {
                valuePrefix[index + 1] = (valuePrefix[index] * 10 + currentDigit) % modValue;
                nonZeroPrefix[index + 1] = nonZeroPrefix[index] + 1;
            }
        }

        int[] result = new int[ranges.length];

        for (int queryIndex = 0; queryIndex < ranges.length; queryIndex++) {

            int start = ranges[queryIndex][0];
            int end = ranges[queryIndex][1];

            long totalDigitSum = digitSumPrefix[end + 1] - digitSumPrefix[start];

            int validDigits = nonZeroPrefix[end + 1] - nonZeroPrefix[start];

            long prefixValue = (valuePrefix[start] * tenPower[validDigits]) % modValue;

            long extractedNumber = (valuePrefix[end + 1] - prefixValue + modValue) % modValue;

            result[queryIndex] = (int) ((extractedNumber * totalDigitSum) % modValue);
        }

        return result;
    }
}
