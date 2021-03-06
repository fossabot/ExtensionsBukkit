package ml.extbukkit.main.secure.log;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.log.ExtensionLogger;
import ml.extbukkit.api.log.LogChannel;
import ml.extbukkit.api.log.Logger;
import ml.extbukkit.api.server.Server;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class SimpleExtensionLogger implements ExtensionLogger {
    private String prefix;
    private Logger globalLogger = Server.getInstance().getGlobalLogger();

    public SimpleExtensionLogger(String name) {
        this.prefix = "[" + name + "] ";
    }

    @Override
    public void info(String message) {
        globalLogger.log(Channels.INFO, prefix + message);
    }

    @Override
    public void info(String... messages) {
        Arrays.stream(messages).forEach(this::info);
    }

    @Override
    public void info(Collection<String> messages) {
        messages.forEach(this::info);
    }

    @Override
    public void warn(String message) {
        globalLogger.log(Channels.WARN, prefix + message);
    }

    @Override
    public void warn(String... messages) {
        Arrays.stream(messages).forEach(this::warn);
    }

    @Override
    public void warn(Collection<String> messages) {
        messages.forEach(this::warn);
    }

    @Override
    public void error(String message) {
        globalLogger.log(Channels.ERROR, prefix + message);
    }

    @Override
    public void error(String message, Throwable stack) {
        globalLogger.logStack(prefix + message, stack);
    }

    @Override
    public void debug(String message) {
        globalLogger.log(Channels.DEBUG, prefix + message);
    }

    @Override
    public void debug(String... messages) {
        Arrays.stream(messages).forEach(this::debug);
    }

    @Override
    public void debug(Collection<String> messages) {
        messages.forEach(this::debug);
    }

    @Override
    public void fatal(String message) {
        globalLogger.log(Channels.FATAL, prefix + message);
    }

    @Override
    public void fatal(String message, Throwable stack) {
        globalLogger.log(Channels.FATAL, prefix + message + "\n" + ExceptionUtils.getMessage(stack));
    }

    @Override
    public void log(LogChannel channel, String message) {
        globalLogger.log(channel, prefix + message);
    }

    @Override
    public void log(LogChannel channel, String... messages) {
        Arrays.stream(messages).forEach(message -> log(channel, message));
    }

    @Override
    public void log(LogChannel channel, Collection<String> messages) {
        messages.forEach(message -> log(channel, message));
    }

    @Override
    public void log(LogChannel channel, String message, Throwable stack) {
        globalLogger.log(channel, prefix + message + "\n" + ExceptionUtils.getMessage(stack));
    }

}