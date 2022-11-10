
create database if not exists Jukebox_project;
use Jukebox_project;

create table checking_user(user_Id int primary key auto_increment, user_Name varchar(50) not null, 
password varchar(50) not null,email varchar(50), phone_Number long);
desc checking_user;

create table songs_list(song_Id int primary key AUTO_INCREMENT,
song_Name varchar(50) unique not null, 
artist_Name varchar(50)not null,
album_Name varchar(50) not null,
genre varchar(50)not null,
duration time not null,
song_Path varchar(150) unique not null);
select * from songs_list;

insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Tera-saath-ho-mein','Arijit-Singh','Tamasha','Romantic','5:41','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Tum-Saath-Ho lofisad.wav');
insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Ae-Watan','Arijit-Singh','Raazi','Patriotic','2:56','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\ae-waatan.wav');
insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Pehla-Pyaar','Armaan-Malik','Kabir-Singh','Romantic','4:33','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\pahele-pyar- romantic.wav');
insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Galla-Golliyan','Sukhwinder','Dil-Dharakne-Do','Party','4:16','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\gallan goliyan.wav');
insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Namoh-Namoh ','Amit-Trivedi','Kedarnath','Devotional','5:28','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\NAMOH NAMOH.wav');
insert into songs_list (song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Tera-yaar-Hoo-mein','Arijit-Singh','Sonu-ki-Tweetu-ki-Sweety','Friendship','4:22','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Tera-Yaar-Hoon.wav');
insert into songs_list(song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Laag-Ja-Gale','Lata-Mangeshkar','Woh-kon-thi?','Romantic','3:11','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Lag-Ja-Gale-Se-romantic.wav');
insert into songs_list(song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Deva-Deva','Amitabh Bhattacharya','Brahmastra','Devotional','4:40','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Deva-Deva.wav');
insert into songs_list(song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Raasiya','Shreya-Ghoshal,Tushar-Joshi','Brahmastra','Indian-Film','4:28','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Raasiya.wav');
insert into songs_list(song_Name,artist_Name,album_Name,genre,duration,song_Path) values('Pasoori','Ali Seth, Shae Gill','Pasoori','Hip Hop','4:37','D:\\NIIT JAVA TASK\\CAPSTONE JUKEBOX MAIN FINAL PROJECT\\major_project_jukebox_1\\src\\jukebox songs in wave\\Pasoori.wav');
select*from songs_list;


delete from song_list where song_Id=14;
delete from song_list where song_Id=12;
delete from song_list where song_Id=13;

select * from songs_list where song_id in(select song_id from playlist_display where playlist_id=3);

insert into checking_user(user_Name,password,email,phone_Number) values('rishita','ris1','ris1@gmail.com',889128464);
insert into checking_user(user_Name,password,email,phone_Number) values('rajat','raj1','ris1@gmail.com',889128464);

select * from  checking_user;
create table playlist(playlist_Id int  primary key auto_increment, playList_Name varchar (50), user_Id int, foreign key(user_Id) references checking_user(user_Id));
select *from playlist;
insert into playlist(playlist_Name,user_Id) values('Relaxing',2);
desc playlist;

create table playlist_display(playlist_Id int, foreign key(playList_Id) references playList(playList_Id), 
song_Id int, foreign key(song_Id) references songs_list(song_Id),user_Id int ,foreign key(user_Id) references checking_user(user_Id));

-- insert into playlist_display  values (1,3,1);
select * from playlist_display;


select * from checking_user;
select *from playlist;
select *from playlist_display;


-- delete from songs_list where song_Id=11;
-- delete from songs_list where song_Id=9;
-- delete from songs_list where song_Id=8;
-- delete  from checking_user where user_Id=2;
-- drop table songs_list;
-- drop table playlist_display;
-- drop table playlist;
-- drop table checking_user;
-- drop database Jukebox_project;
--                           ----------------------------------------------------------------------------------












