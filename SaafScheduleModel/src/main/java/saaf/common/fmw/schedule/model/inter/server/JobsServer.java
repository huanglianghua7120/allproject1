package saaf.common.fmw.schedule.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.schedule.model.entities.SaafJobsEntity_HI;
import saaf.common.fmw.schedule.model.entities.readonly.SaafJobsEntity_HI_RO;
import saaf.common.fmw.schedule.model.inter.IJobs;
import saaf.common.fmw.schedule.utils.SaafToolUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：并发程序
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             modify
 * ==============================================================================
 */
@Component("jobsServer")
public class JobsServer implements IJobs {

    private static Logger log = LoggerFactory.getLogger(JobsServer.class);

    @Autowired
	private ViewObject<SaafJobsEntity_HI> saafJobsDAO_HI;
	
	@Autowired
	private BaseViewObject<SaafJobsEntity_HI_RO> saafJobsDAO_HI_RO;
    public JobsServer() {
        super();
    }
    /**
     * Description：查询并发程序
     * @param parameters 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @Override
    public String findJobs(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        StringBuffer sb = new StringBuffer(SaafJobsEntity_HI_RO.QUERY_SQL);
        String where = " where 1=1 ";
        Map<String, Object> map = new HashMap<String, Object>();
        Pagination<SaafJobsEntity_HI_RO> rows = null;
        //
        if (null != parameters.get("jobId") && !"".equals(parameters.get("jobId"))) {
            where = where + " and sj.JOB_ID = :varJobId ";
            map.put("varJobId", Integer.parseInt(parameters.get("jobId").toString()));
        }
        if (null != parameters.get("jobName") && !"".equals(parameters.get("jobName"))) {
            where = where + "and sj.JOB_NAME like :varJobName ";
            map.put("varJobName", "%" + parameters.get("jobName") + "%");
        }
        if (null != parameters.get("executableName") && !"".equals(parameters.get("executableName"))) {
            where = where + "and sj.EXECUTABLE_NAME like :varExecutableName ";
            map.put("varExecutableName", "%" + parameters.get("executableName") + "%");
        }
        if (null != parameters.get("method") && !"".equals(parameters.get("method"))) {
            where = where + "and sj.METHOD like :varMethod ";
            map.put("varMethod", "%" + parameters.get("method") + "%");
        }
        if (null != parameters.get("system") && !"".equals(parameters.get("system"))) {
            where = where + "and sj.SYSTEM = :varSystem ";
            map.put("varSystem", parameters.get("system"));
        }
        if (null != parameters.get("module") && !"".equals(parameters.get("module"))) {
            where = where + "and sj.MODULE = :varModule ";
            map.put("varModule", parameters.get("module"));
        }
        if (null != parameters.get("jobType") && !"".equals(parameters.get("jobType"))) {
            where = where + "and sj.JOB_TYPE = :varJobType ";
            map.put("varJobType", parameters.get("jobType"));
        }
        sb.append(where);
        StringBuffer countSb = new StringBuffer("select count(1) from (").append(sb).append(") ");
        //排序
        sb.append(" order by sj.LAST_UPDATE_DATE desc,sj.JOB_NAME ");
        if (null!= map && map.size() > 0) {
            rows = saafJobsDAO_HI_RO.findPagination(sb, countSb,map, pageIndex, pageRows);
        } else {
            rows = saafJobsDAO_HI_RO.findPagination(sb,countSb, pageIndex, pageRows);
        }
        return JSON.toJSONString(rows);

    }
    /**
     * Description：保存并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @Override
    public String saveJob(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();
        log.info("----------Job（create）--------------" + parameters + "---------------");

        //
        if (parameters.get("jobName") == null || parameters.get("jobName") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobName为必填!", 0, null);
            return jsonResult.toString();
        }
        //
        if (parameters.get("executableName") == null || parameters.get("executableName") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！可执行（executableName）为必填!", 0, null);
            return jsonResult.toString();
        }
        //
        if (parameters.get("jobType") == null || parameters.get("jobType") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！Job类型（jobType）为必填!", 0, null);
            return jsonResult.toString();
        }

        if (!(parameters.get("jobType").toString().equalsIgnoreCase("java") || parameters.get("jobType").toString().equalsIgnoreCase("webservice") ||
              parameters.get("jobType").toString().equalsIgnoreCase("package"))) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType必须为‘webservice’、‘java’、‘package’之一", 0, null);
            return jsonResult.toString();
        }

        //如果jobType为 ‘webservice’ 或 ‘java’则， method 字段为必填
        if ((parameters.get("jobType").toString().equalsIgnoreCase("webservice") || parameters.get("jobType").toString().equalsIgnoreCase("java")) &&
            (parameters.get("method") == null || parameters.get("method") == "")) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType为‘webservice’或‘java’时，method为必填!", 0, null);
            return jsonResult.toString();
        }

        if (isJobNameRepeat(parameters)) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobName重复!", 0, null);
            return jsonResult.toString();
        }

        //
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
        int userId = Integer.parseInt(parameters.get("varUserId").toString());
//        ViewObject vo = this.saafJobsDAOHI;
        SaafJobsEntity_HI job = new SaafJobsEntity_HI();
        job.setCreationDate(new Timestamp(new Date().getTime()));
        job.setCreatedBy(userId);
        job.setLastUpdateDate(new Timestamp(new Date().getTime()));
        job.setLastUpdatedBy(userId);
        job.setLastUpdateLogin(userId);
        job.setOperatorUserId(userId);

        //
        job.setJobName(parameters.get("jobName") != null ? parameters.get("jobName").toString() : "");
        job.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : "");
        job.setExecutableName(parameters.get("executableName") != null ? parameters.get("executableName").toString() : "");
        job.setJobType(parameters.get("jobType") != null ? parameters.get("jobType").toString() : "");
        job.setMethod(parameters.get("method") != null ? parameters.get("method").toString() : "");
        job.setOutputFileType(parameters.get("outputFileType") != null ? parameters.get("outputFileType").toString() : "");
        job.setSystem(parameters.get("system") != null ? parameters.get("system").toString() : "");
        job.setModule(parameters.get("module") != null ? parameters.get("module").toString() : "");
        //
        saafJobsDAO_HI.save(job);
        //
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "保存成功!", 1, null);
        jsonResult.put("jobId", job.getJobId());

        return jsonResult.toString();

    }
    /**
     * Description：保存并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @Override
	public String saveJobInfo(JSONObject parameters) {

		com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();
		log.info("----------Job（create）--------------" + parameters + "---------------");

		int userId = Integer.parseInt(parameters.get("varUserId").toString());
		//
		if (parameters.get("jobType") == null || parameters.get("jobType") == "") {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！Job类型（jobType）为必填!", 0, null);
			return jsonResult.toString();
		}

		if (!(parameters.get("jobType").toString().equalsIgnoreCase("java")
				|| parameters.get("jobType").toString().equalsIgnoreCase("webservice")
				|| parameters.get("jobType").toString().equalsIgnoreCase("package"))) {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType必须为‘webservice’、‘java’、‘package’之一", 0,
					null);
			return jsonResult.toString();
		}

		// 如果jobType为 ‘webservice’ 或 ‘java’则， method 字段为必填
		if ((parameters.get("jobType").toString().equalsIgnoreCase("webservice")
				|| parameters.get("jobType").toString().equalsIgnoreCase("java"))
				&& (parameters.get("method") == null || parameters.get("method") == "")) {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType为‘webservice’或‘java’时，method为必填!", 0,
					null);
			return jsonResult.toString();
		}
		if (parameters.get("executableName") == null || parameters.get("executableName") == "") {
			jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！可执行（executableName）必填!", 0, null);
			return jsonResult.toString();
		}

		if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
			//
			if (parameters.get("jobName") == null || parameters.get("jobName") == "") {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobName为必填!", 0, null);
				return jsonResult.toString();
			}

			if (isJobNameRepeat(parameters)) {
				jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobName重复!", 0, null);
				return jsonResult.toString();
			}

			//
			// ViewObject vo =
			// (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
//			ViewObject vo = this.saafJobsDAOHI;
			SaafJobsEntity_HI job = new SaafJobsEntity_HI();
			job.setCreationDate(new Timestamp(new Date().getTime()));
			job.setCreatedBy(userId);
			job.setLastUpdateDate(new Timestamp(new Date().getTime()));
			job.setLastUpdatedBy(userId);
			job.setLastUpdateLogin(userId);
			job.setOperatorUserId(userId);

			//
			job.setJobName(parameters.get("jobName") != null ? parameters.get("jobName").toString() : "");
			job.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : "");
			job.setExecutableName(
					parameters.get("executableName") != null ? parameters.get("executableName").toString() : "");
			job.setJobType(parameters.get("jobType") != null ? parameters.get("jobType").toString() : "");
			job.setMethod(parameters.get("method") != null ? parameters.get("method").toString() : "");
			job.setOutputFileType(
					parameters.get("outputFileType") != null ? parameters.get("outputFileType").toString() : "");
			job.setSystem(parameters.get("system") != null ? parameters.get("system").toString() : "");
			job.setModule(parameters.get("module") != null ? parameters.get("module").toString() : "");
			//
			saafJobsDAO_HI.save(job);
			//
			jsonResult = SaafToolUtils.convertResultJSONObj("S", "保存成功!", 1, job);
			jsonResult.put("jobId", job.getJobId());

			return jsonResult.toString();
		} else {

			// ViewObject vo =
			// (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
//			ViewObject vo = this.saafJobsDAOHI;
			SaafJobsEntity_HI job = null;
			try {
				job = saafJobsDAO_HI
						.findByProperty("jobId", Integer.parseInt(parameters.get("jobId").toString())).get(0);
			} catch (Exception ignore) {
                log.error("未知错误:{}", ignore);
			} finally {
				if (job == null) {
					jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！指定jobId的记录不存在 或 服务异常", 0, null);
					return jsonResult.toString();
				}
			}

		 
			job.setOperatorUserId(userId);
			//
			job.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : "");
			job.setExecutableName(
					parameters.get("executableName") != null ? parameters.get("executableName").toString() : "");
			job.setJobType(parameters.get("jobType") != null ? parameters.get("jobType").toString() : "");
			job.setMethod(parameters.get("method") != null ? parameters.get("method").toString() : "");
			job.setOutputFileType(
					parameters.get("outputFileType") != null ? parameters.get("outputFileType").toString() : "");
			job.setSystem(parameters.get("system") != null ? parameters.get("system").toString() : "");
			job.setModule(parameters.get("module") != null ? parameters.get("module").toString() : "");
			//
			saafJobsDAO_HI.saveOrUpdate(job);

			jsonResult = SaafToolUtils.convertResultJSONObj("S", "保存成功", 1, job);
			return jsonResult.toString();

		}

	}
    /**
     * Description：修改并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @Override
    public String updateJob(JSONObject parameters) {

        //前台修改Job时 ，jobName 并不允许被修改

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        log.info("-----------Job（edit）-------------" + parameters + "---------------");

        //
        if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！jobId为必填!", 0, null);
            return jsonResult.toString();
        }
        //// jobName不允许被再次修改
        //        if (parameters.get("jobName") == null || parameters.get("jobName") == "") {
        //            jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！jobName为必填!", 0, null);
        //            return jsonResult.toString();
        //        }
        //
        if (parameters.get("executableName") == null || parameters.get("executableName") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！可执行（executableName）必填!", 0, null);
            return jsonResult.toString();
        }
        //
        if (parameters.get("jobType") == null || parameters.get("jobType") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！（jobType）为必填!", 0, null);
            return jsonResult.toString();
        }

        if (!(parameters.get("jobType").toString().equalsIgnoreCase("java") || parameters.get("jobType").toString().equalsIgnoreCase("webservice") ||
              parameters.get("jobType").toString().equalsIgnoreCase("package"))) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType必须为‘webservice’、‘java’、‘package’之一", 0, null);
            return jsonResult.toString();
        }

        //如果jobType为 ‘webservice’ 或 ‘java’则， method 字段为必填
        if ((parameters.get("jobType").toString().equalsIgnoreCase("webservice") || parameters.get("jobType").toString().equalsIgnoreCase("java")) &&
            (parameters.get("method") == null || parameters.get("method") == "")) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "保存失败！jobType为‘webservice’或‘java’时，method为必填!", 0, null);
            return jsonResult.toString();
        }

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
//        ViewObject vo = this.saafJobsDAOHI;
        SaafJobsEntity_HI job = null;
        try {
            job =  saafJobsDAO_HI.findByProperty("jobId", Integer.parseInt(parameters.get("jobId").toString())).get(0);
        } catch (Exception ignore) {
            log.error("未知错误:{}", ignore);
        } finally {
            if (job == null) {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "编辑失败！指定jobId的记录不存在 或 服务异常", 0, null);
                return jsonResult.toString();
            }
        }

        job.setLastUpdateDate(new Timestamp(new Date().getTime()));
        job.setLastUpdateLogin(-1);
        job.setLastUpdatedBy(Integer.parseInt(parameters.get("varUserId").toString()));
        //
        job.setDescription(parameters.get("description") != null ? parameters.get("description").toString() : "");
        job.setExecutableName(parameters.get("executableName") != null ? parameters.get("executableName").toString() : "");
        job.setJobType(parameters.get("jobType") != null ? parameters.get("jobType").toString() : "");
        job.setMethod(parameters.get("method") != null ? parameters.get("method").toString() : "");
        job.setOutputFileType(parameters.get("outputFileType") != null ? parameters.get("outputFileType").toString() : "");
        job.setSystem(parameters.get("system") != null ? parameters.get("system").toString() : "");
        job.setModule(parameters.get("module") != null ? parameters.get("module").toString() : "");
		job.setOperatorUserId(Integer.parseInt(parameters.get("varUserId").toString()));
        //
		saafJobsDAO_HI.saveOrUpdate(job);

        jsonResult = SaafToolUtils.convertResultJSONObj("S", "保存成功", 1, null);
        return jsonResult.toString();
    }

    private boolean isJobNameRepeat(JSONObject parameters) {

        //新增 Job 时，检查 jobName 是否重复。 jobName 只可在新增时编辑一次， 修改Job时 不允许编辑 jobName

        log.info("---------Job（isJobNameRepeat）--------------------------");

        List<SaafJobsEntity_HI> list = null;
        String where = " where 1=1 ";
        Map<String, Object> map = new HashMap<String, Object>();
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
//        ViewObject vo = this.saafJobsDAOHI;
        //
        where = where + " and jobName = :varJobName";
        map.put("varJobName", parameters.get("jobName"));
        list = saafJobsDAO_HI.findList("from " + saafJobsDAO_HI.getEntityClass().getSimpleName() + where, map);

        if (list.size() > 0) {
            return true;
        }
        return false;
    }
    /**
     * Description：删除并发程序
     * @param parameters 参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             modify
     * ==============================================================================
     */
    @Override
    public String deleteJob(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        if (parameters.get("jobId") == null || parameters.get("jobId") == "") {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "删除失败！未指定jobId", 0, null);
            return jsonResult.toString();
        }

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
//        ViewObject vo = this.saafJobsDAOHI;
        SaafJobsEntity_HI row =  saafJobsDAO_HI.getById(Integer.parseInt(parameters.get("jobId").toString()));
        if (row != null) {
        	saafJobsDAO_HI.delete(row);
        }
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "删除成功!", 1, "");
        return jsonResult.toString();

    }
    public static void main(String[] args) {
    	String params="";
    	JSONObject jsonParam = new JSONObject();
    	jsonParam.put("jobId", 31);
        IJobs jobsServer = (IJobs)SaafToolUtils.context.getBean("jobsServer");
        System.out.println("gggggggggggggggg:"+jobsServer.findJobs(jsonParam, 1, 10));
	}

}
