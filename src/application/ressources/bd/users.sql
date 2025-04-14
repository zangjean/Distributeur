DROP TABLE  Users;
CREATE TABLE Users (
                       username VARCHAR2(100) PRIMARY KEY,
                       password VARCHAR2(100) NOT NULL ,
                       firstName VARCHAR2(100) NOT NULL ,
                       lastName VARCHAR2(100) NOT NULL ,
                       phoneNumber VARCHAR2(100),
                       mailAddress VARCHAR2(100)
);