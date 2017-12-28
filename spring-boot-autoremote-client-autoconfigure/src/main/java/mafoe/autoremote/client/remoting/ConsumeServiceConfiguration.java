package mafoe.autoremote.client.remoting;

import java.util.Set;

/**
 * Interface for a bean a user needs to implement to get the consumption of services functionality.
 */
public interface ConsumeServiceConfiguration {

    /**
     * @return the interfaces of all services that should be consumed
     */
    Set<Class<?>> getServiceInterfaces();

    /**
     * Returns the full URL of the server so that the service name only has to be appended to hit the required endpoint.
     * Must not end with a slash.
     */
    String getServerUrl();
}
