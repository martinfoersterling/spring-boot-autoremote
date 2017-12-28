package mafoe.autoremote.server.remoting;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NestedExceptionUtils;

/**
 * Transforms exceptions that contain non-serializable classes so that the client can still display them without
 * getting {@link ClassNotFoundException}s.
 */
public interface ExceptionHandler {

    RuntimeException transform(Exception e);
}
