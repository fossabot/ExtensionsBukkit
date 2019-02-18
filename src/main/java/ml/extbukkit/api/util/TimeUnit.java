package ml.extbukkit.api.util;

/**
 * Time units
 */
public enum TimeUnit {
    /**
     * Tick (1 tick)
     */
    TICK(1),
    /**
     * Second (20 ticks)
     */
    SECOND(TICK.ticks() * 20),
    /**
     * Minute (60 seconds)
     */
    MINUTE(SECOND.ticks() * 60),
    /**
     * Hour (60 minutes)
     */
    HOUR(MINUTE.ticks() * 60),
    /**
     * Day (24 hours)
     */
    DAY(HOUR.ticks() * 24),
    /**
     * Week (7 days)
     */
    WEEK(DAY.ticks() * 7),
    /**
     * Month (30 days)
     */
    MONTH(DAY.ticks() * 30),
    /**
     * Year (12 months)
     */
    YEAR(MONTH.ticks() * 12);

    private final long ticks;

    TimeUnit(long ticks) {
        this.ticks = ticks;
    }

    /**
     * Get ticks of 1
     * time unit
     *
     * @return
     */
    public long ticks() {
        return ticks;
    }

    /**
     * Multiply ticks by number
     *
     * @param amount Number to multiply by
     * @return Multiplied number (ticks)
     */
    public long multiply(long amount) {
        return ticks * amount;
    }
}