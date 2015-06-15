package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

import dao.Dao;

public class ListEmployeesController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    private static String INSERT_OR_EDIT = "/WEB-INF/views/employee.jsp";
    private static String LIST_EMPLOYEE = "/WEB-INF/views/listEmployees.jsp";
        
    private Dao dao;

    public ListEmployeesController() {
        super();
        dao = new Dao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String forward="";
    	String action = request.getParameter("action");
    	
    	if (action.equalsIgnoreCase("listEmployees")) {
            forward = LIST_EMPLOYEE;
            request.setAttribute("employees", dao.getAllEmployees());
        }

    	RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String[] selectedRows = request.getParameterValues("selectedRows");
    	
    	if (request.getParameter("Refresh") != null) {
    		// do nothing here. refresh is in the end of doPost
    	} else if (request.getParameter("Delete") != null) {
    		if (selectedRows != null) {
    			for (String selectedRow : selectedRows) {
            		try {
            			int id = Integer.parseInt(selectedRow);
            			dao.deleteEmployee(id);
            		} catch (NumberFormatException e) {
         	           e.printStackTrace();
            	    }
            	}	
    		}
        } else if (request.getParameter("Edit") != null) {
        	int length = (selectedRows != null) ? selectedRows.length : 0;
        	if (length > 1) {
        		request.setAttribute("error","Please, select just one row to be edited.");
        	} else {
        		Employee employee;
        		if (length == 1) {
        			int id = Integer.parseInt(selectedRows[0]);
        			employee = dao.getEmployeeById(id);
        		} else {
        			employee = new Employee();
        		}
        		request.setAttribute("employee", employee);
        		RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        		view.forward(request, response);
        		return;
        	}
        } else {
        	// another action
        }
        
    	// refresh
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPLOYEE);
        request.setAttribute("employees", dao.getAllEmployees());
        view.forward(request, response);
    }
}