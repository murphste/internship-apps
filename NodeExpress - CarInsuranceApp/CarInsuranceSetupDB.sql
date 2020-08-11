/*
This T-SQL script creates a MS MSQL database with sample data

Tables:

-----------
| AppUser  |
-----------


-----------
| AppUser  |
-----------

------------
| Person   |
------------
    1|
     |
	M|
------------	 
| Vehicle  |--------------------|
----------- M					|
	M|							|									
	 |							|1
     | 				 -------------------	 
	 |-------------	 | Vehicle_Accident |
	 |				 -------------------
	 |							|1
	 |							|
	M|							|
------------					|
| Accident  |-------------------|
------------ M
    



*/

/****** Object:  Table [dbo].[AppUser]    Script Date: 10/12/19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppUser](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](255) NULL,
	[LastName] [nvarchar](255) NULL,
	[Email] [nvarchar](255) NOT NULL,
	[Password] [nvarchar](255) NOT NULL,
	[Role] [nvarchar](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO









/****** Object:  Table [dbo].[Person]    Script Date: 10/12/19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Person](
	[DriverID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName][nvarchar](255) NOT NULL,
	[LastName][nvarchar](255) NOT NULL,
	[Phone][nvarchar](255) NOT NULL,
	[Email][nvarchar](255) NOT NULL,
	[StreetAddress] [nvarchar](255) NOT NULL,
	[City] [nvarchar](255) NOT NULL,
	[Country] [nvarchar](255) NOT NULL,
	[PenaltyPoints] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[DriverId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO







/****** Object:  Table [dbo].[Vehicle]    Script Date: 10/12/19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehicle](
	[RegNo] [nvarchar](255) NOT NULL,
	[PersonDriverID] [int] NOT NULL,
	[Model] [nvarchar](255) NOT NULL,
	[Year] [int] NOT NULL,
	[VehicleType] [nvarchar](255) NOT NULL,

PRIMARY KEY CLUSTERED 
(
	[RegNo] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO





/****** Assign Foreign Key relationship to PersonDriverID in Vehicle table 
		Script Date: 10/12/19 ******/
ALTER TABLE [dbo].[Vehicle]     
ADD CONSTRAINT FK_Person_PersonDriverID FOREIGN KEY (PersonDriverID)     
    REFERENCES [dbo].[Person] (DriverID)     
    ON DELETE CASCADE    
    ON UPDATE CASCADE  




