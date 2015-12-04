package servlets;

import datastore.DAOSQLite;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dog;

/**
 * All of this application's web pages send their requests to this controller
 * which then updates the model / database as needed and transfers control with
 * data to one the the HTML/JSP view-oriented programs for display.
 *
 * @author John Phillips
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get real path to the sqlite db
        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/dog.db");

        // set default url
        String url = "/home.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            url = "/home.html";

        } else if (action.equalsIgnoreCase("createRecord")) {
            double age;

            // get parameters passed in from the request
            String name = request.getParameter("name");
            String species = request.getParameter("species");
            String color = request.getParameter("color");
            String ageString = request.getParameter("age");

            // validate and convert isbn string into a double
            if (ageString == null || ageString.isEmpty()) {
                age = 0;
            } else {
                age = Double.parseDouble(ageString);
            }

            // store data in an Employee object
            Dog dog = new Dog(0, name, species, color, age);

            // validate the parameters
            if (name == null || species == null || color == null
                    || ageString == null || name.isEmpty()
                    || species.isEmpty() || color.isEmpty()
                    || ageString.isEmpty()) {
                url = "/createRecord.jsp";
            } else {
                // insert this data record into the database
                DAOSQLite.createRecord(dog, dbPath);
                url = "/home.html";
            }

            // add the user object to the request object so that the data is available on the next page
            request.setAttribute("employee", dog);
            
        } else if (action.equalsIgnoreCase("report")) {
            List<Dog> mydata = null;
            mydata = DAOSQLite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            url = "/displayRecords.jsp";

        } else if (action.equalsIgnoreCase("updateRecord1")) {
            String idString = request.getParameter("dogId");
            if (idString == null || idString.isEmpty()) {
                url = "/updateRecord1.jsp";
            } else {
                // get employee
                Dog dog = DAOSQLite.retrieveRecordById(Integer.parseInt(idString), dbPath);
                request.setAttribute("dog", dog);
                url = "/updateRecord2.jsp";
            }

        } else if (action.equalsIgnoreCase("updateRecord2")) {
            int dogId;
            double age;

            // get parameters passed in from the request
            String dogIdString = request.getParameter("dogId");
            String name = request.getParameter("name");
            String species = request.getParameter("species");
            String color = request.getParameter("color");
            String ageString = request.getParameter("age");

            // validate and convert empId string into an int
            if (dogIdString == null || dogIdString.isEmpty()) {
                dogId = 0;
            } else {
                dogId = Integer.parseInt(dogIdString);
            }

            // validate and convert isbn string into a double
            if (ageString == null || ageString.isEmpty()) {
                age = 0;
            } else {
                age = Double.parseDouble(ageString);
            }

            // store data in an Employee object
            Dog dog = new Dog(dogId, name, species, color, age);

            // validate the parameters
            if (dogIdString == null || name == null || species == null
                    || color == null || ageString == null
                    || dogIdString.isEmpty() || name.isEmpty()
                    || species.isEmpty() || color.isEmpty()
                    || ageString.isEmpty()) {
                request.setAttribute("dog", dog);
                url = "/updateRecord2.jsp";
            } else {
                // update this record in the database
                DAOSQLite.updateRecord(dog, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            String idString = request.getParameter("id");
            if (idString == null || idString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                // delete this data record from the database
                DAOSQLite.deleteRecord(Integer.parseInt(idString), dbPath);
                url = "/home.html";
            }
            
        } else if (action.equalsIgnoreCase("makeDB")) {
            DAOSQLite.dropTable(dbPath);
            DAOSQLite.createTable(dbPath);
            DAOSQLite.populateTable(dbPath);
            url = "/home.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller for Employee App";
    }// </editor-fold>

}
