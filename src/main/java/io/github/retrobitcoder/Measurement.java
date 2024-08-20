package io.github.retrobitcoder;

public class Measurement {
    private int feet = 0;
    private int inches = 0;
    private int totalInches = 0;

    public Measurement(int feet, int inches) {
        this.feet = feet;
        this.inches = inches;
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
        this.inches = inches;
    }

    public int getTotalInches() {
        return ConversionUtility.feetToInches(feet, inches);
    }
    
}
