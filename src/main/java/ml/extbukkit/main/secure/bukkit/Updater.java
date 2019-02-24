package ml.extbukkit.main.secure.bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

public class Updater {

    private static BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();

    public static void download()
    {
        FileOutputStream outputStream = null;
        try
        {
            URL downloadURL = new URL("http://ci.extbukkit.ml/job/ExtensionsBukkit/lastSuccessfulBuild/artifact/build/libs/ExtensionsBukkit.jar");
            ReadableByteChannel byteChannel = Channels.newChannel( downloadURL.openStream() );
            File outFile = new File( plugin.getDataFolder().getParent(), "ExtensionsBukkit.jar" );

            //----------------------------------------------------------------------------------------------------------------------
            // This should fix the shit TSEngineer was trying to do...
            File currentFile = new File( plugin.getDataFolder().getParent(), "ExtensionsBukkit-1.2-SNAPSHOT.jar" );
            File currentFile0 = new File( plugin.getDataFolder().getParent(), "ExtensionsBukkit-release.jar" );
            if ( currentFile.exists() )
            {
                currentFile.deleteOnExit();
            }
            if ( currentFile0.exists() )
            {
                currentFile0.deleteOnExit();
            }
            //----------------------------------------------------------------------------------------------------------------------
            if ( !outFile.exists() )
            {
                boolean success = outFile.createNewFile();
                if ( !success )
                {
                    throw new RuntimeException( "Cannot create output file for updated ExtensionsBukkit jar (access denied or another file used?" );
                }
            }
            outputStream = new FileOutputStream( outFile );
            outputStream.getChannel().transferFrom( byteChannel, 0, Long.MAX_VALUE );
        } catch ( Throwable e )
        {
            plugin.getLogger().log( Level.SEVERE, "Something occurred trying to download updated ExtensionsBukkit", e );
            plugin.getServer().getPluginManager().disablePlugin( plugin );
        } finally
        {
            try
            {
                outputStream.close();
            } catch ( IOException e ) {} // Why does java suck ?
        }
    }
    public static void downloadBKCL()
    {
        FileOutputStream outputStream = null;
        try
        {
            URL downloadUrl = new URL( "https://ci.mg-dev.eu/job/BKCommonLib/lastSuccessfulBuild/artifact/target/BKCommonLib.jar" );
            ReadableByteChannel byteChannel = Channels.newChannel( downloadUrl.openStream() );
            File outJar = new File( "plugins/BKCommonLib.jar" );
            if ( !outJar.exists() )
            {
                boolean success = outJar.createNewFile();
                if ( !success )
                {
                    throw new RuntimeException( "Cannot create output file for BKCommonLib (access denied or another file used?)" );
                }
            }
            outputStream = new FileOutputStream( outJar );
            outputStream.getChannel().transferFrom( byteChannel, 0, Long.MAX_VALUE );
        } catch ( Throwable e )
        {
            plugin.getLogger().log( Level.SEVERE, "Something occurred trying to download BKCommonLib", e );
            plugin.getServer().getPluginManager().disablePlugin( plugin );
        } finally
        {
            try
            {
                outputStream.close();
            } catch ( IOException e ) {} // Why does java suck ?
        }
    }
}