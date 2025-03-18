package io.github.retrobitcoder;

public class ConversionUtility {

    private ConversionUtility() {
        // hiding public constructor
    }
    /**
     * Combine given feet and inches into an inches only value
     * @param feet
     * @param inches
     * @return int of only inches
     */
    public static int feetToInches(int feet, int inches) {
        inches += (feet * 12);

        return inches;
    }

    /**
     * Checks if inches is greater than 12, if so converts
     * inches to feet and keeps remaining inches
     * @param feet
     * @param inches
     * @return array of new feet and inches value as {feet, inches}
     */
    public static int[] capInches(int feet, int inches) {
        if (inches > 11){
            int newFeet = inches / 12;
            int inchesRemainder = inches % 12;

            return new int[]{feet + newFeet, inchesRemainder};
        }

        return new int[]{feet, inches};
    }
}
