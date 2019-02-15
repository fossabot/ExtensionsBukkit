package ml.extbukkit.main.manager;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.log.IHandleResult;
import ml.extbukkit.api.log.ILogChannel;
import ml.extbukkit.api.log.ILogHandler;
import ml.extbukkit.api.log.ILogger;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger implements ILogger {
    List<ILogHandler> handlers = new ArrayList<>();
    @Override
    public void logSigned(AExtension extension, ILogChannel channel, String message) {
        log(channel, "[" + extension.getName() + "] " + message);
    }
    @Override
    public void log(ILogChannel channel, String message) {
        String send = "";
        for(ILogHandler h : handlers) {
            IHandleResult r = h.log(channel, message);
            if(!r.send()) return;
            send = r.getMessage();
        }
        logRaw("[" + timestamp() + "] [EXTENSIONSBUKKIT] [" + channel.getName() + "] " + send);
    }
    @Override
    public void logSigned(AExtension extension, String message) {
        logSigned(extension, Channels.INFO, message);
    }
    @Override
    public void log(String message) {
        log(Channels.INFO, message);
    }
    @Override
    public void registerLogHandler(ILogHandler handler) {
        handlers.add(handler);
    }
    @Override
    public List<ILogHandler> getLogHandlers() {
        return handlers;
    }

    public void logBukkit(Level level, String message) {
        logRaw("[" + timestamp() + "] [BUKKIT] [" + level.name() + "] " + message);
    }
    public String timestamp() {
        return new Timestamp(new Date().getTime()).toString();
    }
    public void logRaw(String text) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "UTF-8"), 512);
            out.write(text);
            out.write("\n");
            out.flush();
        } catch (IOException e) {}
    }
}