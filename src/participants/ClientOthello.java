/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;
import javafx.scene.control.Label;
import othello.DatabaseManager;

/**
 *
 * @author Carlos
 */
public class ClientOthello extends Application {
    
    DataOutputStream toServer =  null;
    DataInputStream fromServer = null;
    
    @Override public void start(Stage primaryStage){
        
       TextField textFieldX = new TextField();
       BorderPane paneForTFX = new BorderPane();
       paneForTFX.setLeft(new Label("Enter x: "));
       paneForTFX.setCenter(textFieldX);
       
       TextField textFieldY = new TextField();
       BorderPane paneForTFY = new BorderPane();
       paneForTFY.setLeft(new Label("Enter y: "));
       paneForTFY.setCenter(textFieldY);
       
       BorderPane paneForTextFields = new BorderPane();
       paneForTextFields.setTop(paneForTFX);
       paneForTextFields.setBottom(paneForTFY);
       
       TextArea textArea = new TextArea(); 
       
       BorderPane mainPane = new BorderPane();
       mainPane.setTop(paneForTextFields);
       mainPane.setBottom(textArea);
       
       Scene scene = new Scene(new ScrollPane(mainPane), 492, 195);
       primaryStage.setTitle("Othello Client");
       primaryStage.setScene(scene);
       primaryStage.show();
       
       textFieldX.setOnAction(e -> {
       
           try {
               
               int inputToServer = Integer.parseInt(textFieldX.getText());
               
               toServer.writeInt(inputToServer);
               toServer.flush();
               
               int resultFromServer = fromServer.readInt();
               
               textArea.appendText("Your number: " + inputToServer + '\n');
               textArea.appendText("Result from server\nYour number times itself is: " + resultFromServer);
               
               
           } catch(IOException ex){
               
               ex.printStackTrace();
           }
       });
       
       textFieldY.setOnAction(e -> {
       
           try {
               
               int inputToServer = Integer.parseInt(textFieldY.getText());
               
               toServer.writeInt(inputToServer);
               toServer.flush();
               
               int resultFromServer = fromServer.readInt();
               
               textArea.appendText("Your number: " + inputToServer + '\n');
               textArea.appendText("Result from server\nYour number times itself is: " + resultFromServer);
               
               
           } catch(IOException ex){
               
               ex.printStackTrace();
           }
       });
       
       try {
           
           String portNo = DatabaseManager.getPort();
           int portNumber = Integer.parseInt(portNo);
           Socket socket = new Socket("localhost", portNumber);
           
           fromServer = new DataInputStream(socket.getInputStream());
           toServer = new DataOutputStream(socket.getOutputStream());
                      
       } catch(IOException ex){           
        
           ex.printStackTrace();
       }       
    }
    
    public static void main(String[] args){
        
        launch(args);
    }
}
