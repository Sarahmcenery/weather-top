package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {
	public String name;
	public int code;
	public double temperature;
	public double windSpeed;
	public int pressure;
	public Reading() {
	}
	public Reading(String name, int code, double temperature, double windSpeed, int pressure) {
		this.name = name;
		this.code = code;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
	}

	//****************getters********************

	//returns the name
	public String getName() {
		return name;
	}

	//returns the code
	public int getCode() {
		return code;
	}

	//returns the temperature
	public double getTemperature() {
		return temperature;
	}

	//returns the wind speed
	public double getWindSpeed() {
		return windSpeed;
	}

	//returns the pressure
	public int getPressure() {
		return pressure;
	}

	//****************setters*********************

	//updates the name to the value passed as a parameter.The new name
	public void setName(String name) {
		this.name = name;
	}

	//updates the code to the value passed as a parameter.The new code
	public void setCode(int code) {
		this.code = code;
	}

	//updates the temperature to the value passed as a parameter.The new temperature
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	//updates the wind speed to the value passed as a parameter.The new wind speed
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	//updates the pressure to the value passed as a parameter.The new pressure
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

}
