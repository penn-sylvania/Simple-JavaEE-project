package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Employee;

public class EmployeeController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    private static String INSERT_OR_EDIT = "/WEB-INF/views/employee.jsp";
    private static String LIST_EMPLOYEE = "/WEB-INF/views/listEmployees.jsp";
    
    private static final Date startDate, endDate;
    static {
    	Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -100);
        startDate = c.getTime();
        System.out.println(startDate);
        c.setTime(new Date());
        c.add(Calendar.YEAR, -18);
        endDate = c.getTime();
        System.out.println(endDate);
    }
    
    private Dao dao;

    public EmployeeController() {
        super();
        dao = new Dao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	Employee employee = new Employee();
    	try {
    		String idString = request.getParameter("ID");
    		Integer id = null;
    		if (!idString.isEmpty()) {
    			id = Integer.parseInt(idString);
    		}
	    	employee.setId(id);
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    }
	    employee.setLname(request.getParameter("LName"));
	    employee.setFname(request.getParameter("FName"));
	    employee.setGender(request.getParameter("Gender").charAt(0));
	    employee.setJobcode(request.getParameter("JobCode"));
	    try {
	    	Double salary = Double.parseDouble(
	    			request.getParameter("Salary"));
	    	employee.setSalary(salary);
	    } catch (NumberFormatException e) {
	    	e.printStackTrace();
	    }
	    try {
	        Date bd = new SimpleDateFormat("dd/MM/yyyy")
	            .parse(request.getParameter("BirthDate"));
	        employee.setBirthdate(bd);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    String forward = LIST_EMPLOYEE;
	    if(request.getParameter("Add") != null) {
	    	// check input errors
	    	String inputError = checkEmployee(employee);
	    	if (inputError == null) {
	    		dao.addEmployee(employee);
	    	} else {
	    		// show error message
	    		request.setAttribute("inputError", inputError);
	    		// stay here
	    		forward = INSERT_OR_EDIT;
	    	}
	    } else if (request.getParameter("Save") != null) {
	    	employee.setId(Integer.parseInt(request.getParameter("ID")));
	    	// check input errors
	    	String inputError = checkEmployee(employee);
	    	if (inputError == null) {
		    	try {
			        dao.updateEmployee(employee);
		    	} catch (NumberFormatException e) {
		    		e.printStackTrace();
			    }
	    	} else {
	    		// show error message
	    		request.setAttribute("inputError", inputError);
	    		// stay here
	    		forward = INSERT_OR_EDIT;
	    	}
	    } else if (request.getParameter("Cancel") != null) {
	    	// do nothing here. refresh is in the end of doPost
	    }
    	
	    // go back and refresh
        RequestDispatcher view = request.getRequestDispatcher(forward);
        if (forward == LIST_EMPLOYEE) {
        	request.setAttribute("employees", dao.getAllEmployees());
        } else if (forward == INSERT_OR_EDIT) {
        	request.setAttribute("employee", employee);
        }
        view.forward(request, response);
    }
    
    private String checkEmployee(Employee employee) {
    	if (employee.getFname() == null || employee.getFname().isEmpty()) {
    		System.out.println("Please, enter first name.");
    		return "Please, enter first name.";
    	}
    	if (employee.getLname() == null || employee.getLname().isEmpty()) {
    		System.out.println("Please, enter last name.");
    		return "Please, enter last name.";
    	}
    	Date bd = employee.getBirthdate();
    	if (bd == null || bd.before(startDate) || bd.after(endDate)) {
    		System.out.println("Please, enter valid date of birth.");
    		return "Please, enter valid date of birth.";
    	}
    	
    	return null;
    }
}
