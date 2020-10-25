package saaf.common.fmw.pon.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import org.apache.http.client.utils.DateUtils;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionJuriesEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPriceLibraryEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonPriceLibrary;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonPriceLibraryEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPriceLibraryServer.java
 * Description：寻源--价格库信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPriceLibraryServer")
public class SrmPonPriceLibraryServer implements ISrmPonPriceLibrary{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPriceLibraryServer.class);
	@Autowired
	private ViewObject<SrmPonPriceLibraryEntity_HI> srmPonPriceLibraryDAO_HI;
	@Autowired
	private BaseViewObject<SrmPonPriceLibraryEntity_HI_RO> srmPonPriceLibraryDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;
	@Autowired
	private BaseViewObject<SrmPonAuctionItemsEntity_HI_RO> srmPonAuctionItemsDAO_HI_RO;
    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;
	@Autowired
	private BaseViewObject<SrmPonAuctionSuppliersEntity_HI_RO> srmPonAuctionSuppliersDAO_HI_RO;
	@Autowired
	private SaafSequencesUtil saafSequencesUtil;
	public SrmPonPriceLibraryServer() {
		super();
	}

	public List<SrmPonPriceLibraryEntity_HI> findSrmPonPriceLibraryInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<SrmPonPriceLibraryEntity_HI> findListResult = srmPonPriceLibraryDAO_HI.findList("from SrmPonPriceLibraryEntity_HI", queryParamMap);
		return findListResult;
	}
	public Object saveSrmPonPriceLibraryInfo(JSONObject queryParamJSON) {
		SrmPonPriceLibraryEntity_HI srmPonPriceLibraryEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmPonPriceLibraryEntity_HI.class);
		Object resultData = srmPonPriceLibraryDAO_HI.save(srmPonPriceLibraryEntity_HI);
		return resultData;
	}

    /**
     * Description：招标询价-已完成-创建价格库
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
	@Override
	public JSONObject updateAndCreatePriceLibrary(JSONObject jsonParams) {
		Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
		Integer operatorUserId = jsonParams.getInteger("varUserId");
		SrmPonAuctionHeadersEntity_HI auctionHeader = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
		if ("Y".equals(auctionHeader.getPoCreateFlag())) {
			return SToolUtils.convertResultJSONObj("E", "创建价格库失败！", 1, null);
		}

		SrmPonAuctionItemsEntity_HI_RO ponAuctionItemsEntity = null;
		Date date = new Date();
		String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
		try {
			StringBuffer querySupplierSql = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
			List<SrmPonAuctionSuppliersEntity_HI_RO> supplierLists = srmPonAuctionSuppliersDAO_HI_RO.findList(querySupplierSql,new Object[]{auctionHeaderId});
			if(supplierLists.isEmpty()||supplierLists.size()<=0){
				return SToolUtils.convertResultJSONObj("E", "创建价格库失败！", 1, null);
			}else{
                List<SrmPonAuctionItemsEntity_HI> itemList = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
                for(int n=0;n<itemList.size();n++){
                    StringBuffer queryItemsSql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_BID_LINE_FOR_PRICE_LIBRARY_SQL);
                    Map<String, Object> queryMap = new HashMap();
                    queryItemsSql.append(" and pbh.auction_header_id = :auctionHeaderId ");
                    queryItemsSql.append(" and pbip.item_id = :itemId ");
                    queryMap.put("auctionHeaderId", auctionHeaderId);
                    queryMap.put("itemId", itemList.get(n).getItemId());
                    List<SrmPonAuctionItemsEntity_HI_RO> itemsList = srmPonAuctionItemsDAO_HI_RO.findList(queryItemsSql, queryMap);
                    if(!itemsList.isEmpty() && itemsList.size()>0) {
                        SrmPonAuctionItemsEntity_HI_RO items=itemsList.get(0);
                        //比较报价
                        BigDecimal price=("LOGISTICS".equals(itemsList.get(0).getItemType()))?itemsList.get(0).getTaxPrice():itemsList.get(0).getNoTaxPrice();
                        for (int i = 1; i < itemsList.size(); i++) {
                            BigDecimal priceT=("LOGISTICS".equals(itemsList.get(i).getItemType()))?itemsList.get(i).getTaxPrice():itemsList.get(i).getNoTaxPrice();
                           // price=priceT.compareTo(price)==-1?priceT:price;
                            //items=priceT.compareTo(price)==-1?itemsList.get(i):items;
                            if(priceT.compareTo(price)==-1){
                                price=priceT;
                                items=itemsList.get(i);
                            }
                        }
                            StringBuffer checkString = new StringBuffer();
                            checkString.append(SrmPonPriceLibraryEntity_HI_RO.CHECK_PRICE_LIBRARY_SQL);
                            Map<String, Object> checkMap = new HashMap();
                            if(null != items.getItemId()){
                                checkString.append(" and t.item_id = :itemId ");
                                checkMap.put("itemId",items.getItemId());
                            }else{
                                checkString.append(" and t.item_name = :itemName ");
                                checkMap.put("itemName",items.getItemDescription());
                            }
                            checkString.append(" and t.org_id = :orgId ");
                            checkString.append(" and t.supplier_id = :supplierId ");
                            checkString.append(" and t.price_library_status = :priceLibraryStatus ");
                            checkMap.put("supplierId",items.getSupplierId());
                            checkMap.put("orgId",auctionHeader.getOrgId());
                            checkMap.put("priceLibraryStatus","ACT");
                            List<SrmPonPriceLibraryEntity_HI_RO> checkLibrarys = srmPonPriceLibraryDAO_HI_RO.findList(checkString,checkMap);
                            SrmPonPriceLibraryEntity_HI newLibrary = new SrmPonPriceLibraryEntity_HI();
                            if(!checkLibrarys.isEmpty() && checkLibrarys.size()>0){
                                SrmPonPriceLibraryEntity_HI updateLibrary = srmPonPriceLibraryDAO_HI.getById(checkLibrarys.get(0).getPriceLibraryId());
                                updateLibrary.setPriceExpirationDate(new Date());
                                updateLibrary.setPriceLibraryStatus("INACT");
                                updateLibrary.setOperatorUserId(operatorUserId);
                                srmPonPriceLibraryDAO_HI.saveOrUpdate(updateLibrary);
                                newLibrary.setPriceLibraryVersion(updateLibrary.getPriceLibraryVersion()+1);
                            }else{
                                newLibrary.setPriceLibraryVersion(0);
                            }
                            newLibrary.setPriceLibraryNumber(saafSequencesUtil.getDocSequences("srm_pon_price_library", "XY-", dateFromate, 4));
                            newLibrary.setOrgId(auctionHeader.getOrgId());
                            newLibrary.setSupplierId(items.getSupplierId());
                            newLibrary.setItemId(items.getItemId());
                            newLibrary.setItemName(items.getItemDescription());
                            newLibrary.setBuyerId(operatorUserId);
                            //newLibrary.setItemSpec();
                            newLibrary.setCategoryId(items.getCategoryId());
                            newLibrary.setTaxRateCode(items.getTaxRateCode());
                            newLibrary.setCurrencyCode(auctionHeader.getCurrencyCode());
                            newLibrary.setPaymentCondition(auctionHeader.getPaymentCondition());
                            newLibrary.setTaxPrice(items.getTaxPrice());
                            newLibrary.setNonTaxPrice(items.getNoTaxPrice());
                            newLibrary.setPriceLibraryStatus("ACT");
                            newLibrary.setPriceEffectiveDate(items.getStartDate());
                            newLibrary.setPriceExpirationDate(items.getEndDate());
                            newLibrary.setRemarks(items.getNotes());
                            newLibrary.setSourceNumber(auctionHeader.getAuctionNumber());
                            newLibrary.setSourceCode("srm_pon_auction_headers");
                            newLibrary.setSourceId(auctionHeaderId.toString());
                            newLibrary.setOperatorUserId(operatorUserId);
                            newLibrary.setMaterialsPrice(items.getMaterialsPrice());
                            newLibrary.setArtificialPrice(items.getArtificialPrice());
                            newLibrary.setOrganizationId(auctionHeader.getOrganizationId());

                            srmPonPriceLibraryDAO_HI.saveOrUpdate(newLibrary);
                       // }
                    }
                }

				/*for(SrmPonAuctionSuppliersEntity_HI_RO supplier:supplierLists){
					Integer supplierId = supplier.getSupplierId();
					//报价行-x
					StringBuffer queryItemsSql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_BID_LINE_FOR_PRICE_LIBRARY_SQL);
					Map<String, Object> queryMap = new HashMap();
					queryItemsSql.append(" and pbh.auction_header_id = :auctionHeaderId ");
					queryItemsSql.append(" and pbh.supplier_id = :supplierId ");
					queryMap.put("auctionHeaderId", auctionHeaderId);
					queryMap.put("supplierId", supplierId);
					List<SrmPonAuctionItemsEntity_HI_RO> itemsList = srmPonAuctionItemsDAO_HI_RO.findList(queryItemsSql, queryMap);
					if(!itemsList.isEmpty() && itemsList.size()>0){
						for(SrmPonAuctionItemsEntity_HI_RO items:itemsList){
							StringBuffer checkString = new StringBuffer();
							checkString.append(SrmPonPriceLibraryEntity_HI_RO.CHECK_PRICE_LIBRARY_SQL);
							Map<String, Object> checkMap = new HashMap();
							if(null != items.getItemId()){
								checkString.append(" and t.item_id = :itemId ");
								checkMap.put("itemId",items.getItemId());
							}else{
								checkString.append(" and t.item_name = :itemName ");
								checkMap.put("itemName",items.getItemDescription());
							}
							checkString.append(" and t.org_id = :orgId ");
							checkString.append(" and t.supplier_id = :supplierId ");
							checkString.append(" and t.price_library_status = :priceLibraryStatus ");
							checkMap.put("supplierId",items.getSupplierId());
							checkMap.put("orgId",auctionHeader.getOrgId());
							checkMap.put("priceLibraryStatus","ACT");
							//价格库信息： ITEM + SUPPLIER
							List<SrmPonPriceLibraryEntity_HI_RO> checkLibrarys = srmPonPriceLibraryDAO_HI_RO.findList(checkString,checkMap);
							SrmPonPriceLibraryEntity_HI newLibrary = new SrmPonPriceLibraryEntity_HI();
							if(!checkLibrarys.isEmpty() && checkLibrarys.size()>0){
								SrmPonPriceLibraryEntity_HI updateLibrary = srmPonPriceLibraryDAO_HI.getById(checkLibrarys.get(0).getPriceLibraryId());
								updateLibrary.setPriceExpirationDate(new Date());
								updateLibrary.setPriceLibraryStatus("INACT");
								updateLibrary.setOperatorUserId(operatorUserId);
								srmPonPriceLibraryDAO_HI.saveOrUpdate(updateLibrary);
								newLibrary.setPriceLibraryVersion(updateLibrary.getPriceLibraryVersion()+1);
							}else{
								newLibrary.setPriceLibraryVersion(0);
							}
							newLibrary.setPriceLibraryNumber(saafSequencesUtil.getDocSequences("srm_pon_price_library", "XY-", dateFromate, 4));
							newLibrary.setOrgId(auctionHeader.getOrgId());
							newLibrary.setSupplierId(supplierId);
							newLibrary.setItemId(items.getItemId());
							newLibrary.setItemName(items.getItemDescription());
							newLibrary.setBuyerId(operatorUserId);
							//newLibrary.setItemSpec();
							newLibrary.setCategoryId(items.getCategoryId());
							newLibrary.setTaxRateCode(items.getTaxRateCode());
							newLibrary.setCurrencyCode(auctionHeader.getCurrencyCode());
							newLibrary.setPaymentCondition(auctionHeader.getPaymentCondition());
							newLibrary.setTaxPrice(items.getTaxPrice());
							newLibrary.setNonTaxPrice(items.getNoTaxPrice());
							newLibrary.setPriceLibraryStatus("ACT");
							newLibrary.setPriceEffectiveDate(items.getStartDate());
							newLibrary.setPriceExpirationDate(items.getEndDate());
							newLibrary.setRemarks(items.getNotes());
							newLibrary.setSourceNumber(auctionHeader.getAuctionNumber());
							newLibrary.setSourceCode("srm_pon_auction_headers");
							newLibrary.setSourceId(auctionHeaderId.toString());
							newLibrary.setOperatorUserId(operatorUserId);
							srmPonPriceLibraryDAO_HI.saveOrUpdate(newLibrary);
						}
					}
				}*/

			}
			//回写标书的状态
			//auctionHeader.setPoCreateFlag("Y");
			auctionHeader.setPoCreateDate(date);
			auctionHeader.setOperatorUserId(operatorUserId);
			srmPonAuctionHeadersDAO_HI.update(auctionHeader);
			return SToolUtils.convertResultJSONObj("S", "创建价格库成功！", 1, null);
		} catch (Exception e) {
			//e.printStackTrace();
			return SToolUtils.convertResultJSONObj("E", "创建价格库失败！", 1, null);
		}
	}
}
