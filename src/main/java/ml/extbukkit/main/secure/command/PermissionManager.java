package ml.extbukkit.main.secure.command;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.IPermissionManager;
import ml.extbukkit.api.command.IPermissionProvider;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PermissionManager implements IPermissionManager { //TODO Add vanilla permission provider
    private List<IPermissionProvider> providers;
    private Map<UUID, PermissionAttachment> attachments;

    public void updatePermissions(IEntity e) {
        org.bukkit.entity.Entity be = (org.bukkit.entity.Entity) ((Entity) e).handle;
        if(attachments.containsKey(be.getUniqueId())) {
            attachments.get(be.getUniqueId()).remove();
            attachments.remove(be.getUniqueId());
        }
        PermissionAttachment ach = be.addAttachment(BukkitExtensionsBukkit.getInstance());
        attachments.put(be.getUniqueId(), ach);
        getPermissions(e).forEach(ach::setPermission);
    }

    public void updatePermissions() {
        attachments.forEach((k, v) -> v.remove());
        attachments.clear();
        Bukkit.getWorlds().forEach(w -> w.getEntities().forEach(e -> updatePermissions(new Entity(e))));
    }

    public PermissionManager() {
        attachments = new HashMap<>();
        providers = new ArrayList<>();
        updatePermissions();
    }

    @Override
    public boolean hasPermission(ICommandExecutor executor, String node) {
        for(IPermissionProvider p : providers)
            if(p.hasPermission(executor, node))
                return true;
        return false;
    }

    @Override
    public boolean isDefault(String node) {
        for(IPermissionProvider p : providers)
            if(p.isDefault(node))
                return true;
        return false;
    }

    @Override
    public boolean isDefaultOp(String node) {
        for(IPermissionProvider p : providers)
            if(p.isDefaultOp(node))
                return true;
        return false;
    }

    @Override
    public boolean get(ICommandExecutor executor, String node) {
        for(IPermissionProvider p : providers)
            if(!p.get(executor, node))
                return false;
        return true;
    }

    @Override
    public boolean isSet(ICommandExecutor executor, String node) {
        for(IPermissionProvider p : providers)
            if(p.isSet(executor, node))
                return true;
        return false;
    }

    @Override
    public List<String> getDefaultPermissions() {
        List<String> pm = new ArrayList<>();
        providers.forEach(p -> p.getDefaultPermissions().forEach(s -> {
            if(!pm.contains(s)) pm.add(s);
        }));
        return pm;
    }

    @Override
    public List<String> getDefaultOpPermissions() {
        List<String> pm = new ArrayList<>();
        providers.forEach(p -> p.getDefaultOpPermissions().forEach(s -> {
            if(!pm.contains(s)) pm.add(s);
        }));
        return pm;
    }

    @Override
    public Map<String, Boolean> getPermissions(ICommandExecutor executor) {
        Map<String, Boolean> pm = new HashMap<>();
        providers.forEach(p -> p.getPermissions(executor).forEach((k, v) -> {
            if(!pm.containsKey(k)) pm.put(k, v);
            else if(pm.get(k)) pm.put(k, v);
        }));
        return pm;
    }

    @Override
    public void registerProvider(IPermissionProvider provider) {
        providers.add(provider);
    }

    @Override
    public List<IPermissionProvider> getProviders() {
        return providers;
    }
}