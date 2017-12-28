package mafoe.autoremote.server.remoting;

import org.springframework.remoting.support.RemoteInvocationExecutor;

/**
 * Interface for a bean a user needs to implement to get the exposing of services functionality.
 */
public interface ExposedServiceConfiguration {

    /**
     * @return the super interface of all interfaces eligible for service exposure
     */
    Class<?> getMarkerInterface();

    /**
     * @return the remote invocation executor to be used
     */
    default RemoteInvocationExecutor getRemoteInvocationExecutor() {
        return new CustomRemoteInvocationExecutor(exceptionHandler());
    }

    /**
     * @return the exception handler to be used by the remote invocation executor
     */
    default ExceptionHandler exceptionHandler() {
        return new SimpleExceptionHandler();
    }
}
