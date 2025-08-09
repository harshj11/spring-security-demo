--Following are the scripts used by the spring security framework.

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

--Dummy Users and authorities

INSERT into users VALUES('db_user', 'password', '1');
INSERT into authorities VALUES('db_user', 'read');

INSERT into users VALUES('db_admin', '{noop}password', '1');
INSERT into authorities VALUES('db_admin', 'admin');
