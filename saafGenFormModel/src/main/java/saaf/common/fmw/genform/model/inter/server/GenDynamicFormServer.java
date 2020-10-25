package saaf.common.fmw.genform.model.inter.server;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.model.dao.GenDynamicFormDAO;
import saaf.common.fmw.genform.model.inter.IGenDynamicForm;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("genDynamicFormServer")
public class GenDynamicFormServer implements IGenDynamicForm {
    public GenDynamicFormServer() {
        super();
    }
    
    @Autowired
    private GenDynamicFormDAO genDynamicFormDAO;
    
    public Pagination<JSONObject> findPagination(CharSequence queryString, CharSequence countString, int pageIndex, int pageSize, Object[] params) {
        return genDynamicFormDAO.findPagination(queryString, countString, pageIndex, pageSize, params);
    }    

    @Override
	public Pagination<JSONObject> findPagination(CharSequence queryString,
			CharSequence countString,Map paramsMap, int pageIndex, int pageSize) {
		return genDynamicFormDAO.findPagination(queryString, countString, paramsMap, pageIndex, pageSize);
	}

	public int createTableEntity(String tableName, String createTableSQL) {
        String result = genDynamicFormDAO.createTableEntity(tableName, createTableSQL);
        if(GenDynamicFormDAO.CREATE_UPDATE_TABLE_EXIT.equals(result)){
        	return 1;//表存在
        }else if(GenDynamicFormDAO.CREATE_UPDATE_TABLE_SUCCESS.equals(result)){
        	return 0;//创建表成功
        }else{
        	return -1;//创建表失败
        }
    }

    public JSONObject findById(String tableName,Serializable id) {
        return genDynamicFormDAO.findById(tableName, id);
    }

    public Integer saveOrUpdateExecuteSQL(String tableName, JSONObject entity) {
        return genDynamicFormDAO.saveOrUpdateExecuteSQL(tableName, entity);
    }
}
