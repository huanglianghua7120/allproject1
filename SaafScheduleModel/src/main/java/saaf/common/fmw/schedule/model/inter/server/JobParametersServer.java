package saaf.common.fmw.schedule.model.inter.server;


import com.alibaba.fastjson.JSON;

import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.dao.BaseViewObject;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.model.entities.SaafJobParametersEntity_HI;
import saaf.common.fmw.schedule.model.entities.readonly.SaafJobParametersEntity_HI_RO;
import saaf.common.fmw.schedule.model.entities.readonly.SaafSchedulesEntity_HI_RO;
import saaf.common.fmw.schedule.model.inter.IJobParameters;

@Component("jobParametersServer")
public class JobParametersServer implements IJobParameters {

    private static Logger log = LoggerFactory.getLogger(JobParametersServer.class);

    private ViewObject saafJobParametersDAOHI;
    private BaseViewObject saafJobParametersDAOHIRO;

    @Resource(name = "saafJobParametersDAO_HI")
    public void setSaafJobParametersDAOHI(ViewObject saafJobParametersDAOHI) {
        this.saafJobParametersDAOHI = saafJobParametersDAOHI;
    }

    @Resource(name = "saafJobParametersDAO_HI_RO")
    public void setSaafJobParametersDAOHIRO(BaseViewObject saafJobParametersDAOHIRO) {
        this.saafJobParametersDAOHIRO = saafJobParametersDAOHIRO;
    }

    public JobParametersServer() {
        super();
    }

    public String findJobParameters(JSONObject parameters, Integer pageIndex, Integer pageRows) {

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        //        ViewObject vo = this.saafJobParametersDAOHI;
        BaseViewObject vo = this.saafJobParametersDAOHIRO;

        String sql = SaafJobParametersEntity_HI_RO.QUERY_SQL;
        String where = " ";
        Map<String, Object> map = new HashMap<String, Object>();
        Pagination<SaafJobParametersEntity_HI_RO> rows = null;
        //
        if (null != parameters.get("paramId") && !"".equals(parameters.get("paramId"))) {
            map.put("varParamId", Integer.parseInt(parameters.get("paramId").toString()));
            where = where + " and sjp.PARAM_ID = :varParamId ";
        }
        //
        if (null != parameters.get("jobId") && !"".equals(parameters.get("jobId"))) {
            map.put("varJobId", Integer.parseInt(parameters.get("jobId").toString()));
            where = where + " and sjp.JOB_ID = :varJobId ";
        }
        //
        if (null != parameters.get("paramSeqNum") && !"".equals(parameters.get("paramSeqNum"))) {
            map.put("varParamSeqNum", Integer.parseInt(parameters.get("paramSeqNum").toString()));
            where = where + " and sjp.PARAM_SEQ_NUM = :varParamSeqNum ";
        }
        //
        if (null != parameters.get("paramName") && !"".equals(parameters.get("paramName"))) {
            map.put("varParamName", parameters.get("paramName").toString());
            where = where + " and sjp.PARAM_NAME = :varParamName ";
        }
        //
        if (null != parameters.get("paramType") && !"".equals(parameters.get("paramType"))) {
            map.put("varParamType", Integer.parseInt(parameters.get("paramType").toString()));
            where = where + " and sjp.PARAM_TYPE = :varParamType ";
        }
        //
        if (null != parameters.get("paramRegion") && !"".equals(parameters.get("paramRegion"))) {
            map.put("varParamRegion", parameters.get("paramRegion").toString());
            where = where + " and sjp.PARAM_REGION = :varParamRegion ";
        }

        //当 提交请求 时，查询参数操作 则仅仅查询已启用的参数（即：isEnabled字段标识为‘Y’）
        //isNotSubmitRequest 值为‘Y’时，表示当前查询操作为 新增 或 编辑 job参数
        //                   值为‘N’时，表示当前查询为 提交请求 时的查询参数操作 , 此时 仅仅 查询 ‘已启用’的参数
        if (parameters.get("isNotSubmitRequest") != null && parameters.get("isNotSubmitRequest").toString().equalsIgnoreCase("N")) {
            where = where + " and sjp.IS_ENABLED = 'Y' ";
        }

        //结果排序：paramSeqNum 字段 升序
        where = where + " order by sjp.PARAM_SEQ_NUM asc";

        if (map.size() > 0) {
            rows = vo.findPagination(sql + where, map, pageIndex, pageRows);
        } else {
            rows = vo.findPagination(sql + where, pageIndex, pageRows);
        }

        return JSON.toJSONString(rows);
    }

