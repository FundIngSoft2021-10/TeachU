
use teachU;

drop table Administrador;
drop table Tutoria;
drop table TutorXClase;
drop table Disponibilidad;
drop table DisponibilidadxTutor;
drop table Clases;
DROP TABLE Estudiante;
Drop table Carreras;
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
     Contrasena VARCHAR (50) NOT NULL,
     CONSTRAINT PK_Usuario PRIMARY KEY (Id_Usuario)
    );



CREATE TABLE Tutor 
    (
     Id_Usuario INTEGER NOT NULL , 
     Cedula VARCHAR (20) NOT NULL , 
     tipoDoc VARCHAR (2) NOT NULL , 
     Descripcion VARCHAR (5000) NOT NULL ,
     Ranking integer, 
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


CREATE TABLE Disponibilidad
    (
        IdDisponibilidad INTEGER NOT NULL primary key,
        hora integer,
        dia char
    );
    
Create table DisponibilidadxTutor
(
    Tutor_Id_Usuario INTEGER NOT NULL ,
    IdDisponibilidad INTEGER NOT NULL,
    CONSTRAINT PK_DisponibilidadxTutor PRIMARY KEY (Tutor_Id_Usuario, IdDisponibilidad),
     CONSTRAINT FK_DisponibilidadxTutor_tutor FOREIGN KEY (Tutor_Id_Usuario) REFERENCES Tutor(Id_Usuario)
);


insert into Carreras values(1, 'Ingenieria de sistemas');
insert into Carreras values(		2	,	'	Administracion de Empresas	'	)	;
insert into Carreras values(		3	,	'	Antropologia	'	)	;
insert into Carreras values(		4	,	'	Artes Escenicas	'	)	;
insert into Carreras values(		5	,	'	Arquitectura	'	)	;
insert into Carreras values(		6	,	'	Artes Visuales	'	)	;
insert into Carreras values(		7	,	'	Bacteriologia	'	)	;
insert into Carreras values(		8	,	'	Bioingenieria	'	)	;
insert into Carreras values(		9	,	'	Biologia	'	)	;
insert into Carreras values(		10	,	'	Ciencia Infor Bibliot y Archiv	'	)	;
insert into Carreras values(		11	,	'	Ciencia de Datos	'	)	;
insert into Carreras values(		12	,	'	Comunicacion Social	'	)	;
insert into Carreras values(		13	,	'	Lic Ciencias Religiosas	'	)	;
insert into Carreras values(		14	,	'	Contaduria Publica	'	)	;
insert into Carreras values(		15	,	'	Derecho	'	)	;
insert into Carreras values(		16	,	'	Diseno Industrial	'	)	;
insert into Carreras values(		17	,	'	Ecologia	'	)	;
insert into Carreras values(		18	,	'	Economia	'	)	;
insert into Carreras values(		19	,	'	Lic Ed Basica H y L Castellana	'	)	;
insert into Carreras values(		20	,	'	Estudios Musicales	'	)	;
insert into Carreras values(		21	,	'	Enfermeria	'	)	;
insert into Carreras values(		22	,	'	Filosofia	'	)	;
insert into Carreras values(		23	,	'	Finanzas	'	)	;
insert into Carreras values(		24	,	'	Historia	'	)	;
insert into Carreras values(		25	,	'	Ingenieria Civil	'	)	;
insert into Carreras values(		26	,	'	Ingenieria Electronica	'	)	;
insert into Carreras values(		27	,	'	Informatica Matematica	'	)	;
insert into Carreras values(		28	,	'	Ingenieria Industrial	'	)	;
insert into Carreras values(		29	,	'	Ingenieria Mecanica	'	)	;
insert into Carreras values(		30	,	'	Ingenieria Mecatronica	'	)	;
insert into Carreras values(		31	,	'	Ciencia Inform Bibliotec Archi	'	)	;
insert into Carreras values(		32	,	'	Cs.Informacion-Bibliotecologia	'	)	;
insert into Carreras values(		33	,	'	Lic en Educacion Infantil	'	)	;
insert into Carreras values(		34	,	'	Ing Redes y Telecomunicaciones	'	)	;
insert into Carreras values(		35	,	'	Lic Ciencias Naturales Edu Amb	'	)	;
insert into Carreras values(		36	,	'	Lic Ed Basica H y L Castellana	'	)	;
insert into Carreras values(		37	,	'	Licenciatura Educacion Fisica	'	)	;
insert into Carreras values(		38	,	'	Licenciatura en Filosofia	'	)	;
insert into Carreras values(		39	,	'	Estudios Literarios	'	)	;
insert into Carreras values(		40	,	'	Lic Literatura y Lengua Castel	'	)	;
insert into Carreras values(		41	,	'	Licenciatura Lenguas Modernas	'	)	;
insert into Carreras values(		42	,	'	Licenciatura en Teologia	'	)	;
insert into Carreras values(		43	,	'	Microbiolg Agric y Veterinaria	'	)	;
insert into Carreras values(		44	,	'	Matematicas	'	)	;
insert into Carreras values(		45	,	'	Medicina	'	)	;
insert into Carreras values(		46	,	'	Microbiologia Industrial	'	)	;
insert into Carreras values(		47	,	'	Nutricion y Dietetica	'	)	;
insert into Carreras values(		48	,	'	Odontologia	'	)	;
insert into Carreras values(		49	,	'	Ciencia Politica	'	)	;
insert into Carreras values(		50	,	'	Psicologia	'	)	;
insert into Carreras values(		51	,	'	Quimica Farmaceutica	'	)	;
insert into Carreras values(		52	,	'	Relaciones Internacionales	'	)	;
insert into Carreras values(		53	,	'	Sociologia	'	)	;
insert into Carreras values(		54	,	'	Teologia	'	)	;

insert into Clases values(		1	,	'	Finanzas basicas	'	,	1	)	;
insert into Clases values(		2	,	'	Principios de Economia	'	,	1	)	;
insert into Clases values(		3	,	'	Maquinas Digitales	'	,	1	)	;
insert into Clases values(		4	,	'	Epistemologia de la Ingenieria	'	,	1	)	;
insert into Clases values(		5	,	'	Etica en la Ingenieria	'	,	1	)	;
insert into Clases values(		6	,	'	Lectores y Lecturas	'	,	1	)	;
insert into Clases values(		7	,	'	Logica Matematic-Computacional	'	,	1	)	;
insert into Clases values(		8	,	'	Calculo Diferencial	'	,	1	)	;
insert into Clases values(		9	,	'	Calculo Integral	'	,	1	)	;
insert into Clases values(		10	,	'	Algebra Lineal	'	,	1	)	;
insert into Clases values(		11	,	'	Matematicas Discretas Sistemas	'	,	1	)	;
insert into Clases values(		12	,	'	Ecuaciones Diferenciales	'	,	1	)	;
insert into Clases values(		13	,	'	Calculo Vectorial	'	,	1	)	;
insert into Clases values(		14	,	'	Probabilidad y Estadistica	'	,	1	)	;
insert into Clases values(		15	,	'	Proyecto CDIO Ano I	'	,	1	)	;
insert into Clases values(		16	,	'	Constitucion y Derecho Civil	'	,	1	)	;
insert into Clases values(		17	,	'	Introduccion Ing. de Sistemas	'	,	1	)	;
insert into Clases values(		18	,	'	Pensamiento Algoritmico	'	,	1	)	;
insert into Clases values(		19	,	'	Prog. Orientada Objetos	'	,	1	)	;
insert into Clases values(		20	,	'	Programacion de Computadores	'	,	1	)	;
insert into Clases values(		21	,	'	Analisis y Diseno O.O.	'	,	1	)	;
insert into Clases values(		22	,	'	Pensamiento Sistemico	'	,	1	)	;
insert into Clases values(		23	,	'	Bases de Datos	'	,	1	)	;
insert into Clases values(		24	,	'	Fundamentos Redes e Internet	'	,	1	)	;
insert into Clases values(		25	,	'	Estructuras de Datos	'	,	1	)	;
insert into Clases values(		26	,	'	Hoja de Calculo II	'	,	1	)	;
insert into Clases values(		27	,	'	Sistemas de Informacion	'	,	1	)	;
insert into Clases values(		28	,	'	Comunicaciones y Redes	'	,	1	)	;
insert into Clases values(		29	,	'	Sistemas Operativos	'	,	1	)	;
insert into Clases values(		30	,	'	Seguridad de la informacion	'	,	1	)	;
insert into Clases values(		31	,	'	Fundamentos Ingenieria de SW	'	,	1	)	;
insert into Clases values(		32	,	'	Gestion de innovacion en TI	'	,	1	)	;


insert into Disponibilidad values 	(	1	,	1	,	'L'	)	;
insert into Disponibilidad values 	(	2	,	2	,	'L'	)	;
insert into Disponibilidad values 	(	3	,	3	,	'L'	)	;
insert into Disponibilidad values 	(	4	,	4	,	'L'	)	;
insert into Disponibilidad values 	(	5	,	5	,	'L'	)	;
insert into Disponibilidad values 	(	6	,	6	,	'L'	)	;
insert into Disponibilidad values 	(	7	,	7	,	'L'	)	;
insert into Disponibilidad values 	(	8	,	8	,	'L'	)	;
insert into Disponibilidad values 	(	9	,	9	,	'L'	)	;
insert into Disponibilidad values 	(	10	,	10	,	'L'	)	;
insert into Disponibilidad values 	(	11	,	11	,	'L'	)	;
insert into Disponibilidad values 	(	12	,	12	,	'L'	)	;
insert into Disponibilidad values 	(	13	,	13	,	'L'	)	;
insert into Disponibilidad values 	(	14	,	14	,	'L'	)	;
insert into Disponibilidad values 	(	15	,	15	,	'L'	)	;
insert into Disponibilidad values 	(	16	,	16	,	'L'	)	;
insert into Disponibilidad values 	(	17	,	17	,	'L'	)	;
insert into Disponibilidad values 	(	18	,	18	,	'L'	)	;
insert into Disponibilidad values 	(	19	,	19	,	'L'	)	;
insert into Disponibilidad values 	(	20	,	20	,	'L'	)	;
insert into Disponibilidad values 	(	21	,	21	,	'L'	)	;
insert into Disponibilidad values 	(	22	,	22	,	'L'	)	;
insert into Disponibilidad values 	(	23	,	23	,	'L'	)	;
insert into Disponibilidad values 	(	24	,	24	,	'L'	)	;
insert into Disponibilidad values 	(	25	,	1	,	'M'	)	;
insert into Disponibilidad values 	(	26	,	2	,	'M'	)	;
insert into Disponibilidad values 	(	27	,	3	,	'M'	)	;
insert into Disponibilidad values 	(	28	,	4	,	'M'	)	;
insert into Disponibilidad values 	(	29	,	5	,	'M'	)	;
insert into Disponibilidad values 	(	30	,	6	,	'M'	)	;
insert into Disponibilidad values 	(	31	,	7	,	'M'	)	;
insert into Disponibilidad values 	(	32	,	8	,	'M'	)	;
insert into Disponibilidad values 	(	33	,	9	,	'M'	)	;
insert into Disponibilidad values 	(	34	,	10	,	'M'	)	;
insert into Disponibilidad values 	(	35	,	11	,	'M'	)	;
insert into Disponibilidad values 	(	36	,	12	,	'M'	)	;
insert into Disponibilidad values 	(	37	,	13	,	'M'	)	;
insert into Disponibilidad values 	(	38	,	14	,	'M'	)	;
insert into Disponibilidad values 	(	39	,	15	,	'M'	)	;
insert into Disponibilidad values 	(	40	,	16	,	'M'	)	;
insert into Disponibilidad values 	(	41	,	17	,	'M'	)	;
insert into Disponibilidad values 	(	42	,	18	,	'M'	)	;
insert into Disponibilidad values 	(	43	,	19	,	'M'	)	;
insert into Disponibilidad values 	(	44	,	20	,	'M'	)	;
insert into Disponibilidad values 	(	45	,	21	,	'M'	)	;
insert into Disponibilidad values 	(	46	,	22	,	'M'	)	;
insert into Disponibilidad values 	(	47	,	23	,	'M'	)	;
insert into Disponibilidad values 	(	48	,	24	,	'M'	)	;
insert into Disponibilidad values 	(	49	,	1	,	'W'	)	;
insert into Disponibilidad values 	(	50	,	2	,	'W'	)	;
insert into Disponibilidad values 	(	51	,	3	,	'W'	)	;
insert into Disponibilidad values 	(	52	,	4	,	'W'	)	;
insert into Disponibilidad values 	(	53	,	5	,	'W'	)	;
insert into Disponibilidad values 	(	54	,	6	,	'W'	)	;
insert into Disponibilidad values 	(	55	,	7	,	'W'	)	;
insert into Disponibilidad values 	(	56	,	8	,	'W'	)	;
insert into Disponibilidad values 	(	57	,	9	,	'W'	)	;
insert into Disponibilidad values 	(	58	,	10	,	'W'	)	;
insert into Disponibilidad values 	(	59	,	11	,	'W'	)	;
insert into Disponibilidad values 	(	60	,	12	,	'W'	)	;
insert into Disponibilidad values 	(	61	,	13	,	'W'	)	;
insert into Disponibilidad values 	(	62	,	14	,	'W'	)	;
insert into Disponibilidad values 	(	63	,	15	,	'W'	)	;
insert into Disponibilidad values 	(	64	,	16	,	'W'	)	;
insert into Disponibilidad values 	(	65	,	17	,	'W'	)	;
insert into Disponibilidad values 	(	66	,	18	,	'W'	)	;
insert into Disponibilidad values 	(	67	,	19	,	'W'	)	;
insert into Disponibilidad values 	(	68	,	20	,	'W'	)	;
insert into Disponibilidad values 	(	69	,	21	,	'W'	)	;
insert into Disponibilidad values 	(	70	,	22	,	'W'	)	;
insert into Disponibilidad values 	(	71	,	23	,	'W'	)	;
insert into Disponibilidad values 	(	72	,	24	,	'W'	)	;
insert into Disponibilidad values 	(	73	,	1	,	'J'	)	;
insert into Disponibilidad values 	(	74	,	2	,	'J'	)	;
insert into Disponibilidad values 	(	75	,	3	,	'J'	)	;
insert into Disponibilidad values 	(	76	,	4	,	'J'	)	;
insert into Disponibilidad values 	(	77	,	5	,	'J'	)	;
insert into Disponibilidad values 	(	78	,	6	,	'J'	)	;
insert into Disponibilidad values 	(	79	,	7	,	'J'	)	;
insert into Disponibilidad values 	(	80	,	8	,	'J'	)	;
insert into Disponibilidad values 	(	81	,	9	,	'J'	)	;
insert into Disponibilidad values 	(	82	,	10	,	'J'	)	;
insert into Disponibilidad values 	(	83	,	11	,	'J'	)	;
insert into Disponibilidad values 	(	84	,	12	,	'J'	)	;
insert into Disponibilidad values 	(	85	,	13	,	'J'	)	;
insert into Disponibilidad values 	(	86	,	14	,	'J'	)	;
insert into Disponibilidad values 	(	87	,	15	,	'J'	)	;
insert into Disponibilidad values 	(	88	,	16	,	'J'	)	;
insert into Disponibilidad values 	(	89	,	17	,	'J'	)	;
insert into Disponibilidad values 	(	90	,	18	,	'J'	)	;
insert into Disponibilidad values 	(	91	,	19	,	'J'	)	;
insert into Disponibilidad values 	(	92	,	20	,	'J'	)	;
insert into Disponibilidad values 	(	93	,	21	,	'J'	)	;
insert into Disponibilidad values 	(	94	,	22	,	'J'	)	;
insert into Disponibilidad values 	(	95	,	23	,	'J'	)	;
insert into Disponibilidad values 	(	96	,	24	,	'J'	)	;
insert into Disponibilidad values 	(	97	,	1	,	'V'	)	;
insert into Disponibilidad values 	(	98	,	2	,	'V'	)	;
insert into Disponibilidad values 	(	99	,	3	,	'V'	)	;
insert into Disponibilidad values 	(	100	,	4	,	'V'	)	;
insert into Disponibilidad values 	(	101	,	5	,	'V'	)	;
insert into Disponibilidad values 	(	102	,	6	,	'V'	)	;
insert into Disponibilidad values 	(	103	,	7	,	'V'	)	;
insert into Disponibilidad values 	(	104	,	8	,	'V'	)	;
insert into Disponibilidad values 	(	105	,	9	,	'V'	)	;
insert into Disponibilidad values 	(	106	,	10	,	'V'	)	;
insert into Disponibilidad values 	(	107	,	11	,	'V'	)	;
insert into Disponibilidad values 	(	108	,	12	,	'V'	)	;
insert into Disponibilidad values 	(	109	,	13	,	'V'	)	;
insert into Disponibilidad values 	(	110	,	14	,	'V'	)	;
insert into Disponibilidad values 	(	111	,	15	,	'V'	)	;
insert into Disponibilidad values 	(	112	,	16	,	'V'	)	;
insert into Disponibilidad values 	(	113	,	17	,	'V'	)	;
insert into Disponibilidad values 	(	114	,	18	,	'V'	)	;
insert into Disponibilidad values 	(	115	,	19	,	'V'	)	;
insert into Disponibilidad values 	(	116	,	20	,	'V'	)	;
insert into Disponibilidad values 	(	117	,	21	,	'V'	)	;
insert into Disponibilidad values 	(	118	,	22	,	'V'	)	;
insert into Disponibilidad values 	(	119	,	23	,	'V'	)	;
insert into Disponibilidad values 	(	120	,	24	,	'V'	)	;
insert into Disponibilidad values 	(	121	,	1	,	'S'	)	;
insert into Disponibilidad values 	(	122	,	2	,	'S'	)	;
insert into Disponibilidad values 	(	123	,	3	,	'S'	)	;
insert into Disponibilidad values 	(	124	,	4	,	'S'	)	;
insert into Disponibilidad values 	(	125	,	5	,	'S'	)	;
insert into Disponibilidad values 	(	126	,	6	,	'S'	)	;
insert into Disponibilidad values 	(	127	,	7	,	'S'	)	;
insert into Disponibilidad values 	(	128	,	8	,	'S'	)	;
insert into Disponibilidad values 	(	129	,	9	,	'S'	)	;
insert into Disponibilidad values 	(	130	,	10	,	'S'	)	;
insert into Disponibilidad values 	(	131	,	11	,	'S'	)	;
insert into Disponibilidad values 	(	132	,	12	,	'S'	)	;
insert into Disponibilidad values 	(	133	,	13	,	'S'	)	;
insert into Disponibilidad values 	(	134	,	14	,	'S'	)	;
insert into Disponibilidad values 	(	135	,	15	,	'S'	)	;
insert into Disponibilidad values 	(	136	,	16	,	'S'	)	;
insert into Disponibilidad values 	(	137	,	17	,	'S'	)	;
insert into Disponibilidad values 	(	138	,	18	,	'S'	)	;
insert into Disponibilidad values 	(	139	,	19	,	'S'	)	;
insert into Disponibilidad values 	(	140	,	20	,	'S'	)	;
insert into Disponibilidad values 	(	141	,	21	,	'S'	)	;
insert into Disponibilidad values 	(	142	,	22	,	'S'	)	;
insert into Disponibilidad values 	(	143	,	23	,	'S'	)	;
insert into Disponibilidad values 	(	144	,	24	,	'S'	)	;
insert into Disponibilidad values 	(	145	,	1	,	'D'	)	;
insert into Disponibilidad values 	(	146	,	2	,	'D'	)	;
insert into Disponibilidad values 	(	147	,	3	,	'D'	)	;
insert into Disponibilidad values 	(	148	,	4	,	'D'	)	;
insert into Disponibilidad values 	(	149	,	5	,	'D'	)	;
insert into Disponibilidad values 	(	150	,	6	,	'D'	)	;
insert into Disponibilidad values 	(	151	,	7	,	'D'	)	;
insert into Disponibilidad values 	(	152	,	8	,	'D'	)	;
insert into Disponibilidad values 	(	153	,	9	,	'D'	)	;
insert into Disponibilidad values 	(	154	,	10	,	'D'	)	;
insert into Disponibilidad values 	(	155	,	11	,	'D'	)	;
insert into Disponibilidad values 	(	156	,	12	,	'D'	)	;
insert into Disponibilidad values 	(	157	,	13	,	'D'	)	;
insert into Disponibilidad values 	(	158	,	14	,	'D'	)	;
insert into Disponibilidad values 	(	159	,	15	,	'D'	)	;
insert into Disponibilidad values 	(	160	,	16	,	'D'	)	;
insert into Disponibilidad values 	(	161	,	17	,	'D'	)	;
insert into Disponibilidad values 	(	162	,	18	,	'D'	)	;
insert into Disponibilidad values 	(	163	,	19	,	'D'	)	;
insert into Disponibilidad values 	(	164	,	20	,	'D'	)	;
insert into Disponibilidad values 	(	165	,	21	,	'D'	)	;
insert into Disponibilidad values 	(	166	,	22	,	'D'	)	;
insert into Disponibilidad values 	(	167	,	23	,	'D'	)	;
insert into Disponibilidad values 	(	168	,	24	,	'D'	)	;


commit;
