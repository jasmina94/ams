package upp.project.util;

import java.util.Map;

public class Validator {
	
	//Fali za email i za tipove
	public static boolean validRegistrationForm(Map<String, String> params){
		boolean valid = true;
		boolean ime = checkField(params, "ime");
		boolean prezime = checkField(params, "prezime");
		boolean email = checkField(params, "email");
		boolean username = checkField(params, "username");
		boolean password = checkField(params, "password");
		boolean mesto = checkField(params, "mesto");
		boolean adresa = checkField(params, "adresa");
		boolean ptt = checkField(params, "ptt");
		boolean tip = checkTip(params);
		
		if(!tip || !ptt || !adresa || !mesto || !password || !username || !email || !prezime || !ime){
			valid = false;
		}
		
		return valid;
	}
	
	//Proveri da li je udaljenost broj
	public static boolean validAgentGorm(Map<String, String> params){
		boolean valid = true;
		boolean naziv = checkField(params, "naziv");
		boolean kategorija = checkField(params, "kategorija"); //proveriti da nije mozda neki pogresan broj tj id.
		boolean udaljenost = checkField(params, "udaljenost");
		
		if(!naziv || !kategorija || !udaljenost){
			valid = false;
		}
		return valid;
	}
	
	private static boolean checkTip(Map<String, String> params){
		boolean valid = true;
		if(!params.keySet().contains("tip")){
			valid = false;
		}else {
			String value = params.get("tip");
			if(!value.equals("pravno") && !value.equals("fizicko")){
				valid = false;
			}
		}		
		return valid;
	}
	
	private static boolean checkField(Map<String, String> params, String field){
		boolean valid = true;
		if(!params.keySet().contains(field)){
			valid = false;
		}else {
			String value = params.get(field);
			if(value.isEmpty() || value == null){
				valid = false;
			}
		}
		return valid;
	}
	

}
