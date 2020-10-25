package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcWatermarksEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcWatermarks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcWatermarksServer.java
 * Description：水印服务类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/5      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
@Component("srmOkcWatermarksServer")
public class SrmOkcWatermarksServer implements ISrmOkcWatermarks {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcWatermarksServer.class);

    @Autowired
    private ViewObject<SrmOkcWatermarksEntity_HI> srmOkcWatermarksDAO_HI;

    @Autowired
    private BaseViewObject<SrmOkcWatermarksEntity_HI_RO> srmOkcWatermarksDAO_HI_RO;

    public SrmOkcWatermarksServer() {
        super();
    }

    /**
     * Description：分页查找水印列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmOkcWatermarksEntity_HI_RO> findSrmOkcWatermarksList(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        LOGGER.debug("查询参数:==========>>>{}\t{}\t{}", parameters, pageIndex, pageRows);
        StringBuffer queryString = new StringBuffer(SrmOkcWatermarksEntity_HI_RO.QUERY_WATERMARKS_SQL);
        StringBuffer whereString = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sw.watermark_code", "watermarkCode", whereString, map, "=");
        SaafToolUtils.parperParam(parameters, "sw.watermark_name", "watermarkName", whereString, map, "like");
        SaafToolUtils.parperParam(parameters, "sw.created_by", "createdBy", whereString, map, "=");
        if (whereString.length() > 0) {
            queryString.append(" where 1=1 ").append(whereString);
        }
        String countSql = "select count(1) from (" + queryString + ")";
        return srmOkcWatermarksDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：保存水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveSrmOkcWatermarks(JSONObject queryParamJSON) {
        SrmOkcWatermarksEntity_HI srmOkcWatermarksEntity_HI = null;
        Integer watermarkId = queryParamJSON.getInteger("watermarkId");
        Integer watermarkFileId = queryParamJSON.getInteger("watermarkFileId");
        Integer uploadUserId = queryParamJSON.getInteger("uploadUserId");
        if (watermarkId != null) {
//            修改
            srmOkcWatermarksEntity_HI = srmOkcWatermarksDAO_HI.findByProperty("watermarkId", watermarkId).get(0);
            SrmOkcWatermarksEntity_HI tempObj = JSON.parseObject(queryParamJSON.toString(), SrmOkcWatermarksEntity_HI.class);
            BeanUtils.copyProperties(tempObj, srmOkcWatermarksEntity_HI);
        } else {
//            新增
            srmOkcWatermarksEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmOkcWatermarksEntity_HI.class);
            srmOkcWatermarksEntity_HI.setWatermarkCode(genWatermarkCode());
        }
        if (watermarkFileId != null && uploadUserId == null) {
            srmOkcWatermarksEntity_HI.setUploadUserId(srmOkcWatermarksEntity_HI.getOperatorUserId());
            srmOkcWatermarksEntity_HI.setUploadDate(new Date());
        }
        srmOkcWatermarksDAO_HI.saveOrUpdate(srmOkcWatermarksEntity_HI);
        JSONObject result = SToolUtils.convertResultJSONObj("S", "保存水印成功", 1, srmOkcWatermarksEntity_HI);
        return result;
    }

    /**
     * Description：删除水印
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteSrmOkcWatermarks(JSONObject params) throws Exception {
        LOGGER.info("删除水印信息，参数：" + params.toString());
        SrmOkcWatermarksEntity_HI srmOkcWatermarksEntity_HI = null;
        Integer watermarkId = params.getInteger("watermarkId");
        try {
            if (watermarkId == null) {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("templateId") + "不存在", 0, null);
            }
            srmOkcWatermarksEntity_HI = srmOkcWatermarksDAO_HI.findByProperty("watermarkId", watermarkId).get(0);
            srmOkcWatermarksDAO_HI.delete(srmOkcWatermarksEntity_HI);
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
           // throw new Exception(e);
            throw new UtilsException("删除水印异常："+e.getMessage());
        }
    }

    /**
     * Description：生成水印编码
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/5      欧阳岛          创建
     * ==============================================================================
     */
    private String genWatermarkCode() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer watermarkCode = new StringBuffer("SY-").append(sdf.format(d).replaceAll("-", "")).append("-");
        SrmOkcWatermarksEntity_HI lastEntity = srmOkcWatermarksDAO_HI.get("FROM SrmOkcWatermarksEntity_HI WHERE creationDate LIKE  '" + sdf.format(d) + "%' ORDER BY watermarkCode DESC");
        if (lastEntity == null) {
//                当天创建的第一份合同模板
            watermarkCode.append("0001");
        } else {
            Integer maxTemplateCodeSuffix = Integer.parseInt(lastEntity.getWatermarkCode().substring(12));
            watermarkCode.append(String.format("%04d", ++maxTemplateCodeSuffix));
        }
        return watermarkCode.toString();
    }

    /**
     * Description：获取水印
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public SrmOkcWatermarksEntity_HI findSrmOkcWatermarksEntity_HI(Integer watermarkId) {
        return srmOkcWatermarksDAO_HI.getById(watermarkId);
    }

    /**
     * Description：获取水印列表
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmOkcWatermarksEntity_HI> findEmployeeLov(JSONObject jsonParams, Integer pageIndex,
                                                              Integer pageRows)  throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(" from SrmOkcWatermarksEntity_HI where 1 = 1 ");
        SaafToolUtils.parperParam(jsonParams, "watermarkCode", "watermarkCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "watermarkName", "watermarkName", queryString, queryParamMap, "like");
        try {
            //String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmOkcWatermarksEntity_HI> result = srmOkcWatermarksDAO_HI.findPagination(queryString,/*countSql,*/ queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new UtilsException("查询失败"+e.getMessage());
        }
    }
}
