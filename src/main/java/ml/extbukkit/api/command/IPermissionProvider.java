package ml.extbukkit.api.command;

import java.util.List;
import java.util.Map;

public interface IPermissionProvider {
    boolean get(ICommandExecutor executor, String node);
    boolean isSet(ICommandExecutor executor, String node);
    boolean hasPermission(ICommandExecutor executor, String node);
    boolean isDefault(String node);
    boolean isDefaultOp(String node);
    Map<String, Boolean> getPermissions(ICommandExecutor executor);
    List<String> getProvidedPermissions();
    List<String> getDefaultPermissions();
    List<String>  getDefaultOpPermissions();
}