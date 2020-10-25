package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;
import saaf.common.fmw.pon.model.inter.server.SrmPonBidItemPricesServer;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SrmUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidItemPricesService.java
 * Description：寻源--寻源报价物料行信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonBidItemPricesService")
@Path("/srmPonBidItemPricesService")
public class SrmPonBidItemPricesService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidItemPricesService.class);
	@Autowired
	private ISrmPonBidItemPrices iSrmPonBidItemPrices;
	@Autowired
	private ISrmPonBidHeaders iSrmPonBidHeaders;//供应商报价头表
	@Autowired
	private BaseViewObject<SrmPonBidHeadersEntity_HI_RO> srmPonBidHeadersDAO_HI_RO;//供应商报价头表
	@Autowired
	private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;//供应商报价行
	@Autowired
	private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;//洽谈头表

    @Autowired
    private SrmPonBidItemPricesServer srmPonBidItemPricesServer;//

	public SrmPonBidItemPricesService() {
		super();
	}

	/**
	 * Description：报价中——供应商，单个价格降幅判断
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findBidItemPricesByNoTaxPrice")
	public String findBidItemPricesByNoTaxPrice(@FormParam(PARAMS) String params) {
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidItemPrices.findBidItemPricesByNoTaxPrice(jsonParams));
		} catch (Exception e) {
			LOGGER.error("降幅失败" + e, e);
			return CommonAbstractServices.convertResultJSONObj("E", "降幅失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：查询供应商报价行list（不分页）——已截止
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonBidItemPricesList")
	@Produces("application/json")
	public String findSrmPonBidItemPricesList(@FormParam(PARAMS) String params) {
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidItemPrices.findSrmPonBidItemPricesList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：会签的模态框的中标记录——已截止
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonBidItemPricesAll")
	@Produces("application/json")
	public String findSrmPonBidItemPricesAll(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidItemPrices.findSrmPonBidItemPricesAll(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：标的物的中标按钮=====供应商报价行的中标操作
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("updatePonBidItemPriceAwardStatus")
    @Produces("application/json")
	public String updatePonBidItemPriceAwardStatus(@FormParam(PARAMS) String params) {
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidItemPrices.updatePonBidItemPriceAwardStatus(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (Exception e) {
			LOGGER.error("供应商操作失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：决标汇总模态框的查询（已截止）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonBidItemPricesAllSummary")
	@Produces("application/json")
	public String findSrmPonBidItemPricesAllSummary(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidItemPrices.findSrmPonBidItemPricesAllSummary(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：已截止的保存操作（保存、提交按钮，包括保存中标份额、决标意见）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveAwardProportionAll")
	public String saveAwardProportionAll(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsonData = iSrmPonBidItemPrices.saveAwardProportionAll(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsonData.getString(STATUS), jsonData.getString(MSG), jsonData.getInteger(COUNT), jsonData.getString(DATA));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}" , e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败,"+e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：决标汇总查询的导出按钮（已截止）
	 * @param auctionHeaderId
	 * @param auctionNumber
	 * @param auctionRoundNumber
	 * @param subsectionFlag
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@GET
	@Path("/findBidItemPricesSummaryExport")
	public Response findBidItemPricesSummaryExport(@QueryParam("auctionHeaderId")String auctionHeaderId,@QueryParam("auctionNumber")String auctionNumber,
												   @QueryParam("auctionRoundNumber")String auctionRoundNumber,@QueryParam("subsectionFlag")String subsectionFlag,@QueryParam("itemType")String itemType){
		LOGGER.info("auctionHeaderId:"+auctionHeaderId+",auctionNumber:"+auctionNumber+",auctionRoundNumber:"+auctionRoundNumber+",itemType:"+itemType);
		String fileName = "";//文件名称
		if(null != auctionNumber && !"".equals(auctionNumber)){
			fileName += auctionNumber;
		}
		if(null != auctionRoundNumber.trim() && !"".equals(auctionRoundNumber.trim())){
			fileName += "-"+auctionRoundNumber.trim();
		}
		boolean flag = false;
		if(null != subsectionFlag && "Y".equals(subsectionFlag)){//分段价格
			flag = true;//阶梯数量
		}else{
			flag = false;//无阶梯数量
		}
		List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList = iSrmPonBidHeaders.findBidHeadersList(Integer.parseInt(auctionHeaderId));
		if(null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)){
			return null;
		}
		SrmPonAuctionHeadersEntity_HI auctionHeaders = iSrmPonAuctionHeaders.findSrmPonAuctionHeaders(Integer.parseInt(auctionHeaderId));
		Set<Integer> auctionLineIdList = iSrmPonBidHeaders.findAuctionLineIdList(bidHeadersList);//存放不重复的所有报价行的洽谈行Id
		try{
			Integer curRow = 0;
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("决标汇总");
			sheet.setDefaultRowHeightInPoints(24);//设置默认行高（需要先设置行高再设置列宽，否则列宽会失效）

			HSSFDataFormat format = wb.createDataFormat();

			HSSFFont redFont = wb.createFont();//红色字体标识
			redFont.setColor(new HSSFColor.RED().getIndex());
			redFont.setFontName("宋体");
			redFont.setFontHeightInPoints((short)11);//设置字体大小

			HSSFFont blackFont = wb.createFont();//黑色字体
			blackFont.setColor(new HSSFColor.BLACK().getIndex());
			blackFont.setFontName("宋体");
			blackFont.setFontHeightInPoints((short)11);//设置字体大小

			HSSFCellStyle style_default = wb.createCellStyle();
			HSSFCellStyle style_header = wb.createCellStyle();
			HSSFCellStyle style_header_required = wb.createCellStyle();
			HSSFCellStyle style_line = wb.createCellStyle();

			style_header_required.setFont(redFont);
			style_header_required.setWrapText(true); // 设置自动换行
			style_header_required.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
			style_header_required.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//			style_header_required.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//			style_header_required.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//			style_header_required.setBorderTop(HSSFCellStyle.BORDER_THIN);//  上边框
//			style_header_required.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

			style_default.setDataFormat(format.getFormat("@"));
			style_default.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
			style_default.setFont(blackFont);
			style_default.setWrapText(true);// 设置自动换行
			style_default.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中

			style_line.setDataFormat(format.getFormat("@"));
			style_line.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
			style_line.setFont(blackFont);
			style_line.setWrapText(true);// 设置自动换行
			style_line.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中

			style_header.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
			style_header.setFillForegroundColor(new HSSFColor.GREY_50_PERCENT().getIndex());
			style_header.setWrapText(true);// 设置自动换行
			style_header.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中

			HSSFFont headerfont = wb.createFont();
			headerfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
			headerfont.setFontName("宋体");
			headerfont.setFontHeightInPoints((short)11);//设置字体大小
			style_header.setFont(headerfont);
			int supplierSize = bidHeadersList.size();
			int size = 4+supplierSize;
			// 文本格式
			for (short i = 0; i < size; i++) {
				sheet.setDefaultColumnStyle(i, style_default);
			}

			String[] start = { "物料编码", "物料描述","采购分类","数量"};
            //String[] end = { "付款条件", "报价总计金额", "中标总计金额"};
			String[] end;
			if("ENGINEERING".equals(itemType)){
				end=new String[]{ "付款条件", "材料总价", "运杂及管理费", "规费及措施费", "工程造价", "税金(工程税)", "工程总造价"};
			}else{
				end=new String[]{ "付款条件", "LOGISTICS".equals(itemType)?"含税总价":"不含税总价"};
			}
			//String[] end = { "付款条件", "材料总价", "运杂及管理费", "规费及措施费", "工程造价", "税金(工程税)", "工程总造价"};
			if(null != auctionLineIdList && auctionLineIdList.size()>0){
				if(true){
					//第一行
					HSSFRow row = sheet.createRow(curRow);
					sheet.setColumnWidth(curRow,(int)(30 * 256+0.72));//设置列宽
					int i = 0;
					for(;i<start.length;i++){
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(String.valueOf(start[i]));
						cell.setCellStyle(style_header);
					}
					for (; i < start.length + supplierSize; i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellValue("供应商");
						cell.setCellStyle(style_header);
					}

					if (supplierSize>0){
						CellRangeAddress cra=new CellRangeAddress(0,0, start.length, start.length + supplierSize-1);
						sheet.addMergedRegion(cra);
					}
				}
				curRow++;
				if(true){
					//第二行
					HSSFRow row = sheet.createRow(curRow);
					sheet.setColumnWidth(curRow,(int)(30 * 256+0.72));//设置列宽
					int i = 0;
					for(;i<start.length;i++){
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(String.valueOf(start[i]));
						cell.setCellStyle(style_header);
					}
					for(;i<start.length + supplierSize;i++){
						SrmPonBidHeadersEntity_HI_RO bidHeadersEntity = bidHeadersList.get(i-start.length);
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(String.valueOf(bidHeadersEntity.getSupplierName()));//供应商名称
						cell.setCellStyle(style_header);
					}
					CellRangeAddress cra=new CellRangeAddress(0,1, 0, 0);
					sheet.addMergedRegion(cra);
					cra=new CellRangeAddress(0,1, 1, 1);
					sheet.addMergedRegion(cra);
					cra=new CellRangeAddress(0,1, 2, 2);
					sheet.addMergedRegion(cra);
					cra=new CellRangeAddress(0,1, 3, 3);
					sheet.addMergedRegion(cra);
					cra=new CellRangeAddress(0,0, 4, 4+bidHeadersList.size());
					sheet.addMergedRegion(cra);
				}
				//遍历标的物的Id
				if(null != auctionLineIdList && auctionLineIdList.size()>0){
					for(Integer auctionLineId:auctionLineIdList){
						List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesList = iSrmPonBidItemPrices.findBidItemPricesList(auctionLineId,Integer.parseInt(auctionHeaderId),bidHeadersList,flag);
						if(null != bidItemPricesList && bidItemPricesList.size()>0){
							if(flag){//有阶梯数量
								for(SrmPonBidItemPricesEntity_HI_RO k:bidItemPricesList){
									curRow++;
									HSSFRow row = sheet.createRow(curRow);
									sheet.setColumnWidth(curRow,(int)(30 * 256+0.72));//设置列宽
									int i = 0;
									int e = 0;
									for(;i<start.length;i++){//标的物的信息
										HSSFCell cell = row.createCell(i);
										switch (e){
											case 0:
												cell.setCellValue(SrmUtils.object2String(k.getItemCode()));
												e++;
												break;
											case 1:
												cell.setCellValue(SrmUtils.object2String(k.getItemDescription()));
												e++;
												break;
											case 2:
												cell.setCellValue(SrmUtils.object2String(k.getCategoryName()));
												e++;
												break;
											case 3:
												cell.setCellValue(SrmUtils.object2String(k.getLadderQuantity()));
												e++;
												break;
										}
									}
                                    //供应商报价行的不含税单价
                                    Object[] supplierNoTaxPrice = iSrmPonBidItemPrices.getNoTaxPrice(Integer.parseInt(auctionHeaderId),auctionLineId,k.getItemLadderId(),subsectionFlag,bidHeadersList);
                                    for(Integer j = 0;j<supplierNoTaxPrice.length;j++){
                                        SrmPonBidItemPricesEntity_HI_RO bidItemPrices = (SrmPonBidItemPricesEntity_HI_RO)supplierNoTaxPrice[j];
                                        HSSFCell cell = row.createCell(i);
                                        cell.setCellValue(SrmUtils.object2String(bidItemPrices.getNoTaxPrice()));//不含税单价
                                        if(null != bidItemPrices.getAwardStatus() && "4".equals(bidItemPrices.getAwardStatus())){
                                            cell.setCellStyle(style_header_required);
                                        }
                                        i++;
                                    }
								}
							}else{//无阶梯数量
                                SrmPonBidItemPricesEntity_HI_RO bidItemPricesEntity = bidItemPricesList.get(0);
								curRow++;
								HSSFRow row = sheet.createRow(curRow);
								sheet.setColumnWidth(curRow,(int)(30 * 256+0.72));//设置列宽
								int i = 0;
                                int e = 0;
                                for(;i<start.length;i++){//标的物的信息
                                    HSSFCell cell = row.createCell(i);
                                    switch (e){
                                        case 0:
                                            cell.setCellValue(SrmUtils.object2String(bidItemPricesEntity.getItemCode()));
                                            e++;
                                            break;
                                        case 1:
                                            cell.setCellValue(SrmUtils.object2String(bidItemPricesEntity.getItemDescription()));
                                            e++;
                                            break;
                                        case 2:
                                            cell.setCellValue(SrmUtils.object2String(bidItemPricesEntity.getCategoryName()));
                                            e++;
                                            break;
                                        case 3:
                                            cell.setCellValue(SrmUtils.object2String(bidItemPricesEntity.getQuantity()));
                                            e++;
                                            break;
                                    }
                                }
                                //供应商报价行的不含税单价
                                Object[] supplierNoTaxPrice = iSrmPonBidItemPrices.getNoTaxPrice(Integer.parseInt(auctionHeaderId),auctionLineId,null,subsectionFlag,bidHeadersList);
                                for(Integer j = 0;j<supplierNoTaxPrice.length;j++){
                                    SrmPonBidItemPricesEntity_HI_RO bidItemPrices = (SrmPonBidItemPricesEntity_HI_RO)supplierNoTaxPrice[j];
                                    HSSFCell cell = row.createCell(i);
                                    cell.setCellValue(SrmUtils.object2String(bidItemPrices.getNoTaxPrice()));//不含税单价
                                    if(null != bidItemPrices.getAwardStatus() && "4".equals(bidItemPrices.getAwardStatus())){
                                        cell.setCellStyle(style_header_required);
                                    }
                                    i++;
                                }
							}
						}
					}

				}
				//付款条件，报价总计金额，中标总计金额
                List<SrmPonBidHeadersEntity_HI_RO> bidHeadersListV = iSrmPonBidItemPrices.getTotalAccount(auctionLineIdList,bidHeadersList,subsectionFlag);
                curRow++;
                for(int j = 0;j<end.length;j++ ){
                    HSSFRow row = sheet.createRow(curRow);
					sheet.setColumnWidth(curRow,(int)(30 * 256+0.72));//设置列宽
                    int i = 0;
                    for(;i<start.length;i++){
                        HSSFCell cell = row.createCell(i);
                        cell.setCellValue(String.valueOf(end[j]));
                        cell.setCellStyle(style_header);
                    }
                    CellRangeAddress cra=new CellRangeAddress(curRow,curRow, 0, 3);
                    sheet.addMergedRegion(cra);

                    for(;i<start.length + supplierSize;i++){
                        SrmPonBidHeadersEntity_HI_RO k = bidHeadersListV.get(i-start.length);
                        HSSFCell cell = row.createCell(i);
                        if("ENGINEERING".equals(itemType)){
							switch (j){
								case 0 :
									if(null != k.getPaymentTermName()){
										cell.setCellValue(String.valueOf(k.getPaymentTermName()));//付款条件
									}
									if(null != auctionHeaders && null != auctionHeaders.getPaymentCondition() && !auctionHeaders.getPaymentCondition().equals(k.getPaymentCondition())){
										cell.setCellStyle(style_header_required);
									}
									break;
								case 1 :
									if(null != k.getTotalAccountOffer()){
										cell.setCellValue(String.format("%.2f", k.getTotalAccountOffer()));//报价总计金额
									}
									break;
								case 2 :
									if(null != k.getTranManageFees()){
										cell.setCellValue(String.format("%.2f", k.getTranManageFees()));//运杂及管理费
									}
									break;
								case 3 :
									if(null != k.getMeasuresFees()){
										cell.setCellValue(String.format("%.2f", k.getMeasuresFees()));//规费及措施费
									}
									break;
								case 4 :
									if(null != k.getProjectCosts()){
										cell.setCellValue(String.format("%.2f", k.getProjectCosts()));//工程造价
									}
									break;
								case 5 :
									if(null != k.getEngineeringTax()){
										cell.setCellValue(String.format("%.2f", k.getEngineeringTax()));//税金(工程税)
									}
									break;
								case 6 :
									if(null != k.getTotalProjectCost()){
										cell.setCellValue(String.format("%.2f", k.getTotalProjectCost()));//工程总造价
									}
									break;
							}
						}else{
							switch (j){
								case 0 :
									if(null != k.getPaymentTermName()){
										cell.setCellValue(String.valueOf(k.getPaymentTermName()));//付款条件
									}
									if(null != auctionHeaders && null != auctionHeaders.getPaymentCondition() && !auctionHeaders.getPaymentCondition().equals(k.getPaymentCondition())){
										cell.setCellStyle(style_header_required);
									}
									break;
                            /*case 1 :
								if(null != k.getTotalAccountOffer()){
									cell.setCellValue(String.format("%.2f", k.getTotalAccountOffer()));//报价总计金额
								}
								break;
							case 2 :
								if(null != k.getTotalAccountBiddinng()){
									cell.setCellValue(String.format("%.2f", k.getTotalAccountBiddinng()));//中标总计金额
								}
                                break;*/
								case 1 :
									if(null != k.getTotalAccountOffer()){
										cell.setCellValue(String.format("%.2f", k.getTotalAccountOffer()));//报价总计金额
									}
									break;


							}
						}


                    }
                    curRow++;
                }

			}

			ByteArrayOutputStream baos = null;
			try{
				baos = new ByteArrayOutputStream();
				wb.write(baos);
			}catch (Exception e){
				//e.printStackTrace();
			}
			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-Disposition", "attachment; filename=\""+fileName+".xls\"");
			return response.build();
		}catch (Exception e){
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * Description：查询供应商报价信息（定价）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSupplierBidPrice")
	public String findSupplierBidPrice(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidItemPrices.findSupplierBidPrice(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询供应商报价失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}


    public void saveEkpStatus() {
        try {
            srmPonBidItemPricesServer.saveEkpStatus();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("招标询价定时查询EKP状态服务异常:" + e);
        }
    }
}
