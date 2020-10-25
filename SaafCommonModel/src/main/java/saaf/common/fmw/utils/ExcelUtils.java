package saaf.common.fmw.utils;


import com.yhg.base.utils.SToolUtils;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;


public class ExcelUtils {
    public static Workbook createExcel2007Doc(String sheetName, List<Map<String, Object>> data, String[] allAttributeLabels, String[] allAttributeNames, int[] columnWidthArray) {
        Workbook wb = new SXSSFWorkbook();
        Sheet sheet = null;

        if ((null != sheetName) && (!"".equals(sheetName))) {
            sheet = wb.createSheet(sheetName);
        } else {
            sheet = wb.createSheet();
        }

        int idx = 0;
        XSSFCellStyle boldcs = (XSSFCellStyle)wb.createCellStyle();
        Font boldFont = wb.createFont();
        boldFont.setColor(XSSFFont.DEFAULT_FONT_COLOR);
        boldFont.setBoldweight((short)700);
        boldcs.setFont(boldFont);
        Row excelrowHead = sheet.createRow(idx);
        for (int i = 0; i < allAttributeLabels.length; i++) {
            String labelName = allAttributeLabels[i];
            createCell(excelrowHead, i, CellStyle.ALIGN_CENTER, labelName, boldcs);
        }
        try {
            XSSFCellStyle cs2 = (XSSFCellStyle)wb.createCellStyle();
            boldFont.setColor(XSSFFont.DEFAULT_FONT_COLOR);
            boldFont.setFontHeightInPoints((short)11);
            boldcs.setFont(boldFont);
            for (int i = 0; i < allAttributeLabels.length; i++) {
                String labelName = allAttributeLabels[i];
                createCell(excelrowHead, i, CellStyle.ALIGN_CENTER, labelName, boldcs);
            }
            if (null != data) {
                setColumnWidth(sheet, allAttributeLabels, allAttributeNames, columnWidthArray);

                for (int i = 0; i < data.size(); i++) {
                    Map tMap = (Map)data.get(i);

                    Row excelrow = sheet.createRow(idx + 1);
                    if ((null == allAttributeNames) || (allAttributeNames.length == 0))
                        for (int j = 0; j < allAttributeLabels.length; j++) {
                            Object value = tMap.get(allAttributeLabels[j]);
                            createCell(excelrow, j, (short)1, value, cs2);
                        }
                    else {
                        for (int k = 0; k < allAttributeNames.length; k++) {
                            String columnName = allAttributeNames[k];
                            Object value = tMap.get(columnName);
                            createCell(excelrow, k, CellStyle.ALIGN_LEFT, value, cs2);
                        }
                    }
                    idx++;
                }
            }
            return wb;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return wb;
    }

    private static void setCellValue(Cell cell, Object value) {
        if ((value instanceof Number)) {
            Number number = (Number)value;
            cell.setCellType(0);
            cell.setCellValue(number + "");
        } else {
            cell.setCellValue(SToolUtils.object2String(value));
        }
    }

    private static Cell createCell(Row row, int column, short align, Object value, CellStyle cellStyle) {
        Cell cell = row.createCell(column);
        setCellValue(cell, value);
        cellStyle.setAlignment(align);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    private static void setColumnWidth(Sheet sh, String[] allAttributeLabels, String[] allAttributeNames, int[] columnWidthArray) {
        if (null == columnWidthArray) {
            columnWidthArray = new int[allAttributeLabels.length];
            String[] attributeNames = null;
            if ((null == allAttributeNames) || (allAttributeNames.length == 0))
                attributeNames = allAttributeLabels;
            else {
                attributeNames = allAttributeNames;
            }
            for (int i = 0; i < attributeNames.length; i++) {
                int attributeWidth = 0;
                if (0 == attributeWidth)
                    attributeWidth = 3000;
                else {
                    attributeWidth *= 180;
                }
                if (attributeWidth >= 10000)
                    attributeWidth = 8500;
                else if (attributeWidth < 1000) {
                    attributeWidth = 3000;
                }
                sh.setColumnWidth(i, attributeWidth);
            }
        } else {
            for (int i = 0; i < columnWidthArray.length; i++)
                sh.setColumnWidth(i, columnWidthArray[i]);
        }
    }
}
