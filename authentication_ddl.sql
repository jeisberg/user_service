drop database authentication;
create database authentication;

use authentication;

create table user_app_auth (
	id int(10) unsigned NOT NULL,
	uid int(10) unsigned NOT NULL,
	api_key varchar(100) NOT NULL,
	token  varchar(500),
	PRIMARY KEY(id),
	UNIQUE (uid,api_key)
)    ENGINE= InnoDB DEFAULT CHARSET= utf8;