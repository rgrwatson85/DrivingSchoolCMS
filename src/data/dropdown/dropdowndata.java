/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dropdown;

import data.database_connection.SettingsController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 *
 * @author mrgnwatson
 */
public class dropdowndata {

    public static ObservableList<String> countryList;
    public static ObservableList<String> stateList;
    public static ObservableList<String> courseList;
    public static ObservableList<String> filteredCourseList;
    public static ObservableList<String> employeeList;
    public static ObservableList<String> instructorList;
    public static ObservableList<String> salutationList;
    public static ObservableList<String> suffixList;
    public static ObservableList<String> locationList;
    public static ObservableList<String> relationshipList;
    public static ObservableList<String> employeeTypeList;
    public static ObservableList<String> transactionTypeList;
    public static ObservableList<String> paymentTypeList;
    public static ObservableList<String> permitRestrictionList;
    public static ObservableList<String> highSchoolList;
    public static ObservableList<String> maintenanceTypeList;
    public static ObservableList<String> shopNameTypeList;
    public static ObservableList<String> vehicleNameList;
    public static ObservableList<String> vehicleMakeNameList;
    public static ObservableList<String> vehicleModelNameList;
    public static ObservableList<String> vehicleTypeNameList;
    public static ObservableList<String> vehicleFuelTypeList;

    public static void createDropDownData() throws ClassNotFoundException, SQLException {
        SettingsController.openConnection();
        countryList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
        filteredCourseList = FXCollections.observableArrayList();
        employeeList = FXCollections.observableArrayList();
        instructorList = FXCollections.observableArrayList();
        salutationList = FXCollections.observableArrayList();
        suffixList = FXCollections.observableArrayList();
        locationList = FXCollections.observableArrayList();
        relationshipList = FXCollections.observableArrayList();
        employeeTypeList = FXCollections.observableArrayList();
        transactionTypeList = FXCollections.observableArrayList();
        paymentTypeList = FXCollections.observableArrayList();
        permitRestrictionList = FXCollections.observableArrayList();
        highSchoolList = FXCollections.observableArrayList();
        maintenanceTypeList = FXCollections.observableArrayList();
        shopNameTypeList = FXCollections.observableArrayList();
        vehicleNameList = FXCollections.observableArrayList();
        vehicleMakeNameList = FXCollections.observableArrayList();
        vehicleModelNameList = FXCollections.observableArrayList();
        vehicleFuelTypeList = FXCollections.observableArrayList();
        vehicleTypeNameList = FXCollections.observableArrayList();

        loadCountry();
        loadCourse();
        loadEmployeeInstructor();
        loadSalutation();
        loadSuffix();
        loadLocation();
        loadRelationship();
        loadEmployeeType();
        loadTransactionType();
        loadPaymentType();
        loadPermitRestriction();
        loadHighSchool();
        loadMaintenanceType();
        loadShopNameTypeList();
        loadVehicleNameList();
        loadVehicleMakeNameList();
        loadVehicleTypeNameList();
        loadFuelTypeNameList();

        SettingsController.closeConnection();
    }
    
    public static void createVehicleDropDownData(String vehicleMake) throws ClassNotFoundException, SQLException{
        SettingsController.openConnection();
        vehicleModelNameList = FXCollections.observableArrayList();
        loadVehicleModelNameList(vehicleMake);
        SettingsController.closeConnection();
    }
    
    public static void createStateDropDownData(String countryName) throws ClassNotFoundException, SQLException{
        SettingsController.openConnection();
        stateList = FXCollections.observableArrayList();
        loadState(countryName);
        SettingsController.closeConnection();
    }

