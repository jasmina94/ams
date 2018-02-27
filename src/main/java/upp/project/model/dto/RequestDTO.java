package upp.project.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDTO {

	private String kategorija;
	
	private String opis;
	
	private double procenjenaVrednost;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rokPrijema;
	
	private int minBrojPonuda;
	
	private int maxBrojPonuda;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rokIzvrsenja;
	
	private String requestMaker;//username preko kojeg cemo traziti lokaciju
}
