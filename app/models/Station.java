package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
	public String name;
	public int code;
	@OneToMany(cascade = CascadeType.ALL)
	public List<Reading> readings = new ArrayList<Reading>();
	public double windSpeed;
	public double temperature;

	public Station(String name, int code, double temperature, double windSpeed) {
		this.name = name;
		this.code = code;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
	}
}

