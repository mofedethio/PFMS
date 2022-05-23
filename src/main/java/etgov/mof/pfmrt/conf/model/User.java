package etgov.mof.pfmrt.conf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;



@Entity 
@Table(name="users")
public class User {

	/**
	 * 
	 * serialversionUID is used to version serialized data 
	 * Serialization is a mechanism of converting the state of an object into byte stream
	 * advantage of serialization is used to save and persist state of an object 
	 * the other advantage is to travel an object across the network  
	 */
	//private static final long serialVersionUID = 1671417246199538663L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	
	@Column
	private String username;
	@Column
	private String password;
	
	@Transient
	private String confirmPassword;
	
	private boolean status;
	@Transient
	private String userStatus;
	
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user", orphanRemoval=true)
	private List<MasterTransaction> trans = new ArrayList<MasterTransaction>();
	
	
	@ManyToOne
	@JoinColumn(name="org_id",insertable =false,updatable=false)
	private Organization organization;
	private String org_id;
	
	@ManyToOne
	@JoinColumn(name="user_dir_name",insertable =false,updatable=false)
	private Directorate directorate;
	private String user_dir_name;
	
    //user constructor with no argument 
	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	public void setUserStatus() {
        if(status == true) {
            userStatus = "Active";
        }
        else if(status == false) {
            userStatus = "Inactive";
        }
    }
    
    public String getUserStatus() {
        return userStatus;
    }
    

	public List<MasterTransaction> getTrans() {
		return trans;
	}

	public void setTrans(List<MasterTransaction> trans) {
		this.trans = trans;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Directorate getDirectorate() {
		return directorate;
	}

	public void setDirectorate(Directorate directorate) {
		this.directorate = directorate;
	}

	public String getUser_dir_name() {
		return user_dir_name;
	}

	public void setUser_dir_name(String user_dir_name) {
		this.user_dir_name = user_dir_name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", status=" + status + ", roles="
				+ roles + ", trans=" + trans + ", organization=" + organization + ", org_id=" + org_id
				+ ", directorate=" + directorate + ", user_dir_name=" + user_dir_name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((org_id == null) ? 0 : org_id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((trans == null) ? 0 : trans.hashCode());
		result = prime * result + ((user_dir_name == null) ? 0 : user_dir_name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (org_id == null) {
			if (other.org_id != null)
				return false;
		} else if (!org_id.equals(other.org_id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (status != other.status)
			return false;
		if (trans == null) {
			if (other.trans != null)
				return false;
		} else if (!trans.equals(other.trans))
			return false;
		if (user_dir_name == null) {
			if (other.user_dir_name != null)
				return false;
		} else if (!user_dir_name.equals(other.user_dir_name))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	
	
	
	

	
	
}
