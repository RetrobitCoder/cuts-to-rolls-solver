package io.github.retrobitcoder;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConversionUtilityTest extends TestCase{
    public void testConversion() {
        double zero = 0.0;
        
        int convertedInches = ConversionUtility.feetToInches(zero);

        Assert.assertEquals(0, convertedInches);

        double one = 0.12;

        convertedInches = ConversionUtility.feetToInches(one);

        Assert.assertEquals(12, convertedInches);

        double two = 1.12;

        convertedInches = ConversionUtility.feetToInches(two);

        Assert.assertEquals(24, convertedInches);

        double mixed = 10.06; // TODO: should 6 inches be .6 or .06?

        convertedInches = ConversionUtility.feetToInches(mixed);

        Assert.assertEquals(126, convertedInches);
    }
    
}
