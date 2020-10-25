package saaf.common.fmw.intf.model.inter.server;

import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.dao.readonly.SaafUserInstDAO_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xuwen
 * @date 2018/11/30
 */
@Component
public class SrmUserInstSqlUtil {

    @Resource
    private SaafUserInstDAO_HI_RO saafUserInstDaoHiRo;

    /**
     * 将当前用户关联的业务实体查询条件，拼接到参数2的查询sql
     *
     * @param varUserId 当前登陆用户的id
     * @param querySql  查询sql
     * @param paramMap  查询sql对应的参数Map
     * @param prefix    带有org_id字段的表名前缀
     */
    public void concatUserInstSql(Integer varUserId, StringBuffer querySql, Map<String, Object> paramMap, String prefix) {
        concatUserInstSql(varUserId, querySql, paramMap, prefix, "org_id");
    }

    /**
     * 将当前用户关联的业务实体查询条件，拼接到参数2的查询sql
     *
     * @param varUserId 当前登陆用户的id
     * @param querySql  查询sql
     * @param paramMap  查询sql对应的参数Map
     * @param prefix    带有org_id字段的表名前缀
     * @param orgIdKey  业务实体id字段名
     */
    public void concatUserInstSql(Integer varUserId, StringBuffer querySql, Map<String, Object> paramMap, String prefix, String orgIdKey) {
        if (varUserId == null || querySql == null || paramMap == null || prefix == null || "".equals(prefix)) {
            return;
        }
        //只查询当前用户对应的业务实体
        List<SaafUserInstEntity_HI_RO> userInstList =
                saafUserInstDaoHiRo.findList(SaafUserInstEntity_HI_RO.QUERY_USER_INST_TREE_SQL + " and si.inst_type = 'ORG' and su.user_id = ?",
                        varUserId);

        //有业务实体，则拼接业务实体，否则，没有不允许查询到数据
        if (userInstList != null && userInstList.size() > 0) {
            querySql.append(" and (");
            int i = 0;
            for (SaafUserInstEntity_HI_RO userInst : userInstList) {
                if (i == 0) {
                    querySql.append(prefix).append(".").append(orgIdKey).append(" = :instId").append(i);
                } else {
                    querySql.append(" or ").append(prefix).append(".").append(orgIdKey).append(" = :instId").append(i);
                }
                paramMap.put("instId" + i, userInst.getInstId());
                i++;
            }
            querySql.append(" )");
        } else {
            //没有业务实体不允许查询到数据
            querySql.append(" and 1 = 2");
        }
    }
}
