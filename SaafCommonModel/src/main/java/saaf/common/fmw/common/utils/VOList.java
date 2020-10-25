package saaf.common.fmw.common.utils;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class VOList implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    public static final int VOLIST_NONRESIZE = 1;
    public static final int VOLIST_RESIZE = 2;

    private String[][] arrayVO = null;
    @SuppressWarnings("rawtypes")
    private List<Map> listVO = null;
    private int VOType = -1;
    //列名
    private String[] VOTitle = null;

    public VOList(int voType) {
        if (voType > 2 || voType < 1) {
            VOType = 1;
        }

        VOType = voType;
    }

    /**
     * 返回一行名值对 返回类型Hashtable
     * @param iRow 行号
     * @return Hashtable 的名值对
     */
    @SuppressWarnings( { "rawtypes", "unchecked" })
    public Map getByIndex(int iRow) {
        Map ret = null;
        switch (VOType) {
        case VOLIST_NONRESIZE:
            if (VOTitle == null || VOTitle.length == 0) {
                return null;
            }
            Map ht = new HashMap();
            for (int i = 0; i < VOTitle.length; i++) {
                ht.put(VOTitle[i], arrayVO[iRow][i]);
            }
            ret = ht;
            break;
        case VOLIST_RESIZE:
            ret = (Map)listVO.get(iRow);
            break;
        }
        return ret;
    }

    /**
     * 得到一行记录
     * @param iRow 行号
     * @return 行值可与getTitle（）函数的返回结果组成名值对
     */
    @SuppressWarnings("rawtypes")
    public String[] get(int iRow) {
        String[] ret = null;
        if (VOTitle == null || VOTitle.length == 0) {
            return null;
        }
        switch (VOType) {
        case VOLIST_NONRESIZE:
            ret = arrayVO[iRow];
            break;
        case VOLIST_RESIZE:
            Map ht = (HashMap)listVO.get(iRow);
            int size = VOTitle.length;
            String[] temp = new String[size];
            for (int i = 0; i < VOTitle.length; i++) {
                temp[i] = (String)ht.get(VOTitle[i]);
            }
            ret = temp;
            break;
        }
        return ret;
    }

    /**
     * 获得指定的列名所在的列号
     * @param value 列名称
     * @return 列号
     */
    private int getPos(String value) {
        int ret = -1;
        if (VOTitle == null) {
            return ret;
        }
        for (int i = 0; i < VOTitle.length; i++) {
            if (VOTitle[i].toUpperCase().equals(value.toUpperCase())) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    /**
     *
     * @param col
     * @return
     */
    public String[] getColumn(int col) {
        String[] ret = null;
        int size = getSize();
        if (size == 0) {
            return null;
        }
        switch (VOType) {
        case VOLIST_NONRESIZE:
            String[] tmp = new String[size];
            for (int i = 0; i < size; i++) {
                tmp[i] = arrayVO[i][col];
            }
            ret = tmp;
            break;
        case VOLIST_RESIZE:
            if (VOTitle == null) {
                return null;
            }
            String colName = VOTitle[col];
            String[] tmps = new String[size];
            try {
                for (int i = 0; i < size; i++) {
                    tmps[i] = (String)getByIndex(i).get(colName);
                }
                ret = tmps;
            } catch (Exception e) {
                //e.printStackTrace();
            }
            break;
        }
        return ret;
    }

    /**
     * 取得指定列的所有值
     * @param colName 列名
     * @return 所给列名的所有值
     * @throws Exception
     */
    public String[] getColumn(String colName) {
        String[] ret = null;
        switch (VOType) {
        case VOLIST_NONRESIZE:
            int pos = getPos(colName);
            if (pos == -1) {
                return null;
            }
            ret = new String[arrayVO.length];
            for (int i = 0; i < arrayVO.length; i++) {
                ret[i] = arrayVO[i][pos];
            }
            break;
        case VOLIST_RESIZE:
            ret = new String[listVO.size()];
            for (int i = 0; i < listVO.size(); i++) {
                @SuppressWarnings("rawtypes")
                Map ht = (Map)listVO.get(i);
                Object obj = ht.get(colName.toUpperCase());
                if (obj == null) {
                    ret[i] = "";
                } else {
                    ret[i] = obj.toString();
                }
            }
            break;
        }
        return ret;
    }

    /**
     * 得到名值对的大小
     * @return 名值对的大小
     */
    public int getSize() {
        int ret = 0;
        switch (VOType) {
        case VOLIST_NONRESIZE:
            if (arrayVO != null) {
                ret = arrayVO.length;
            }
            break;
        case VOLIST_RESIZE:
            if (listVO != null) {
                ret = listVO.size();
            }
            break;
        }
        return ret;
    }

    /**
     * 设置值元素
     * @param ResultSet rs 查询得到的结果集
     */
    @SuppressWarnings( { "unchecked", "rawtypes" })
    public void set(ResultSet rs) {
        try {
            ResultSetMetaData rsm = rs.getMetaData();
            int colNum = 0;
            colNum = rsm.getColumnCount();
            VOTitle = new String[colNum];
            String value = "";
            //列名，存放在VOTitle
            for (int i = 1; i <= colNum; i++) {
                VOTitle[i - 1] = rsm.getColumnName(i).toUpperCase();
            }

            switch (VOType) {
            case VOLIST_NONRESIZE:
                int rowNum = 0;
                rs.last();
                rowNum = rs.getRow();
                arrayVO = new String[rowNum][colNum];
                int rowPos = 0;
                rs.beforeFirst();
                while (rs.next()) {
                    for (int i = 0; i < VOTitle.length; i++) {
                        value = rs.getString(i + 1);
                        if (value == null) {
                            value = "";
                        }
                        arrayVO[rowPos][i] = value.trim();
                    }
                    rowPos += 1;
                }
                break;
            case VOLIST_RESIZE:
                listVO = new ArrayList();
                while (rs.next()) {
                    HashMap<String, String> ht = new HashMap<String, String>();
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        String tt = rs.getString(i);
                        if (tt == null) {
                            tt = "";
                        }
                        ht.put(rsm.getColumnName(i), tt);
                    }
                    listVO.add(ht);
                }
                break;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 设置值元素
     * @param values
     */
    public void set(String[][] values) {
        if (VOType == VOLIST_NONRESIZE) {
            arrayVO = values;
        }
    }

    /**
     * 设置值元素
     * @param values
     */
    @SuppressWarnings("unchecked")
    public void set(@SuppressWarnings("rawtypes")
        List values) {
        if (VOType == VOLIST_RESIZE) {
            listVO = values;
        }
    }

    /**
     * 得到指定行和指定列的值
     * @param index
     * @param col
     * @return
     */
    public String getItemValue(int index, int col) {
        String ret = "";
        switch (VOType) {
        case VOLIST_NONRESIZE:
            if (arrayVO != null) {
                ret = arrayVO[index][col];
            }
            break;
        case VOLIST_RESIZE:
            if (listVO != null) {
                String[] tmp = get(index);
                ret = tmp[col];
            }
            break;
        }
        return ret;
    }

    /**
     * 取得指定行指定列的值
     * @param index 行号
     * @param colName 列名称
     * @return 值
     */
    public String getItemValue(int index, String colName) {
        String ret = "";
        switch (VOType) {
        case VOLIST_NONRESIZE:
            if (arrayVO == null) {
                return "";
            }
            int pos = getPos(colName);
            if (pos == -1) {
                return "";
            }
            ret = arrayVO[index][pos];
            break;
        case VOLIST_RESIZE:
            if (listVO == null) {
                return "";
            }
            @SuppressWarnings("rawtypes")
            Map ht = (Map)listVO.get(index);
            // ret = (String)ht.get(colName);
            Object obj = ht.get(colName.toUpperCase());
            if (obj == null) {
                ret = "";
            } else {
                ret = obj.toString();
            }

            break;
        }
        return ret;
    }

    /**
     * 获取时间格式的字符串
     * @param index
     * @param col
     * @return
     */
    public String getItemDateValue(int index, int col) {
        return this.stringToDate(this.getItemValue(index, col));
    }

    /**
     * 获取时间格式的字符串
     * @param index
     * @param colName
     * @return
     */
    public String getItemDateValue(int index, String colName) {
        return this.stringToDate(this.getItemValue(index, colName));
    }

    private String stringToDate(String ret) {
        String retParse = ret;
        java.text.SimpleDateFormat ddf;
        java.text.SimpleDateFormat df;
        Date parseDate;
        if (ret != null && (ret = ret.trim()).length() > 0) {
            try {
                switch (ret.length()) {
                case 8:
                    ddf = new java.text.SimpleDateFormat("yyyyMMdd");
                    parseDate = ddf.parse(ret);
                    df = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
                    retParse = df.format(parseDate);
                    break;
                case 12:
                    ddf = new java.text.SimpleDateFormat("yyyyMMddHHmm");
                    parseDate = ddf.parse(ret);
                    df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.SIMPLIFIED_CHINESE);
                    retParse = df.format(parseDate);
                    break;
                case 14:
                    ddf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
                    parseDate = ddf.parse(ret);
                    df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
                    retParse = df.format(parseDate);
                    break;
                default:
                    retParse = ret;
                    break;
                }
            } catch (Exception pex) {
                retParse = ret;
            }
        }
        return retParse;
    }

    /**
     * 向值对象中插入一行值对象
     * @param row 值对象
     * @return False 插入失败 true 插入成功
     */
    @SuppressWarnings( { "rawtypes", "unchecked" })
    public boolean insertRow(Map row) {
        if (VOType == VOLIST_NONRESIZE) {
            return false;
        }
        boolean ret = false;
        if (listVO != null) {
            listVO.add(row);
            ret = true;
        } else {
            listVO = new ArrayList();
            listVO.add(row);
            ret = true;
        }
        return ret;

    }

    /**
     * 向值对象中插入一列
     * @param colName 列名
     * @param value 列值
     * @return true：成功   false：失败
     */
    @SuppressWarnings( { "rawtypes", "unchecked" })
    public boolean insertCol(String colName, String[] value) {
        colName = colName.toUpperCase();
        boolean ret = false;
        if (VOType == VOLIST_NONRESIZE) {
            return false;
        } else {
            fillTitle(colName);
            if (listVO == null) {
                listVO = new ArrayList<Map>();
                for (int i = 0; i < value.length; i++) {
                    Map<String, String> ht = new HashMap<String, String>();
                    ht.put(colName, value[i]);
                    listVO.add(i, ht);
                }
                ret = true;
            } else {
                if (listVO.size() != value.length) {
                    return false;
                }
                for (int i = 0; i < value.length; i++) {
                    Map tmp = (Map)listVO.get(i);
                    tmp.put(colName, value[i]);
                }
                ret = true;
            }
        }
        return ret;
    }

    private void fillTitle(String name) {
        name = name.toUpperCase();
        if (VOTitle == null) {
            VOTitle = new String[1];
            VOTitle[0] = name;
        } else {
            String[] temp = new String[VOTitle.length + 1];
            for (int i = 0; i < VOTitle.length; i++) {
                temp[i] = VOTitle[i];
            }
            temp[VOTitle.length] = name;
            VOTitle = temp;
        }
    }

    /**
     * 设置值对象标题
     * @param title 值对象标题
     */
    public void setTitle(String[] title) {
        for (int i = 0; i < title.length; i++) {
            title[i] = title[i].toUpperCase();
        }
        VOTitle = title;
    }

    /**
     * 取得值对象标题
     * @return
     */
    public String[] getTitle() {
        return VOTitle;
    }

    @SuppressWarnings("rawtypes")
    public void updateItem(int row, String name, String value) {
        switch (VOType) {
        case VOLIST_NONRESIZE:
            int pos = getPos(name);
            if (pos == -1) {
                return;
            }
            arrayVO[row][pos] = value;
            break;
        case VOLIST_RESIZE:
            if (listVO == null) {
                listVO = new ArrayList<Map>();
            }
            @SuppressWarnings("unchecked")
            Map<String, String> tmp = (Map)listVO.get(row);
            tmp.put(name.toUpperCase(), value);
            break;
        }
    }

    @SuppressWarnings( { "rawtypes", "unchecked" })
    public void updateItem(int row, int col, String value) {
        switch (VOType) {
        case VOLIST_NONRESIZE:
            arrayVO[row][col] = value;
            break;
        case VOLIST_RESIZE:
            if (listVO == null) {
                listVO = new ArrayList();
            }
            Map tmp = (Map)listVO.get(row);
            tmp.put(VOTitle[col], value);
            break;
        }
    }

    public int getRowNo(String colName, String value) {
        int ret = -1;
        String[] values = getColumn(colName.toUpperCase());
        for (int i = 0; i < values.length; i++) {
            if (values[i].trim().equals(value.trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    public Object clone() {
        VOList vo = new VOList(VOType);
        vo.setTitle(VOTitle);
        try {
            switch (VOType) {
            case VOLIST_NONRESIZE:
                vo.set(arrayVO);
                break;
            case VOLIST_RESIZE:
                vo.set(listVO);
                break;
            }
            return vo;
        } catch (Exception e) {
            //e.printStackTrace();
            return new VOList(VOType);
        }

    }

    /**
     * 此方法只适用于"变长"的VOList
     */
    public boolean isItemNull(int index, String colName) {
        boolean ret = false;
        switch (VOType) {
        case VOLIST_NONRESIZE:
            if (arrayVO == null) {
                return true;
            }
            break;

        case VOLIST_RESIZE:
            if (listVO == null) {
                return true;
            }
            @SuppressWarnings("rawtypes")
            Map ht = (Map)listVO.get(index);
            Object obj = ht.get(colName.toUpperCase());
            if (obj == null) {
                ret = true;
            }
            break;
        }
        return ret;
    }
}
