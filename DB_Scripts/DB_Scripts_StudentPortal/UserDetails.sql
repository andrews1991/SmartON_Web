create table user_credentials (userId serial primary key,username varchar(255) unique,password varchar(255),user_role varchar(20));

create table user_details (userId serial primary key,firstname varchar(50),lastname varchar(50),username varchar(255) unique,password varchar(255),email varchar(255) unique,dob date,gender varchar(25),mobile varchar(25),college varchar(255),ispremium varchar(25),city varchar(50),state varchar(50),referralCode varchar(50));

-----insert data to user credentials table--------

insert into user_credentials values (nextval('user_credentials_userid_seq'),'sarath','$2a$10$5G3i8hSg3.EuiRrFb24/g.wc/pguyydm5m8N8ei0j4g35ZuerGBZ6','USER')