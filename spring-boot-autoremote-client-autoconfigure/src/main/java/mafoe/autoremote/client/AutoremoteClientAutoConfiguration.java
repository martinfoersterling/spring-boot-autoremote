package mafoe.autoremote.client;

import mafoe.autoremote.client.remoting.ConsumeServicesPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@ConditionalOnClass(HttpInvokerProxyFactoryBean.class)
public class AutoremoteClientAutoConfiguration {

    @Bean
    ConsumeServicesPostProcessor consumeServicesPostProcessor() {
        return new ConsumeServicesPostProcessor();
    }
}
