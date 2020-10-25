package saaf.common.fmw.prc.services;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcIndicatorHeadersEntity_HI_RO;
import saaf.common.fmw.prc.model.inter.IPrcIndicatorHeaders;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SrmUtils;

@Path("/srmExcelExportServices")
@Component
public class SrmExcelExportServices extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmExcelExportServices.class);

	@Autowired
	private IPrcIndicatorHeaders srmPrcIndicatorHeadersServer;

	/**
	 * 指标加导出
	 * 
	 * @Description:
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	@GET
	@Path("/excel")
	@Produces("application/vnd.ms-excel; charset=UTF-8")
	public Response getExcelFile(@QueryParam("indicatorNumber") String indicatorNumber,
			@QueryParam("indicatorStatus") String indicatorStatus, @QueryParam("creationDateS") String creationDateS,
			@QueryParam("creationDateE") String creationDateE) {

		System.out.println("getExcelFile indicatorNumber:" + indicatorNumber);

		try {
			int curRow = 0;
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("sheet1");

			HSSFDataFormat format = wb.createDataFormat();

			HSSFFont redFont = wb.createFont();
			redFont.setColor(new HSSFColor.RED().getIndex());

			HSSFFont blackFont = wb.createFont();
			blackFont.setColor(new HSSFColor.BLACK().getIndex());

			HSSFCellStyle style_default = wb.createCellStyle();
			HSSFCellStyle style_header = wb.createCellStyle();
			HSSFCellStyle style_header_required = wb.createCellStyle();
			HSSFCellStyle style_line = wb.createCellStyle();

			style_default.setDataFormat(format.getFormat("@"));
			style_header_required.setFont(redFont);
			style_line.setDataFormat(format.getFormat("@"));

			 style_header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			 style_header.setFillForegroundColor(new
			 HSSFColor.GREY_50_PERCENT().getIndex());
			 
			 HSSFFont headerfont = wb.createFont();  
//			 headerfont.setColor(HSSFFont.COLOR_RED);  
			 headerfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
			 style_header.setFont(headerfont);
			// style_header.setFillBackgroundColor(new
			// HSSFColor.GREY_25_PERCENT().getIndex());
//			 style_header.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			 style_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			 style_header.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			 style_header.setBorderTop(HSSFCellStyle.BORDER_THIN);

			// style_line.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// style_line.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// style_line.setBorderRight(HSSFCellStyle.BORDER_THIN);
			// style_line.setBorderTop(HSSFCellStyle.BORDER_THIN);

			List<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorInfo = srmPrcIndicatorHeadersServer
					.findExpHeader(indicatorNumber, indicatorStatus, creationDateS, creationDateE);

			List<SrmPrcIndicatorHeadersEntity_HI_RO> supplierInfo = srmPrcIndicatorHeadersServer
					.findSupplier(indicatorNumber, indicatorStatus, creationDateS, creationDateE);

			int supplierSize = supplierInfo.size();

			int size = 6 + supplierSize;

			// 文本格式
			for (short i = 0; i < size; i++) {
				sheet.setDefaultColumnStyle(i, style_default);
			}

			String[] start = { "指标名称", "单位" };
			String[] end = { "最低价", "最高价", "最低价供应商", "最高供应商" };
			if (true) {
				HSSFRow row = sheet.createRow(curRow);
				int i = 0;
				for (; i < start.length; i++) {
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
				sheet.addMergedRegion( cra  );
				}
				
				int e = 0;
				for (; i < start.length + supplierSize + end.length; i++) {
					
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(String.valueOf(end[e]));
					cell.setCellStyle(style_header);
					e++;
				}

				curRow++;
			}
			if (true) {
				HSSFRow row = sheet.createRow(curRow);
				int i = 0;
				for (; i < start.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellValue("");
				}
				for (; i < start.length + supplierSize; i++) {
					int s = 0;
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(supplierInfo.get(s).getSupplierName());
					s++;
				}
				for (; i < start.length + supplierSize + end.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellValue("");
				}
				curRow++;
			}

			if (true) {
				int a = 0;
			
				for (; a < indicatorInfo.size(); a++) {
					SrmPrcIndicatorHeadersEntity_HI_RO ind = indicatorInfo.get(a);
					HSSFRow row = sheet.createRow(curRow);
					int i = 0;
					for (; i < start.length; i++) {
						HSSFCell cell = row.createCell(i);
						switch (i) {
						case 0:
							cell.setCellValue(ind.getIndicatorName());
							break;
						case 1:
							cell.setCellValue(ind.getUnitOfMeasure());
							break;
						}
					}
					BigDecimal maxValue = null;
					String maxSupplier = null;
					BigDecimal minValue = null;
					String minSupplier = null;

					Integer maxCount = 0;
					Integer minCount = 0;

					Integer maxCellNum = 0;
					Integer minCellNum = 0;

					
					int s = 0;
					
					List<SrmPrcIndicatorHeadersEntity_HI_RO> suppPrices = srmPrcIndicatorHeadersServer
							.findSupplierPrice(ind.getIndicatorHeaderId());
					for (; i < start.length + supplierSize; i++) {
						
						HSSFCell cell = row.createCell(i);
						SrmPrcIndicatorHeadersEntity_HI_RO supp =    	supplierInfo.get(s);
						SrmPrcIndicatorHeadersEntity_HI_RO p = getPrice(supp, suppPrices);

						if (p != null) {
							
							if (maxValue != null && (maxValue.compareTo(p.getOriginalPrice()) == 0)) {
								maxCount++;
							}
							if (maxValue == null || (maxValue.compareTo(p.getOriginalPrice()) < 0)) {
								maxValue = p.getOriginalPrice();
								maxSupplier = p.getSupplierName();
								maxCellNum = i;
								maxCount = 1;
							}

							if (minValue != null && (minValue.compareTo(p.getOriginalPrice()) == 0)) {
								minCount++;
							}

							if (minValue == null || (minValue.compareTo(p.getOriginalPrice()) > 0)) {
								minValue = p.getOriginalPrice();
								minSupplier = p.getSupplierName();
								minCellNum = i;
								minCount=1;

							}

						

							cell.setCellValue(SrmUtils.object2String(p.getOriginalPrice()));

						} else {
							cell.setCellValue("");
						}

						s++;
					}
					if (maxCount == 1) {
						HSSFCell maxCell = row.getCell(maxCellNum);
						maxCell.setCellStyle(style_header_required);
					} else {
						maxValue = null;
						maxSupplier = null;

					}

					if (minCount == 1) {
						HSSFCell minCell = row.getCell(minCellNum);
						minCell.setCellStyle(style_header_required);
					} else {

						minValue = null;
						minSupplier = null;
					}
					int e = 0;
					for (; i < start.length + supplierSize + end.length; i++) {

						

						HSSFCell cell = row.createCell(i);
						switch (e) {
						case 0:

							cell.setCellValue(SrmUtils.object2String(minValue));
							e++;
							break;
						case 1:
							cell.setCellValue(SrmUtils.object2String(maxValue));
							e++;
							break;
						case 2:
							cell.setCellValue(minSupplier);
							e++;
							break;
						case 3:
							cell.setCellValue(maxSupplier);
							e++;
							break;
						}
						
					}

					curRow++;

				}

			}

			ByteArrayOutputStream baos = null;
			try {
				baos = new ByteArrayOutputStream();
				wb.write(baos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-Disposition", "attachment; filename=\"indicatorPrice.xls\"");
			return response.build();
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

	}

	public SrmPrcIndicatorHeadersEntity_HI_RO getPrice(SrmPrcIndicatorHeadersEntity_HI_RO supp,
			List<SrmPrcIndicatorHeadersEntity_HI_RO> suppPrices) {
		System.out.println("getSupplierId" +supp.getSupplierId());

		for (SrmPrcIndicatorHeadersEntity_HI_RO row : suppPrices) {
			System.out.println(row.getSupplierId());
			if (row.getSupplierId().equals( supp.getSupplierId())) {
				return row;

			}

		}
		return null;
	}
}
