package computercomponentchooser;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LinkedHashMap2D<T> {
    private LinkedHashMap<String, LinkedHashMap<String,T>> outerMap= new LinkedHashMap<String,LinkedHashMap<String,T>>();
    private LinkedHashMap<String,T> innerMap;

    public LinkedHashMap2D() {
    }

    public void addElement(String key1, String key2, T value) {
        innerMap=outerMap.get(key1);
        if (innerMap==null) {
            innerMap = new LinkedHashMap<String,T>();
            outerMap.put(key1,innerMap);
        }
        innerMap.put(key2,value);
    }

    public T getElement(String key1, String key2) {
        innerMap = outerMap.get(key1);
        if (innerMap==null) {
            return null;
        }
        return innerMap.get(key2);
    }

    public void removeElement(String key1, String key2) {
        innerMap = outerMap.get(key1);
        innerMap.remove(key2);
    }

    public ArrayList<String> getNameList () {
        ArrayList<String> list = new ArrayList();
        for (String key1 : outerMap.keySet()) {
            innerMap=outerMap.get(key1);
            for (String key2 : innerMap.keySet()) {
                list.add(key2);
            }
        }
        return list;
    }
}
