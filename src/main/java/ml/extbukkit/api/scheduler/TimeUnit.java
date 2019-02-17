package ml.extbukkit.api.scheduler;

public enum TimeUnit {
    TICK(1),
    SECOND(TICK.ticks() * 20),
    MINUTE(SECOND.ticks() * 60),
    HOUR(MINUTE.ticks() * 60),
    DAY(HOUR.ticks() * 24),
    WEEK(DAY.ticks() * 7),
    MONTH(DAY.ticks() * 30),
    YEAR(MONTH.ticks() * 12);

    private final long ticks;
    TimeUnit(long ticks) {
        this.ticks = ticks;
    }

    public long ticks() {
        return ticks;
    }

    public long multiply(long t) {
        return ticks * t;
    }
}