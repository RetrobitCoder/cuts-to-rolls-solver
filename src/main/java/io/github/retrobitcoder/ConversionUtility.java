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
}
