package com.example.ecomersewebsite;
import java.sql.*;

public class DatabaseConnection {
    String dbURL="jdbc:mysql://localhost:3306/ecomm";
    String userName="root";
    String passworld ="Thiru@1995";

    private Statement getStatement(){
        try{
            Connection conn =  DriverManager.getConnection(dbURL, userName, passworld);
            return conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public ResultSet getQueryTable(String query){
        Statement statement = getStatement();
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean excuteUpdate(String query){
        Statement statement = getStatement();
        try {
             statement.executeUpdate(query);
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


//    public static void main(String[] args) {
//        String query = "SELECT * FROM ecomm.products";
//        DatabaseConnection dbConn = new DatabaseConnection();
//        ResultSet rs = dbConn.getQueryTable(query);
//        if(rs !=null){
//            System.out.println("Connected To Database");
//        }
//    }
}
/*private void showDialogue(String message){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);


            dialog.showAndWait();

    }*/
/*Angad  to  Everyone 21:18
CREATE TABLE `ecomm`.`orderss` (
  `oid` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL DEFAULT 1,
  `status` VARCHAR(45) NULL,
  `orderscol` VARCHAR(45) NULL,
  PRIMARY KEY (`oid`),
  INDEX `fk_customer_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `ecomm`.`customer` (`cid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `ecomm`.`products` (`pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);*/
//UPDATE customer SET password = 'ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad'   WHERE cid = 1;