package baic.base.service.dao.bean;

import static javax.persistence.GenerationType.IDENTITY;

// Generated 2015-10-13 15:43:18 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Device generated by hbm2java
 */
@Entity
@Table(name = "admin", catalog = "baic_base")
@NamedQueries(value = {
		@NamedQuery(name = Admin.FIND_ADMIN_BY_NAME, query = "FROM Admin a WHERE a.username = :username"),
		@NamedQuery(name = Admin.FIND_ADMIN_BY_DEPARTMENT_ID, query = "FROM Admin a WHERE a.belongDepartment = :departmentId") })
public class Admin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7432737716733045822L;

	public static final String FIND_ADMIN_BY_NAME = "findAdminByName";
	public static final String FIND_ADMIN_BY_DEPARTMENT_ID = "findAdminByDepartmentId";

	private Long adminId;
	private String username;
	private String password;
	private String realname;
	private String email;
	private String phone;
	private Long belongDepartment;

	public Admin() {
	}

	public Admin(Long adminId, String username, String password, String realname, String email, String phone,
			Long belongDepartment) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.email = email;
		this.phone = phone;
		this.belongDepartment = belongDepartment;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "admin_id", unique = true, nullable = false)
	public Long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "realname", length = 45)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "belong_department")
	public Long getBelongDepartment() {
		return belongDepartment;
	}

	public void setBelongDepartment(Long belongDepartment) {
		this.belongDepartment = belongDepartment;
	}

}
