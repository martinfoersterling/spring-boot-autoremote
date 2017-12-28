package mafoe.autoremote.server.remoting;

import org.springframework.remoting.support.DefaultRemoteInvocationExecutor;
import org.springframework.remoting.support.RemoteInvocation;

/**
 * Custom RemoteInvocationExecutor to handle exceptions.
 */
public class CustomRemoteInvocationExecutor extends DefaultRemoteInvocationExecutor {

    private ExceptionHandler exceptionHandler;

    public CustomRemoteInvocationExecutor(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public Object invoke(RemoteInvocation invocation, Object targetObject) {

        Object result;
        try {
            result = super.invoke(invocation, targetObject);
        } catch (Exception e) {
            throw exceptionHandler.transform(e);
        }

        return result;
    }
}
