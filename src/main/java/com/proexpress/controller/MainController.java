package com.proexpress.controller;

import com.proexpress.Main;
import com.proexpress.model.DAO;
import com.proexpress.model.ParseExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController {
    public TextField filePatch;
    public ComboBox districtSelect;

    DAO dao;
    ParseExcel parseExcel;

    public MainController(){this.parseExcel=new ParseExcel(this);
    this.dao=new DAO( this );
    }

    public void createDb(ActionEvent actionEvent) {/*dao.createRow( "plot","street","house","housing","floor","door","apartament","other" );*/
   /* dao.createTable( "AVT" );*/
        ObservableList<String> district = FXCollections.observableArrayList("AVT", "KAN", "LEN", "MOS","NIZ","PRI","SOR","SOV");
districtSelect.setItems( district );
        districtSelect.setValue("AVT");

    }

    public void chooseFile(ActionEvent actionEvent) { parseExcel.selectFile();}

    public void parseFile(ActionEvent actionEvent) throws IOException {parseExcel.parseFile(); }

    public void compareFile(ActionEvent actionEvent) /*throws  IOException*/ {/*parseExcel.compareFile();*/parseExcel.testT(); }


}
