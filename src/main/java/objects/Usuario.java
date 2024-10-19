package objects;

import java.util.Arrays;
import java.util.Objects;

public class Usuario {
	
	
	private String name;
	private String surname;
	private String birthday;
	private String password;
	private String email;
	private boolean registered;
	private Integer level;
	private boolean isTrainer; 
	//its called ref_workouts in firebase.

	private String[] ref_workouts;
	private String[] wk_history;
	

	public Usuario(String name, String surname, String birthday, String password, String email, boolean registered,
			Integer level, boolean isTrainer, String[] ref_workouts, String[] wk_history) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.password = password;
		this.email = email;
		this.registered = registered;
		this.level = level;
		this.isTrainer = isTrainer;
		this.ref_workouts = ref_workouts;
		this.wk_history = wk_history;
	}
	public Usuario() {
		super();
		this.name = "";
		this.surname = "";
		this.birthday = "";
		this.password = "";
		this.email = "";
		this.registered = false;
		this.level = 0;
		this.isTrainer = false;
	
		this.ref_workouts = null;
		this.wk_history = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthday() {
		return birthday;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public boolean isTrainer() {
		return isTrainer;
	}
	public void setTrainer(boolean isTrainer) {
		this.isTrainer = isTrainer;
	}
	public String[] getRef_workouts() {
		return ref_workouts;
	}
	public void setRef_workouts(String[] ref_workouts) {
		this.ref_workouts = ref_workouts;
	}
	public String[] getWk_history() {
		return wk_history;
	}
	public void setWk_history(String[] wk_history) {
		this.wk_history = wk_history;
	}
	@Override
	public String toString() {
		return "Usuario [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", password=" + password
				+ ", email=" + email + ", registered=" + registered + ", level=" + level + ", isTrainer=" + isTrainer
				+ ", ref_workouts=" + Arrays.toString(ref_workouts) + ", wk_history=" + Arrays.toString(wk_history)
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ref_workouts);
		result = prime * result + Arrays.hashCode(wk_history);
		result = prime * result + Objects.hash(birthday, email, isTrainer, level, name, password, registered, surname);
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
		Usuario other = (Usuario) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& isTrainer == other.isTrainer && Objects.equals(level, other.level)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Arrays.equals(ref_workouts, other.ref_workouts) && registered == other.registered
				&& Objects.equals(surname, other.surname) && Arrays.equals(wk_history, other.wk_history);
	}

	
	
	

}
