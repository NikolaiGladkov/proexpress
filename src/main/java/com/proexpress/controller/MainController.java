package com.proexpress.controller;

import com.proexpress.model.DAO;
import javafx.event.ActionEvent;

public class MainController {
    DAO dao=new DAO();
    public void createDb(ActionEvent actionEvent) {/*dao.createRow( "plot","street","house","housing","floor","door","apartament","other" );*/
    dao.createTable( "SOV" );
    }
}
