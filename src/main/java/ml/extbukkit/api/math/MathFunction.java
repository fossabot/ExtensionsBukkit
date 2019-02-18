package ml.extbukkit.api.math;

/**
 * Math function class
 */
public enum MathFunction {
    /**
     * Add math function
     */
    ADD(0),
    /**
     * Subtract math function
     */
    SUBTRACT(0),
    /**
     * Multiply math function
     */
    MULTIPLY(1),
    /**
     * Divide math function
     */
    DIVIDE(1);

    private final double uv;
    MathFunction(double uv) {
        this.uv = uv;
    }

    /**
     * Unchanged value when applying math function
     *
     * @return Unchanged value
     */
    public double UNCHANGED() {
        return uv;
    }
}