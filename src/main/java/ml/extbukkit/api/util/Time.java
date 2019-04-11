package ml.extbukkit.api.util;

import lombok.Getter;
import lombok.Setter;

public class Time {
    @Getter
    @Setter
    private long ticks;

    /**
     * Time
     *
     * @param unit   Time unit
     * @param amount Amount of time units
     */
    public Time(TimeUnit unit, long amount) {
        this.ticks = unit.multiply(amount);
    }

    /**
     * Time
     *
     * @param unit Time unit
     */
    public Time(TimeUnit unit) {
        this(unit, 1);
    }

    /**
     * Time
     *
     * @param ticks Ticks
     */
    public Time(long ticks) {
        this(TimeUnit.TICK, ticks);
    }

    /**
     * Time
     */
    public Time() {
        this(0);
    }

    /**
     * Add units to time
     *
     * @param unit   Unit of time
     * @param amount Time to add
     * @return New time
     */
    public Time add(TimeUnit unit, long amount) {
        Time nt = new Time(TimeUnit.TICK, ticks);
        nt.setTicks(nt.getTicks() + unit.multiply(amount));
        return nt;
    }

    /**
     * Add 1 unit to time
     *
     * @param unit Unit of time
     * @return New time
     */
    public Time add(TimeUnit unit) {
        return add(unit, 1);
    }

    /**
     * Add ticks to time
     *
     * @param ticks Ticks
     * @return New time
     */
    public Time add(long ticks) {
        return add(TimeUnit.TICK, ticks);
    }

    /**
     * Convert ticks to int
     *
     * @return Ticks
     */
    public int toInt() {
        return Long.valueOf(ticks).intValue();
    }

    /**
     * Convert ticks to long
     *
     * @return Ticks
     */
    public long toLong() {
        return ticks;
    }

    /**
     * Convert ticks to float
     *
     * @return Ticks
     */
    public float toFloat() {
        return Long.valueOf(ticks).floatValue();
    }

    /**
     * Convert ticks to double
     *
     * @return Ticks
     */
    public double toDouble() {
        return Long.valueOf(ticks).doubleValue();
    }
}