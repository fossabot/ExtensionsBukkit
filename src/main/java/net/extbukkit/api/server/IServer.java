package net.extbukkit.api.server;

import net.extbukkit.api.event.IEventManager;
import net.extbukkit.api.extension.AExtension;
import net.extbukkit.api.loader.IExtensionLoader;
import net.extbukkit.api.log.ILogger;
import net.extbukkit.api.scheduler.ISchedulerManager;
import net.extbukkit.api.world.IWorldManager;

import java.io.File;

public interface IServer {
    IExtensionLoader getExtensionLoader();
    File getExtensionsDir();
    ILogger getLogger(AExtension extension);
    IEventManager getEventManager();
    ISchedulerManager getSchedulerManager();
    File getExtensionsBukkitFile();
    IWorldManager getWorldManager();
}