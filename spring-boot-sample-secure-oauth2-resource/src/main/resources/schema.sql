DROP TABLE IF EXISTS flight;
CREATE TABLE flight (
  id int(11) NOT NULL AUTO_INCREMENT,
  origin varchar(100) DEFAULT NULL,
  destination varchar(100) DEFAULT NULL,
  airline varchar(100) DEFAULT NULL,
  flight_number varchar(100) DEFAULT NULL,
  Date date DEFAULT NULL,
  traveler varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
