package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser implements IDataParser {

    List<Data> dataList;

    public DataParser(List<Data> dataList) {
        this.dataList = dataList;
        dataList.forEach(d -> System.out.println("Name: " + d.name + "\n" + "Price: " + d.price + "\n" + "Type: " + d.type + "\n" + "Expiration: " + d.expiration + "\n"));
    }

    @Override
    public List<Data> getInstancesOfName(String name) {
        return null;
    }

    // Groups classes together with similar names. Returns all of their instances.
    // Name is the key, a list of instances with that name is the pair.
    @Override
    public Map<String, List<Data>> getInstancesOfEveryName() {
        // Name, every class instance holding that name
        Map<String, List<Data>> map = new HashMap<>();

        for (Data data : dataList) {
            String name = data.name;
            if (name == "") continue;

            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            List<Data> list = map.get(name);
            list.add(data);
        }
        return map;
    }

    public void getHowManyTimesAppears() {

    }
}
