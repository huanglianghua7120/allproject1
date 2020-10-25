package saaf.common.fmw.base.model.inter.server;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseCategories;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

@Component("srmBaseCategoriesServer")
public class SrmBaseCategoriesServer implements ISrmBaseCategories {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseCategoriesServer.class);

    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseCategoriesEntity_HI_RO> srmBaseCategoriesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> SaafUserEmployeesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    public SrmBaseCategoriesServer() {
        super();
    }

    /**
     * 接口（数据输入）——保存采购分类信息（用于外部访问的接口）参数params：array{categoryId，enabledFlag，fullCategoryCode，fullCategoryName，parentFullCategoryCode
     * categoryLevel}
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateProcurementMaintenanceExternal(JSONObject jsonParams, int userId) throws Exception {
        JSONArray jsonArray = jsonParams.getJSONArray("array");
        JSONObject resultObj = new JSONObject();
        List<SrmBaseCategoriesEntity_HI> list = new ArrayList<SrmBaseCategoriesEntity_HI>();
        SrmBaseCategoriesEntity_HI srmBaseCategoriesEntity_HI = null;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                srmBaseCategoriesEntity_HI = new SrmBaseCategoriesEntity_HI();
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    if (object.getInteger("categoryId") != null && object.getInteger("categoryId") > 0) {
                        if ("Y".equals(object.getString("enabledFlag"))) {
                            String hint = "第" + (i + 1) + "行存在已生效的采购分类！";
                            return SToolUtils.convertResultJSONObj("E", hint, 0, null);
                        } else {
                            srmBaseCategoriesEntity_HI = srmBaseCategoriesDAO_HI.getById(object.getInteger("categoryId"));
                            srmBaseCategoriesEntity_HI.setEnabledFlag("Y");
                            srmBaseCategoriesEntity_HI.setOperatorUserId(userId);
                            list.add(srmBaseCategoriesEntity_HI);
                        }
                    } else {
                        if (object.getString("fullCategoryCode") != null && object.getString("fullCategoryName") != null) {
                            System.out.println("parentFullCategoryCode===" + object.getString("parentFullCategoryCode"));
                            if (object.getString("parentFullCategoryCode") != null && !"".equals(object.getString("parentFullCategoryCode"))) {
                                SrmBaseCategoriesEntity_HI srmBaseCategoriesEntity_HI1 = srmBaseCategoriesDAO_HI.findByProperty("full_category_code", object.getString("parentFullCategoryCode")).get(0);
                                String strsFullCode = object.getString("parentFullCategoryCode");
                                String strsCode = object.getString("fullCategoryCode");
                                String strsName = object.getString("fullCategoryName");
                                String categoryCode = cehckString(strsCode, strsFullCode);
                                String categoryName = cehckString(strsName, srmBaseCategoriesEntity_HI1.getFullCategoryName());
                                int categoryLevel = srmBaseCategoriesEntity_HI1.getCategoryLevel() + 1;
                                srmBaseCategoriesEntity_HI.setParentCategoryId(srmBaseCategoriesEntity_HI1.getCategoryId());
                                if (categoryCode != null && categoryName != null) {
                                    srmBaseCategoriesEntity_HI.setCategoryCode(categoryCode);
                                    srmBaseCategoriesEntity_HI.setCategoryName(categoryName);
                                } else {
                                    return SToolUtils.convertResultJSONObj("E", "请输入正确的编码或名称！", 0, null);
                                }
                                if (categoryLevel == 4) {
                                    srmBaseCategoriesEntity_HI.setLeafFlag("Y");
                                }
                                srmBaseCategoriesEntity_HI.setCategoryLevel(categoryLevel);
                            } else {
                                srmBaseCategoriesEntity_HI.setCategoryCode(object.getString("fullCategoryCode"));
                                srmBaseCategoriesEntity_HI.setCategoryName(object.getString("fullCategoryName"));
                                srmBaseCategoriesEntity_HI.setParentCategoryId(-1);
                                srmBaseCategoriesEntity_HI.setCategoryLevel(1);
                            }
                            srmBaseCategoriesEntity_HI.setFullCategoryName(object.getString("fullCategoryName"));
                            srmBaseCategoriesEntity_HI.setFullCategoryCode(object.getString("fullCategoryCode"));
                            srmBaseCategoriesEntity_HI.setFileId(object.getInteger("fileId"));
                            srmBaseCategoriesEntity_HI.setEnabledFlag("Y");
                            srmBaseCategoriesEntity_HI.setOperatorUserId(userId);
                            list.add(srmBaseCategoriesEntity_HI);
                        } else {
                            String hint = "第" + (i + 1) + "行分类编码不能为空！";
                            return SToolUtils.convertResultJSONObj("E", hint, 0, null);
                        }
                    }
                }
            }
            srmBaseCategoriesDAO_HI.saveOrUpdateAll(list);
            resultObj.put("srmBaseCategoriesEntity_HI", srmBaseCategoriesEntity_HI);
            resultObj.put("msg", "生效成功！");
            resultObj.put("status", "S");
            //保存日志
            JSONObject jsonData = new JSONObject();  //最终结果的返回
            SrmIntfLogsEntity_HI logsEntity = null;
            List<SrmIntfLogsEntity_HI> logsEntityList = null;
            if (list != null && list.size() > 0) {
                for (SrmBaseCategoriesEntity_HI srmBaseCategories : list) {
                    logsEntity = new SrmIntfLogsEntity_HI();
                    logsEntity.setIntfType("PO_CATEGORY_IN");//接口类型BASE_INTF_TYPE
                    logsEntity.setTableName("srm_base_categories");
                    logsEntity.setTableId(srmBaseCategories.getCategoryId());//接口取数对应的表ID
                    logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
                    logsEntity.setSendSystem(srmBaseCategories.getSourceCode());//数据发送方
                    logsEntity.setReceiveSystem("SRM");
                    logsEntity.setInData(jsonParams.toJSONString());//输入报文
                    jsonData.put("srmBaseCategories", srmBaseCategories);
                    logsEntity.setOutData(jsonData.toJSONString());//输出报文
                    logsEntity.setDescription("采购分类输入接口");
                    logsEntity.setOperatorUserId(userId);
                    logsEntity.setIntfStatus("S");
                    logsEntityList.add(logsEntity);
                }
            }
            srmIntfLogsDAO_HI.saveAll(logsEntityList);
            resultObj.put("jsonData", jsonData);
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 接口（数据输出）——查询采购分类所有信息（用于外部访问的接口）参数fullCategoryCode，lastUpdateDateFrom，lastUpdateDateTo，parentFullCategoryCode，categoryLevel，parentFullCategoryCode
     * 查询结果：（分类编码fullCategoryCode、分类名称fullCategoryName、当前分类编码categoryCode、当前分类编码名称categoryName、上级分类编码parentFullCategoryCode
     * 上级分类编码名称parentFullCategoryName、状态enabledFlagDesc、最后更新时间lastUpdateDate）
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> pushFindProcurementListExternal(JSONObject jsonParams, Integer userId) {
        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("PO_CATEGORY_OUT");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_base_categories");
        logsEntity.setDataDirection("OUT");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem("SRM");//数据发送方
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setDescription("采购分类输出接口");
        logsEntity.setOperatorUserId(userId);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmBaseCategoriesEntity_HI_RO.QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "t.category_code", "categoryCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.last_update_date", "lastUpdateDateTo", queryString, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "t.CREATION_DATE", "lastUpdateDateFrom", queryString, queryParamMap, ">=");
        List<SrmBaseCategoriesEntity_HI_RO> list = srmBaseCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        Map<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            map.put("data", list);
            map.put("count", 1);
            map.put("msg", "查询成功");
            map.put("status", "S");
            logsEntity.setIntfStatus("S");//查无数据状态为E，查有数据状态为S
            srmIntfLogsDAO_HI.save(logsEntity);
        } else {
            map.put("status", "E");
            map.put("msg", "查无此数据！");
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            logsEntity.setIntfStatus("E");
            srmIntfLogsDAO_HI.save(logsEntity);
        }
        return map;
    }

    /**
     * 批量失效采购分类（修改）
     *
     * @param jsonArray
     * @return
     * @throws Exception
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateInvalidProcurementMaintenance(JSONArray jsonArray, int operatorUserId) throws Exception {
        JSONObject resultObj = new JSONObject();
        List<SrmBaseCategoriesEntity_HI> list = new ArrayList<SrmBaseCategoriesEntity_HI>();
        SrmBaseCategoriesEntity_HI srmBaseCategoriesEntity_HI = null;

        List<SrmBaseCategoriesEntity_HI_RO> listRO = new ArrayList<SrmBaseCategoriesEntity_HI_RO>();
        Boolean alp = false;
        if (jsonArray.size() > 0) {
            alp = jsonArray.getJSONObject(0).getBoolean("alp");
        }
        Set<Integer> idList = new HashSet<>();
        Set<Integer> seleIDList = new HashSet<>();
        for (int b = 0; b < jsonArray.size(); b++) {
            JSONObject tempOb = jsonArray.getJSONObject(b);
            if (null != tempOb.getInteger("sonCategoryId")) {
                seleIDList.add(tempOb.getInteger("sonCategoryId"));
                idList.add(tempOb.getInteger("sonCategoryId"));
            }
        }


        for (Integer cateId : seleIDList) {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sbb = new StringBuffer();
            sbb.append("select c.category_id\n" +
                    "  from srm_base_categories c");


            sbb.append(" start with c.category_id =:categoryId");
            sbb.append(" connect by prior c.category_id =  c.parent_category_id ");

            queryParamMap.put("categoryId", cateId);
            listRO = srmBaseCategoriesDAO_HI_RO.findList(sbb.toString(), queryParamMap);

            for (SrmBaseCategoriesEntity_HI_RO sbcero : listRO) {
                idList.add(sbcero.getCategoryId());
            }

        }


        try {
//            if(alp == true) {
            for (int i = 0; i < jsonArray.size(); i++) {
                srmBaseCategoriesEntity_HI = new SrmBaseCategoriesEntity_HI();
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    if (object.getBoolean("alp") == true) {
                        if (object.getInteger("categoryId") != null && object.getInteger("categoryId") > 0) {
//                            if ("Y".equals(object.getString("enabledFlag"))) {
                            srmBaseCategoriesEntity_HI = srmBaseCategoriesDAO_HI.getById(object.getInteger("categoryId"));
                            srmBaseCategoriesEntity_HI.setEnabledFlag("N");
                            srmBaseCategoriesEntity_HI.setInvalidDate(new Date());
                            srmBaseCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                            list.add(srmBaseCategoriesEntity_HI);
//                            } else {
//                                return SToolUtils.convertResultJSONObj("E", "存在不是生效状态的采购分类，请重选！", 0, null);
//                            }
                        }
                    }
                }
            }
//            }
            for (Integer cateId : idList) {
                if (cateId != null) {
                    srmBaseCategoriesEntity_HI = srmBaseCategoriesDAO_HI.getById(cateId);
//                    if ("Y".equals(srmBaseCategoriesEntity_HI.getEnabledFlag())) {
                    srmBaseCategoriesEntity_HI.setEnabledFlag("N");
                    srmBaseCategoriesEntity_HI.setInvalidDate(new Date());
                    srmBaseCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                    list.add(srmBaseCategoriesEntity_HI);
//                    } else {
//                    return SToolUtils.convertResultJSONObj("E", "存在不是生效状态的采购分类，请重选！", 0, null);
//                        continue;
//                    }
                }
            }


            srmBaseCategoriesDAO_HI.saveOrUpdateAll(list);
            resultObj.put("srmBaseCategoriesEntity_HI", srmBaseCategoriesEntity_HI);
            resultObj.put("msg", "失效成功！");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }


    }


    /**
     * 根据父品类类id递归查询所有的子孙品类id
     *
     * @param
     * @author
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    private void getAllCategoryWithParentId(Integer cateId) {
        if (cateId == null) {
            return;
        }
        List<SrmBaseCategoriesEntity_HI_RO> list = new ArrayList<SrmBaseCategoriesEntity_HI_RO>();
        SrmBaseCategoriesEntity_HI_RO srmBaseCategoriesEntity_HI_RO = null;

        try {
            //将品类id拼接到sql上面
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sbb = new StringBuffer();
            sbb.append(SrmBaseCategoriesEntity_HI_RO.QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL);

            sbb.append(" and t.category_id = :categoryId");
            queryParamMap.put("categoryId", cateId);

            sbb.append(" and sbc.category_id is not null ");

            list = srmBaseCategoriesDAO_HI_RO.findList(sbb.toString(), queryParamMap);

            //该父类id没有子类说明已经到了叶子节点
            if (list.size() == 0) {
                return;
            }
            //递归遍历该父类id，查询它的字类id，并存放到集合中
            for (SrmBaseCategoriesEntity_HI_RO sbceh : list) {
                getAllCategoryWithParentId(sbceh.getSonCategoryId());
            }

        } catch (Exception e) {

        }
    }


    /**
     * 批量生效采购分类（修改或保存）
     *
     * @param jsonArray
     * @return
     * @throws Exception
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateProcurementMaintenance(JSONArray jsonArray, int operatorUserId) throws Exception {

        JSONObject resultObj = new JSONObject();
        List<SrmBaseCategoriesEntity_HI> list = new ArrayList<SrmBaseCategoriesEntity_HI>();
        SrmBaseCategoriesEntity_HI srmBaseCategoriesEntity_HI = null;
        List<SrmBaseCategoriesEntity_HI_RO> listRO = new ArrayList<SrmBaseCategoriesEntity_HI_RO>();
        List<SrmBaseCategoriesEntity_HI_RO> listROF = new ArrayList<SrmBaseCategoriesEntity_HI_RO>();

        Integer firsId = null;

        Integer leval = -1;
        if (jsonArray.size() > 0) {
            leval = jsonArray.getJSONObject(0).getInteger("categoryLevel");
            firsId = jsonArray.getJSONObject(0).getInteger("categoryId");
        }
        Set<Integer> idList = new HashSet<>();
        Set<Integer> seleIDList = new HashSet<>();
        for (int b = 0; b < jsonArray.size(); b++) {
            JSONObject tempOb = jsonArray.getJSONObject(b);
            if (null != tempOb.getInteger("sonCategoryId")) {
                seleIDList.add(tempOb.getInteger("sonCategoryId"));
                idList.add(tempOb.getInteger("sonCategoryId"));
            }
        }

        for (Integer cateId : seleIDList) {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sbb = new StringBuffer();
            sbb.append("select c.category_id\n" +
                    "  from srm_base_categories c");


            sbb.append(" start with c.category_id =:categoryId");
            sbb.append(" connect by prior c.category_id =  c.parent_category_id ");

            queryParamMap.put("categoryId", cateId);
            listRO = srmBaseCategoriesDAO_HI_RO.findList(sbb.toString(), queryParamMap);

            for (SrmBaseCategoriesEntity_HI_RO sbcero : listRO) {
                idList.add(sbcero.getCategoryId());
            }

        }

        for (Integer cateId : seleIDList) {
            Map<String, Object> queryParamMapFirst = new HashMap<String, Object>();
            StringBuffer sbbFi = new StringBuffer();
            sbbFi.append("select c.category_id\n" +
                    "  from srm_base_categories c");


            sbbFi.append(" start with c.category_id =:categoryId");
            sbbFi.append(" connect by prior c.parent_category_id = c.category_id  ");

            queryParamMapFirst.put("categoryId", firsId);
            listROF = srmBaseCategoriesDAO_HI_RO.findList(sbbFi.toString(), queryParamMapFirst);

            for (SrmBaseCategoriesEntity_HI_RO sbcero : listROF) {
                idList.add(sbcero.getCategoryId());
            }

        }


        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                srmBaseCategoriesEntity_HI = new SrmBaseCategoriesEntity_HI();
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    if (object.getInteger("categoryId") != null && object.getInteger("categoryId") > 0) {
//                        if ("Y".equals(object.getString("enabledFlag"))) {
//                            return SToolUtils.convertResultJSONObj("E", "存在已生效的采购分类，请重选！", 0, null);
//                        } else {
                        srmBaseCategoriesEntity_HI = srmBaseCategoriesDAO_HI.getById(object.getInteger("categoryId"));
                        srmBaseCategoriesEntity_HI.setEnabledFlag("Y");
                        srmBaseCategoriesEntity_HI.setInvalidDate(null);
                        srmBaseCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                        list.add(srmBaseCategoriesEntity_HI);
//                        }
                    } else {
                        if (object.getString("fullCategoryCode") != null && object.getString("fullCategoryName") != null) {
                            List<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntity_HI2 = srmBaseCategoriesDAO_HI.findByProperty("full_category_code", object.getString("fullCategoryCode"));
                            if (srmBaseCategoriesEntity_HI2 != null && srmBaseCategoriesEntity_HI2.size() > 0) {
                                return SToolUtils.convertResultJSONObj("E", "第" + (i + 1) + "行分类编码已存在", 0, null);
                            }
                            if (object.getString("parentFullCategoryCode") != null && !"".equals(object.getString("parentFullCategoryCode"))) {
                                SrmBaseCategoriesEntity_HI srmBaseCategoriesEntity_HI1 = srmBaseCategoriesDAO_HI.findByProperty("full_category_code", object.getString("parentFullCategoryCode")).get(0);
                                String strsFullCode = object.getString("parentFullCategoryCode");
                                String strsCode = object.getString("fullCategoryCode");
                                Boolean a = strsCode.contains("strsFullCode");
                                String[] b = strsFullCode.split("\\.");
                                String[] c = strsCode.split("\\.");
                                if (c.length > b.length) {
                                    int cont = 0;
                                    for (int y = 0; y < b.length; y++) {
                                        if (b[y].equals(c[y])) {
                                            ++cont;
                                        }
                                    }
                                    if (cont != b.length) {

                                        String sg = "";


                                        if (leval == 1) {
                                            sg = ".001";
                                        } else if (leval == 2) {
                                            sg = ".0001";
                                        } else if (leval == 3) {
                                            sg = ".00001";
                                        }

                                        String prompt = "第" + (i + 1) + "行分类编码不正确，以" + strsFullCode + "开头！比如" + strsFullCode + sg;
//                                        String prompt = "第" + (i + 1) + "行分类编码不正确，以" + strsFullCode + sg+"做参考！";

                                        return SToolUtils.convertResultJSONObj("E", prompt, 0, null);
                                    }
                                } else if (strsCode.equals(strsFullCode)) {
                                    return SToolUtils.convertResultJSONObj("E", "第" + (i + 1) + "行分类编码不能与上级编码相同，请输入正确的，比如" + strsFullCode + ".001", 0, null);
                                } else {

                                    String sg = "";


                                    if (leval == 1) {
                                        sg = ".001";
                                    } else if (leval == 2) {
                                        sg = ".0001";
                                    } else if (leval == 3) {
                                        sg = ".00001";
                                    }


                                    String prompt = "第" + (i + 1) + "行分类编码不正确，以" + strsFullCode + "开头！比如" + strsFullCode + sg;
                                    return SToolUtils.convertResultJSONObj("E", prompt, 0, null);
                                }
                                String strsName = object.getString("fullCategoryName");
                                String categoryCode = cehckString(strsCode, strsFullCode);
                                String categoryName = cehckString(strsName, srmBaseCategoriesEntity_HI1.getFullCategoryName());
                                int categoryLevel = srmBaseCategoriesEntity_HI1.getCategoryLevel() + 1;
                                srmBaseCategoriesEntity_HI.setParentCategoryId(srmBaseCategoriesEntity_HI1.getCategoryId());
                                if (categoryCode != null && !"".equals(categoryCode) && categoryName != null && !"".equals(categoryName)) {
                                    srmBaseCategoriesEntity_HI.setCategoryCode(categoryCode);
                                    srmBaseCategoriesEntity_HI.setCategoryName(categoryName);
                                } else {
                                    return SToolUtils.convertResultJSONObj("E", "请输入正确的编码或名称！", 0, null);
                                }
                                if (categoryLevel == 4) {
                                    srmBaseCategoriesEntity_HI.setLeafFlag("Y");
                                }
                                srmBaseCategoriesEntity_HI.setCategoryLevel(categoryLevel);
                            } else {
                                srmBaseCategoriesEntity_HI.setCategoryCode(object.getString("fullCategoryCode"));
                                srmBaseCategoriesEntity_HI.setCategoryName(object.getString("fullCategoryName"));
                                srmBaseCategoriesEntity_HI.setParentCategoryId(-1);
                                srmBaseCategoriesEntity_HI.setCategoryLevel(1);
                            }
                            srmBaseCategoriesEntity_HI.setFullCategoryName(object.getString("fullCategoryName"));
                            srmBaseCategoriesEntity_HI.setFullCategoryCode(object.getString("fullCategoryCode"));
                            srmBaseCategoriesEntity_HI.setFileId(object.getInteger("fileId"));
                            /*srmBaseCategoriesEntity_HI.setExpenseItemCode(object.getString("expenseItemCode"));
                            srmBaseCategoriesEntity_HI.setExpenseItemName(object.getString("expenseItemName"));*/
                            srmBaseCategoriesEntity_HI.setEnabledFlag("Y");
                            srmBaseCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                            list.add(srmBaseCategoriesEntity_HI);
                        }
                    }
                }
            }

            if (firsId != null && firsId > 0) {
                for (Integer cateId : idList) {
                    if (cateId != null) {
                        srmBaseCategoriesEntity_HI = srmBaseCategoriesDAO_HI.getById(cateId);

//                        if ("Y".equals(srmBaseCategoriesEntity_HI.getEnabledFlag())) {
//                        return SToolUtils.convertResultJSONObj("E", "存在已生效的采购分类，请重选！", 0, null);
//                            continue;
//                        } else {
                        srmBaseCategoriesEntity_HI.setEnabledFlag("Y");
                        srmBaseCategoriesEntity_HI.setInvalidDate(null);
                        srmBaseCategoriesEntity_HI.setOperatorUserId(operatorUserId);
                        list.add(srmBaseCategoriesEntity_HI);
//                        }
                    }
                }
            }
            srmBaseCategoriesDAO_HI.saveOrUpdateAll(list);
            resultObj.put("srmBaseCategoriesEntity_HI", srmBaseCategoriesEntity_HI);
            resultObj.put("msg", "生效成功！");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String cehckString(String strs1, String strs2) {
        String[] arr1 = strs1.split("\\.");
        String[] arr2 = strs2.split("\\.");
        int a = arr2.length;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j].equals(arr2[i])) {
                    arr1[j] = "";
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < arr1.length; j++) {
            if (!"".equals(arr1[j])) {
                sb.append(arr1[j] + "\\.");
            }
        }
        String yy = "";
        if(sb.toString().length() - 1>0) {
            yy = sb.toString().substring(0, sb.toString().length() - 1);
        }
        String xx = "";
        if(yy.length() - 1>0) {
            xx = yy.substring(0, yy.length() - 1);
        }
        return xx;
    }

    /**
     * 点击菜单查询采购分类维护
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    /*public Pagination<SrmBaseCategoriesEntity_HI_RO> findprocurementMaintenanceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sbb = new StringBuffer();
        String parentFullCategoryCode = jsonParams.getString("parentFullCategoryCode");
        System.out.println("fullCategoryCode===" + jsonParams.getString("fullCategoryCode"));
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        try {
            queryString.append(SrmBaseCategoriesEntity_HI_RO.QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL);

            SaafToolUtils.parperParam(jsonParams, "pare.full_category_code", "pareFullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "pare.full_category_name", "pareFullCategoryName", queryString, queryParamMap, "like");


            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");
            //SaafToolUtils.parperParam(jsonParams, "t.category_level", "categoryLevel", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.enabled_flag", "enabledFlag", queryString, queryParamMap, "=");
            appendString.append(queryString);
            if (parentFullCategoryCode != null && !"".equals(parentFullCategoryCode)) {
                appendString.append(" and t.full_category_code LIKE '%");
                appendString.append(parentFullCategoryCode);
                appendString.append("%'");
                appendString.append(" AND t.category_level = (SELECT t1.category_level  FROM srm_base_categories t1 WHERE t1.full_category_code ='");
                appendString.append(parentFullCategoryCode);
                appendString.append("')");
                appendString.append(" AND t.full_category_code = '");
                appendString.append(parentFullCategoryCode);
                appendString.append("'");
            } else {
                appendString.append(" AND t.category_level = 1");
            }
            appendString.append(" ORDER BY t.last_update_date DESC");
            System.out.println(appendString.toString());
            Pagination<SrmBaseCategoriesEntity_HI_RO> pag = srmBaseCategoriesDAO_HI_RO.findPagination(appendString.toString(), queryParamMap, pageIndex, pageRows);

            *//*List<SrmBaseCategoriesEntity_HI_RO> list = pag.getData();
            for (SrmBaseCategoriesEntity_HI_RO sbceh : list) {
                if (sbceh.getCategoryLevel() != null) {
                    if (sbceh.getCategoryLevel() == 1) {
                    } else if (sbceh.getCategoryLevel() == 2) {
                    } else if (sbceh.getCategoryLevel() == 3) {
                    } else {
                        sbceh.setSonFullCategoryCode4(sbceh.getFullCategoryCode());
                        sbceh.setSonFullCategoryName4((sbceh.getFullCategoryName()));
                    }
                }
            }

            Set<Integer> saveIDList = new HashSet<>();
            for (SrmBaseCategoriesEntity_HI_RO sbceh : list) {
                saveIDList.add(sbceh.getCategoryId());
            }
            for (Integer dehID : saveIDList) {
                SrmBaseCategoriesEntity_HI se = srmBaseCategoriesDAO_HI.getById(dehID);
                if (se.getCategoryLevel() != null) {
                    if (se.getCategoryLevel() == 1) {
                    } else if (se.getCategoryLevel() == 2) {
                    } else if (se.getCategoryLevel() == 3) {
                      sese.getFullCategoryCode()
                    } else {
                    }
                }
                if (dehID != headerId) {
                    List<SrmPoOemDeliveryHeadesEntity_HI> list4 = srmPoOemDeliveryHeadesDAO_HI.findByProperty("delivery_header_id", dehID);
                    //如果list不为空则删除
                    if (null != list4) {
                        srmPoOemDeliveryHeadesDAO_HI.delete(list4);
                    }
                }
            }*//*

            return pag;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }*/
    /*public Pagination<SrmBaseCategoriesEntity_HI_RO> findprocurementMaintenanceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sbb = new StringBuffer();
        String parentFullCategoryCode = jsonParams.getString("parentFullCategoryCode");
        System.out.println("fullCategoryCode===" + jsonParams.getString("fullCategoryCode"));
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        try {
            queryString.append(SrmBaseCategoriesEntity_HI_RO.QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL);


            SaafToolUtils.parperParam(jsonParams, "pare.full_category_code", "pareFullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "pare.full_category_name", "pareFullCategoryName", queryString, queryParamMap, "like");



            SaafToolUtils.parperParam(jsonParams, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");


            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "sonFullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "sonFullCategoryName", queryString, queryParamMap, "like");

            SaafToolUtils.parperParam(jsonParams, "t.enabled_flag", "enabledFlag", queryString, queryParamMap, "=");


            appendString.append(queryString);
            if (parentFullCategoryCode != null && !"".equals(parentFullCategoryCode)) {
                appendString.append(" and t.full_category_code LIKE '%");
                appendString.append(parentFullCategoryCode);
                appendString.append("%'");

                appendString.append(" AND t.category_level = (SELECT t1.category_level FROM srm_base_categories t1 WHERE t1.full_category_code ='");

                appendString.append(parentFullCategoryCode);
                appendString.append("')");
                appendString.append(" AND t.full_category_code = '");
                appendString.append(parentFullCategoryCode);
                appendString.append("'");
            } else {
                appendString.append(" AND t.category_level = 1");
            }
            appendString.append(" ORDER BY t.last_update_date DESC");
            System.out.println(appendString.toString());
            Pagination<SrmBaseCategoriesEntity_HI_RO> pag = srmBaseCategoriesDAO_HI_RO.findPagination(appendString.toString(), queryParamMap, pageIndex, pageRows);

            return pag;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }*/
    /**
     * 点击菜单查询采购分类维护
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findProcurementMaintenanceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sbb = new StringBuffer();
        String parentFullCategoryCode = jsonParams.getString("parentFullCategoryCode");
        System.out.println("fullCategoryCode===" + jsonParams.getString("fullCategoryCode"));
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        try {
            queryString.append(SrmBaseCategoriesEntity_HI_RO.QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL);


            SaafToolUtils.parperParam(jsonParams, "pare.full_category_code", "pareFullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "pare.full_category_name", "pareFullCategoryName", queryString, queryParamMap, "like");


            SaafToolUtils.parperParam(jsonParams, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");


            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "sonFullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "sonFullCategoryName", queryString, queryParamMap, "like");

//            SaafToolUtils.parperParam(jsonParams, "t.enabled_flag", "enabledFlag", queryString, queryParamMap, "=");
            String enabledFlag = jsonParams.getString("enabledFlag");
            if (jsonParams.getString("enabledFlag") != null && !"".equals(jsonParams.getString("enabledFlag"))) {
                queryString.append(" and CASE WHEN sbc.enabled_flag is not null and sbc.enabled_flag = 'Y' THEN\n" +
                        "            '生效' WHEN sbc.enabled_flag is not null and sbc.enabled_flag = 'N' THEN\n" +
                        "            '失效' WHEN sbc.enabled_flag is null and t.enabled_flag = 'Y' THEN\n" +
                        "            '生效' ELSE\n" +
                        "            '失效' end =:enabledFlag ");
                if("Y".equals(enabledFlag)){
                    enabledFlag = "生效";
                }else {
                    enabledFlag = "失效";
                }
                queryParamMap.put("enabledFlag",enabledFlag);
            }



            appendString.append(queryString);
            if (parentFullCategoryCode != null && !"".equals(parentFullCategoryCode)) {
                /*appendString.append(" and t.full_category_code LIKE '%");
                appendString.append(parentFullCategoryCode);
                appendString.append("%'");*/

                appendString.append(" AND t.category_level = (SELECT t1.category_level FROM srm_base_categories t1 WHERE t1.full_category_code ='");
                appendString.append(parentFullCategoryCode);
                appendString.append("')");

                appendString.append(" AND t.full_category_code = '");
                appendString.append(parentFullCategoryCode);
                appendString.append("'");
            } else {
                appendString.append(" AND t.category_level = 1");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            appendString.append(" ORDER BY t.last_update_date DESC");
            System.out.println(appendString.toString());
            Pagination<SrmBaseCategoriesEntity_HI_RO> pag = srmBaseCategoriesDAO_HI_RO
                    .findPagination(appendString.toString(),countSql, queryParamMap, pageIndex, pageRows);

            return pag;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 查询采购分类lov
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findCategoryLove(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseCategoriesEntity_HI_RO.GET_PO_HEADER_SQLS);
            SaafToolUtils.parperParam(jsonParams, "t.category_code", "categoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.category_name", "categoryName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");
            if("Y".equals(jsonParams.getString("isPo"))){
                queryString.append(" AND t.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+") and t.LEAF_FLAG ='Y' ");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO
                    .findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String getCategoryId(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        List  categoryIds=new ArrayList();
        for(SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList){
            categoryIds.add(ro.getCategoryId().toString());
        }
        String categoryId= String.valueOf(categoryIds.stream().distinct().collect(Collectors.joining(",")));
        return categoryId;
    }
    /**
     * 查询采购分类（分页，通用）
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findSrmBaseCategoriesInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        System.out.println(jsonParams);
        sb.append("SELECT * FROM srm_base_categories t WHERE 1=1 ");
        if (jsonParams.get("selectFlag") != null && "muiCategories".equals(jsonParams.getString("selectFlag"))) {
            SaafToolUtils.parperParam(jsonParams, "t." + jsonParams.getString("searchLovFlag"), "searchLovName", sb, queryParamMap, "like");
        } else {
            SaafToolUtils.parperParam(jsonParams, "t.category_id", "categoryId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.category_code", "categoryCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.category_name", "categoryName", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.category_level", "categoryLevel", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.parent_category_id", "parentCategoryId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_code", "fullCategory_Code", sb, queryParamMap, "=");//精准查询
            SaafToolUtils.parperParam(jsonParams, "t.full_category_code", "fullCategoryCode", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.full_category_name", "fullCategoryName", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "t.leaf_flag", "leafFlag", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.enabled_flag", "enabledFlag", sb, queryParamMap, "=");
        }
        //默认取最后一级，不指定层级
        if (null != jsonParams.getString("lastLevel") && "Y".equals(jsonParams.getString("lastLevel"))) {
            sb.append(" and not exists(select 1 from srm_base_categories tt where tt.parent_category_id=t.category_id) ");
        }
        //updated by xuwen at 2018/12/15，添加非空判断，防止空指针异常
        if (jsonParams.getJSONArray("exCategory") != null && jsonParams.getJSONArray("exCategory").size() > 0) {
            if (null != jsonParams.getString("sceneType") && !"50".equals(jsonParams.getString("sceneType"))) {
                JSONArray categoryArray = jsonParams.getJSONArray("exCategory");
                StringBuffer categoryIds = new StringBuffer("('");
                for (int i = 0; i < categoryArray.size(); i++) {
                    JSONObject categoryJson = categoryArray.getJSONObject(i);
                    SrmBaseCategoriesEntity_HI category = srmBaseCategoriesDAO_HI.getById(categoryJson.getInteger("categoryId"));
                    if (category != null) {
                        if (i < categoryArray.size() - 1) {
                            categoryIds.append(category.getCategoryId() + "','");
                        } else {
                            categoryIds.append(category.getCategoryId() + "')");
                        }
                    }
                }
                sb.append(" and t.category_id not in " + categoryIds);
            }
        }
        //updated by xuwen at 2018/12/19，添加排除的分类
        String excludeItem = jsonParams.getString("excludeItem");
        if (excludeItem != null && !"".equals(excludeItem)) {
            String[] categorys = excludeItem.split(",");
            for (String category : categorys) {
                sb.append(" and t.category_id != " + category);
            }
        }
        //在新建资质审查时，选择引入品类，在第一行选了A品类之后，在第二行开窗选择，需要隐藏品类A不能显示
        if (jsonParams.getJSONArray("selfCategory") != null && jsonParams.getJSONArray("selfCategory").size() > 0) {
            JSONArray selfCategoryArray = jsonParams.getJSONArray("selfCategory");
            if (selfCategoryArray.getJSONObject(0).getInteger("categoryId") != null) {
                StringBuffer selfCategoryIds = new StringBuffer("(");
                for (int i = 0; i < selfCategoryArray.size(); i++) {
                    JSONObject categoryJson = selfCategoryArray.getJSONObject(i);
                    SrmBaseCategoriesEntity_HI category = srmBaseCategoriesDAO_HI.getById(categoryJson.getInteger("categoryId"));
                    if (category != null) {
                        if (i < selfCategoryArray.size() - 1) {
                            selfCategoryIds.append(category.getCategoryId() + ",");
                        } else {
                            selfCategoryIds.append(category.getCategoryId());
                        }
                    }
                }
                String strSelfCategoryIds = selfCategoryIds.toString();
                if (strSelfCategoryIds.endsWith(",")) {
                    selfCategoryIds.deleteCharAt(selfCategoryIds.length() - 1);
                }
                selfCategoryIds.append(")");
                sb.append(" and t.category_id not in " + selfCategoryIds);
            }
        }
        //资质审查过滤档案已失效品类
        if (null != jsonParams.getString("sceneType") && !"".equals(jsonParams.getString("sceneType"))&&!("50".equals(jsonParams.getString("sceneType"))||"20".equals(jsonParams.getString("sceneType")))) {
            sb.append(" AND t.enabled_flag='Y'\n"
                    + "AND (t.invalid_date IS NULL OR t.invalid_date > to_date('" + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:mi:ss')) ");
            sb.append(" and not exists (select 1\n" +
                    "          from srm_pos_supplier_categories sc\n" +
                    "         where sc.category_id = t.category_id\n" +
                    "           and sc.status = 'DISABLED'\n" +
                    "           and sc.supplier_id = " + jsonParams.getString("supplierId") + " ) ");
        }
        //只取采购分类的enabledFlag为Y且（失效日期为空或大于当前时间）的数据
        if (null != jsonParams.getString("enabled_Flag") && "ENABLED_FLAG".equals(jsonParams.getString("enabled_Flag"))) {
            //有效的采购分类
            sb.append(" AND t.enabled_flag='Y'\n"
                    + "AND (t.invalid_date IS NULL OR t.invalid_date > to_date('" + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:mi:ss')) ");
        }
        //只取供应商档案合格状态且有效的采购分类，并且在采购分类表中为有效数据
        if (null != jsonParams.getString("effectiveSupplier") && "EFFECTIVESUPPLIER".equals(jsonParams.getString("effectiveSupplier"))) {
            if (null != jsonParams.getInteger("supplierId") && !"".equals(jsonParams.getInteger("supplierId"))) {
                sb.append(" AND EXISTS (select spsc.category_id from srm_pos_supplier_categories spsc where spsc.category_id=t.category_id\n"
                        + "AND (spsc.invalid_date IS NULL OR spsc.invalid_date > to_date('" + DateUtil.date2Str(new Date(), "yyyy-MM-dd") + "','yyyy-MM-dd'))\n"
                        + "AND spsc.status='EFFECTIVE' and spsc.supplier_id=" + jsonParams.getInteger("supplierId") + " )\n"
                        + "AND t.enabled_flag='Y'\n"
                        + "AND (t.invalid_date IS NULL OR t.invalid_date > to_date('" + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:mi:ss')) ");
            }
        }
        //绩效模型的采购分类的过滤，详情界面
        if (null != jsonParams.getString("tplDomain") && !"".equals(jsonParams.getString("tplDomain"))) {
            //取消限制
//            sb.append(" AND NOT EXISTS(\n"
//                    + " SELECT cate.category_id FROM srm_spm_tpl_categories cate\n"
//                    + " WHERE EXISTS(\n"
//                    + " SELECT tpl.tpl_id FROM srm_spm_performance_tpl tpl \n"
//                    + " WHERE (tpl.status ='NEW' OR tpl.status='ACTIVE') \n"
//                    + "  AND tpl.TPL_DOMAIN='" + jsonParams.getString("tplDomain") + "' AND tpl.ORG_ID=" + jsonParams.getInteger("orgId") + " AND TPL_FREQUENCY='" + jsonParams.getString("tplFrequency") + "' \n"
//                    + " AND tpl.tpl_id=cate.tpl_id) \n"
//                    + " AND t.category_id=cate.category_id) ");
            //有效的采购分类
            sb.append(" AND t.enabled_flag='Y'\n"
                    + "AND (t.invalid_date IS NULL OR t.invalid_date > to_date('" + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:mi:ss')) ");
        }

        //updated by xuwen at 2019/1/4，添加失效过滤
        Boolean noDisabled = jsonParams.getBoolean("noDisabled");
//        noDisabled = true;
        if (noDisabled != null && noDisabled) {
            sb.append(" and t.enabled_flag = 'Y' and (t.invalid_date is null or t.invalid_date > sysdate)");
        }

        //updated by xuwen at 2018/12/15，添加排序条件，updated 2019/1/18，二级分类优化排序
        /*Integer categoryLevel = jsonParams.getInteger("categoryLevel");
        if (categoryLevel != null && categoryLevel == 2) {
            sb.append(" order by to_number(t.full_category_code)");
        } else {
            sb.append(" order by t.full_category_code");
        }*/

        if("Y".equals(jsonParams.getString("isPo"))){
            sb.append(" AND t.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+") and t.LEAF_FLAG ='Y' ");
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" order by t.full_category_code");
        /*Pagination<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO.findPagination(sb.toString()
                , "select count(1) from (" + sb + ")", queryParamMap, pageIndex, pageRows);*/
        Pagination<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO.findPagination(sb.toString(),
                countSql, queryParamMap, pageIndex, pageRows);
        //updated by xuwen at 2018/12/5 添加了是否为所有分类添加一个总分类
        if (jsonParams.getBoolean("withRoot") != null && jsonParams.getBoolean("withRoot")) {
            for (SrmBaseCategoriesEntity_HI_RO category : result.getData()) {
                //没有父节点，则添加一个总分类的父节点
                if (category.getParentCategoryId() == null || category.getParentCategoryId() == -1) {
                    category.setParentCategoryId(-1);
                }
            }
            SrmBaseCategoriesEntity_HI_RO category = new SrmBaseCategoriesEntity_HI_RO();
            category.setCategoryId(-1);
            category.setFullCategoryName("总分类");
            result.getData().add(category);
        }
        return result;
    }

    /**
     * 查询冻结品类
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmBaseCategoriesEntity_HI_RO> findSrmBaseCategoriesById(
            JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseCategoriesEntity_HI_RO.QUERY_CATEGORIES_SQL);
            SaafToolUtils.parperParam(jsonParams, "b.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.status", "statusType", queryString, queryParamMap, "=");
            List<SrmBaseCategoriesEntity_HI_RO> rowSet = srmBaseCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：查询所有有效的采购分类
     *
     * @param jsonParams 参数
     * @param pageIndex
     * @param pageRows
     * @return 采购分类的实体集合
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findAllCategoriesList(JSONObject jsonParams, Integer pageIndex,
                                                                     Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseCategoriesEntity_HI_RO.GET_ALL_CATEGORY_SQL);
            SaafToolUtils.parperParam(jsonParams, "sbc.category_code", "categoryCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "sbc.category_name", "categoryName", queryString, queryParamMap, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO.findPagination
                    (queryString.toString(), countSql,queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
