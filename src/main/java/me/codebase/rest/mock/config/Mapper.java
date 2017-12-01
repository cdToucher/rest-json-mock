package me.codebase.rest.mock.config;

import org.springframework.http.MediaType;

/**
 * Created by chendong on 2017/11/30.
 */
public class Mapper {

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String path;

    private String method;

    private String json;

    public String getMediaType() {
        return mediaType;
    }

    private transient final String mediaType = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Override
    public String toString() {
        return "Config{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", json='" + json + '\'' +
                '}';
    }

}
