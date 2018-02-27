INSERT INTO `aukcije`.`job_category`(`name`) VALUES("Usluge smeštaja i ishrane"), ("Informisanje i komunikacije"), ("Saobraćaj i skladištenje"), ("Trgovina"), ("Umetnost, zabava i rekreacija");

INSERT INTO `aukcije`.`company` (`name`,`address`,`postal_code`,`place`,`max_distance`, `job_category_id`) VALUES ("Restoran Canzona", "Janka Čmelika 24", "21000", "Novi Sad", 80.0, 1), ("Novalite", "Pap Pavla 11", "21000", "Novi Sad", 2000.0, 2), ("Naovis", "Bulevar oslobođenja 24", "21000", "Novi Sad", 100.0, 2), ("Telekom Srbija", "Takovska 2", "11000", "Beograd", 600.0, 2), ("Telenor", "Omladinskih brigada 82", "11000", "Beograd", 700.0, 2), ("Lasta", "Milovana Milovanovića 4", "11000", "Beograd", 3000.0, 3), ("Arriva Travel", "Đure Đakovića 3", "12000", "Požarevac", 1800.0, 3), ("Niš ekspres", "Čamurlija 160", "18000", "Niš", 2000.0, 3), ("Put", "Kralja Petra 1", "35000", "Jagodina", 1800.0, 3), ("InterKop", "Bulevar Milutina Milankovića 23", "11000", "Beograd", 2500.0, 3);

INSERT INTO `aukcije`.`custom_user`(`adresa`,`email`,`firstname`,`is_active`,`lastname`,`mesto`,`password`,`ptt`,`tip`,`username`,`company_id`)VALUES ("Takovska 2","pera@something.com","Petar",1,"Petrovic","Beograd","pera",11000,"PRAVNO","pera",4), ("Bourbon street 2","zika@something.com","Zika",1,"Zikic","Amsterdam","zika",1046,"FIZICKO","zika",null);

INSERT INTO `aukcije`.`location`(`latitude`,`longitude`,`user`)VALUES(44.786568,20.448922,"pera"),(52.370216,4.895168,"zika");

