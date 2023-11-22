package me.stef.bluemooncity.config;

import jakarta.ws.rs.Path;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
public class ServerConfig extends ResourceConfig {

    Logger log = LoggerFactory.getLogger(this.getClass());

    public ServerConfig() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        scanner.findCandidateComponents("me.stef.bluemooncity.service")
                .forEach(bean -> {
                    try {
                        register(Class.forName(bean.getBeanClassName()));
                    } catch (ClassNotFoundException e) {
                        log.error(e.getMessage());
                    }
                });
    }
}
