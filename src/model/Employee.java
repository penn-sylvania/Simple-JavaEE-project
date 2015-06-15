package model;

import java.util.Date;

public class Employee {
		
	private Integer id;
	
	private String lname;	
	
	private String fname;
	
	private Character gender;
	
	private String jobcode;
	
	private Double salary;
	
	private Date birthdate;

    public Employee() {
    	this.id = null;
    	this.lname = "";
    	this.fname = "";
    	this.gender = 'M';
    	this.jobcode = "";
    	this.salary = 0.0;
    	this.birthdate = new Date();
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getJobcode() {
		return this.jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Date getBirthdate() {
    	return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	 @Override
	 public int hashCode() {
	        int hash = 0;
	        hash += (id != null ? id.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        if (!(object instanceof Employee)) {
	            return false;
	        }
	        Employee other = (Employee) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "entity.Employee[ id=" + id + " ]";
	    }   

}
