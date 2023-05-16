package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {
	public String name;
	public int code;
	public double temperature;
	public double windSpeed;

	public Reading(String name, int code, double temperature, double windSpeed) {
		this.name = name;
		this.code = code;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
	}
}
