package saaf.common.fmw.genform.model.inter;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IGenDynamicForm {
    
    public Pagination<JSONObject> findPagination(CharSequence queryString, CharSequence countString, int pageIndex, int pageSize, Object[] params);
    
    public Pagination<JSONObject> findPagination(CharSequence queryString,CharSequence countString, Map paramsMap,int pageIndex,int pageSize);
    
    public int createTableEntity(String tableName, String createTableSQL);
    
    public JSONObject findById(String tableName,Serializable id);
    
    public Integer saveOrUpdateExecuteSQL(String tableName, JSONObject entity);
}
