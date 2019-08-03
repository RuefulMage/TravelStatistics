CREATE DATABASE travel_statistics;
CREATE SCHEMA travel_statistics;

CREATE TYPE reason AS ENUM('Посещение достопримечательностей', 'Спортивный туризм', 'Оздоровительный туризм',
    'Визит друзей/родственников','Природные особенности');

CREATE TABLE markers(
    marker_id SERIAL NOT NULL ,
    reasonOfTurism varchar(200) NOT NULL ,
    comment varchar(200),
    country varchar(100) NOT NULL ,
    longitude float NOT NULL ,
    latitude float NOT NULL ,
    user_id integer NOT NULL ,
    CONSTRAINT pk_markers PRIMARY KEY ( marker_id )
);

CREATE TABLE users(
  user_id SERIAL NOT NULL ,
  userName varchar(50) NOT NULL UNIQUE ,
  fullName varchar(50) NOT NULL ,
  email varchar(200) UNIQUE NOT NULL ,
  password varchar(50) NOT NULL ,
  userRole varchar(255),
  CONSTRAINT pk_users PRIMARY KEY ( user_id )
);
-- CREATE INDEX idx_markers ON markers ( user_id );
ALTER TABLE markers ADD CONSTRAINT fk_markers_users FOREIGN KEY ( user_id ) REFERENCES users( user_id );

CREATE TABLE countries(
  countries_id SERIAL NOT NULL ,
  country varchar(200),
  sightseingReason integer,
  sportReason integer,
  wellnessReason integer,
  visitReason integer,
  natureReason integer,
  CONSTRAINT pk_countries PRIMARY KEY (countries_id)
);

