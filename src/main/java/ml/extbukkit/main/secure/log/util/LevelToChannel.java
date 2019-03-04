package ml.extbukkit.main.secure.log.util;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.log.LogChannel;
import org.apache.logging.log4j.Level;

public class LevelToChannel
{

    public static LogChannel transform(Level level)
    {
        LogChannel returned;
        // Bukkit doesn't use the other channels, so that's why we don't handle them
        switch ( level.getStandardLevel() )
        {
            case INFO:
                returned = Channels.INFO;
                break;
            case WARN:
                returned = Channels.WARN;
                break;
            case ERROR:
                returned = Channels.ERROR;
                break;
            default:
                returned = Channels.INFO;
        }
        return returned;
    }

}