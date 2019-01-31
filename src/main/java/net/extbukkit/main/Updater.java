package net.extbukkit.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater {
    public static final String REPO_API = "https://api.github.com/repos/TSEngineer/TSEExtensionsBukkit";

    public static void download() {
        URL r;
        try {
            r = new URL(REPO_API + "/releases");
        } catch (MalformedURLException e) {
            return;
        }
        HttpsURLConnection con;
        try {
            con = (HttpsURLConnection) r.openConnection();
        } catch (IOException e) {
            return;
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return;
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            return;
        }
        String input;
        StringBuffer res = new StringBuffer();
        try {
            while ((input = in.readLine()) != null)
                res.append(input);
        } catch (IOException e) {
            return;
        }
        JsonArray obj = new JsonParser().parse(res.toString()).getAsJsonArray();
        URL d;
        try {
            d = new URL(obj.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0).getAsJsonObject().get("browser_download_url").getAsString());
        } catch (MalformedURLException e) {
            return;
        }
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(d.openStream());
        } catch (IOException e) {
            return;
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(BukkitExtensionsBukkit.getInstance().getDataFolder().getParentFile().getParent() + "/" + obj.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString()));
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