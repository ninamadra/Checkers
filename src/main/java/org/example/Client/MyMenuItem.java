package org.example.Client;

import javafx.scene.control.MenuItem;

public class MyMenuItem extends MenuItem {

    MyMenuItem(String type) {
        super();
        setText(type);
        setOnAction(e -> {
            //wyslij do gry type
            
            System.out.println(type);
        });
    }


}
