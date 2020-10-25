package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseManualsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseManuals;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseManualsEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：系统帮助
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseManualsServer")
public class SrmBaseManualsServer implements ISrmBaseManuals {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseManualsServer.class);

    @Autowired
    private ViewObject<SrmBaseManualsEntity_HI> srmBaseManualsDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseManualsEntity_HI_RO> srmBaseManualsDAO_HI_RO;

    public SrmBaseManualsServer() {
        super();
    }

    /**
     * 查询系统帮助
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseManualsEntity_HI_RO> findManuals(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseManualsEntity_HI_RO.QUERY_MANUALS_INFO);
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY t.LAST_UPDATE_DATE DESC");
            Pagination<SrmBaseManualsEntity_HI_RO> result = srmBaseManualsDAO_HI_RO.findPagination
                    (queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存系统帮助附件
     *
     * @param jsonArray
     * @param operatorUserId
     * @return
     * Update History
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * noticeViewerId  公告查看者ID  NUMBER  Y
     * noticeId  公告ID  NUMBER  Y
     * userId  查看用户ID  NUMBER  Y
     * viewDate  查看时间  DATE  N
     * viewIp  查看IP  VARCHAR2  N
     * replyDate  回复时间  DATE  N
     * replyContent  回复内容  VARCHAR2  N
     * noticeViewerId  公告查看者ID  NUMBER  Y
     * noticeId  公告ID  NUMBER  Y
     * userId  查看用户ID  NUMBER  Y
     * viewDate  查看时间  DATE  N
     * viewIp  查看IP  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveManualsFile(JSONArray jsonArray, int operatorUserId) throws Exception {
        JSONObject resultObj = new JSONObject();
        List<SrmBaseManualsEntity_HI> list = new ArrayList<SrmBaseManualsEntity_HI>();
        SrmBaseManualsEntity_HI srmBaseManualsEntity_HI = null;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                srmBaseManualsEntity_HI = new SrmBaseManualsEntity_HI();
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    if (object.getInteger("fileId") != null && object.getInteger("fileId") > 0) {
                        srmBaseManualsEntity_HI.setFileId(object.getInteger("fileId"));
                        srmBaseManualsEntity_HI.setCreatedBy(operatorUserId);
                        srmBaseManualsEntity_HI.setLastUpdatedBy(operatorUserId);
                        srmBaseManualsEntity_HI.setCreationDate(new Date());
                        srmBaseManualsEntity_HI.setLastUpdateDate(new Date());
                        list.add(srmBaseManualsEntity_HI);
                    }
                }
            }
            srmBaseManualsDAO_HI.saveOrUpdateAll(list);
            resultObj.put("srmBaseManualsEntity_HI", srmBaseManualsEntity_HI);
            resultObj.put("msg", "保存成功！");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
