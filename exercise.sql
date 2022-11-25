create database if not exists `telstra_db`;
use `telstra_db`;

create Table `customer_resource`(
`uuid` varchar(50) not null,
`name` varchar(50) not null,
`address` varchar(50) not null,
 primary key(`uuid`)
);

create Table `purchased_product`(
`product_id` varchar(50) not null,
`description` varchar(50) not null,
`original_price` decimal(8,2) not null,
`final_price` decimal(8,2) not null,
 primary key(`product_id`)
);


create table `discount`(
`discount_id` varchar(50) not null,
`type` varchar(50) not null,
`description` varchar(50) not null,
`amount` decimal(8,2) not null,
`product_id` varchar(50),
 primary key(`discount_id`),
 foreign key(`product_id`) references `purchased_product`(`product_id`)
 );
 
 create Table `customer_discount`(
	`cd_uuid` varchar(50) not null,
    `cd_discount_id` varchar(50) not null,
	 primary key(`cd_uuid`,`cd_discount_id`),
	 foreign key(`cd_uuid`) references `customer_resource`(`uuid`),
	 foreign key(`cd_discount_id`) references `discount`(`discount_id`)

 );
 
 create Table `customer_product`(
	`cp_uuid` varchar(50) not null,
    `cp_product_id` varchar(50) not null,
	 primary key(`cp_uuid`,`cp_product_id`),
	 foreign key(`cp_uuid`) references `customer_resource`(`uuid`),
	 foreign key(`cp_product_id`) references `purchased_product`(`product_id`)
 );
 

    
INSERT INTO `customer_resource` VALUES 
	('qa-dev-andrews','Andrews','BSB Sreet'),
	('qa-dev-emma','Emma','BPR Street'),
	('qa-tester-gupta','Gupta','MNM Street'),
	('qa-tester-petrov','Petrov','PKR Street'),
	('qa-dev-vega','Vega','GKH Street');
    
 INSERT INTO `purchased_product` VALUES 
	('sku-100','subscription of 100',100,90),
    ('sku-200','subscription of 200',200,180),
    ('sku-300','subscription of 300',300,255),
    ('sku-400','subscription of 400',400,320),
    ('sku-50','subscription of 50',50,50);
    
    INSERT INTO `discount` VALUES 
	('d-9','PERCENT','9% Discount',9,'sku-100'),
    ('d-10','PERCENT','10% Discount',10,'sku-200'),
    ('d-15','PERCENT','15% Discount',15,'sku-300'),
    ('d-20','PERCENT','20% Discount',20,'sku-400'),
    ('df-15','Amount','15$ flat discount on purchases over 250$',15,null);
 
	INSERT INTO `customer_discount` values
    ('qa-dev-emma','df-15');
    
    INSERT INTO `customer_product` values
    ('qa-dev-andrews','sku-100'),
    ('qa-dev-emma','sku-300'),
    ('qa-tester-gupta','sku-50');
    
    commit;



