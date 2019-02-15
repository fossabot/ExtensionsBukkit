package ml.extbukkit.api.log;

import ml.extbukkit.api.extension.AExtension;

import java.util.List;

public interface ILogger {
    void logSigned(AExtension extension, ILogChannel channel, String message);
    void logSigned(AExtension extension, String message);
    void log(String message);
    void log(ILogChannel channel, String message);
    void registerLogHandler(ILogHandler handler);
    List<ILogHandler> getLogHandlers();
}