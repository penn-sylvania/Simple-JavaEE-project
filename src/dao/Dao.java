package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import util.DbUtil;

public class Dao {

    private Connection connection;

    public Dao() {
        connection = DbUtil.getConnection();
    }

    public void addEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into employees (LName, FName, Gender, JobCode, " +
                    		"Salary, BirthDate) values (?, ?, ?, ?, ?, ?)");
            
            preparedStatement.setString(1, employee.getLname());
            preparedStatement.setString(2, employee.getFname());
            preparedStatement.setString(3, employee.getGender().toString());
            preparedStatement.setString(4, employee.getJobcode());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setDate(6, new java.sql.Date(employee.getBirthdate().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from employees where ID=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection
            		.prepareStatement("alter table employees auto_increment=1");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update employees set LName=?, FName=?, " +
                    		"Gender=?, JobCode=?, Salary=?, BirthDate=? where ID=?");
            preparedStatement.setString(1, employee.getLname());
            preparedStatement.setString(2, employee.getFname());
            preparedStatement.setString(3, employee.getGender().toString());
            preparedStatement.setString(4, employee.getJobcode());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setDate(6, new java.sql.Date(employee.getBirthdate().getTime()));
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from employees");
            while (rs.next()) {
            	Employee employee = new Employee();
            	employee.setId(rs.getInt("ID"));
            	employee.setLname(rs.getString("LName"));
            	employee.setFname(rs.getString("FName"));
            	employee.setGender(rs.getString("Gender").charAt(0));
            	employee.setJobcode(rs.getString("JobCode"));
            	employee.setSalary(rs.getDouble("Salary"));
            	employee.setBirthdate(rs.getDate("BirthDate"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
    	Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from employees where ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	employee.setId(rs.getInt("ID"));
            	employee.setLname(rs.getString("LName"));
            	employee.setFname(rs.getString("FName"));
            	employee.setGender(rs.getString("Gender").charAt(0));
            	employee.setJobcode(rs.getString("JobCode"));
            	employee.setSalary(rs.getDouble("Salary"));
            	employee.setBirthdate(rs.getDate("BirthDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