/****** Object:  Table [dbo].[Accident]    Script Date: 10/12/19  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accident](
	[ReportNum] [int] IDENTITY(1,1) NOT NULL,
	[Location] [nvarchar](255) NOT NULL,
	[IncidentDate] [Date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ReportNum] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



/****** Object:  Table [dbo].[Vehicle_Accident]    Script Date: 10/12/19  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehicle_Accident](
	[VehicleRegNo] [nvarchar](255) NOT NULL,
	[AccidentReportNum] [int] NOT NULL,
	CONSTRAINT PK_Vehicle_Accident PRIMARY KEY (VehicleRegNo, AccidentReportNum),
	CONSTRAINT FK_Vehicle_Accident_Vehicle FOREIGN KEY (VehicleRegNo) REFERENCES Vehicle(RegNo),
	CONSTRAINT FK_Vehicle_Accident_Accident FOREIGN KEY (AccidentReportNum) REFERENCES Accident(ReportNum)
);







ALTER TABLE [dbo].[Person]     
ADD [firstName] [nvarchar](255) NOT NULL,
	[lastName] [nvarchar](255) NOT NULL;


UPDATE [dbo].[Person]
SET [firstName] = [FirstName],
	[lastName] = [LastName];





SET IDENTITY_INSERT [dbo].[AppUser] ON 
GO
INSERT [dbo].[AppUser] ([UserId], [FirstName], [LastName], [Email], [Password], [Role]) VALUES (1, N'Alice', N'Admin', N'alice@web.com', N'password', N'admin')
GO
INSERT [dbo].[AppUser] ([UserId], [FirstName], [LastName], [Email], [Password], [Role]) VALUES (2, N'Bob', N'Manager', N'bob@web.com', N'password', N'manager')
GO
INSERT [dbo].[AppUser] ([UserId], [FirstName], [LastName], [Email], [Password], [Role]) VALUES (3, N'Eve', N'User', N'eve@web.com', N'password', N'user')
GO
SET IDENTITY_INSERT [dbo].[AppUser] OFF
GO




-- Individual drivers
SET IDENTITY_INSERT [dbo].[Person] ON 
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (1, 'John', 'Barnes', '0851458559', 'jb@gmail.com', N'123 Baker Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (2, 'Jane', 'Murray', '0875855245', 'jmurray@gmail.com', N'15 North Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (3, 'Sven', 'Johanson', '08601458857', 'svjoh@gmail.com', N'10 Dowling Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (4, 'Freya', 'Bishop', '0879559585', 'fishb@gmail.com', N'14 Blake Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (5, 'Tommy', 'Gunn', '0864157400', 'bangbang@gmail.com', N'1 The Pines', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (6, 'Sarah', 'Priestly', '0851554748', 'holybitch@gmail.com', N'158 Mass Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (7, 'Vico', 'Ricardo', '0851847445', 'lemonpledge@gmail.com', N'117 Thomas Street', N'Dublin', N'Ireland', 2)
GO
INSERT [dbo].[Person] ([DriverId],[FirstName], [LastName], [Phone], [Email], [StreetAddress], [City], [Country], [PenaltyPoints]) VALUES (8, 'Stephen', 'Murphy', '0860548557', 'webmaster@gmail.com', N'18 Silk Road', N'Dublin', N'Ireland', 2)
GO
SET IDENTITY_INSERT [dbo].[Person] OFF
GO


-- Additional entries - For where a driver is insured on more than one car
SET IDENTITY_INSERT [dbo].[Vehicle] ON 
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('14-D-1524', 1, N'Renault Fluence', 2015, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('13-C-17184', 2, N'Kia Sportage', 2012, N'Jeep')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('13-WX-181', 3, N'Nissan Micra', 2013, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('12-D-1845', 4, N'Nissan Qashqai', 2011, N'Jeep')
GO
SET IDENTITY_INSERT [dbo].[Vehicle] OFF
GO


-- Vehicle Listing
SET IDENTITY_INSERT [dbo].[Vehicle] ON 
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('15-D-451', 1, N'Renault Fluence', 2015, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('12-OY-17761', 2, N'Kia Sportage', 2012, N'Jeep')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('13-WX-1852', 3, N'Nissan Micra', 2013, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('11-C-18995', 4, N'Nissan Qashqai', 2011, N'Jeep')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('18-D-21502', 5, N'Mercedes Benz C-Class', 2018, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('14-C-17478', 6, N'Fiat Punto', 2014, N'Car')
GO
INSERT [dbo].[Vehicle] ([RegNo], [PersonDriverID], [Model], [Year], [VehicleType]) VALUES ('10-WW-19456', 7, N'Seat Alteca', 2010, N'SUV')
GO
SET IDENTITY_INSERT [dbo].[Vehicle] OFF
GO





-- Accident Table
SET IDENTITY_INSERT [dbo].[Accident] ON 
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (1, N'Coynes Cross, Ashford, Co. Wicklow', '20190618 10:34:09 PM')
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (2, N'Red Cow Roundabout, Dublin', '20190112 01:34:09 AM')
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (3, N'M50 Exit 12 Ramp, Knocklyon, Dublin', '20190511 07:54:09 AM')
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (4, N'Longmile Road, Walkinstown, Dublin 12', '20190405 11:07:39 AM')
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (5, N'Newline Road, Murrintown, Wexford', '20190405 15:04:15 PM')
GO
INSERT [dbo].[Accident] ([ReportNum], [Location], [IncidentDate]) VALUES (6, N'Glanmire Road, Glanmire, Cork', '20190405 13:47:01 PM')
GO
SET IDENTITY_INSERT [dbo].[Accident] OFF
GO


UPDATE [dbo].[Accident]
SET [IncidentDate] = '20190205'
WHERE [ReportNum] = 6;




DROP TABLE IF EXISTS  CarInsuranceCA2.dbo.Vehicle;




SET IDENTITY_INSERT [dbo].[Vehicle_Accident] ON 
GO
INSERT [dbo].[Vehicle_Accident] ([VehicleRegNo], [AccidentReportNum]) VALUES ('18-D-21502', 1)
GO
INSERT [dbo].[Vehicle_Accident] ([VehicleRegNo], [AccidentReportNum]) VALUES ('15-D-451', 2)
GO
INSERT [dbo].[Vehicle_Accident] ([VehicleRegNo], [AccidentReportNum]) VALUES ('14-C-17478', 4)
GO
INSERT [dbo].[Vehicle_Accident] ([VehicleRegNo], [AccidentReportNum]) VALUES ('12-OY-17761', 4)
GO
SET IDENTITY_INSERT [dbo].[Vehicle_Accident] OFF
GO



ALTER TABLE [dbo].[Vehicle_Accident]
ADD [DamageAmount] [nvarchar](255) NOT NULL;

















DROP TABLE [dbo].[Vehicle_Accident];

DROP TABLE [dbo].[Vehicle];

DROP TABLE [dbo].[Accident];

DROP TABLE [dbo].[Person];






ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [ProductStock]
GO

ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0.00)) FOR [ProductPrice]
GO

ALTER TABLE [dbo].[Link]  WITH CHECK ADD FOREIGN KEY([TopicId])
REFERENCES [dbo].[Topic] ([TopicId])
GO

ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([CategoryId])
GO
