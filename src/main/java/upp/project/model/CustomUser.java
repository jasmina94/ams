package upp.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 26/02/2018.
 */
@Entity
@Data
@NoArgsConstructor
public class CustomUser {

	public enum Type {
        PRAVNO,
        FIZICKO,
    }
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String adresa;
	
	@Column
	private String mesto;
	
	@Column
	private String ptt;
	
	@Enumerated(EnumType.STRING)
	private Type tip;
	
	@Column
	private boolean isActive;
	
	@ManyToOne
	private Company company; //ovo polje ako je agent
}
