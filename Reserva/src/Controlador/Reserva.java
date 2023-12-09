/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */







package Controlador;

import com.sun.tools.javac.Main;
import java.io.IOException;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;


import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;//
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Usuario
 */
public class Reserva extends Application {

     @Override
    public void start(Stage primaryStage) throws IOException  {
        
       /* Label l1 = new Label("Login");
        Label luser = new Label ("usuario");
        Label lcontr = new Label ("contraseña");
        
        TextField txtUser =new TextField();
        TextField txtPassword = new TextField();
        
        Button btnIngresar = new Button("ingresar");
        VBox root = new VBox();
        
        root.getChildren().addAll(l1,luser,txtUser,lcontr,txtPassword);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();*/
       
           /* Parent root = FXMLLoader.load(getClass().getResource("/vista/FormularioLU.fxml"));
            Scene scene = new Scene (root);
            primaryStage.setScene(scene);
            primaryStage.show();*/
        
       
        
     try{
            //new ControladorEscenario(primaryStage);
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(Reserva.class.getResource("/Vista/Principal.fxml"));//FormularioLU.fxml
            
            Pane ventana = (Pane) loader.load();
            
           
            
            Scene scene = new Scene (ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        } catch(IOException e){
            System.out.println("tratar error"+ e.getMessage());
        
         }
        
        
         
    }
    
       public static void main(String[] args) {
         
        System.out.println("hola mundo desde Main_controlador");
        launch(args);            
       }
    
}