package ml.extbukkit.api.math;

public enum MathFunction {
    ADD(0),
    SUBTRACT(0),
    MULTIPLY(1),
    DIVIDE(1);

    private final double uv;
    MathFunction(double uv) {
        this.uv = uv;
    }

    public double UNCHANGED() {
        return uv;
    }
}