package top.luhancc.poi.test;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import top.luhancc.poi.handler.SheetHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * poi百万数据的导入导出
 *
 * @author luHan
 * @create 2021/5/20 11:05
 * @since 1.0.0
 */
public class PoiMillionDataTest {

    /**
     * 使用SXSSFWorkbook对象导出百万数据
     * <p>
     * 注意:SXSSFWorkbook不支持模板打印，也就是对样式的处理不是很好,一般情况下百万数据不需要携带样式
     */
    @Test
    public void testExportMillionData() throws IOException {
        long startTime = System.currentTimeMillis();
        // 创建SXSSFWorkbook,int rowAccessWindowSize 表示当内存对象超过这个值后就会将对象写入到一个临时文件中
        Workbook wb = new SXSSFWorkbook(200);
        //构造sheet
        Sheet sheet = wb.createSheet();

        String[] titles = {"编号", "姓名", "手机", "最高学历", "国家地区", "护照号", "籍贯", "生日", "属相", "入职时间", "离职类型", "离职原因", "离职时间"};
        //处理标题
        Row row = sheet.createRow(0);
        int titleIndex = 0;
        for (String title : titles) {
            Cell cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }

        // 处理数据
        Cell cell = null;
        for (int i = 1; i < 1048575; i++) {
            row = sheet.createRow(i);
            // 编号,
            cell = row.createCell(0);
            cell.setCellValue((i + 1) + "");
            // 姓名,
            cell = row.createCell(1);
            cell.setCellValue("姓名:" + i);
            // 手机,
            cell = row.createCell(2);
            cell.setCellValue("1385645587" + i);
            // 最高学历,
            cell = row.createCell(3);
            cell.setCellValue("大专");
            // 国家地区,
            cell = row.createCell(4);
            cell.setCellValue("中国大陆");
            // 护照号,
            cell = row.createCell(5);
            cell.setCellValue("48561358" + i);
            // 籍贯,
            cell = row.createCell(6);
            cell.setCellValue("湖北");
            // 生日,
            cell = row.createCell(7);
            cell.setCellValue("1996-05-13");
            // 属相,
            cell = row.createCell(8);
            cell.setCellValue("龙");
            // 入职时间,
            cell = row.createCell(9);
            cell.setCellValue("2018-10-1");
            // 离职类型,
            cell = row.createCell(10);
            cell.setCellValue("");
            // 离职原因,
            cell = row.createCell(11);
            cell.setCellValue("");
            // 离职时间
            cell = row.createCell(12);
            cell.setCellValue("");
        }
        //3.完成下载
        FileOutputStream fos = new FileOutputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/test-million.xlsx");
        wb.write(fos);
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("导入百万数据耗时:" + ((endTime - startTime) / 1000) + "s");
    }

    /**
     * 读取百万数据的excel
     * <p>
     * 使用poi的事件模型解析excel
     */
    @Test
    public void testReadMillionData() throws IOException, OpenXML4JException, SAXException, ParserConfigurationException {
        long startTime = System.currentTimeMillis();
        String path = "/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/demo-million-data.xlsx";
        // 以只读、xml压缩形式打开excel
        OPCPackage opcPackage = OPCPackage.open(path, PackageAccess.READ);
        XSSFReader reader = new XSSFReader(opcPackage);
        SharedStringsTable strings = reader.getSharedStringsTable();
        StylesTable stylesTable = reader.getStylesTable();
        XMLReader xmlReader = XMLHelper.newXMLReader();
        xmlReader.setContentHandler(new XSSFSheetXMLHandler(stylesTable, strings, new SheetHandler(), false));
        // 逐行读取
        XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) reader.getSheetsData();
        while (sheetIterator.hasNext()) {
            InputStream inputStream = sheetIterator.next();
            InputSource is = new InputSource(inputStream);
            xmlReader.parse(is);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("读取百万数据耗时:" + ((endTime - startTime) / 1000) + "s");
    }
}
