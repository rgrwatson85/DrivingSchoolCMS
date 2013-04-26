/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database_connection;

import com.mjwtech.main.controller.MainController;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import name.antonsmirnov.javafx.dialog.Dialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class SettingsController implements Initializable {

    @FXML
    public static AnchorPane root;
    @FXML
    public static Button btnLogin;
    @FXML
    public static Label lblStatus;
    @FXML
    public TextField txtPass;
    @FXML
    public TextField txtPort;
    @FXML
    public TextField txtServerIP;
    @FXML
    public TextField txtUser;
    @FXML
    private TextField txtDatabase;
    private static boolean connection;
    public static Connection conn;
    private static String urlString;
    private static String IP;
    private static String port;
    private static String database;
    private static String Username;
    private static String Password;
    private static boolean loginstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();

        txtPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                login();
            }
        });

        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                login();
            }
        });
    }

    private void login() {
        IP = txtServerIP.getText();
        port = txtPort.getText();
        database = txtDatabase.getText();
        Username = txtUser.getText();
        Password = txtPass.getText();
        loginstatus = true;
        testConnection();
    }

    public static void logout() {
        Username = "Guest";
        Password = "cis1!2@3#4$";
        loginstatus = false;
        testConnection();
        dbconnection.instance.stage.show();
    }

    private static void testConnection() {
        connection = false;
        lblStatus.setText("");
        try {
            openConnection();
            closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
            Dialog.showError("ERROR", e.getMessage() + "\n\nSettingsController:125");
        }
        if (connection) {
            saveLogin();
            dbconnection.instance.stage.getScene().getWindow().hide();
            MainController.root.setEffect(null);
        } else {
            lblStatus.setText("Unsuccessful Database Credentials");
        }
    }

    private void loadData() {
        try {
            File file = new File("src/data/database_connection/dbconn.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            Element eElement;
            NodeList nlList;
            Node nValue;

            //get ip
            Node ip = nList.item(0);
            eElement = (Element) ip;
            nlList = eElement.getElementsByTagName("ip").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            txtServerIP.setText(nValue.getNodeValue());

            //get port
            Node portNode = nList.item(0);
            eElement = (Element) portNode;
            nlList = eElement.getElementsByTagName("port").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            txtPort.setText(nValue.getNodeValue());

            //get database name
            Node databaseNode = nList.item(0);
            eElement = (Element) databaseNode;
            nlList = eElement.getElementsByTagName("database").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            txtDatabase.setText(nValue.getNodeValue());

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Dialog.showError("Error", "Could load load dbconn.xml");
        }
    }

    public static void saveLogin() {
        try {
            File file = new File("src/data/database_connection/dbconn.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            Element eElement;
            NodeList nlList;
            Node nValue;

            //update ip
            Node ip = nList.item(0);
            eElement = (Element) ip;
            nlList = eElement.getElementsByTagName("ip").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            nValue.setTextContent(IP);

            //update port
            Node portNode = nList.item(0);
            eElement = (Element) portNode;
            nlList = eElement.getElementsByTagName("port").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            nValue.setTextContent(port);

            //update database
            Node databaseNode = nList.item(0);
            eElement = (Element) databaseNode;
            nlList = eElement.getElementsByTagName("database").item(0).getChildNodes();
            nValue = (Node) nlList.item(0);
            nValue.setTextContent(database);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/data/database_connection/dbconn.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Dialog.showError("Error", ex.getMessage());
        }
    }

    public static void openConnection() throws ClassNotFoundException, SQLException {
        Socket s = null;
        try {
            new Socket().connect(new InetSocketAddress(IP, Integer.parseInt(port)), 1000);
            urlString = "jdbc:jtds:sqlserver://";
            urlString += IP;
            urlString += ":";
            urlString += port;
            urlString += ";databaseName=" + database;

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(urlString, Username, Password);
            if (loginstatus) {
                MainController.lblUserName.setText("Logged In As: " + Username.toUpperCase());
            } else {
                MainController.lblUserName.setText("Logged Out");
            }
            SettingsController.connection = true;      
        } catch (IOException ex) {
            SettingsController.connection = false;
            Dialog.showError("Error", "No connection to server.\nCheck Server IP in advanced tab.");
        }
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }
}
