package ml.extbukkit.api.command;

import java.util.List;
import java.util.Map;

public interface IPermissionManager {
    boolean hasPermission(ICommandExecutor executor, String node);
    boolean isDefault(String node);
    boolean isDefaultOp(String node);
    boolean get(ICommandExecutor executor, String node);
    boolean isSet(ICommandExecutor executor, String node);
    List<String> getDefaultPermissions();
    List<String> getDefaultOpPermissions();
    Map<String, Boolean> getPermissions(ICommandExecutor executor);
    void registerProvider(IPermissionProvider provider);
    List<IPermissionProvider> getProviders();
}