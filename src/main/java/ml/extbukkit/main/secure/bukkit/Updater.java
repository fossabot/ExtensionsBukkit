package ml.extbukkit.main.secure.bukkit;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater {
    public static void download() {
        URL d;
        try {
            d = new URL("http://ci.extbukkit.ml/job/ExtensionsBukkit/lastSuccessfulBuild/artifact/build/libs/ExtensionsBukkit-1.2-SNAPSHOT.jar");
        } catch (MalformedURLException e) {
            return;
        }
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(d.openStream());
        } catch (IOException e) {
            return;
        }
        File pf = new File("plugins/ExtensionsBukkit-1.2-SNAPSHOT.jar");
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
}