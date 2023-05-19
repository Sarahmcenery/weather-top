package controllers;

import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;


public class StationCtrl extends Controller {
	public static void index(Long id) {

		Station station = Station.findById(id);
		Logger.info("Station id =" + id);
		Reading minimumPressure = StationAnalytics.getMinimumPressure(station.readings);
		Reading maximumPressure = StationAnalytics.getMaximumPressure(station.readings);
		Reading minimumWindSpeed = StationAnalytics.getMinimumWindSpeed(station.readings);
		Reading maximumWindSpeed = StationAnalytics.getMaximumWindSpeed(station.readings);
		Reading minimumTemperature = StationAnalytics.getMinimumTemperature(station.readings);
		Reading maximumTemperature = StationAnalytics.getMaximumTemperature(station.readings);
		String codeToText = StationAnalytics.codeToText(station.readings);
		Double fahrenheit = StationAnalytics.celsiusToFahrenheit(station.readings);
		render("station.html", station, minimumPressure, maximumPressure, minimumWindSpeed, maximumWindSpeed, minimumTemperature, maximumTemperature, fahrenheit, codeToText);
	}

	public static void addReading(Long id, String name, int code, double temperature, double windSpeed, int pressure, int windDirection, double latitude, double longitude) {
		Reading reading = new Reading(name, code, temperature, windSpeed, pressure, windDirection, latitude, longitude);
		Station station = Station.findById(id);
		station.readings.add(reading);
		station.save();
		redirect("/stations/" + id);
	}

}
