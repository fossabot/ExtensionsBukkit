package ml.extbukkit.main.secure.bukkit;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater {
    public static void download() {
        URL d;
        try {
            d = new URL("http://ci.extbukkit.ml/job/ExtensionsBukkit/lastSuccessfulBuild/artifact/build/libs/ExtensionsBukkit-release.jar");
        } catch (MalformedURLException e) {
            return;
        }
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(d.openStream());
        } catch (IOException e) {
            return;
        }
        File pf = new File("plugins/ExtensionsBukkit.jar");
        if(!pf.exists()) {
            try {
                pf.createNewFile();
            } catch (IOException e) {}
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pf);
        } catch (FileNotFoundException e) {
            return;
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            return;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }
    public static void downloadBKCL() {
        URL d;
        try {
            d = new URL("https://ci.mg-dev.eu/job/BKCommonLib/lastSuccessfulBuild/artifact/target/BKCommonLib.jar");
        } catch (MalformedURLException e) {
            Bukkit.getLogger().severe("Unable to download BKCommonLib! Please, install it manually");
            Bukkit.shutdown();
            return;
        }
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(d.openStream());
        } catch (IOException e) {
            Bukkit.getLogger().severe("Unable to download BKCommonLib! Please, install it manually");
            Bukkit.shutdown();
            return;
        }
        File pf = new File("plugins/BKCommonLib.jar");
        if(!pf.exists()) {
            try {
                pf.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Unable to download BKCommonLib! Please, install it manually");
                Bukkit.shutdown();
                return;
            }
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pf);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().severe("Unable to download BKCommonLib! Please, install it manually");
            Bukkit.shutdown();
            return;
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Unable to download BKCommonLib! Please, install it manually");
            Bukkit.shutdown();
            return;
        } finally {
            Bukkit.getLogger().info("BKCommonLib downloaded successfully! Please, restart your server");
            Bukkit.shutdown();
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }
}