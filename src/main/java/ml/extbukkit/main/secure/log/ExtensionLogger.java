package ml.extbukkit.main.secure.log;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.LogChannel;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.server.Server;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExtensionLogger implements IExtensionLogger
{
    private String prefix;
    private ILogger globalLogger = Server.getInstance().getGlobalLogger();

    public ExtensionLogger(String name)
    {
        this.prefix = "[" + name + "] ";
    }

    @Override
    public void info(String message)
    {
        globalLogger.log( Channels.INFO, prefix + message );
    }

    @Override
    public void info(String... messages)
    {
        Arrays.stream( messages ).forEach( this::info );
    }

    @Override
    public void info(List<String> messages)
    {
        messages.forEach( this::info );
    }

    @Override
    public void info(Set<String> messages)
    {
        messages.forEach( this::info );
    }

    @Override
    public void warn(String message)
    {
        globalLogger.log( Channels.WARN, prefix + message );
    }

    @Override
    public void warn(String... messages)
    {
        Arrays.stream( messages ).forEach( this::warn );
    }

    @Override
    public void warn(List<String> messages)
    {
        messages.forEach( this::warn );
    }

    @Override
    public void warn(Set<String> messages)
    {
        messages.forEach( this::warn );
    }

    @Override
    public void error(String message)
    {
        globalLogger.log( Channels.ERROR, prefix + message );
    }

    @Override
    public void error(String message, Throwable stack)
    {
        globalLogger.logStack( prefix + message, stack );
    }

    @Override
    public void debug(String message)
    {
        globalLogger.log( Channels.DEBUG, prefix + message );
    }

    @Override
    public void debug(String... messages)
    {
        Arrays.stream( messages ).forEach( this::debug );
    }

    @Override
    public void debug(List<String> messages)
    {
        messages.forEach( this::debug );
    }

    @Override
    public void debug(Set<String> messages)
    {
        messages.forEach( this::debug );
    }

    @Override
    public void fatal(String message)
    {
        globalLogger.log( Channels.FATAL, prefix + message );
    }

    @Override
    public void fatal(String message, Throwable stack)
    {
        globalLogger.log( Channels.FATAL, prefix + message + "\n" + ExceptionUtils.getMessage( stack ) );
    }

    @Override
    public void log(LogChannel channel, String message)
    {
        globalLogger.log( channel, prefix + message );
    }

    @Override
    public void log(LogChannel channel, String... messages)
    {
        Arrays.stream( messages ).forEach( message -> log( channel, message ) );
    }

    @Override
    public void log(LogChannel channel, List<String> messages)
    {
        messages.forEach( message -> log( channel, message ) );
    }

    @Override
    public void log(LogChannel channel, Set<String> messages)
    {
        messages.forEach( message -> log( channel, message ) );
    }

    @Override
    public void log(LogChannel channel, String message, Throwable stack)
    {
        globalLogger.log( channel, prefix + message + "\n" + ExceptionUtils.getMessage( stack ) );
    }

}