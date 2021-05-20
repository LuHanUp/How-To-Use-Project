package top.luhancc.poi.handler;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import top.luhancc.poi.model.PoiEntity;

/**
 * SheetContentsHandler逐行解析excel的handler
 *
 * @author luHan
 * @create 2021/5/20 13:14
 * @since 1.0.0
 */
public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    private PoiEntity poiEntity;

    /**
     * 开始解析第rowNum行时触发
     *
     * @param rowNum 行的索引
     */
    @Override
    public void startRow(int rowNum) {
        if (rowNum > 0)
            poiEntity = new PoiEntity();
    }

    /**
     * 结束解析第rowNum行时触发
     *
     * @param rowNum
     */
    @Override
    public void endRow(int rowNum) {
        System.out.println(poiEntity);
    }

    /**
     * 对行中的每个单元格数据解析时触发
     *
     * @param cellReference  单元格名称
     * @param formattedValue 单元格数据
     * @param comment        批注
     */
    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        if (poiEntity != null) {
//            System.out.println("cellReference:" + cellReference);
            String pix = cellReference.substring(0, 1);
            switch (pix) {
                case "A":
                    poiEntity.setId(formattedValue);
                    break;
                case "B":
                    poiEntity.setBreast(formattedValue);
                    break;
                case "C":
                    poiEntity.setAdipocytes(formattedValue);
                    break;
                case "D":
                    poiEntity.setNegative(formattedValue);
                    break;
                case "E":
                    poiEntity.setStaining(formattedValue);
                    break;
                case "F":
                    poiEntity.setSupportive(formattedValue);
                    break;
                default:
                    break;
            }
        }
    }
}
