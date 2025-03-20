package io.github.retrobitcoder;

public class Measurement {
    private int feet = 0;
    private int inches = 0;

    public static Measurement max(Measurement m1, Measurement m2) {
        if (m1.getTotalInches() > m2.getTotalInches()) {
            return m1;
        } else if(m1.getTotalInches() < m2.getTotalInches()) {
            return m2;
        }

        return m1;
    }

    public static Measurement add(Measurement m1, Measurement m2) {
        return new Measurement(m1.getFeet() + m2.getFeet(), m1.getInches() + m2.getInches());
    }

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
