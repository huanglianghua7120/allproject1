package saaf.common.fmw.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class GlobalVariableUtil {
    private static HashMap<String, Object> variableMap = new HashMap<String, Object>();

    // 单实例构造方法

    private GlobalVariableUtil() {
        super();
    }

    /**
     * 获取一个指定的全局变量
     * @param key
     * @return
     */
    public static Object getValue(String key) {
        return variableMap.get(key);
    }

    /**
     * 设置全局变量
     * @param key
     * @param value
     */
    public synchronized static void setValue(String key, Object value) {
        variableMap.put(key, value);
    }

    /**
     * 判断是否存在一个变量
     *
     * @param key
     * @return
     */
    public synchronized static boolean hasVariable(String key) {
        return variableMap.containsKey(key);
    }

    /**
     * 清除所有变量
     */
    public synchronized static void clearAll() {
        variableMap.clear();
    }

    /**
     * 清除某一类特定变量,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
     *
     * @param type
     */
    public synchronized static void clearAll(String type) {
        try {
            String key;
            Iterator<Entry<String, Object>> i = variableMap.entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> entry = i.next();
                key = entry.getKey();
                if (key.startsWith(type)) { //以某段相同的字符作为开头则认为是同一类型
                    clearOnly(key);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 清除指定的变量
     *
     * @param key
     */
    public synchronized static void clearOnly(String key) {
        variableMap.remove(key);
    }

    /**
     * 获取全局变量数量
     *
     * @return
     */
    public static int getVariableSize() {
        return variableMap.size();
    }

    /**
     * 获取指定的类型的大小
     *
     * @param type
     * @return
     */
    public static int getVariableSize(String type) {
        int k = 0;
        try {
            String key;
            Iterator<Entry<String, Object>> i = variableMap.entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> entry = i.next();
                key = entry.getKey();
                if (key.startsWith(type)) { //以某段相同的字符作为开头则认为是同一类型
                    k++;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return k;
    }

    /**
     * 获取全局变量对象中的所有KEY名称
     *
     * @return
     */
    public static ArrayList<String> getVariableAllkey() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String key;
            Iterator<Entry<String, Object>> i = variableMap.entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> entry = i.next();
                key = entry.getKey();
                list.add(key);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取全局变量对象中指定类型的KEY名称
     *
     * @param type
     * @return
     */
    public static ArrayList<String> getVariableListkey(String type) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String key;
            Iterator<Entry<String, Object>> i = variableMap.entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> entry = i.next();
                key = entry.getKey();
                if (key.startsWith(type)) { //以某段相同的字符作为开头则认为是同一类型
                    list.add(key);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return list;
    }

}
