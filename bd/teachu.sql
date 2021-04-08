
use teachU;

drop table Administrador;
DROP TABLE Estudiante;
drop table Clases;
Drop table Carreras;
drop table Tutoria;
drop table TutorXClase;
drop table Tutor;
drop table Usuario;

CREATE TABLE Usuario 
    (
     Id_Usuario INTEGER NOT NULL , 
     Foto BLOB, 
     Nombre VARCHAR (50) NOT NULL , 
     Apellido VARCHAR (50) NOT NULL , 
     CorreoInst VARCHAR (100) NOT NULL , 
     Nusuario VARCHAR (50) NOT NULL , 
     Contraseña VARCHAR (50) NOT NULL,
     CONSTRAINT PK_Usuario PRIMARY KEY (Id_Usuario)
    );



CREATE TABLE Tutor 
    (
     Id_Usuario INTEGER NOT NULL , 
     Cedula VARCHAR (20) NOT NULL , 
     tipoDoc VARCHAR (2) NOT NULL , 
     Descripcion VARCHAR (5000) NOT NULL ,
     CONSTRAINT PK_Tutor PRIMARY KEY (Id_usuario),
     CONSTRAINT FK_Tutor_Usuario FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
    );



CREATE TABLE Administrador 
    (
     Id_Usuario INTEGER NOT NULL , 
     Permisos BIT NOT NULL ,
     CONSTRAINT PK_Admin PRIMARY KEY (Id_usuario),
     CONSTRAINT FK_Admin_Usuario FOREIGN KEY (Id_usuario) REFERENCES Usuario(Id_Usuario)
    );


CREATE TABLE Carreras 
    (
     IdCarrera INTEGER NOT NULL , 
     Ncarrera VARCHAR (50) NOT NULL ,
     CONSTRAINT PK_Carreras PRIMARY KEY (IdCarrera)
    );




CREATE TABLE Estudiante 
    (
     Id_Usuario INTEGER NOT NULL , 
     IdCarrera INTEGER NOT NULL ,
     CONSTRAINT PK_Estudiante PRIMARY KEY (Id_usuario),
     CONSTRAINT FK_Estudiante_Usuario FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
     CONSTRAINT FK_Estudiante_Carreras FOREIGN KEY (IdCarrera) REFERENCES Carreras(IdCarrera)
    );


CREATE TABLE Clases 
    (
     idClase INTEGER NOT NULL , 
     Nclase VARCHAR (50) NOT NULL , 
     IdCarrera INTEGER NOT NULL , 
     Carreras_IdCarrera INTEGER NOT NULL ,
     CONSTRAINT PK_Clases PRIMARY KEY(idClase),
     CONSTRAINT FK_Clases_Carreras FOREIGN KEY (IdCarrera) REFERENCES Carreras(IdCarrera)
    );



CREATE TABLE Tutoria 
    (
     IdEstudiante INTEGER NOT NULL,
     IdTutor INTEGER NOT NULL,
     IdTutoria INTEGER NOT NULL , 
     Fecha DATE NOT NULL , 
     Duracion INTEGER NOT NULL , 
     idClase INTEGER NOT NULL , 
     Precio DOUBLE NOT NULL ,
     CONSTRAINT PK_Tutoria PRIMARY KEY (IdTutoria) ,
     CONSTRAINT FK_Tutoria_Clases FOREIGN KEY (idClase) REFERENCES Clases(idClase) ,
     CONSTRAINT FK_Tutoria_Estudiante FOREIGN KEY (IdEstudiante) REFERENCES Estudiante(Id_Usuario),
     CONSTRAINT FK_Tutoria_Tutor FOREIGN KEY (IdTutor) REFERENCES Tutor(Id_Usuario)    
    );


CREATE TABLE TutorXClase 
    (
     Tutor_Id_Usuario INTEGER NOT NULL , 
     Clases_idClase INTEGER NOT NULL ,
     CONSTRAINT PK_TutorXClase PRIMARY KEY (Tutor_Id_Usuario, Clases_idClase),
     CONSTRAINT FK_TutorXClase_Tutor FOREIGN KEY (Tutor_Id_Usuario) REFERENCES Tutor(Id_Usuario)
    );

ALTER TABLE Tutor
ADD ranking integer;

commit;
