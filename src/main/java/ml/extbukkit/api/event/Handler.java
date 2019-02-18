package ml.extbukkit.api.event;

import java.lang.annotation.*;

/**
 * Handler annotation
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    /**
     * Handler priority parameter<br>
     * Default: Normal
     *
     * @return Handle priority
     */
    HandlePriority priority() default HandlePriority.NORMAL;
}