INSERT INTO `aukcije`.`job_category`(`name`) VALUES("Usluge smeštaja i ishrane"), ("Informisanje i komunikacije"), ("Saobraćaj i skladištenje"), ("Trgovina"), ("Umetnost, zabava i rekreacija");

INSERT INTO `aukcije`.`custom_user`(`adresa`,`email`,`firstname`,`is_active`,`lastname`,`mesto`,`password`,`ptt`,`tip`,`username`,`name`,`job_category_id`, `max_distance`, `ocena`) VALUES ("Takovska 2","username@localhost","Petar",1,"Petrovic","Beograd","pera", "11000","PRAVNO","pera","Telenor", 2, 100, 0.0), ("Bourbon street 2","zika@localhost","Zika",1,"Zikic","Amsterdam","zika", "1025","FIZICKO","zika",null, null, null, 0.0), ("Janka Čmelika 24","canzona@localhost","Mika",1,"Mikic","Novi Sad","mika", "21000","PRAVNO","mika","Restoran Canzona", 1, 100, 0.0), ("Milovana Milovanovića 4","lasta@localhost","Lasta",1,"Lastic","Beograd","lasta", "11000","PRAVNO","lasta","Lasta prevoz", 3, 5000, 0.0), ("Đure Đakovića 3","arriva@localhost","Janko",1,"Jankovic","Požarevac","arriva", "12000","PRAVNO","arriva","Arriva Travel", 3, 500, 0.0), ("Kralja Petra 18","nisekspres@localhost","Nišlija",1,"Nišic","Niš","nisekspres", "18000","PRAVNO","nisekspres","Nišekspres d.o.o", 3, 800, 0.0),("London street 1","interkop@localhost","John",1,"London","London","interkop", "1105","PRAVNO","interkop","Interkop", 3, 300, 0.0), ("London street 2","test@localhost","Test",1,"Test","London","test", "1105","FIZICKO","test",null,null, null, 0.0); 

INSERT INTO `aukcije`.`location`(`latitude`,`longitude`,`user`)VALUES(44.786568,20.448922,"pera"),(52.370216,4.895168,"zika"), (45.267135,19.833550,"canzona"),(44.786568,20.448922,"lasta"),(44.620835,21.184205,"arriva"),(43.320902,21.895759,"nisekspres"),(51.507351,-0.127758,"interkop"),(51.453336,-0.106853,"test");