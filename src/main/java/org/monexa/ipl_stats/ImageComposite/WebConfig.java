package org.monexa.ipl_stats.ImageComposite;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.storage.local-path:src/main/resources/static/player-images}")
    private String localPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = Paths.get(localPath).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/player-images/**")
                .addResourceLocations("file:" + absolutePath + "/");
    }
}
