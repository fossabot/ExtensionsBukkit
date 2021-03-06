package ml.extbukkit.main.secure.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.log.HandleResult;
import ml.extbukkit.api.log.LogChannel;
import ml.extbukkit.api.log.LogHandler;
import ml.extbukkit.api.log.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;

public class SimpleLogger implements Logger {

    private boolean enabled = true;
    private File LATEST, BUKKIT, EB, LOGSTP;
    private List<LogHandler> handlers = new ArrayList<>();
    private static SimpleLogger instance = new SimpleLogger();

    public static SimpleLogger getInstance() {
        return instance;
    }

    private SimpleLogger() {
        final File LOGS = new File("extensionsbukkit/logs/");
        LATEST = new File(LOGS, "everything.log");
        BUKKIT = new File(LOGS, "bukkit.log");
        EB = new File(LOGS, "extensionsbukkit.log");
        LOGSTP = new File(LOGS, timestamp().replace(' ', '_') + "/");
        if (!LOGS.exists() || !LOGS.isDirectory()) LOGS.mkdirs();
        if (LATEST.exists()) {
            zip(LATEST);
            LATEST.delete();
        }
        if (BUKKIT.exists()) {
            zip(BUKKIT);
            BUKKIT.delete();
        }
        if (EB.exists()) {
            zip(EB);
            EB.delete();
        }
        if (!LOGSTP.exists()) LOGSTP.mkdirs();
        try {
            LATEST.createNewFile();
            BUKKIT.createNewFile();
            EB.createNewFile();
        } catch (IOException e) {
            log(Channels.FATAL, "Cannot create log files! This may cause errors");
            return;
        }
        File RDM = new File("logs/README.txt");
        if (!RDM.exists()) {
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
    public void logSigned(Extension extension, LogChannel channel, String message) {
        log(channel, "[" + extension.getName() + "] " + message);
    }

    @Override
    public void log(LogChannel channel, String message) {
        String send = "";
        for (LogHandler h : handlers) {
            HandleResult r = h.log(channel, message);
            if (!r.send()) return;
            send = r.getMessage();
        }
        if (send == null || send.length() == 0) {
            send = message;
        }
        if (send.contains("\n")) {
            String[] arr = send.split("\n");
            String firstLine = arr[0];
            String[] others = Arrays.copyOfRange(arr, 1, arr.length);
            String firstLineFormatted = "[" + timestamp() + "] [EXTENSIONSBUKKIT] [" + channel.getName() + "] " + firstLine;
            logRaw(firstLineFormatted);
            fileAppend(EB, firstLineFormatted);
            for (String other : others) {
                logRaw(other);
                fileAppend(EB, other);
            }
        } else {
            String msg = "[" + timestamp() + "] [EXTENSIONSBUKKIT] [" + channel.getName() + "] " + send;
            logRaw(msg);
            fileAppend(EB, msg);
        }
    }

    @Override
    public void logSigned(Extension extension, String message) {
        logSigned(extension, Channels.INFO, message);
    }

    @Override
    public void log(String message) {
        log(Channels.INFO, message);
    }

    @Override
    public void registerLogHandler(LogHandler handler) {
        handlers.add(handler);
    }

    @Override
    public List<LogHandler> getLogHandlers() {
        return handlers;
    }

    @Override
    public void logStack(String message, Throwable t) {
        log(Channels.ERROR, message + "\n" + ExceptionUtils.getStackTrace(t));
    }

    @Override
    public void logStackSigned(Extension extension, String message, Throwable t) {
        logStack("[" + extension.getName() + "] " + message, t);
    }

    public void logBukkitStack(Throwable t) {
        logBukkit(Level.ERROR, ExceptionUtils.getStackTrace(t));
    }

    public void logBukkit(Level level, String message) {
        if (message.contains("\n")) {
            String[] ln = message.split("\n");
            String[] others = Arrays.copyOfRange(ln, 1, ln.length);
            String msg1st = "[" + timestamp() + "] [BUKKIT] [" + level.name() + "] " + ln[0];
            logRaw(msg1st);
            fileAppend(BUKKIT, msg1st);
            for (String cl : others) {
                logRaw(cl);
                fileAppend(BUKKIT, cl);
            }
        } else {
            String msg = "[" + timestamp() + "] [BUKKIT] [" + level.name() + "] " + message;
            logRaw(msg);
            fileAppend(BUKKIT, msg);
        }
    }

    private String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void logRaw(String text) {
        if (!enabled) return;
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.UTF_8), 512);
            out.write(text);
            out.write("\n");
            out.flush();
            fileAppend(LATEST, text);
        } catch (IOException e) {
        }
    }

    private void fileAppend(File f, String a) {
        try (FileWriter writer = new FileWriter(f)) {
            writer.write(a);
            writer.write('\n');
        } catch (IOException e) {
        }
    }

    private void zip(File file) {
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(file))) {
            ZipEntry entry = new ZipEntry(file.getName());
            output.putNextEntry(entry);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            inputStream.close();
        } catch (IOException e) {
        }
    }
}