package net.extbukkit.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.extbukkit.api.helper.HashHelper;
import net.extbukkit.main.server.Server;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater {
    public static final String REPO_API = "https://api.github.com/repos/TSEngineer/TSEExtensionsBukkit";

    public static void updatesPresent() {

    }
    public static JsonArray getReleases() {
        URL r;
        try {
            r = new URL(REPO_API + "/releases");
        } catch (MalformedURLException e) {
            return null;
        }
        HttpsURLConnection con;
        try {
            con = (HttpsURLConnection) r.openConnection();
        } catch (IOException e) {
            return null;
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return null;
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            return null;
        }
        String input;
        StringBuffer res = new StringBuffer();
        try {
            while ((input = in.readLine()) != null)
                res.append(input);
        } catch (IOException e) {
            return null;
        }
        return new JsonParser().parse(res.toString()).getAsJsonArray();
    }
    public static JsonObject getRelease(int num) {
        return getReleases().get(num).getAsJsonObject();
    }
    public static String getLatestURL() {
        return getRelease(0).get("assets").getAsJsonArray().get(0).getAsJsonObject().get("browser_download_url").getAsString();
    }
    public static String getLatestFileName() {

        return getRelease(0).get("assets").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
    }
    public static File downloadTemp() {
        URL d;
        try {
            d = new URL(getLatestURL());
        } catch (MalformedURLException e) {
            return null;
        }
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(d.openStream());
        } catch (IOException e) {
            return null;
        }
        File pf = new File("extensionsbukkit/temp/" + getLatestFileName());
        if(!pf.exists()) {
            try {
                pf.createNewFile();
            } catch (IOException e) {}
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pf);
        } catch (FileNotFoundException e) {
            return null;
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            return null;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {}
        }
        return pf;
    }
    public static boolean needUpdate() {
        File tmp = downloadTemp();
        return !HashHelper.md5FileTry(Server.getInstance().getExtensionsBukkitFile()).equalsIgnoreCase(HashHelper.md5FileTry(tmp));
    }
    public static void run() {
        System.out.println("Updating ExtensionsBukkit");
        if(needUpdate()) {
            System.out.println("Found a new update, removing old file");
            Server.getInstance().getExtensionsBukkitFile().delete();
            System.out.println("Downloading new release");
            File f = downloadTemp();
            f.renameTo(Server.getInstance().getExtensionsBukkitFile());
        }
        else System.out.println("ExtensionsBukkit is up to date");
        System.out.println("Finished updating ExtensionsBukkit");
    }
}