USE [master]
GO
/****** Object:  Database [MyTunes]    Script Date: 12/12/2012 9:57:56 AM ******/
CREATE DATABASE [MyTunes] ON  PRIMARY 
( NAME = N'MyTunes', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\MyTunes.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'MyTunes_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\MyTunes_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [MyTunes] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MyTunes].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MyTunes] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MyTunes] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MyTunes] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MyTunes] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MyTunes] SET ARITHABORT OFF 
GO
ALTER DATABASE [MyTunes] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MyTunes] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [MyTunes] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MyTunes] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MyTunes] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MyTunes] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MyTunes] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MyTunes] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MyTunes] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MyTunes] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MyTunes] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MyTunes] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MyTunes] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MyTunes] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MyTunes] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MyTunes] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MyTunes] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MyTunes] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MyTunes] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [MyTunes] SET  MULTI_USER 
GO
ALTER DATABASE [MyTunes] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MyTunes] SET DB_CHAINING OFF 
GO
USE [MyTunes]
GO
/****** Object:  User [java]    Script Date: 12/12/2012 9:57:59 AM ******/
CREATE USER [java] FOR LOGIN [java] WITH DEFAULT_SCHEMA=[dbo]
GO
sys.sp_addrolemember @rolename = N'db_datareader', @membername = N'java'
GO
sys.sp_addrolemember @rolename = N'db_datawriter', @membername = N'java'
GO
/****** Object:  Table [dbo].[Artist]    Script Date: 12/12/2012 9:58:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Artist](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Artist] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 12/12/2012 9:58:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Category] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Playlist]    Script Date: 12/12/2012 9:58:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Playlist](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Created] [datetime] NOT NULL,
 CONSTRAINT [PK_Playlist] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PlaylistSong]    Script Date: 12/12/2012 9:58:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlaylistSong](
	[PlaylistID] [int] NOT NULL,
	[SongID] [int] NOT NULL,
	[SeqNum] [int] NOT NULL,
 CONSTRAINT [PK_PlaylistSong] PRIMARY KEY CLUSTERED 
(
	[PlaylistID] ASC,
	[SongID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Song]    Script Date: 12/12/2012 9:58:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Song](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[ArtistID] [int] NOT NULL,
	[CategoryID] [int] NULL,
	[FileName] [nvarchar](max) NOT NULL,
	[Duration] [int] NULL,
 CONSTRAINT [PK_Song] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Artist] ON 

GO
INSERT [dbo].[Artist] ([ID], [Name]) VALUES (1, N'Cola')
GO
INSERT [dbo].[Artist] ([ID], [Name]) VALUES (2, N'PinkPanther')
GO
INSERT [dbo].[Artist] ([ID], [Name]) VALUES (3, N'Sons of Anarchy')
GO
INSERT [dbo].[Artist] ([ID], [Name]) VALUES (4, N'Cliff Adams')
GO
INSERT [dbo].[Artist] ([ID], [Name]) VALUES (5, N'Children')
GO
SET IDENTITY_INSERT [dbo].[Artist] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

GO
INSERT [dbo].[Category] ([ID], [Category]) VALUES (1, N'Comercial')
GO
INSERT [dbo].[Category] ([ID], [Category]) VALUES (2, N'Christmas')
GO
INSERT [dbo].[Category] ([ID], [Category]) VALUES (3, N'Movie')
GO
INSERT [dbo].[Category] ([ID], [Category]) VALUES (4, N'Drama')
GO
INSERT [dbo].[Category] ([ID], [Category]) VALUES (5, N'Funny')
GO
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Playlist] ON 

GO
INSERT [dbo].[Playlist] ([ID], [Name], [Created]) VALUES (3, N'Christmas Music', CAST(0x0000A12400EB934B AS DateTime))
GO
INSERT [dbo].[Playlist] ([ID], [Name], [Created]) VALUES (5, N'General', CAST(0x0000A124016C1B55 AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Playlist] OFF
GO
INSERT [dbo].[PlaylistSong] ([PlaylistID], [SongID], [SeqNum]) VALUES (3, 1, 3)
GO
INSERT [dbo].[PlaylistSong] ([PlaylistID], [SongID], [SeqNum]) VALUES (3, 2, 2)
GO
INSERT [dbo].[PlaylistSong] ([PlaylistID], [SongID], [SeqNum]) VALUES (3, 3, 1)
GO
INSERT [dbo].[PlaylistSong] ([PlaylistID], [SongID], [SeqNum]) VALUES (5, 5, 1)
GO
SET IDENTITY_INSERT [dbo].[Song] ON 

GO
INSERT [dbo].[Song] ([ID], [Title], [ArtistID], [CategoryID], [FileName], [Duration]) VALUES (1, N'Coca-Cola', 1, 1, N'coke_cola_christmas.mp3', 22)
GO
INSERT [dbo].[Song] ([ID], [Title], [ArtistID], [CategoryID], [FileName], [Duration]) VALUES (2, N'Laughing', 5, 5, N'ha_ha_ha_ha_ha.mp3', 12)
GO
INSERT [dbo].[Song] ([ID], [Title], [ArtistID], [CategoryID], [FileName], [Duration]) VALUES (3, N'Jingle Bell', 4, 2, N'jingle_bell_rocks.mp3', 17)
GO
INSERT [dbo].[Song] ([ID], [Title], [ArtistID], [CategoryID], [FileName], [Duration]) VALUES (5, N'Pink Panther', 2, 3, N'pink_panther.mp3', 14)
GO
INSERT [dbo].[Song] ([ID], [Title], [ArtistID], [CategoryID], [FileName], [Duration]) VALUES (6, N'Sons of Anarchy', 3, 4, N'sons_of_anarchy.mp3', 17)
GO
SET IDENTITY_INSERT [dbo].[Song] OFF
GO
ALTER TABLE [dbo].[PlaylistSong]  WITH CHECK ADD  CONSTRAINT [FK_PlaylistSong.PlaylistID_Playlist.ID] FOREIGN KEY([PlaylistID])
REFERENCES [dbo].[Playlist] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PlaylistSong] CHECK CONSTRAINT [FK_PlaylistSong.PlaylistID_Playlist.ID]
GO
ALTER TABLE [dbo].[PlaylistSong]  WITH CHECK ADD  CONSTRAINT [FK_PlaylistSong.SongID_Song.ID] FOREIGN KEY([SongID])
REFERENCES [dbo].[Song] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PlaylistSong] CHECK CONSTRAINT [FK_PlaylistSong.SongID_Song.ID]
GO
ALTER TABLE [dbo].[Song]  WITH CHECK ADD  CONSTRAINT [FK_Song.ArtistID_Artist.ID] FOREIGN KEY([ArtistID])
REFERENCES [dbo].[Artist] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Song] CHECK CONSTRAINT [FK_Song.ArtistID_Artist.ID]
GO
ALTER TABLE [dbo].[Song]  WITH CHECK ADD  CONSTRAINT [FK_Song.CategoryID_Category.ID] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Song] CHECK CONSTRAINT [FK_Song.CategoryID_Category.ID]
GO
USE [master]
GO
ALTER DATABASE [MyTunes] SET  READ_WRITE 
GO
