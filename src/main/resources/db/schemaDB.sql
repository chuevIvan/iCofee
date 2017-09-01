CREATE DATABASE  IF NOT EXISTS `icoffee` ;

USE `icoffee`;

CREATE TABLE `coffee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert  into `coffee`(`id`,`name`,`price`) values (1,'Stumptown Hairbender Blend - Editor\'s Pick',26.08);
insert  into `coffee`(`id`,`name`,`price`) values (2,'Peet’s Coffee Guatemala San Sebastián Blend',16.95);
insert  into `coffee`(`id`,`name`,`price`) values (3,'Cuisinart DCC-3200',77.43);
insert  into `coffee`(`id`,`name`,`price`) values (4,'Philz Philharmonic Coffee - Editor\'s Pick',44.99);
insert  into `coffee`(`id`,`name`,`price`) values (5,'Bulletproof Whole-Bean Coffee',18.95);


CREATE TABLE `delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

insert  into `delivery`(`id`,`name`,`price`) values (1,'Singly',0);
insert  into `delivery`(`id`,`name`,`price`) values (2,'Courier',1);
insert  into `delivery`(`id`,`name`,`price`) values (3,'Express',2.5);


CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coffee_id` bigint(20) NOT NULL,
  `delivery_id` bigint(20) NOT NULL,
  `count_coffee` double NOT NULL,
  `startDate` timestamp NULL DEFAULT NULL,
  `endDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ert_idx` (`coffee_id`),
  KEY `hv_idx` (`delivery_id`),
  CONSTRAINT `fk_order_coffee` FOREIGN KEY (`coffee_id`) REFERENCES `coffee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_delivery` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;


insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (52,1,3,12.56,'2017-08-30 18:28:00','2017-08-31 19:28:00');
insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (53,1,2,10,'2017-08-30 18:31:00','2017-08-31 19:31:00');
insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (54,1,3,20,'2017-08-30 18:50:00','2017-08-31 19:50:00');
insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (55,1,2,17,'2017-08-30 18:54:00','2017-08-31 19:54:00');
insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (57,1,2,0.1,'2017-08-30 19:36:00','2017-08-31 20:36:00');
insert  into `order`(`id`,`coffee_id`,`delivery_id`,`count_coffee`,`startDate`,`endDate`) values (58,1,3,0.1,'2017-08-30 20:11:00','2017-08-31 21:11:00');
