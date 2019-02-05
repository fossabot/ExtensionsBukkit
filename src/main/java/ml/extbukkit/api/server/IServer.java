package ml.extbukkit.api.server;

import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.world.IWorldManager;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface IServer {
    IExtensionLoader getExtensionLoader();
    File getExtensionsDir();
    ILogger getLogger(AExtension extension);
    IEventManager getEventManager();
    ISchedulerManager getSchedulerManager();
    File getExtensionsBukkitFile();
    IWorldManager getWorldManager();

    // MrIvanPlays start
    void registerCommand(Command command);
    Set<Command> getRegisteredCommands();
    // MrIvanPlays end
}