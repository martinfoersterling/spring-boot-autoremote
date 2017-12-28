package mafoe.autoremote.server;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ConditionalOnClass(HttpInvokerServiceExporter.class)
public class AutoremoteServerAutoConfiguration {
}
