INSERT INTO `aukcije`.`job_category`(`name`) VALUES("Usluge smeštaja i ishrane"), ("Informisanje i komunikacije"), ("Saobraćaj i skladištenje"), ("Trgovina"), ("Umetnost, zabava i rekreacija");

INSERT INTO `aukcije`.`company` (`name`,`address`,`postal_code`,`place`,`max_distance`) VALUES ("Restoran Canzona", "Janka Čmelika 24", "21000", "Novi Sad", 80.0), ("Restoran Sokače", "Pap Pavla 11", "21000", "Novi Sad", 100.0), ("Hrana PRODUCT", "Horgoški put 92", "24413", "Palić", 150.0), ("Telekom Srbija", "Takovska 2", "11000", "Beograd", 600.0), ("Telenor", "Omladinskih brigada 82", "11000", "Beograd", 700.0), ("Lasta", "Milovana Milovanovića 4", "11000", "Beograd", 3000.0), ("Arriva Travel", "Đure Đakovića 3", "12000", "Požarevac", 1800.0), ("Niš ekspres", "Čamurlija 160", "18000", "Niš", 2000.0), ("Put", "Kralja Petra 1", "35000", "Jagodina", 1800.0), ("InterKop", "Bulevar Milutina Milankovića 23", "11000", "Beograd", 2500.0);

INSERT INTO `aukcije`.`job_category_company`(`job_category_id`,`company_id`) VALUES (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10);

