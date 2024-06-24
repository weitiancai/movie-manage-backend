# noinspection SqlNoDataSourceInspectionForFile
# create table if not exists USER (id bigint primary key auto_increment, USERNAME varchar(50) not null, PASSWORD varchar(200) not null,
# FIRSTNAME varchar(50) not null, LASTNAME varchar(50) not null, EMAIL varchar(50) not null, ACTIVATED bit not null);
#
# create table if not exists AUTHORITY (NAME varchar(50) primary key);
#
# drop table USER_AUTHORITY;
# create table if not exists USER_AUTHORITY (USER_ID bigint not null, AUTHORITY_NAME varchar(50) not null);
# alter table USER_AUTHORITY add unique uk_user_id(USER_ID);
# # alter table USER_AUTHORITY add constraint FKh7c8nq3y6q0fve8pvcto2ut2c foreign key (USER_ID) references USER;
#
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1);
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1);
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0);
#
# INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_USER');
# INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_ADMIN');
#
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (4, 'ROLE_ADMIN');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_USER');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (3, 'ROLE_USER');


# noinspection SqlNoDataSourceInspectionForFile
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1);
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1);
# INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ACTIVATED) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0);
#
# INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_USER');
# INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_ADMIN');
#
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_USER');
# INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (3, 'ROLE_USER');
