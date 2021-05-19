package top.luhancc.poi.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 使用Poi写excel
 *
 * @author luHan
 * @create 2021/5/19 15:27
 * @since 1.0.0
 */
public class PoiWriteTest {

    /**
     * 创建excel
     *
     * @throws Exception
     */
    @Test
    public void testCreateExcel() throws Exception {
        Workbook workbook = new XSSFWorkbook();// 创建2007版本的excel
        Sheet sheet = workbook.createSheet("test");// 创建sheet
        FileOutputStream fos = new FileOutputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/test.xlsx");
        workbook.write(fos);
        fos.close();
    }

    /**
     * 向excel中保存数据
     *
     * @throws Exception
     */
    @Test
    public void testSaveData() throws Exception {
        Workbook workbook = new XSSFWorkbook();// 创建2007版本的excel
        Sheet sheet = workbook.createSheet("test");// 创建sheet
        Row row = sheet.createRow(2);// 创建行对象,索引从0开始
        Cell cell = row.createCell(2);// 创建单元格对象,索引从0开始
        cell.setCellValue("这是一个数据");
        FileOutputStream fos = new FileOutputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/test1.xlsx");
        workbook.write(fos);
        fos.close();
    }

    /**
     * excel的样式处理
     */
    @Test
    public void testStyle() throws Exception {
        Workbook workbook = new XSSFWorkbook();// 创建2007版本的excel
        Sheet sheet = workbook.createSheet("test");// 创建sheet
        Row row = sheet.createRow(2);// 创建行对象,索引从0开始
        Cell cell = row.createCell(2);// 创建单元格对象,索引从0开始
        cell.setCellValue("这是一个数据");

        // 处理样式

        // 创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置边框的样式
        cellStyle.setBorderBottom(BorderStyle.DASH_DOT_DOT);
        cellStyle.setBorderLeft(BorderStyle.DASH_DOT_DOT);
        cellStyle.setBorderRight(BorderStyle.DASH_DOT_DOT);
        cellStyle.setBorderTop(BorderStyle.DASH_DOT_DOT);

        // 创建字体样式对象
        Font font = workbook.createFont();
        font.setFontName("华文行楷");// 字体名称
        font.setFontHeightInPoints((short) 28);// 字号
        cellStyle.setFont(font);

        // 列宽和行高
        row.setHeightInPoints(50);// 行高
        sheet.setColumnWidth(2, 31 * 256);// 列宽

        // 设置居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        cell.setCellStyle(cellStyle);// 向单元格设置样式

        FileOutputStream fos = new FileOutputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/test2.xlsx");
        workbook.write(fos);
        fos.close();
    }

    /**
     * 向excel中保存图片
     */
    @Test
    public void testSaveImg() throws Exception {
        Workbook workbook = new XSSFWorkbook();// 创建2007版本的excel
        Sheet sheet = workbook.createSheet("test");// 创建sheet
        Row row = sheet.createRow(2);// 创建行对象,索引从0开始
        Cell cell = row.createCell(2);// 创建单元格对象,索引从0开始

        FileInputStream fis = new FileInputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/pkq.jpg");
        byte[] bytes = IOUtils.toByteArray(fis);
        fis.read(bytes);
        // 将图片添加进POI内存中,会返回一个图片集合的索引
        // (byte[] pictureData 图片的二进制, int format 图片的类型
        int imgIndex = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        // 创建绘制图片的工具类
        CreationHelper creationHelper = workbook.getCreationHelper();
        // 创建绘制图片对象
        Drawing<?> patriarch = sheet.createDrawingPatriarch();
        // 创建锚点,设置图片坐标
        ClientAnchor anchor = creationHelper.createClientAnchor();
        anchor.setRow1(0);// 行的起始位置,索引从0开始
        anchor.setCol1(0);// 列的起始位置,索引从0开始
        // 绘制图片
        // ClientAnchor anchor 图片的锚点, int pictureIndex 图片的索引
        Picture picture = patriarch.createPicture(anchor, imgIndex);
        picture.resize();// 自适应渲染图片

        FileOutputStream fos = new FileOutputStream("/Users/luhan/学习/java/How-To-Use-Project/Poi/poi-simple/test3.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
