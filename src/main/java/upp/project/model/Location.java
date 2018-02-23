package upp.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 23/02/2018.
 */
@Entity
@Data
@NoArgsConstructor
public class Location {

	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	private double longitude;
	
	@Column
	private double latitude;
	
	@Column
	private String user;
}
