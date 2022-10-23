package computercomponentchooser;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * LinkedHashMap2D is a 2D LinkedHashMap data structure that stores the components in a build. In the context of this
 * program, the outer LinkedHashMap stores the component types (e.g. CPU, GPU, etc.) and the inner LinkedHashMaps
 * store the components of those types.
 *
 * @param <T> The object that is to be stored in the LinkedHashMap.
 */
public class LinkedHashMap2D<T> {
    private LinkedHashMap<String, LinkedHashMap<String, T>> outerMap
            = new LinkedHashMap<String, LinkedHashMap<String, T>>();
    private LinkedHashMap<String, T> innerMap = new LinkedHashMap<String, T>();

    /**
     * Initializes a new LinkedHashMap2D object.
     */
    public LinkedHashMap2D() {
    }

    /**
     * Adds a new object to be stored in the 2D LinkedHashMap at the specified keys.
     *
     * @param key1 The key of the outer LinkedHashMap.
     * @param key2 The key of the inner LinkedHashMap.
     * @param value The value to be stored in the LinkedHashMap.
     */
    public void addElement(String key1, String key2, T value) {
        innerMap = outerMap.get(key1);
        if (innerMap == null) {
            LinkedHashMap<String, T> innerMap = new LinkedHashMap<String, T>();
            outerMap.put(key1, innerMap);
            innerMap.put(key2, value);
        } else {
            innerMap.put(key2, value);
        }
    }

    /**
     * Get the value of the object that is stored in the 2D LinkedHashMap at the specified keys.
     *
     * @param key1 The key of the outer LinkedHashMap.
     * @param key2 The key of the inner LinkedHashMap.
     * @return The value of the object that is stored in the LinkedHashMap.
     */
    public T getElement(String key1, String key2) {
        innerMap = outerMap.get(key1);
        if (innerMap == null) {
            return null;
        }
        return innerMap.get(key2);
    }

    /**
     * Removes the object that is stored in the 2D LinkedHashMap at the specified keys.
     *
     * @param key1 The key of the outer LinkedHashMap.
     * @param key2 The key of the inner LinkedHashMap.
     */
    public void removeElement(String key1, String key2) {
        innerMap = outerMap.get(key1);
        innerMap.remove(key2);
    }

    /**
     * Gets a list of all the keys of the inner LinkedHashMaps.
     *
     * @return A list of all the keys of the inner LinkedHashMaps.
     */
    public ArrayList<String> getNameList() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key1 : outerMap.keySet()) {
            innerMap = outerMap.get(key1);
            for (String key2 : innerMap.keySet()) {
                list.add(key2);
            }
        }
        return list;
    }

    /**
     * Gets a list of all the keys of the outer LinkedHashMap.
     *
     * @return A list of all the keys of the outer LinkedHashMap.
     */
    public ArrayList<String> getTypeList() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key1 : outerMap.keySet()) {
            list.add(key1);
        }
        return list;
    }

    /**
     * Gets a list of all the keys of the specified inner LinkedHashMap.
     *
     * @param type The type of the component.
     * @return A list of all the keys of the inner LinkedHashMaps of the specified type.
     */
    public ArrayList<String> getNamesOfTypeList(String type) {
        ArrayList<String> list = new ArrayList<String>();
        innerMap = outerMap.get(type);
        for (String key2 : innerMap.keySet()) {
            list.add(key2);
        }
        return list;
    }
}
