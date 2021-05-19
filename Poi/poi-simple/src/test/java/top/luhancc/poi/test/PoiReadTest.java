package top.luhancc.poi.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 使用Poi读取excel数据
 *
 * @author luHan
 * @create 2021/5/19 16:14
 * @since 1.0.0
 */
public class PoiReadTest {

    /**
     * 读取excel中的数据
     */
    @Test
    public void testReadExcel() throws Exception {
        Workbook workbook = new XSSFWorkbook("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/demo.xlsx");
        Sheet sheet = workbook.getSheetAt(0);// 获取Sheet,参数是索引
        // 循环读取sheet的数据,getLastRowNum返回的是最后一行的索引
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            StringBuilder sb = new StringBuilder();
            // getLastCellNum返回的是最后一个单元格的号码
            for (int cellNum = 0; cellNum < ((int) row.getLastCellNum()); cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell != null) {
                    Object value = getCellValue(cell);
                    sb.append(value).append("-");
                }
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 获取当前单元格数据
     *
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        // 1.获取到单元格的属性类型
        CellType cellType = cell.getCellType();
        // 2.根据单元格数据类型获取数据
        Object value = null;
        switch (cellType) {
            case STRING:// 字符串类型
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:// boolean类型
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:// 数字类型(包含日期类型)
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期格式
                    value = cell.getDateCellValue();
                } else {
                    //数字
                    value = cell.getNumericCellValue();
                }
                break;
            case FORMULA: // 公式类型
                value = cell.getCellFormula();
                break;
            default:
                value = cell.getStringCellValue();
                break;
        }
        return value;
    }
}
