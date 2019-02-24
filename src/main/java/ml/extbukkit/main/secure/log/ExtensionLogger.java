package ml.extbukkit.main.secure.log;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.ILogChannel;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.main.server.Server;
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
    public void warn(String message)
    {
        globalLogger.log( Channels.WARN, prefix + message );
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
    public void log(ILogChannel channel, String message)
    {
        globalLogger.log( channel, prefix + message );
    }

    @Override
    public void log(ILogChannel channel, String message, Throwable stack)
    {
        globalLogger.log( channel, prefix + message + "\n" + ExceptionUtils.getMessage( stack ) );
    }

}