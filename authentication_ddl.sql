create database authentication;

use authentication;
create table user_app_auth (
	uid int(10) unsigned NOT NULL,
	api_key varchar(100) NOT NULL,
	token  varchar(500)
)    ENGINE= InnoDB DEFAULT CHARSET= utf8;