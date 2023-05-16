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
	@OneToMany(cascade = CascadeType.ALL)
	public List<Reading> readings = new ArrayList<Reading>();
	public int code;
	public double windSpeed;
	public double temperature;
	public int pressure;
	public int latestCode;
	public double latestTemp;

	public Station(String name, int code, double temperature, double windSpeed, int pressure) {
		this.name = name;
		this.code = code;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
	}

	//methods to get the latest weather readings from yaml.
	public double lastTemp(double temperature) {
		double temp = readings.get(readings.size() - 1).getTemperature();
		return temp;
	}

	public int lastPressure(int pressure) {
		int latestPressure = readings.get(readings.size() - 1).getPressure();
		return latestPressure;
	}

	public int lastCode(int code) {
		int latestCode = readings.get(readings.size() - 1).getCode();
		return latestCode;
	}
	public double lastWind(double windSpeed) {
		double latestWind = readings.get(readings.size() - 1).getWindSpeed();
		return latestWind;
	}

	public int latestCode() {
		return lastCode(this.latestCode);
	}

	public double temp() {
		return lastTemp(this.latestTemp);
	}

	public int latestPressure() {return lastPressure(this.pressure);}

	public double latestWind() {
		return lastWind(this.windSpeed);
	}

	public double fahrenheit() {
		return celsiusToFahrenheit(this.latestTemp);
	}

	//Convert temperature from c to f
	public double celsiusToFahrenheit(double temperature) {
		double temp = readings.get(readings.size() - 1).getTemperature();
		double fahrenheit = (temp * 9 / 5 + 32);
		return fahrenheit;
	}

	//switch statement to convert the weather code to the latest weather condition
	public String codeToText() {
		{
			switch ((readings.get(readings.size() - 1).getCode())) {
				case 100:
					return "Clear";
				case 200:
					return "Partial Clouds";
				case 300:
					return "Cloudy";
				case 400:
					return "Light Showers";
				case 500:
					return "Heavy Showers";
				case 600:
					return "Rain";
				case 700:
					return "Snow";
				case 800:
					return "Thunder";
				default:
					return "Invalid Code";
			}
		}
	}

	//converts the latest wind speed in km/h to the correct number on the beaufort scale
	public String kmToBeaufort() {
		String codeString = "Invalid Reading. Please enter a valid wind speed.";
		if (latestWind() == 1) {
			return ("0 bft - Calm");
		} else if (latestWind() >= 1 && latestWind() <= 5) {
			return ("1 bft - Light Air");
		} else if (latestWind() >= 6 && latestWind() <= 11) {
			return ("2 bft - Light Breeze");
		} else if (latestWind() >= 12 && latestWind() <= 19) {
			return ("3 bft - Gentle Breeze");
		} else if (latestWind() >= 20 && latestWind() <= 28) {
			return ("4 bft - Moderate Breeze");
		} else if (latestWind() >= 29 && latestWind() <= 38) {
			return ("5 bft - Fresh Breeze");
		} else if (latestWind() >= 39 && latestWind() <= 49) {
			return ("6 bft - Strong Breeze");
		} else if (latestWind() >= 50 && latestWind() <= 61) {
			return ("7 bft - Near Gale");
		} else if (latestWind() >= 62 && latestWind() <= 74) {
			return ("8 bft - Gale");
		} else if (latestWind() >= 75 && latestWind() <= 88) {
			return ("9 bft - Severe Gale");
		} else if (latestWind() >= 89 && latestWind() <= 102) {
			return ("10 bft - Strong Storm");
		} else if (latestWind() >= 103 && latestWind() <= 117) {
			return ("11 bft - Violent Storm");
		}
		return codeString;
	}
}


