
package vragen_formulier;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    Connection conn;

    @FXML
    private Button Done;
    @FXML
    private TextField Vooropleiding;
    @FXML
    private TextField Studentnummer;
    @FXML
    private TextField Geboortedatum;
    @FXML
    private TextField Medestudent;
    @FXML
    private TextField Overig;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonDone(ActionEvent event) {
        
        getConnection();
        Stage stage = (Stage) Done.getScene().getWindow();
        stage.close();
    }
    
    public void getConnection() {
        
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/klassenindeling";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");

            vragen_formulier.Student student = new vragen_formulier.Student();

            student.id = Studentnummer.getText();
            student.vooropleiding = Vooropleiding.getText();

            String datum = Geboortedatum.getText();
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            student.gebdatum = sqlDate;

            student.medestudent = Medestudent.getText();
            student.overig = Overig.getText();

            student.update(conn);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
