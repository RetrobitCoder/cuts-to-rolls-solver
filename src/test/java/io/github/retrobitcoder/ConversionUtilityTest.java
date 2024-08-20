package io.github.retrobitcoder;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ConversionUtilityTest extends TestCase{
    public void testConversion() {        
        int convertedInches = ConversionUtility.feetToInches(0, 0);

        Assert.assertEquals(0, convertedInches);

        convertedInches = ConversionUtility.feetToInches(0, 12);

        Assert.assertEquals(12, convertedInches);

        convertedInches = ConversionUtility.feetToInches(1, 0);

        Assert.assertEquals(12, convertedInches);

        convertedInches = ConversionUtility.feetToInches(1, 12);

        Assert.assertEquals(24, convertedInches);

        convertedInches = ConversionUtility.feetToInches(10, 6);

        Assert.assertEquals(126, convertedInches);
    }
    
}
