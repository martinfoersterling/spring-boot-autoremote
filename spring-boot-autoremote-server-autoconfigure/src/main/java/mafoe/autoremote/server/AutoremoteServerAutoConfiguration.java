package mafoe.autoremote.server;

import mafoe.autoremote.server.remoting.ExposeServicePostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ConditionalOnClass(HttpInvokerServiceExporter.class)
public class AutoremoteServerAutoConfiguration {

    @Bean
    static ExposeServicePostProcessor exposeServicePostProcessor() {
        return new ExposeServicePostProcessor();
    }
}