    public String saveParameter(JSONObject parameters) {
        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //参数所属的jobId
        if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobId为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数序号
     /*   if (parameters.get("paramSeqNum") == null || parameters.get("paramSeqNum") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！参数序号（paramSeqNum）为必填!", 0, null);
            return jsonResult.toString();
        }*/
        //参数名称
        if (parameters.get("paramName") == null || parameters.get("paramName") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！参数名称（paramName）为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数类型
        if (parameters.get("paramType") == null || parameters.get("paramType") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！参数类型（paramType）为必填!", 0, null);
            return jsonResult.toString();
        }

      /*  try {
            Integer.parseInt(parameters.get("paramSeqNum").toString());
        } catch (NumberFormatException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！参数序号（paramSeqNum）必须为数字", 0, null);
            return jsonResult.toString();
        }*/

        if (isParamRegionValue_BODY_Repeat(parameters)) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！'BODY'位置已存在参数，不能再添加", 0, null);
            return jsonResult.toString();
        }
        //
        StringBuilder msg = new StringBuilder();
        if (isParamSeqNumRepeat(parameters)) {
            msg.append("参数序号（paramSeqNum）重复 ");
        }
        if (isParamNameRepeat(parameters)) {
            msg.append("参数名称（paramName）重复 ");
        }
        log.info("--------------------- createParameters ---------" + msg + "----------------------");

        if (msg.toString().length() > 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！" + msg.toString(), 0, null);
            return jsonResult.toString();
        }

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        SaafJobParametersEntity_HI row = new SaafJobParametersEntity_HI();

        //
        int userId = Integer.parseInt(parameters.get("varUserId").toString());
        row.setCreationDate(new Timestamp(new Date().getTime()));
        row.setCreatedBy(userId);
        row.setLastUpdateDate(new Timestamp(new Date().getTime()));
        row.setLastUpdateLogin(userId);
        row.setLastUpdatedBy(userId);
        row.setOperatorUserId(userId);
        
        //
        row.setJobId(Integer.parseInt(parameters.get("jobId").toString()));
        row.setParamSeqNum(Integer.parseInt(parameters.get("paramSeqNum").toString()));
        row.setParamName(parameters.get("paramName").toString());
        row.setParamType(parameters.get("paramType").toString());
        row.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : null);
        row.setIsRequired(parameters.get("isRequired") != null ? parameters.get("isRequired").toString() : "");
        //默认启用
        row.setIsEnabled(parameters.get("isEnabled") != null ? parameters.get("isEnabled").toString() : "Y");
        row.setDefaultValue(parameters.get("defaultValue") != null ? parameters.get("defaultValue").toString() : "");
        ////记录参数位置：WebService（url、body、head），package（in、out）
        row.setParamRegion((parameters.get("paramRegion") != null ? parameters.get("paramRegion").toString() : ""));
        //
        vo.save(row);
        //
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "新增Job参数成功!", 1, null);
        return jsonResult.toString();
    }
    
	public String saveParameterInfo(JSONObject parameters) {
		com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

		// 参数名称
		if (parameters.get("paramName") == null || parameters.get("paramName") == "") {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数名称（paramName）为必填!", 0, null);
			return jsonResult.toString();
		}
		// 参数类型
		if (parameters.get("paramType") == null || parameters.get("paramType") == "") {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数类型（paramType）为必填!", 0, null);
			return jsonResult.toString();
		}
		
		//
		StringBuilder msg = new StringBuilder();
		if (isParamNameRepeat(parameters)) {
			msg.append("参数名称（paramName）重复 ");
		}
		log.info("--------------------- editParameter ---------" + msg + "----------------------");

		if (msg.toString().length() > 0) {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存更新失败！" + msg.toString(), 0, null);
			return jsonResult.toString();
		}
		int userId = Integer.parseInt(parameters.get("varUserId").toString());

		if (parameters.get("paramId") == null || parameters.get("paramId") == "") {
          //新增
		
			if (isParamRegionValue_BODY_Repeat(parameters)) {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！'BODY'位置已存在参数，不能再添加", 0, null);
				return jsonResult.toString();
			}
	
			ViewObject vo = this.saafJobParametersDAOHI;
			SaafJobParametersEntity_HI row = new SaafJobParametersEntity_HI();

			//
			
			row.setCreationDate(new Timestamp(new Date().getTime()));
			row.setCreatedBy(userId);
			row.setLastUpdateDate(new Timestamp(new Date().getTime()));
			row.setLastUpdateLogin(userId);
			row.setLastUpdatedBy(userId);
			row.setOperatorUserId(userId);
			//
			row.setJobId(Integer.parseInt(parameters.get("jobId").toString()));
			int seconds = (int) (System.currentTimeMillis() / 1000);
			row.setParamSeqNum(seconds);//从数字改为jobId 保持唯一性
			row.setParamName(parameters.get("paramName").toString());
			row.setParamType(parameters.get("paramType").toString());
			row.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : null);
			row.setIsRequired(parameters.get("isRequired") != null ? parameters.get("isRequired").toString() : "");
			// 默认启用
			row.setIsEnabled(parameters.get("isEnabled") != null ? parameters.get("isEnabled").toString() : "Y");
			row.setDefaultValue(
					parameters.get("defaultValue") != null ? parameters.get("defaultValue").toString() : "");
			//// 记录参数位置：WebService（url、body、head），package（in、out）
			row.setParamRegion((parameters.get("paramRegion") != null ? parameters.get("paramRegion").toString() : ""));
			//
			vo.save(row);
			//
			jsonResult = SaafToolUtils.convertResultJSONObj("S", "新增Job参数成功!", 1, row);
			return jsonResult.toString();
		} else {
			// 更新job参数时： 参数Id、参数序号、参数名称、参数类型 为必填。 jobId 不会被修改。

			// 参数位置
			if (parameters.get("paramRegion") == null || parameters.get("paramRegion") == "") {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数位置（paramRegion）为必填!", 0, null);
				return jsonResult.toString();
			}
			// 参数所属的 jobId
			if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！jobId为必填!", 0, null);
				return jsonResult.toString();
			}
			if (isParamRegionValue_BODY_Repeat(parameters)) {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！'BODY'位置已存在参数，不能再添加!", 0, null);
				return jsonResult.toString();
			}
			ViewObject vo = this.saafJobParametersDAOHI;
			SaafJobParametersEntity_HI row = (SaafJobParametersEntity_HI) vo
					.findByProperty("paramId", Integer.parseInt(parameters.get("paramId").toString())).get(0);

			// 跟踪修改
			row.setLastUpdateDate(new Timestamp(new Date().getTime()));
			row.setLastUpdateLogin(-1);
			row.setLastUpdatedBy(Integer.parseInt(parameters.get("varUserId").toString()));
			row.setOperatorUserId(userId);
			//新增的时候 已插入
		   // row.setParamSeqNum(1/*Integer.parseInt(parameters.get("paramSeqNum").toString())*/);
			row.setParamName(parameters.get("paramName") != null ? parameters.get("paramName").toString() : "");
			row.setParamType(parameters.get("paramType") != null ? parameters.get("paramType").toString() : "");
			row.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : null);
			row.setIsRequired(parameters.get("isRequired") != null ? parameters.get("isRequired").toString() : "");
			// 默认启用
			row.setIsEnabled(parameters.get("isEnabled") != null ? parameters.get("isEnabled").toString() : "Y");
			row.setDefaultValue(
					parameters.get("defaultValue") != null ? parameters.get("defaultValue").toString() : "");
			// 记录参数位置：WebService（url、body、head），package（in、out）
			row.setParamRegion((parameters.get("paramRegion") != null ? parameters.get("paramRegion").toString() : ""));
			//
			vo.saveOrUpdate(row);
			//
			jsonResult = SaafToolUtils.convertResultJSONObj("S", "编辑成功!", 1, row);
			return jsonResult.toString();

		}
	}

    public String deleteJobParameter(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        if (parameters.get("paramId") == null || parameters.get("paramId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "删除失败!未指定 Job参数id（paramId）", 0, null);
            return jsonResult.toString();
        }

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        SaafJobParametersEntity_HI row = (SaafJobParametersEntity_HI)vo.getById(Integer.parseInt(parameters.get("paramId").toString()));
        if (row != null) {
            vo.delete(row);
        }
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "删除Job参数成功!", 1, null);
        return jsonResult.toString();
    }

    public String updateJobParameter(JSONObject parameters) {
        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        log.info("---------------------- editParameter ------------ " + parameters.toString());

        //更新job参数时： 参数Id、参数序号、参数名称、参数类型 为必填。 jobId 不会被修改。
        //参数Id
        if (parameters.get("paramId") == null || parameters.get("paramId") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数Id（paramId）为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数序号
     /*   if (parameters.get("paramSeqNum") == null || parameters.get("paramSeqNum") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数序号（paramSeqNum）为必填!", 0, null);
            return jsonResult.toString();
        }*/
        //参数名称
        if (parameters.get("paramName") == null || parameters.get("paramName") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数名称（paramName）为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数类型
        if (parameters.get("paramType") == null || parameters.get("paramType") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数类型（paramType）为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数位置
        if (parameters.get("paramRegion") == null || parameters.get("paramRegion") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数位置（paramRegion）为必填!", 0, null);
            return jsonResult.toString();
        }
        //参数所属的 jobId
        if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！jobId为必填!", 0, null);
            return jsonResult.toString();
        }

      /*  try {
            Integer.parseInt(parameters.get("paramSeqNum").toString());
        } catch (NumberFormatException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！参数序号（paramSeqNum）必须为数字（整数）!", 0, null);
            return jsonResult.toString();
        }*/

        if (isParamRegionValue_BODY_Repeat(parameters)) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！'BODY'位置已存在参数，不能再添加!", 0, null);
            return jsonResult.toString();
        }
        //
        StringBuilder msg = new StringBuilder();
      /*  if (isParamSeqNumRepeat(parameters)) {
            msg.append("参数序号（paramSeqNum）重复 ");
        }*/
    

        log.info("--------------------- editParameter ---------" + msg + "----------------------");

        if (msg.toString().length() > 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "更新失败！" + msg.toString(), 0, null);
            return jsonResult.toString();
        }

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        SaafJobParametersEntity_HI row = (SaafJobParametersEntity_HI)vo.findByProperty("paramId", Integer.parseInt(parameters.get("paramId").toString())).get(0);

        //跟踪修改
        row.setLastUpdateDate(new Timestamp(new Date().getTime()));
        row.setLastUpdateLogin(-1);
        row.setLastUpdatedBy(Integer.parseInt(parameters.get("varUserId").toString()));
        row.setOperatorUserId(Integer.parseInt(parameters.get("varUserId").toString()));
        //
        //row.setParamSeqNum(Integer.parseInt(parameters.get("paramSeqNum").toString()));
        row.setParamName(parameters.get("paramName") != null ? parameters.get("paramName").toString() : "");
        row.setParamType(parameters.get("paramType") != null ? parameters.get("paramType").toString() : "");
        row.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : null);
        row.setIsRequired(parameters.get("isRequired") != null ? parameters.get("isRequired").toString() : "");
        //默认启用
        row.setIsEnabled(parameters.get("isEnabled") != null ? parameters.get("isEnabled").toString() : "Y");
        row.setDefaultValue(parameters.get("defaultValue") != null ? parameters.get("defaultValue").toString() : "");
        //记录参数位置：WebService（url、body、head），package（in、out）
        row.setParamRegion((parameters.get("paramRegion") != null ? parameters.get("paramRegion").toString() : ""));
        //
        vo.saveOrUpdate(row);
        //
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "编辑成功!", 1, null);
        return jsonResult.toString();

    }

    private boolean isParamSeqNumRepeat(JSONObject parameters) {

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        Map<String, Object> map = new HashMap<String, Object>();
        String where = " where 1=1 ";

        Pagination<SaafJobParametersEntity_HI> rows = null;

        int paramSeqNum = Integer.parseInt(parameters.get("paramSeqNum").toString());
        where = where + " and paramSeqNum = :varParamSeqNum ";
        map.put("varParamSeqNum", paramSeqNum);

        int jobId = Integer.parseInt(parameters.get("jobId").toString());
        where = where + " and jobId = :varJobId ";
        map.put("varJobId", jobId);

        //paramId 为空时 表示当前操作为 新增参数
        if (null == parameters.get("paramId")) {
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        } else {
            int paramId = Integer.parseInt(parameters.get("paramId").toString());
            where = where + " and paramId != :varParamId ";
            map.put("varParamId", paramId);
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        }

        if (rows != null && rows.getData().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isParamNameRepeat(JSONObject parameters) {

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        Map<String, Object> map = new HashMap<String, Object>();
        String where = " where 1=1 ";

        Pagination<SaafJobParametersEntity_HI> rows = null;
        String paramName = parameters.get("paramName").toString();
        where = where + " and paramName = :varParamName ";
        map.put("varParamName", paramName);

        int jobId = Integer.parseInt(parameters.get("jobId").toString());
        where = where + " and jobId = :varJobId ";
        map.put("varJobId", jobId);

        //paramId 为空时 表示当前操作为 新增参数
        if (null == parameters.get("paramId")) {
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        } else {
            int paramId = Integer.parseInt(parameters.get("paramId").toString());
            where = where + " and paramId != :varParamId ";
            map.put("varParamId", paramId);
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        }

        if (rows != null && rows.getData().size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isParamRegionValue_BODY_Repeat(JSONObject parameters) {

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobParametersDAO_HI");
        ViewObject vo = this.saafJobParametersDAOHI;
        Map<String, Object> map = new HashMap<String, Object>();
        String where = " where 1=1 ";

        Pagination<SaafJobParametersEntity_HI> rows = null;

        where = where + " and paramRegion = :varParamRegion ";
        map.put("varParamRegion", "BODY");

        int jobId = Integer.parseInt(parameters.get("jobId").toString());
        where = where + " and jobId = :varJobId ";
        map.put("varJobId", jobId);

        //paramId 为空时 表示当前操作为 新增参数
        if (null == parameters.get("paramId")) {
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        } else {
            int paramId = Integer.parseInt(parameters.get("paramId").toString());
            where = where + " and paramId != :varParamId ";
            map.put("varParamId", paramId);
            rows = vo.findPagination("from " + vo.getEntityClass().getSimpleName() + where, map, 0, -1);
        }

        if (rows != null && rows.getData().size() > 0 && parameters.get("paramRegion").toString().equalsIgnoreCase("BODY")) {
            return true;
        } else {
            return false;
        }
    }


}
