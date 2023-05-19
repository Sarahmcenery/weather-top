/**
 * This class contains methods to return the minimum and maximum readings for pressure, wind speed and temperature .
 *
 * @author Sarah Mc Enery
 */

package utils;

import models.Reading;
import models.Station;

import java.util.List;

public class StationAnalytics {

	/**
	 * Converts the latest temperature reading from Celsius to Fahrenheit
	 *
	 * @return The temperature in Fahrenheit
	 */
	public static double celsiusToFahrenheit(List<Reading> readings) {
		if (readings.size() > 0) {
			double temp = readings.get(readings.size() - 1).getTemperature();
			double fahrenheit = (temp * 9 / 5 + 32);
			return fahrenheit;
		} else {
			return 00;
		}
	}

	/**
	 * Initialises an empty object to contain the minimum pressure
	 * Loops through an array and if a lower reading is located that
	 * lower reading is then set to be the minimum pressure.
	 *
	 * @return minimumPressure
	 */
	public static Reading getMinimumPressure(List<Reading> readings) {

		Reading minimumPressure = null;
		if (readings.size() > 0) {
			minimumPressure = readings.get(0);
			for (Reading reading : readings) {
				if (reading.pressure < minimumPressure.pressure) {
					minimumPressure = reading;
				}
			}
		}
		return minimumPressure;
	}

	/**
	 * Initialises an empty object to contain the maximum pressure
	 * Loops through an array and if a higher reading is located that
	 * higher reading is then set to be the maximum pressure.
	 *
	 * @return maximumPressure
	 */
	public static Reading getMaximumPressure(List<Reading> readings) {

		Reading maximumPressure = null;
		if (readings.size() > 0) {
			maximumPressure = readings.get(0);
			for (Reading reading : readings) {
				if (reading.pressure > maximumPressure.pressure) {
					maximumPressure = reading;
				}
			}
		}
		return maximumPressure;
	}

	/**
	 * Initialises an empty object to contain the minimum wind speed
	 * Loops through an array and if a lower reading is located that
	 * lower reading is then set to be the minimum wind speed.
	 *
	 * @return minimumWindSpeed
	 */
	public static Reading getMinimumWindSpeed(List<Reading> readings) {

		Reading minimumWindSpeed = null;
		if (readings.size() > 0) {
			minimumWindSpeed = readings.get(0);
			for (Reading reading : readings) {
				if (reading.windSpeed < minimumWindSpeed.windSpeed) {
					minimumWindSpeed = reading;
				}
			}
		}
		return minimumWindSpeed;
	}

	/**
	 * Initialises an empty object to contain the maximum wind speed
	 * Loops through an array and if a higher reading is located that
	 * higher reading is then set to be the maximum wind speed.
	 *
	 * @return maximumWindSpeed
	 */
	public static Reading getMaximumWindSpeed(List<Reading> readings) {

		Reading maximumWindSpeed = null;
		if (readings.size() > 0) {
			maximumWindSpeed = readings.get(0);
			for (Reading reading : readings) {
				if (reading.windSpeed > maximumWindSpeed.windSpeed) {
					maximumWindSpeed = reading;
				}
			}
		}
		return maximumWindSpeed;
	}

	/**
	 * Initialises an empty object to contain the minimum temperature
	 * Loops through an array and if a lower reading is located that
	 * lower reading is then set to be the minimum temperature.
	 *
	 * @return minimumTemperature
	 */
	public static Reading getMinimumTemperature(List<Reading> readings) {

		Reading minimumTemperature = null;
		if (readings.size() > 0) {
			minimumTemperature = readings.get(0);
			for (Reading reading : readings) {
				if (reading.temperature < minimumTemperature.temperature) {
					minimumTemperature = reading;
				}
			}

			return minimumTemperature;
		} else {
			return null;
		}

	}

	/**
	 * Initialises an empty object to contain the maximum temperature
	 * Loops through an array and if a higher reading is located that
	 * higher reading is then set to be the maximum temperature.
	 *
	 * @return maximumTemperature
	 */
	public static Reading getMaximumTemperature(List<Reading> readings) {

		Reading maximumTemperature = null;
		if (readings.size() > 0) {
			maximumTemperature = readings.get(0);
			for (Reading reading : readings) {
				if (reading.temperature > maximumTemperature.temperature) {
					maximumTemperature = reading;
				}
			}
		}
		return maximumTemperature;
	}

	/**
	 * A switch statement to convert the weather code to the corresponding weather condition
	 */
	public static String codeToText(List<Reading> readings) {
		{
			if (readings.size() > 0) {
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
			} else {
				return "";
			}
		}
	}
}