    //load countries
    private static void loadCountry() {
        //get countries
        try {
            String sql = "SELECT CountryName FROM Country";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] country = new String[size];
            int i = 0;
            while (rs.next()) {
                country[i] = rs.getString("CountryName");
                i++;
            }
            countryList.addAll(Arrays.asList(country));


        } catch (Exception e) {
            Dialog.showError("Error", "Could not load country drop downs");
        }
    }

    //load state
    private static void loadState(String countryFilter) {
        //get states
        try {
            String sql = "SELECT COUNT(DISTINCT(a.CountryID)) AS CountryCount, b.StateName, b.StateID, b.CountryID, Country.CountryName "
                    + "FROM State a, State b, Country "
                    + "WHERE b.CountryID = Country.CountryID "
                    + "AND Country.CountryName='" + countryFilter + "' "
                    + "GROUP BY b.StateID, b.StateName, b.CountryID, CountryName";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            int countryCount = 0;
            try {
                rs.last();
                countryCount = rs.getInt("CountryCount");
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }

            String[][] states = new String[countryCount + 1][size];
            int i = 0;
            int countryID_old = 0;
            while (rs.next()) {
                int countryID = rs.getInt("CountryID");
                //if the country id has not changed, then add to array
                if (countryID == countryID_old) {
                    states[countryID][i] = rs.getString("StateName");
                } //country id has changed, so start a new row in the array and reset the second index to 0 for first state
                else {
                    countryID_old = countryID;
                    i = 0;
                    states[countryID][i] = rs.getString("StateName");
                }
                stateList.add(states[countryID][i]);
                i++;
            }
        } catch (Exception e) {
            Dialog.showError("Error", e.getMessage());
        }
    }

    //load course
    private static void loadCourse() {
        //get courses
        try {
            String sql = "SELECT CourseName,FloatingDate FROM CourseMaster";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }

            String[] courses = new String[size];
            int i = 0;
            while (rs.next()) {
                courses[i] = rs.getString("CourseName");
                if (rs.getBoolean("FloatingDate") == Boolean.FALSE) {
                    filteredCourseList.add(rs.getString("CourseName"));
                }
                i++;
            }
            courseList.addAll(Arrays.asList(courses));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //load employee/instructor
    private static void loadEmployeeInstructor() {
        //get employees and instructor subset
        try{
            String sql = "SELECT FirstName, LastName FROM EMPLOYEE ";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] employee;
            employee = new String[size];
            int i = 0;
            while (rs.next()) {
                employee[i] = rs.getString("FirstName") + " " + rs.getString("LastName");
                i++;
            }
            //only instructor
            sql = "SELECT FirstName, LastName "
                    + "FROM EMPLOYEE "
                    + "WHERE EmployeeTypeID IN ("
                    + "SELECT EmployeeTypeID "
                    + "FROM EmployeeType "
                    + "WHERE EmployeeTypeName='Instructor'"
                    + ")";
            stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] instructor;
            instructor = new String[size];
            i = 0;
            while (rs.next()) {
                instructor[i] = rs.getString("FirstName") + " " + rs.getString("LastName");
                i++;
            }
            employeeList.addAll(Arrays.asList(employee));
            instructorList.addAll(Arrays.asList(instructor));
        } catch (Exception e) {
        }
    }

    //load salutation
    private static void loadSalutation() {
        try {
            String sql = "SELECT SalutationName FROM Salutation";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] salutation;
            salutation = new String[size];
            int i = 0;
            while (rs.next()) {
                salutation[i] = rs.getString("SalutationName");
                salutationList.add(salutation[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load suffix
    private static void loadSuffix() {
        //get suffix
        try {
            String sql = "SELECT SuffixName FROM LastNameSuffix";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] suffix;
            suffix = new String[size];
            int i = 0;
            while (rs.next()) {
                suffix[i] = rs.getString("SuffixName");
                suffixList.add(suffix[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load locations
    private static void loadLocation() {
        //get suffix
        try {
            String sql = "SELECT Address FROM Location";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] location;
            location = new String[size];
            int i = 0;
            while (rs.next()) {
                location[i] = rs.getString("Address");
                locationList.add(location[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load emergency contact relationships
    private static void loadRelationship() {
        //get relationships
        try {
            String sql = "SELECT RelationshipType FROM Relationship";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] relationship;
            relationship = new String[size];
            int i = 0;
            while (rs.next()) {
                relationship[i] = rs.getString("RelationshipType");
                relationshipList.add(relationship[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load employee types
    private static void loadEmployeeType() {
        try {
            String sql = "SELECT EmployeeTypeName FROM EmployeeType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] employeeType;
            employeeType = new String[size];
            int i = 0;
            while (rs.next()) {
                employeeType[i] = rs.getString("EmployeeTypeName");
                employeeTypeList.add(employeeType[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load transaction types
    private static void loadTransactionType() {
        try {
            String sql = "SELECT TransactionTypeName FROM TransactionType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] transactionType;
            transactionType = new String[size];
            int i = 0;
            while (rs.next()) {
                transactionType[i] = rs.getString("TransactionTypeName");
                transactionTypeList.add(transactionType[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    //load payment types
    private static void loadPaymentType() {
        try {
            String sql = "SELECT PaymentMethodName FROM PaymentMethod";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] paymentMethod;
            paymentMethod = new String[size];
            int i = 0;
            while (rs.next()) {
                paymentMethod[i] = rs.getString("PaymentMethodName");
                paymentTypeList.add(paymentMethod[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    private static void loadPermitRestriction() {
        try {
            String sql = "SELECT (RestrictionTypeID + ' - ' + RestrictionTypeName) AS RestrictionType FROM RestrictionType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] permitRestriction;
            permitRestriction = new String[size];
            int i = 0;
            while (rs.next()) {
                permitRestriction[i] = rs.getString("RestrictionType");
                permitRestrictionList.add(permitRestriction[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    private static void loadHighSchool() {
        try {
            String sql = "SELECT HighschoolName FROM HighSchool";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception ex) {
            }
            String[] highSchool;
            highSchool = new String[size];
            int i = 0;
            while (rs.next()) {
                highSchool[i] = rs.getString("HighSchoolName");
                highSchoolList.add(highSchool[i]);
                i++;
            }
        } catch (Exception e) {
        }
    }

    private static void loadVehicleNameList() {
        //maintenance type 
        try {
            String sql = "SELECT VehicleID FROM Vehicle";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] vehicle;
            vehicle = new String[size];
            int i = 0;
            while (rs.next()) {
                vehicle[i] = rs.getString("VehicleID");
                vehicleNameList.add(vehicle[i]);
                i++;

            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load vehicles to drop downs");
        }
    }

    private static void loadMaintenanceType() {
        //maintenance type 
        try {
            String sql = "SELECT MaintenanceTypeName FROM MaintenanceType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] maintenanceType;
            maintenanceType = new String[size];
            int i = 0;
            while (rs.next()) {
                maintenanceType[i] = rs.getString("MaintenanceTypeName");
                maintenanceTypeList.add(maintenanceType[i]);
                i++;
            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load Maintenance drop downs");
        }
    }

    private static void loadShopNameTypeList() {
        //get shopnames
        try {
            String sql = "SELECT AuthorizedMechanicShopName FROM AuthorizedMechanicShop";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] shopName;
            shopName = new String[size];
            int i = 0;
            while (rs.next()) {
                shopName[i] = rs.getString("AuthorizedMechanicShopName");
                shopNameTypeList.add(shopName[i]);
                i++;
            }

        } catch (Exception e) {
            Dialog.showError("Error", "Could not load Shop Name drop downs");
        }
    }

    private static void loadVehicleMakeNameList() {
        //maintenance type 
        try {
            String sql = "SELECT VehicleMakeName FROM VehicleMake";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] vehiclemake;
            vehiclemake = new String[size];
            int i = 0;
            while (rs.next()) {
                vehiclemake[i] = rs.getString("VehicleMakeName");
                vehicleMakeNameList.add(vehiclemake[i]);
                i++;

            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load vehiclemake drop downs");
        }
    }

    private static void loadVehicleModelNameList(String vehicleMake) {
        //maintenance type 
        try {
            String sql = "SELECT COUNT(DISTINCT(a.VehicleMakeID)) AS MakeCount, b.VehicleModelName, b.VehicleModelID, b.VehicleMakeID, VehicleMake.VehicleMakeName "
                    + "FROM VehicleModel a, VehicleModel b, VehicleMake "
                    + "WHERE b.VehicleMakeID = VehicleMake.VehicleMakeID "
                    + "AND VehicleMake.VehicleMakeName='" + vehicleMake + "' "
                    + "GROUP BY b.VehicleMakeID, b.VehicleModelName, b.VehicleMakeID, b.VehicleModelID, VehicleMakeName";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            int makeCount = 0;
            try {
                rs.last();
                makeCount = rs.getInt("MakeCount");
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[][] models = new String[makeCount + 1][size];
            int i = 0;
            int makeID_old = 0;
            while (rs.next()) {
                int makeID = rs.getInt("VehicleMakeID");
                //if the country id has not changed, then add to array
                if (makeID == makeID_old) {
                  models[makeID][i] = rs.getString("VehicleModelName");
                } //country id has changed, so start a new row in the array and reset the second index to 0 for first state
                else {
                    makeID_old = makeID;
                    i = 0;
                   models[makeID][i] = rs.getString("VehicleModelName");
                }
                vehicleMakeNameList.add(models[makeID][i]);
                i++;
            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load vehicle model drop downs\n\n"+e.getMessage());
        }
    }

    private static void loadVehicleTypeNameList() {
        //vehicle type 
        try {
            String sql = "SELECT VehicleTypeName FROM VehicleType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] vehicletype;
            vehicletype = new String[size];
            int i = 0;
            while (rs.next()) {
                vehicletype[i] = rs.getString("VehicleTypeName");
                vehicleTypeNameList.add(vehicletype[i]);
                i++;
            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load vehicle type drop downs");
        }
    }

    private static void loadFuelTypeNameList() {
        //fuel type 
        try {
            String sql = "SELECT FuelTypeName FROM FuelType";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            } catch (Exception e) {
                Dialog.showError("Error", e.getMessage());
            }
            String[] fueltype;
            fueltype = new String[size];
            int i = 0;
            while (rs.next()) {
                fueltype[i] = rs.getString("FuelTypeName");
                vehicleFuelTypeList.add(fueltype[i]);
                i++;
            }
        } catch (Exception e) {
            Dialog.showError("Error", "Could not load fuel type drop downs");
        }
    }
}
