package me.codebase.rest.mock.config;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import me.codebase.rest.mock.constant.Patterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chendong on 2017/11/30.
 */
@Component
@Lazy
public class ConfigFactory {

    private final static Logger log = LoggerFactory.getLogger(ConfigFactory.class);

    private Map<String, Mapper> mappers = new HashMap<>();

    private Config config;

    public ConfigFactory() {
        String jsonConfig;
        try {
            jsonConfig = Resources.toString(Resources.getResource(Patterns.configJsonName), Charset.defaultCharset());
            this.config = JSON.parseObject(jsonConfig, Config.class);
            config.getMappers().forEach(mapper -> mappers.put(mapper.getPath(), mapper));
            String filePath = System.getProperty("user.dir") + Patterns.configJsonName;
            if (Files.isFile().apply(new File(filePath))) {
                String ext = Files.toString(new File(filePath), Charset.defaultCharset());
                this.config = JSON.parseObject(ext, Config.class);
                config.getMappers().forEach(mapper -> mappers.put(mapper.getPath(), mapper));
            }

        } catch (IOException e) {
            log.info("--------Deserialization failed-------------", e);
        }
    }

    public boolean contains(String path) {
        return mappers.containsKey(path);
    }

    public Mapper get(String path) {
        if (path != null && !Patterns.interceptorPath.equals(path))
            return mappers.get(path);
        else
            return null;
    }

    public String getFilter() {
        return this.config.getFilter();
    }

}
