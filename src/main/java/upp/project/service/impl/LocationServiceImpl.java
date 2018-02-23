package upp.project.service.impl;

import org.springframework.stereotype.Service;

import upp.project.model.Location;
import upp.project.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	@Override
	public Location bindLocation(String address, String postalCode, String place, String username) {
		Location location = new Location();
		//Neka logika
		
		
		return location;
	}

}
