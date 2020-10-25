package saaf.common.fmw.base.model.inter.server;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SrmBaseNoticeViewersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeViewers;
import saaf.common.fmw.common.utils.SaafToolUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("/srmBaseNoticeViewersServer")
public class SrmBaseNoticeViewersServer implements ISrmBaseNoticeViewers {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SrmBaseNoticeViewersServer.class);

    public SrmBaseNoticeViewersServer() {
        super();
    }

    @Autowired
    private ViewObject<SrmBaseNoticeViewersEntity_HI> srmBaseNoticeViewersDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseNoticesEntity_HI_RO> srmBaseNoticesDAO_HI_RO;

    /**
     * Description：保存公告查看者
     *
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
    public Pagination<SrmBaseNoticesEntity_HI_RO> findViewersList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_VIEWERS_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, "v.notice_id", "noticeId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by v.last_update_date desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> viewersList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return viewersList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存公告查看者
     */
    @Override
    public JSONObject saveViewers(JSONObject params, String viewIP) throws Exception {
        try {
            boolean flag = false;
            Integer userId = params.getInteger("varUserId");
            Integer noticeId = params.getInteger("noticeId");
            flag = findExistsId(noticeId, userId);
            if (flag) { //如果存在不用做操作
                return SToolUtils.convertResultJSONObj("N", "", 0, null);
            } else {    //每个用于第一次查看,记录第一次查看
                SrmBaseNoticeViewersEntity_HI viewersRow = new SrmBaseNoticeViewersEntity_HI();
                viewersRow.setNoticeId(noticeId);
                viewersRow.setUserId(userId);
                viewersRow.setViewDate(new Date());
                viewersRow.setViewIp(viewIP);
                viewersRow.setOperatorUserId(userId);
                srmBaseNoticeViewersDAO_HI.save(viewersRow);
                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, viewersRow);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * 验证是否查看过
     *
     * @param noticeId
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean findExistsId(int noticeId, int userId) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("noticeId", noticeId);
            map.put("userId", userId);
            List<SrmBaseNoticeViewersEntity_HI> rowSet = this.srmBaseNoticeViewersDAO_HI.findByProperty(map);
            if (rowSet.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
