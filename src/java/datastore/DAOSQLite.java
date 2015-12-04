package datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Dog;

/**
 * DAOSQLite Data Access Object for an SQLite database
 *
 * @author John Phillips
 * @version 0.3 on 2015-11-03
 */
public class DAOSQLite {

    protected final static String DRIVER = "org.sqlite.JDBC";
    protected final static String JDBC = "jdbc:sqlite";

    /**
     * Inserts an record into the database table. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param dog the object to insert
     * @param dbPath the path to the SQLite database
     */
    public static void createRecord(Dog dog, String dbPath) {
        String q = "insert into dog (dogId, name, species, color, "
                + "age) values (null, ?, ?, ?, ?)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, dog.getName());
            ps.setString(2, dog.getSpecies());
            ps.setString(3, dog.getColor());
            ps.setDouble(4, dog.getAge());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve a record given an empId.
     *
     * @param dogId the empId of the record to retrieve
     * @param dbPath the path to the SQLite database
     * @return Employee object
     */
    public static Dog retrieveRecordById(int dogId, String dbPath) {
        String q = "select dogId, name, species, color, age from dog where dogId = ?";
        Dog dog = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, dogId);
            dog = myQuery(conn, ps).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return dog;
    }

    /**
     * Retrieve all of the records in the database as a list sorted by lastName,
     * firstName.
     *
     * @param dbPath the path to the SQLite database
     * @return list of objects
     */
    public static List<Dog> retrieveAllRecordsByName(String dbPath) {
        String q = "select * from dog order by name, species";
        List<Dog> list = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            list = myQuery(conn, ps);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Update a record from the database given an employee object. Note the use
     * of a parameterized query to prevent SQL Injection attacks.
     *
     * @param dog the employee record to update
     * @param dbPath the path to the SQLite database
     */
    public static void updateRecord(Dog dog, String dbPath) {
        String q = "update dog set name=?, species=?, color=?, age=? where dogId = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, dog.getName());
            ps.setString(2, dog.getSpecies());
            ps.setString(3, dog.getColor());
            ps.setDouble(4, dog.getAge());
            ps.setInt(5, dog.getDogId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete a record from the database given its id. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param id the id of the record to delete
     * @param dbPath the path to the SQLite database
     */
    public static void deleteRecord(int id, String dbPath) {
        String q = "delete from dog where dogId = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new employee table.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void createTable(String dbPath) {
        String q = "create table dog ("
                + "dogId integer not null primary key autoincrement, "
                + "name varchar(100) not null, "
                + "species varchar(100) not null, "
                + "color varchar(100) not null, "
                + "age double not null)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Drops the employee table erasing all of the data.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void dropTable(String dbPath) {
        final String q = "drop table if exists dog";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the table with sample data records.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void populateTable(String dbPath) {
       // Dog p;
        //p = new Dog(0, "Beginning Java 2", "Murach", "Java", 50000.00);
       // DAOSQLite.createRecord(p, dbPath);
       // p = new Dog(0, "Java Application Development on Linux", "Albing", "Java", 40000.00);
        //DAOSQLite.createRecord(p, dbPath);
        //p = new Dog(0, "Java Structures", "Bob", "Java", 52000.00);
        //DAOSQLite.createRecord(p, dbPath);
       // p = new Dog(0, "Web Publishing with HTML 4", "Lemay", "HTML", 55000.00);
       // DAOSQLite.createRecord(p, dbPath);
        //p = new Dog(0, "Foundations of Algorithms", "Naimipour", "Math", 35000.00);
        //DAOSQLite.createRecord(p, dbPath);
    }

    /**
     * A helper method that executes a prepared statement and returns the result
     * set as a list of objects.
     *
     * @param conn a connection to the database
     * @param ps a prepared statement
     * @return list of objects from the result set
     */
    protected static List<Dog> myQuery(Connection conn, PreparedStatement ps) {
        List<Dog> list = new ArrayList();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int dogId = rs.getInt("dogId");
                String name = rs.getString("name");
                String species = rs.getString("species");
                String color = rs.getString("color");
                double age = rs.getDouble("age");
                Dog p = new Dog(dogId, name, species, color, age);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Creates a connection to the SQLite database.
     *
     * @param dbPath the path to the SQLite database
     * @return connection to the database
     */
    protected static Connection getConnectionDAO(String dbPath) {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC + ":" + dbPath);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
