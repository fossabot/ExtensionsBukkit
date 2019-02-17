package ml.extbukkit.api.event;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    HandlePriority priority() default HandlePriority.NORMAL;
}