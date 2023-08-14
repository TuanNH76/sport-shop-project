package com.sportshoptest.utils;

import com.sportshoptest.Entity.OrderMain;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {
    private List<OrderMain> orderList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List < OrderMain > orderList) {
        this.orderList = orderList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Order List");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int columnIndex = 0;
        createCell(row, columnIndex++, "OrderId", style);
        createCell(row, columnIndex++, "BuyerAddress", style);
        createCell(row, columnIndex++, "BuyerEmail", style);
        createCell(row, columnIndex++, "BuyerName", style);
        createCell(row, columnIndex++, "BuyerPhone", style);
        createCell(row, columnIndex++, "CreateTime", style);
        createCell(row, columnIndex++, "OrderAmount", style);
        createCell(row, columnIndex++, "OrderStatus", style);
        createCell(row, columnIndex++, "UpdateTime", style);

    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellStyle(style);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof BigDecimal) {
            cell.setCellValue(String.valueOf((BigDecimal) valueOfCell));
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        } else if (valueOfCell instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) valueOfCell);
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(dateCellStyle);
        }
    }



    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (OrderMain record: orderList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getOrderId(), style);
            createCell(row, columnCount++, record.getBuyerAddress(), style);
            createCell(row, columnCount++, record.getBuyerEmail(), style);
            createCell(row, columnCount++, record.getBuyerName(), style);
            createCell(row, columnCount++, record.getBuyerPhone(), style);
            createCell(row, columnCount++, record.getCreateTime(), style);
            createCell(row, columnCount++, record.getOrderAmount(), style);
            createCell(row, columnCount++, record.getOrderStatus(), style);
            createCell(row, columnCount++, record.getUpdateTime(), style);
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
