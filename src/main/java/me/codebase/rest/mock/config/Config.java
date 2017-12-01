package me.codebase.rest.mock.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chendong on 2017/12/1.
 */
public class Config {

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(List<Mapper> mappers) {
        this.mappers = mappers;
    }

    private String filter = "/**";

    private List<Mapper> mappers = new ArrayList<>();
}
