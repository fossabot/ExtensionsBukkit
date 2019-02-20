package ml.extbukkit.main.secure.log;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.log.IHandleResult;
import ml.extbukkit.api.log.ILogChannel;
import ml.extbukkit.api.log.ILogHandler;
import ml.extbukkit.api.log.ILogger;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger implements ILogger {
    private boolean enabled = true;
    private File LOGS, LATEST, BUKKIT, EB, LOGSTP;
    private List<ILogHandler> handlers = new ArrayList<>();
    private static Logger instance = new Logger();

    public static Logger getInstance()
    {
        return instance;
    }

    private Logger() {
        LOGS = new File("extensionsbukkit/logs/");
        LATEST = new File(LOGS, "everything.log");
        BUKKIT = new File(LOGS, "bukkit.log");
        EB = new File(LOGS, "extensionsbukkit.log");
        LOGSTP = new File(LOGS, timestamp().replace(' ', '_') + "/");
        if(!LOGS.exists() || !LOGS.isDirectory()) LOGS.mkdirs();
        if(LATEST.exists()) LATEST.delete();
        if(BUKKIT.exists()) BUKKIT.delete();
        if(EB.exists()) EB.delete();
        if(!LOGSTP.exists()) LOGSTP.mkdirs();
        try {
            LATEST.createNewFile();
            BUKKIT.createNewFile();
            EB.createNewFile();
        } catch (IOException e) {
            log(Channels.FATAL, "Cannot create log files! This may cause errors");
            return;
        }
        File RDM = new File("logs/README.txt");
        if(RDM.exists()) RDM.delete();
        try {
            RDM.createNewFile();
        } catch (IOException e) {
            log(Channels.INFO, "Cannot write logs/README.txt file! Logs are now in extensionsbukkit/logs");
            return;
        }
        fileAppend(RDM, "If you're looking for logs:");
        fileAppend(RDM, "You have ExtensionsBukkit installed on your server");
        fileAppend(RDM, "Log files are no longer here while ExtensionsBukkit is installed");
        fileAppend(RDM, "They are now located at extensionsbukkit/logs");
    }
    public void closeLog() {
        enabled = false;
        LATEST.renameTo(new File(LOGSTP, "everything.log"));
        BUKKIT.renameTo(new File(LOGSTP, "bukkit.log"));
        EB.renameTo(new File(LOGSTP, "extensionsbukkit.log"));
        LATEST.delete();
        BUKKIT.delete();
        EB.delete();
    }
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
        String msg = "[" + timestamp() + "] [EXTENSIONSBUKKIT] [" + channel.getName() + "] " + send;
        logRaw(msg);
        fileAppend(EB, msg);
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
        if(message.contains("\n")) {
            String[] ln = message.split("\n");
            for(String cl : ln)
                logBukkit(level, cl);
        }
        else {
            String msg = "[" + timestamp() + "] [BUKKIT] [" + level.name() + "] " + message;
            logRaw(msg);
            fileAppend(BUKKIT, msg);
        }
    }
    private String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    private void logRaw(String text) {
        if(!enabled) return;
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "UTF-8"), 512);
            out.write(text);
            out.write("\n");
            out.flush();
            fileAppend(LATEST, text);
        } catch (IOException e) {}
    }
    private void fileAppend(File f, String a) {
        try {
            BufferedWriter o = new BufferedWriter(new FileWriter(f, true));
            o.write(a + "\n");
            o.close();
        } catch(IOException e) {}
    }
}