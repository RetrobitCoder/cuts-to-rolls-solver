package io.github.retrobitcoder;

public class Measurement {
    private int feet = 0;
    private int inches = 0;

    public Measurement(int feet, int inches) {
        int[] values = ConversionUtility.capInches(feet, inches);

        this.feet = values[0];
        this.inches = values[1];
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        int[] values = ConversionUtility.capInches(this.getFeet(), inches);

        this.setFeet(values[0]); 
        this.inches = values[1];
    }

    public int getTotalInches() {
        return ConversionUtility.feetToInches(feet, inches);
    }

    @Override
    public String toString() {
        return getFeet() + "." + getInches();
    }
    
}
