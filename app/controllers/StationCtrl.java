package controllers;

import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;


public class StationCtrl extends Controller {
	public static void index(Long id) {
		Station station = Station.findById(id);
		Logger.info("Station id =" + id);
		render("station.html", station);
	}

	public static void addReading(Long id, String name, int code, double temperature, double windSpeed, int pressure, int windDirection, double latitude, double longitude) {
		Reading reading = new Reading(name, code, temperature, windSpeed, pressure, windDirection, latitude, longitude);
		Station station = Station.findById(id);
		station.readings.add(reading);
		station.save();
		redirect("/stations/" + id);
	}
}
