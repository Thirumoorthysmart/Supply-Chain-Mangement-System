package com.example.ecomersewebsite;

import javafx.collections.ObservableList;

import java.security.cert.Extension;

public class Oder {
    public static boolean PlaceOder(Customer customer,Product product){
        try{
        String PlaceOder="insert into orders(customers_id,product_id,quantity,status)values("+customer.getId()+","+product.getId()+",1,'ordered'";
      DatabaseConnection dbconn=new DatabaseConnection();

        return dbconn.excuteUpdate(PlaceOder);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static int placemultipleProducts(ObservableList<Product>productObservableList,Customer customer){
   int count=0;
        for(Product product:productObservableList){

       if(PlaceOder(customer,product)){
           count++;
       }
        }
        return count;
    }
}
