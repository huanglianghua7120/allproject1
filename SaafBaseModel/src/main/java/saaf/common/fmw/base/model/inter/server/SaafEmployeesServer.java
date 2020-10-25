package saaf.common.fmw.base.model.inter.server;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafEmployees;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("baseSaafEmployeesServer")
public class SaafEmployeesServer implements ISaafEmployees {
    public SaafEmployeesServer() {
        super();
    }

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

    /**
     * 创建员工信息
     * @param parameters
     * @return
     */
    public String saveSaafEmployees(JSONObject parameters) {
        int userId2 = SToolUtils.object2Int(parameters.get("varUserId"));
        SaafEmployeesEntity_HI row = null;
        try {
            row = new SaafEmployeesEntity_HI();
            //验证员工编号是否重复
            boolean flag1 = verifyEmployeeNumRepeat(SToolUtils.object2String(parameters.get("employeeNumber")).trim());
            if (flag1) {
                JSONObject json = new JSONObject();
                json.put("status", "E");
                json.put("msg", "该员工编号已存在，请检查！");
                json.put("count", 0);
                json.put("data", "");
                return json.toString();
            }
            //验证员工名称是否重复
            boolean flag2 = verifyEmployeeNameRepeat(SToolUtils.object2String(parameters.get("employeeName")).trim());
            if (flag2) {
                JSONObject json = new JSONObject();
                json.put("status", "E");
                json.put("msg", "员工名称有重复，请检查！");
                json.put("count", 0);
                json.put("data", "");
                return json.toString();
            }
            row.setUserId(SToolUtils.object2Int(parameters.get("userId")));
            row.setEmployeeNumber(SToolUtils.object2String(parameters.get("employeeNumber")));
            row.setEmployeeName(SToolUtils.object2String(parameters.get("employeeName")));
            row.setSex(SToolUtils.object2String(parameters.get("sex")));

            String birthDay_ = SToolUtils.object2String(parameters.get("birthDay"));
            if (null != parameters.get("birthDay") && !"".equals(birthDay_.trim())) {
                Date birthDay = SToolUtils.string2DateTime(birthDay_, "yyyy-MM-dd");
                row.setBirthDay(birthDay);
            }

            row.setCardNo(SToolUtils.object2String(parameters.get("cardNo")));
            if (null == parameters.get("instId") || "".equals(parameters.get("instId").toString())) {
                row.setInstId(null);
            } else {
                Integer bdInstId = Integer.valueOf(parameters.get("instId").toString());
                row.setInstId(bdInstId);
            }
            row.setInstName(SToolUtils.object2String(parameters.get("instName")));
            row.setInstName(SToolUtils.object2String(parameters.get("instName")));

            if (null == parameters.get("positionId") || "".equals(parameters.get("positionId").toString())) {
                row.setPositionId(null);
            } else {
                Integer bdPositionId = Integer.valueOf(parameters.get("positionId").toString());
                row.setPositionId(bdPositionId);
            }
            row.setPositionName(SToolUtils.object2String(parameters.get("positionName")));
            row.setMobilePhone(SToolUtils.object2String(parameters.get("mobilePhone")));
            row.setEmail(SToolUtils.object2String(parameters.get("email")));
            row.setPostalAddress(SToolUtils.object2String(parameters.get("postalAddress")));
            row.setPostcode(SToolUtils.object2String(parameters.get("postcode")));
            row.setOperatorUserId(userId2);
            saafEmployeesDAO_HI.saveOrUpdate(row);
        } catch (Exception e) {
            //e.printStackTrace();
            return SToolUtils.convertResultJSONObj("E", "保存失败", 0, "").toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "S");
        json.put("msg", "保存成功！");
        json.put("count", 0);
        json.put("data", row.getEmployeeId() + "");
        return json.toString();
    }


    /**
     * 验证字段：EMPLOYEE_NAME是否重复
     * @param employeeName
     * @return
     */
    public boolean verifyEmployeeNameRepeat(String employeeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeName", employeeName);
        List<SaafEmployeesEntity_HI> rowSet = saafEmployeesDAO_HI.findList("select * from SaafEmployeesDAO_HI where employeeName = :employeeName", map);
        if (rowSet.size() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 验证字段：EMPLOYEE_NUMBER是否重复
     * @param employeeNumber
     * @return
     */
    public boolean verifyEmployeeNumRepeat(String employeeNumber) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeNumber", employeeNumber);
        List<SaafEmployeesEntity_HI> rowSet = saafEmployeesDAO_HI.findList("select * from SaafEmployeesEntity_HI where employeeNumber = :employeeNumber", map);
        if (rowSet.size() == 1) {
            return true;
        }
        return false;
    }


    /**
     * 查询采购人员
     */
	@Override
	public Pagination<SaafEmployeesEntity_HI> findEmployeeLov(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows)  throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(" from SaafEmployeesEntity_HI where 1=1 ");
        SaafToolUtils.parperParam(jsonParams, "employeeNumber", "employeeNumber", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "employeeName", "employeeName", queryString, queryParamMap, "like");

        try {
            Pagination<SaafEmployeesEntity_HI> result = saafEmployeesDAO_HI.findPagination(queryString, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
