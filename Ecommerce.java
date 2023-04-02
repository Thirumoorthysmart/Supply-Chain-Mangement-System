package com.example.ecomersewebsite;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Ecommerce extends Application {


    private final int with=500,height=400,headerLine=50;
    ProductList productList=new ProductList();
     Pane BodyPane;

     ObservableList<Product>cartListItem= FXCollections.observableArrayList();

     Button signInButton=new Button("sign In");

     Button placeOderButton=new Button("Place Oder");
     Label welcomelabel=new Label("Welcome customers");
     Customer LogedIncustomer=null;
     private void addItemtoCart(Product product){
         if(cartListItem.contains(product)){
             return;
         }
         cartListItem.add(product);
     }
     private GridPane headerBar(){
         TextField searchBar=new TextField();
         Button searchButton= new Button("search");
         Button cartButton=new Button("Cart");

         searchButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 BodyPane.getChildren().clear();
                 BodyPane.getChildren().add(productList.getAllProducts());
             }
         });
         signInButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 BodyPane.getChildren().clear();
                 BodyPane.getChildren().add(loginPage());
             }
         });
         cartButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 BodyPane.getChildren().clear();
                 BodyPane.getChildren().add(productList.productsInCart(cartListItem));
             }
         });
         GridPane header =new GridPane();
         header.setHgap(10);
         header.add(searchBar,0,0);
         header.add(searchButton,1,0);
         header.add(signInButton,2,0);
         header.add(welcomelabel,3,0);
         header.add(cartButton,4,0);

         return header;

     }
     private GridPane loginPage(){

         Label userlable=new Label("User Name");
         Label passlable=new Label("Password");
         TextField userName=new TextField();
         userName.setText("thiru@gmail.com");
         userName.setPromptText("Enter User Name");
         PasswordField password=new PasswordField();
         password.setText("abc");
         password.setPromptText("Enter Password");
         Button loginButton=new Button("Login");
         Label messageLabel=new Label("Login-Message");

         loginButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 String user=userName.getText();
                 String pass=password.getText();
                 LogedIncustomer=Login.customerLogin(user,pass);
                 if(LogedIncustomer!=null){
                     messageLabel.setText("Login successfully");
                     welcomelabel.setText("Welcome"+LogedIncustomer.getName());
                 }else{
                     messageLabel.setText("Login Failed");
                 }
             }
         });

         GridPane loginPane=new GridPane();
         loginPane.setTranslateY(50);
         loginPane.setTranslateX(50);
         loginPane.setVgap(10);
         loginPane.setHgap(10);
         loginPane.add(userlable,0,0);
         loginPane.add(userName,1,0);
         loginPane.add(passlable,0,1);
         loginPane.add(password,1,1);
         loginPane.add(loginButton,0,2);
         loginPane.add(messageLabel,1,2);


         return loginPane;
     }
    private void showDialogue(String message){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);


        dialog.showAndWait();

    }

     private GridPane footerBar(){
         Button buyNowButton =new Button("Buy Now");
         Button addtoCartButton=new Button("AddtoCart");
         buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 Product product=productList.getSelectedProduct();
                 boolean orderStatus=false;
                 if(product!=null&&LogedIncustomer!=null){
                     orderStatus=Oder.PlaceOder(LogedIncustomer,product);
                 }
                 if(orderStatus==true){
                     //
                     showDialogue("Order successfull!!");
                 }
                 else {
                     //
                 }
             }
         });
         addtoCartButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 Product product=productList.getSelectedProduct();
                 addItemtoCart(product);
                 System.out.println("Product to Cart"+cartListItem.stream().count());
             }
         });
         placeOderButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {


                 int Odercount=0;
                 if(!cartListItem.isEmpty()&&LogedIncustomer!=null){
                     Odercount=Oder.placemultipleProducts(cartListItem,LogedIncustomer);
                 }
                 if(Odercount>0){
                     //
                     showDialogue("Order for"+Odercount+"oders place successfull!!");
                 }
                 else {
                     //
                 }
             }
         });

         GridPane footer=new GridPane();
         footer.setHgap(10);
         footer.setTranslateY(headerLine+height);
         footer.add(buyNowButton,0,0);
         footer.add(addtoCartButton,1,0);
         footer.add(placeOderButton,2,0);
         return footer;
     }
    private Pane createContent(){
        Pane root =new Pane();
        root.setPrefSize(with,height+2*headerLine);

        BodyPane=new Pane();
        BodyPane.setPrefSize(with,height);
        BodyPane.setTranslateY(headerLine);
        BodyPane.setTranslateX(10);
        BodyPane.getChildren().add(loginPage());

        root.getChildren().addAll(headerBar(),
//                loginPage(),
//                productList.getAllProducts()
                BodyPane,
                footerBar()
        );

        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

         launch();
    }
}