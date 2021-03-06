package ml.extbukkit.api.command;

/**
 * Object, that can have permissions
 */
public interface Permissible {
    /**
     * Check permission
     *
     * @param permission Permission to check
     * @return true, if object has permission
     */
    boolean hasPermission(String permission);
}