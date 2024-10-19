package objects;

import java.util.ArrayList;
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
	private Workout workout;
	private ArrayList<Workout> wk_history;
	
	public Usuario(String name, String surname, String birthday, String password, String email, boolean registered,
			Integer level, boolean isTrainer, Workout workout, ArrayList<Workout> wk_history) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.password = password;
		this.email = email;
		this.registered = registered;
		this.level = level;
		this.isTrainer = isTrainer;
		this.workout = workout;
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
		this.level = -1;
		this.isTrainer = false;
		this.workout = null;
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
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public ArrayList<Workout> getWk_history() {
		return wk_history;
	}
	public void setWk_history(ArrayList<Workout> wk_history) {
		this.wk_history = wk_history;
	}
	@Override
	public String toString() {
		return "Usuario [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", password=" + password
				+ ", email=" + email + ", registered=" + registered + ", level=" + level + ", isTrainer=" + isTrainer
				+ ", workout=" + workout + ", wk_history=" + wk_history + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, isTrainer, level, name, password, registered, surname, wk_history,
				workout);
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
				&& registered == other.registered && Objects.equals(surname, other.surname)
				&& Objects.equals(wk_history, other.wk_history) && Objects.equals(workout, other.workout);
	}
	

}
