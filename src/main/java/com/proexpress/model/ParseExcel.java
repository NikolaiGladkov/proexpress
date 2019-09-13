package com.proexpress.model;

import com.proexpress.controller.MainController;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ParseExcel {
    private MainController mainController;
    private DAO dao;
   public ParseExcel(MainController mainController){this.mainController=mainController;}
    //Выбор файла
   public void selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        ///fileChooser.setInitialDirectory( new File( System.getProperty( "user.home" ) ) );
        File selectedFile = fileChooser.showOpenDialog(null);
        mainController.filePatch.setText( selectedFile.toString() );
    }

    public void parseFile() throws IOException{
        String[] arrValParseFile;//Массив с результатами парсинга файла
        String [] arrPlot;String[] arrStreet;String[] arrHouses;   String[] arrHousing;
        String[] arrFloor; String[] arrDoor;String[] arrApartament; String[] arrOther;

        ArrayList<String> arrListPlot=new ArrayList<String>( );
        ArrayList<String> arrListStreet=new ArrayList<String>( );
        ArrayList<String> arrListHouses=new ArrayList<String>( );
        ArrayList<String> arrListHousing=new ArrayList<String>( );
        ArrayList<String> arrListFloor=new ArrayList<String>( );
        ArrayList<String> arrListDoor=new ArrayList<String>( );
        ArrayList<String> arrListAppartament=new ArrayList<String>( );
        ArrayList<String> arrListOther=new ArrayList<String>( );

        ArrayList<String> arrListParseFile=new ArrayList<String>( );//ArrayList с результатами парсинга файла

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(mainController.filePatch.getText()));
        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                // Change to getCellType() if using POI 4.x
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        arrListParseFile.add( cell.getStringCellValue());
                        break;

                    case BLANK:
                        arrListParseFile.add( cell.getStringCellValue());
                        break;

                    case NUMERIC:
                        arrListParseFile.add( NumberToTextConverter.toText( cell.getNumericCellValue())) ;
                        break;
                }

            }
        }
        arrValParseFile=arrListParseFile.toArray(new String[arrListParseFile.size()]);
        for(int i=0;i< arrValParseFile.length;i++){
            if(arrValParseFile[i].length()>0) {
                arrListPlot.add( arrValParseFile[i] );//0
                i = ++i;
                arrListStreet.add( arrValParseFile[i] );//1
                i = ++i;
                arrListHouses.add( arrValParseFile[i] );//2
                i = ++i;
                arrListHousing.add( arrValParseFile[i] );//3
                i = ++i;
                arrListFloor.add( arrValParseFile[i] );//4
                i = ++i;
                arrListDoor.add( arrValParseFile[i] );//5
                i = ++i;
                arrListAppartament.add( arrValParseFile[i] );//6
                i = ++i;
                arrListOther.add( arrValParseFile[i] );//7

            }
        }

         arrPlot=arrListPlot.toArray(new String[arrListPlot.size()]);
       arrStreet=arrListStreet.toArray(new String[arrListStreet.size()]);
         arrHouses=arrListHouses.toArray(new String[arrListHouses.size()]);
         arrHousing=arrListHousing.toArray(new String[arrListHousing.size()]);
         arrFloor=arrListFloor.toArray(new String[arrListFloor.size()]);
         arrDoor=arrListDoor.toArray(new String[arrListDoor.size()]);
         arrApartament=arrListAppartament.toArray(new String[arrListAppartament.size()]);
        arrOther=arrListOther.toArray(new String[arrListOther.size()]);
        for(int i=0;i<arrPlot.length;i++){
            dao.createRow( arrPlot[i],arrStreet[i],arrHouses[i], arrHousing[i],arrFloor[i],arrDoor[i], arrApartament[i],arrOther[i] );
        }

        //System.out.println( Arrays.toString(  arrStreet ) );
        //System.out.println( Arrays.toString(  arrHouses ) );
        //System.out.print(  arrValParseFile.length );
        //System.out.print(  arrValParseFile[19652].length() );
    }

}
