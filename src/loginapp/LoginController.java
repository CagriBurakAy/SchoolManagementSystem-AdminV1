package loginapp;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel=new LoginModel();
    @FXML
    private TextField username;
    @FXML
    private Label dbstatus;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button login;
    @FXML
    private Label loginStatus;
    public LoginController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    if (this.loginModel.isDatabaseConnected()){
        this.dbstatus.setText("Connected to DB");
    }else {
        this.dbstatus.setText("Not Connected to DB");
    }
    this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }
    @FXML
    public  void Login(ActionEvent event){
        try {
            if (loginModel.isLogin(this.username.getText(),this.password.getText(),((Option)this.combobox.getValue()).toString())){
                Stage stage=(Stage)this.login.getScene().getWindow();
                stage.close();
                switch (((Option)this.combobox.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            }else {
            this.loginStatus.setText("Wrong Credentials");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void studentLogin(){
    try{
        Stage userStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root=(Pane)loader.load(getClass().getResource("/students/studentsFXML.fxml").openStream());
        StudentsController studentsController=(StudentsController)loader.getController();
        Scene scene=new Scene(root);
        userStage.setScene(scene);
        userStage.setTitle("Student DashBoard");
        userStage.setResizable(false);
        userStage.show();
    }catch (IOException e){
        e.printStackTrace();
    }
    }
    public void adminLogin(){
        try{
            Stage adminStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane adminRoot=(Pane)loader.load(getClass().getResource("/Admin/AdminFXml.fxml").openStream());
            AdminController adminController=(AdminController)loader.getController();
            Scene scene=new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin DashBoard");
            adminStage.setResizable(false);
            adminStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
