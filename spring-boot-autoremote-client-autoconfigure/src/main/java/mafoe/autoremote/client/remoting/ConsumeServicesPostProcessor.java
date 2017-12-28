package mafoe.autoremote.client.remoting;

import mafoe.autoremote.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Connects the client with a number of exposed services via HttpInvokerProxyFactoryBean, using Spring HTTP invoker.
 */
@Component
public class ConsumeServicesPostProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumeServicesPostProcessor.class);
    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        try {
            postProcessBeanDefinitionRegistryImpl(registry);
        } catch (NoSuchBeanDefinitionException e) {
            throw new RuntimeException("You need to have a bean with the type " +
                    "mafoe.autoremote.client.remoting.ConsumeServiceConfiguration in your project if you want to use " +
                    "autoremoting", e);
        }

    }

    private void postProcessBeanDefinitionRegistryImpl(BeanDefinitionRegistry registry) {
        ConsumeServiceConfiguration configuration = applicationContext.getBean(ConsumeServiceConfiguration.class);
        String serverUrl = configuration.getServerUrl();
        configuration
                .getServiceInterfaces()
                .forEach(serviceInterface -> consumeService(serviceInterface, registry, serverUrl));
    }

    private void consumeService(Class<?> serviceInterface, BeanDefinitionRegistry registry, String serverUrl) {

        String endpointUrl = serverUrl + RemotingHelper.serviceInterfaceToEndpoint(serviceInterface);

        AbstractBeanDefinition proxyDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(HttpInvokerProxyFactoryBean.class.getName())
                .addPropertyValue("serviceUrl", endpointUrl)
                .addPropertyValue("serviceInterface", serviceInterface)
                .setRole(BeanDefinition.ROLE_INFRASTRUCTURE)
                .getBeanDefinition();

        String proxyBeanName = RemotingHelper.serviceInterfaceToEndpoint(serviceInterface);
        if (registry.containsBeanDefinition(proxyBeanName)) {
            LOG.warn("While trying to register a proxy with the name {} for the endpoint {}, "
                            + " a bean with that name was already defined: {}",
                    proxyBeanName,
                    endpointUrl,
                    registry.getBeanDefinition(proxyBeanName));
        } else {
            LOG.info("Consuming a service at the endpoint {} via the proxy bean {}", endpointUrl, proxyBeanName);
            registry.registerBeanDefinition(proxyBeanName, proxyDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}