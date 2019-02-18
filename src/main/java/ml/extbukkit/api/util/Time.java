package ml.extbukkit.api.util;

import lombok.Getter;
import lombok.Setter;

public class Time {
    @Getter
    @Setter
    private long ticks;

    public Time(TimeUnit unit, long amount) {
        this.ticks = unit.multiply(amount);
    }
    public Time(TimeUnit unit) {
        this(unit, 1);
    }
    public Time(long ticks) {
        this(TimeUnit.TICK, ticks);
    }
    public Time() {
        this(0);
    }
    public Time add(TimeUnit unit, long amount) {
        Time nt = new Time(TimeUnit.TICK, ticks);
        nt.setTicks(nt.getTicks() + unit.multiply(amount));
        return nt;
    }
    public Time add(TimeUnit unit) {
        return add(unit, 1);
    }
    public Time add(long ticks) {
        return add(TimeUnit.TICK, ticks);
    }

    public int toInt() {
        return Long.valueOf(ticks).intValue();
    }

    public long toLong() {
        return ticks;
    }

    public float toFloat() {
        return Long.valueOf(ticks).floatValue();
    }

    public double toDouble() {
        return Long.valueOf(ticks).doubleValue();
    }
}