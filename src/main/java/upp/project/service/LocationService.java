package upp.project.service;

import upp.project.model.Location;

public interface LocationService {

	Location bindLocation(String address, String postalCode, String place, String username);
}
