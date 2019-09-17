create table user_credentials (userId serial primary key,username varchar(255),password varchar(255));

-----insert data to user credentials table--------

insert into user_credentials values (nextval('user_credentials_userid_seq'),'sarath','$2a$10$5G3i8hSg3.EuiRrFb24/g.wc/pguyydm5m8N8ei0j4g35ZuerGBZ6')