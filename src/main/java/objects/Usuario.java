package objects;

import java.util.Objects;

public class Usuario {
	
	private String user;
	private String name;
	private String password;
	private String surname;
	private boolean registered; 
	private boolean isTrainer; 
	//its called ref_workouts in firebase.
	private String workout;
	
	
	


	public Usuario(String user, String name, String password, String surname, boolean registered, boolean isTrainer,
			String workout) {
		super();
		this.user = user;
		this.name = name;
		this.password = password;
		this.surname = surname;
		this.registered = registered;
		this.isTrainer = isTrainer;
		this.workout = workout;
	}
	public Usuario() {
		super();
		this.name = "name";
		this.password = "NOpassword";
		this.surname = "surname";
		this.registered = false;
		this.isTrainer = false;

		this.user = "user";
		this.workout = null;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public boolean isTrainer() {
		return isTrainer;
	}
	public void setTrainer(boolean isTrainer) {
		this.isTrainer = isTrainer;
	}
	public String getWorkout() {
		return workout;
	}
	public void setWorkout(String workout) {
		this.workout = workout;
	}
	@Override
	public String toString() {
		return "Usuario [user=" + user + ", name=" + name + ", password=" + password + ", surname=" + surname
				+ ", registered=" + registered + ", isTrainer=" + isTrainer + ", workout=" + workout + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(isTrainer, name, password, registered, surname, user, workout);
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
		return isTrainer == other.isTrainer && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && registered == other.registered
				&& Objects.equals(surname, other.surname) && Objects.equals(user, other.user)
				&& Objects.equals(workout, other.workout);
	}

	
	
}
