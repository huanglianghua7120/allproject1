package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.po.model.entities.SrmPoRequisitionLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRequisitionLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRequisitionLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("srmPoRequisitionLinesServer")
public class SrmPoRequisitionLinesServer implements ISrmPoRequisitionLines{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRequisitionLinesServer.class);

	public SrmPoRequisitionLinesServer() {
		super();
	}

	@Autowired
	private ViewObject<SrmPoRequisitionLinesEntity_HI> srmPoRequisitionLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoRequisitionLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;
    /**
     * Description：拆分订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSplitRequisitionLine(JSONObject jsonParams) throws Exception{
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        Integer requisitionHeaderId = jsonParams.getInteger("requisitionHeaderId");
        Integer requisitionLineId = jsonParams.getInteger("requisitionLineId");
        SrmPoRequisitionLinesEntity_HI line = srmPoRequisitionLinesDAO_HI.getById(requisitionLineId);
        if (null == line){
            return SToolUtils.convertResultJSONObj("E", "申请行不存在！", 0, null);
        }
        //校验是否已验收
        if (!"OPEN".equals(line.getLineStatus())){
            return SToolUtils.convertResultJSONObj("E", "只能对状态为打开的申请行进行拆分！", 0, null);
        }
        JSONArray splitLinesArray = jsonParams.getJSONArray("splitRows");
        List<SrmPoRequisitionLinesEntity_HI> splitLinesList = new ArrayList<>();
        if (null != splitLinesArray && splitLinesArray.size() > 0){
            //获取最大行号
            Integer maxLineNumber = this.findRequisitionLineNumberMax(requisitionHeaderId);
            for (int i = 0;i < splitLinesArray.size(); i ++){
                maxLineNumber ++;
                JSONObject splitLineObject = (JSONObject)splitLinesArray.get(i);
                SrmPoRequisitionLinesEntity_HI splitLine = new SrmPoRequisitionLinesEntity_HI();
                PropertyUtils.copyProperties(splitLine, line);
                splitLine.setRequisitionLineId(null);
                splitLine.setLineNumber(maxLineNumber);
                splitLine.setDemandQty(splitLineObject.getBigDecimal("demandQty"));
                splitLine.setDemandDate(splitLineObject.getDate("demandDate"));
                splitLine.setSupplierId(splitLineObject.getInteger("supplierId"));
                splitLine.setVersionNum(0);
                splitLine.setOperatorUserId(userId);
                splitLinesList.add(splitLine);
            }
            srmPoRequisitionLinesDAO_HI.saveOrUpdateAll(splitLinesList);
            //删除原PO行
            srmPoRequisitionLinesDAO_HI.delete(line);
            srmPoRequisitionLinesDAO_HI.fluch();
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, requisitionHeaderId);
    }
    /**
     * Description：根据申请头ID查询最大行号
     *
     * @param requisitionHeaderId 申请头ID
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public Integer findRequisitionLineNumberMax(Integer requisitionHeaderId){
        Integer lineNumber = null;
        if (null != requisitionHeaderId) {
            Map<String, Object> map = new HashMap<>();
            map.put("requisitionHeaderId", requisitionHeaderId);
            List<SrmPoRequisitionLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(SrmPoRequisitionLinesEntity_HI_RO.QUERY_REQUISITION_LINE_NUMBER_MAX_SQL, map);
            if (null != list && list.size() > 0) {
                lineNumber = list.get(0).getLineNumber();
            }
        }
        return lineNumber;
    }



    /**
     * Description：拆分订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveRequisitionLine(JSONObject jsonParams) throws Exception{
        Integer userId = jsonParams.getInteger("varUserId");
        JSONArray linesArray = jsonParams.getJSONArray("supplierData");
        List<SrmPoRequisitionLinesEntity_HI> linesList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(linesArray)){
            for(int i=0;i<linesArray.size();i++){
                JSONObject lineObject = linesArray.getJSONObject(i);
                SrmPoRequisitionLinesEntity_HI line = srmPoRequisitionLinesDAO_HI.getById(lineObject.getInteger("requisitionLineId"));
                if (null == line){
                    return SToolUtils.convertResultJSONObj("E", "申请行不存在！", 0, null);
                }
                line.setSupplierId(lineObject.getInteger("supplierId"));
                line.setOperatorUserId(userId);
                linesList.add(line);
            }
            srmPoRequisitionLinesDAO_HI.saveOrUpdateAll(linesList);
            srmPoRequisitionLinesDAO_HI.fluch();
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", linesList.size(), null);
    }


}
