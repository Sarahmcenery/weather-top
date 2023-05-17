package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
	public static void index() {
		Logger.info("Rendering Dashboard");
		Member member = Accounts.getLoggedInMember();
		List<Station> stations = member.stations;
		render("dashboard.html", member, stations);
	}

	public static void addStation(String name, double latitude, double longitude) {
		Station station = new Station(name, latitude, longitude);
		station.save();
		Logger.info("Adding a new station called " + name);
		redirect("/dashboard");
	}
}

