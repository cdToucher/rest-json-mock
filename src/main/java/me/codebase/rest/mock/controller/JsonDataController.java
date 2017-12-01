package me.codebase.rest.mock.controller;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import me.codebase.rest.mock.config.ConfigFactory;
import me.codebase.rest.mock.config.Mapper;
import me.codebase.rest.mock.constant.Patterns;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by chendong on 2017/11/30.
 */
@Lazy
@DependsOn("dispatchRouting")
@Component("controller")
@Description("support json data to different url")
@RestController
public class JsonDataController {

    @Resource
    private ConfigFactory configFactory;

    @RequestMapping(path = Patterns.testPath, method = {RequestMethod.GET}
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test() {
        return "Hello this is a test!";
    }

    @RequestMapping(path = Patterns.interceptorPath, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getAttribute(Patterns.redirectedURI).toString();
        return getJsonData(path);
    }

    private String getJsonData(String path) throws IOException {
        Mapper mapper = configFactory.get(path);
        if (mapper == null)
            return "NO MATCH";
        String filePath = System.getProperty("user.dir") + "/json/" + mapper.getJson();
        if (Files.isFile().apply(new File(filePath)))
            return Files.toString(new File(filePath), Charset.defaultCharset());
        else
            return Resources.toString(Resources.getResource("json/" + mapper.getJson())
                    , Charset.defaultCharset());
    }


}
