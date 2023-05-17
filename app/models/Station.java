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
	public double windDirection;
	public int latestCode;
	public double latestTemp;
	public double celsiusToFahrenheit;

	public Station(String name) {
		this.name = name;
	}

	//methods to get the latest weather readings
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

	public double lastWindSpeed(double windSpeed) {
		double latestWindSpeed = readings.get(readings.size() - 1).getWindSpeed();
		return latestWindSpeed;
	}

	public double lastWindDirection(double windDirection) {
		double latestWindDirection = readings.get(readings.size() - 1).getWindDirection();
		return latestWindDirection;
	}

	public double latestWindDirection() {
		return lastWindDirection(this.windDirection);
	}

	public double temp() {
		return lastTemp(this.latestTemp);
	}

	public int latestPressure() {
		return lastPressure(this.pressure);
	}

	public double latestWindSpeed() {
		return lastWindSpeed(this.windSpeed);
	}

	public double fahrenheit() {
		return celsiusToFahrenheit(this.latestTemp);
	}

	public double windChill() {
		return convertWind(this.windSpeed, this.temperature);
	}

	public int latestCode() {
		return lastCode(this.latestCode);
	}

	private double toTwoDecimalPlaces(double num) {
		return (int) (num * 100) / 100.0;
	}

	//Converts temperature from c to f
	public double celsiusToFahrenheit(double temperature) {
		double temp = readings.get(readings.size() - 1).getTemperature();
		double fahrenheit = (temp * 9 / 5 + 32);
		return fahrenheit;
	}

	//Calculates windchill using the latest readings for both temperature and wind speed
	public double convertWind(double windSpeed, double temperature) {
		double temp = readings.get(readings.size() - 1).getTemperature();
		double latestWind = readings.get(readings.size() - 1).getWindSpeed();
		double windChill = (13.12 + (0.6215 * (temp)) - 11.37 * Math.pow(latestWind, 0.16) + 0.3965 * (temp * Math.pow(latestWind, 0.16)));
		return toTwoDecimalPlaces(windChill);
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
		if (latestWindSpeed() == 1) {
			return ("0 bft - Calm");
		} else if (latestWindSpeed() >= 1 && latestWindSpeed() <= 5) {
			return ("1 bft - Light Air");
		} else if (latestWindSpeed() >= 6 && latestWindSpeed() <= 11) {
			return ("2 bft - Light Breeze");
		} else if (latestWindSpeed() >= 12 && latestWindSpeed() <= 19) {
			return ("3 bft - Gentle Breeze");
		} else if (latestWindSpeed() >= 20 && latestWindSpeed() <= 28) {
			return ("4 bft - Moderate Breeze");
		} else if (latestWindSpeed() >= 29 && latestWindSpeed() <= 38) {
			return ("5 bft - Fresh Breeze");
		} else if (latestWindSpeed() >= 39 && latestWindSpeed() <= 49) {
			return ("6 bft - Strong Breeze");
		} else if (latestWindSpeed() >= 50 && latestWindSpeed() <= 61) {
			return ("7 bft - Near Gale");
		} else if (latestWindSpeed() >= 62 && latestWindSpeed() <= 74) {
			return ("8 bft - Gale");
		} else if (latestWindSpeed() >= 75 && latestWindSpeed() <= 88) {
			return ("9 bft - Severe Gale");
		} else if (latestWindSpeed() >= 89 && latestWindSpeed() <= 102) {
			return ("10 bft - Strong Storm");
		} else if (latestWindSpeed() >= 103 && latestWindSpeed() <= 117) {
			return ("11 bft - Violent Storm");
		}
		return codeString;
	}

	//converts the latest wind direction reading to a compass direction
	public String windDirectionToCompass() {

		String codeString = "Invalid Reading";
		{
			if (latestWindDirection() >= 0 && latestWindDirection() < 11.25) {
				return ("North");
			} else if (latestWindDirection() >= 348.75 && latestWindDirection() <= 360.00) {
				return ("North");
			} else if (latestWindDirection() >= 11.25 && latestWindDirection() < 33.75) {
				return ("North North East");
			} else if (latestWindDirection() >= 33.75 && latestWindDirection() < 56.25) {
				return ("North East");
			} else if (latestWindDirection() >= 56.25 && latestWindDirection() < 78.75) {
				return ("East North East");
			} else if (latestWindDirection() >= 78.75 && latestWindDirection() < 101.25) {
				return ("East");
			} else if (latestWindDirection() >= 101.25 && latestWindDirection() < 123.75) {
				return ("East South East");
			} else if (latestWindDirection() >= 123.75 && latestWindDirection() < 146.25) {
				return ("South East");
			} else if (latestWindDirection() >= 146.25 && latestWindDirection() < 168.75) {
				return ("South South East");
			} else if (latestWindDirection() >= 168.75 && latestWindDirection() < 191.25) {
				return ("South");
			} else if (latestWindDirection() >= 191.25 && latestWindDirection() < 213.75) {
				return ("South South West");
			} else if (latestWindDirection() >= 213.75 && latestWindDirection() < 236.25) {
				return ("South West");
			} else if (latestWindDirection() >= 236.25 && latestWindDirection() < 258.75) {
				return ("West South West");
			} else if (latestWindDirection() >= 258.75 && latestWindDirection() < 281.25) {
				return ("West");
			} else if (latestWindDirection() >= 281.25 && latestWindDirection() < 303.75) {
				return ("West North West");
			} else if (latestWindDirection() >= 303.75 && latestWindDirection() < 326.25) {
				return ("North West");
			} else if (latestWindDirection() >= 326.25 && latestWindDirection() < 348.75) {
				return ("North North West");
			}
			return codeString;
		}
	}
}


