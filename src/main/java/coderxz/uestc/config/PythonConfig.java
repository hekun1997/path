package coderxz.uestc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "python")
@PropertySource(value = "classpath:python-config.properties")
public class PythonConfig {
    private String path;
    private String projectPath;
}
