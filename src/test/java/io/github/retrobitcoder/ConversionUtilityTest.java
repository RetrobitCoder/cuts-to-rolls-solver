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

        convertedInches = ConversionUtility.feetToInches(20, 0);

        Assert.assertEquals(240, convertedInches);
    }

    public void testCapInches() {
        int[] value = ConversionUtility.capInches(0, 24);

        Assert.assertEquals(2, value[0]);

        value = ConversionUtility.capInches(1, 13);

        Assert.assertEquals(2, value[0]);
        Assert.assertEquals(1, value[1]);

        value = ConversionUtility.capInches(1, 11);

        Assert.assertEquals(1, value[0]);
        Assert.assertEquals(11, value[1]);
    }
    
}
