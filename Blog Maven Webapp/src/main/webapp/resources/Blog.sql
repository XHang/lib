create database Blog;
use  Blog;
create table user(
id int primary key auto_increment,
username varchar(20),
password varchar(20),
email varchar(20),
valid tinyint(1),
mood varchar(50),
activationcode varchar(255)
);


create table category(
id int primary key auto_increment,
context varchar(25),
userid int ,
constraint category_userid_fk foreign key(userid) references user(id)
);



create table article(
id int primary key auto_increment,
title varchar(20),
context text,
userid int,
god int,
bad int,
categoryid int,
data DateTime,
hide tinyint(1),
constraint useridd_fk foreign key(userid) references user(id),
constraint article_categoryid_fk foreign key(categoryid) references category(id)	
);



create table discuss(
id int primary key auto_increment,
pid int,
context varchar(25),
userid int,
Articleid int,
data DateTime,
constraint discuss_userid_fk foreign key(userid) references user(id),
constraint discuss_Articleid_fk foreign key(Articleid) references Article(id)
);

create table friendship(
id int primary key auto_increment,
userid int,
friendid int,
constraint friendship_userid_fk foreign key(userid) references user(id),
constraint friendship_friendid_fk foreign key(friendid) references user(id)
);


create table message(
userid int primary key auto_increment,
sendid int,
msg varchar(255),
remind tinyint(1),
constraint message_userid_fk foreign key(userid) references user(id)
);

select user.id ,user2.id ,user2.username from user join (
select user2.id id ,user2.username username from user as user2 join 
(select friend.userid userid,friend.friendid friendid from user join friendship as friend on (friend.userid=user.id or friend.friendid=user.id)  where user.id=1) as friend
on ((user2.id=friend.userid or user2.id=friend.friendid) and user2.id<>1 )) as user2  where  user.id=1;

