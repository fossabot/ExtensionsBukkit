package ml.extbukkit.main.log;

import ml.extbukkit.api.log.ILogChannel;

public class LogChannel implements ILogChannel {
    private String name;
    public LogChannel(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}