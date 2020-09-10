package indi.xk.report.utils;

import com.alibaba.fastjson.JSON;
import com.aspose.cells.*;

import java.util.UUID;

/**
 * @Author xk
 * @Date 2020/9/4 17:44
 * @Version 1.0
 */
public class ExcelUtils {
    public static void getSelect(){
        try {
            String fileName = "院内老人导入模板.xlsx";
            License license = new License();
            license.setLicense(License.class.getResourceAsStream("/resources/aspose.cells.license.xml"));
            Workbook book = new Workbook();
            Worksheet sheet = book.getWorksheets().get(0);
            String hiddenSheetName = "dropdownList";
            //excel中的"名称"，用于标记隐藏sheet中的用作菜单下拉项的所有单元格
            String formulaId = "form" + UUID.randomUUID().toString().replace("-", "");
            //用于存储下拉菜单数据
            Worksheet hiddenSheet = book.getWorksheets().add(hiddenSheetName);

            Cells cells = sheet.getCells();

            ValidationCollection validations = sheet.getValidations();
            Validation validation = validations.get(validations.add());

            validation.setInCellDropDown(true);
            CellArea cellArea = new CellArea();
            cellArea.StartRow = 5;
            cellArea.EndRow = 10;
            cellArea.StartColumn = 0;
            cellArea.EndColumn = 5;
            validation.addArea(cellArea);
            validation.setType(ValidationType.LIST);
            validation.setValue1("汉字,是");

//            //Set the data validation type
//            validation.setType(ValidationType.WHOLE_NUMBER);
//            //Set the operator for the data validation
//            validation.setOperator(OperatorType.BETWEEN);
//            //Set the value or expression associated with the data validation
//            validation.setFormula1("0");
//            //the value or expression associated with the second part of the data validation
//            validation.setFormula2("10");
//            validation.setShowError(true);
//            //Set the validation alert style
//            validation.setAlertStyle(ValidationAlertType.INFORMATION);
//            //Set the title of the data-validation error dialog box
//            validation.setErrorTitle("error");
//            //Set the data validation error message
//            validation.setErrorMessage("Enter value between 0 to 10");
//            //Set the data validation input message
//            validation.setInputMessage("Data Validation using Condition for Numbers");
//            validation.setIgnoreBlank(true);
//            validation.setShowInput(true);
//            validation.setShowError(true);
//            //设置Validations的区域，因为现在要Validations的位置是12,1，所以下面设置对应的也要是12,1
//            CellArea cellArea = new CellArea();
//            cellArea.StartRow = 12;
//            cellArea.EndRow = 12;
//            cellArea.StartColumn = 1;
//            cellArea.EndColumn = 1;
//            validation.addArea(cellArea);

            String[] titles = {"姓名","地址","电话"};
            Cells hiddenCells = hiddenSheet.getCells();
            cells.get(0, 0).setValue("序号");
            for (int i = 1; i < titles.length; i++) {
                cells.get(0, i).setValue(titles[i]);

            }
            book.save("D:\\yardUserExcel\\" + fileName);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 给单元格设置下拉框
     * @param sheet
     * @param valueArr
     * @param startRow
     * @param endRow
     * @param startColumn
     * @param endColumn
     */
    public static void setDropDownList(Worksheet sheet,String valueArr,Integer startRow,Integer endRow,Integer startColumn,Integer endColumn){
        ValidationCollection validations = sheet.getValidations();
        Validation validation = validations.get(validations.add());
        validation.setInCellDropDown(true);
        CellArea cellArea = new CellArea();
        if(startRow != null){
            cellArea.StartRow = startRow;
        }
        if(endRow != null){
            cellArea.EndRow = endRow;
        }
        if(startColumn != null){
            cellArea.StartColumn = startColumn;
        }
        if(endColumn != null){
            cellArea.EndColumn = endColumn;
        }
        validation.addArea(cellArea);
        validation.setType(ValidationType.LIST);
        validation.setValue1(JSON.toJSONString(valueArr));
    }
}
