Drop table User_account;
Drop table Employee_account;
Drop table Gift_card;
Drop table Movies;
Drop table Cinema;
Drop table Screen;
Drop table Upcoming_week;
Drop table Current_week;
Drop table Transaction;

create table User_account(
    uid VARCHAR(20) primary key,
    name VARCHAR(50) not null,
    cardno CHAR(20) default 'no record',
    password VARCHAR(50)
);

INSERT INTO User_account (uid,name,password)
VALUES ('charles1','Charles','123456')
,('sergio1','Sergio','123456')
,('kasey1','Kasey','123456')
,('vincent1','Vincent','123456')
,('ruth1','Ruth','123456')
,('donald1','Donald','123456');

create table Employee_account(
    eid VARCHAR(20) primary key,
    name VARCHAR(50) not null,
    type VARCHAR(50) not null,
    password VARCHAR(50)
);

INSERT INTO Employee_account (eid,name,password, type)
VALUES ('admin','Kittibhumi','123456','manager')
,('b1','B','123456','staff')
,('karl1','Karl','123456','staff')
,('carl1','Carl','123456','staff')
,('daniel1','Daniel','123456','staff')
,('duck1','Duck','123456','staff');

CREATE TABLE Gift_card(
    gid SERIAL primary key,
    serial_no CHAR(16) not null,
    value DOUBLE not null,
    status boolean
);

INSERT INTO Gift_card (serial_no, value, status)
VALUES ('GC12345678901234',10.00,0)
,('GC12345678901235',200.00,0)
,('GC12345678901236',25.00,0)
,('GC12345678901237',50.00,0)
,('GC12345678901238',80.00,0)
,('GC12345678901239',100.00,0);

create table Movies(
    mid SERIAL primary key,
    title VARCHAR(50) not null,
    synopsis VARCHAR(1000) , 
    classification VARCHAR(100) ,
    release_date VARCHAR(100) ,
    director VARCHAR(50) ,
    cast VARCHAR(500) 

);

INSERT INTO Movies (title,synopsis,classification,release_date,director,cast)
VALUES ('Titanic','In 1996, aboard the research vessel Akademik Mstislav Keldysh, Brock Lovett and his team search the wreck of RMS Titanic. They recover a safe they hope contains a necklace with a large diamond known as the Heart of the Ocean. Instead, they only find a drawing of a young nude woman wearing the necklace. The sketch is dated April 14, 1912, the same day the Titanic struck an iceberg.[Note 1] Rose Dawson Calvert, the woman in the drawing, and her granddaughter, are brought aboard Keldysh. Rose recounts her experiences aboard Titanic.', 'General (G)','11/1/1997','James Cameron','Leonardo DiCaprio, Kate Winslet, Billy Zane, Kathy Bates, Frances Fisher, Bernard Hill, Jonathan Hyde, Danny Nucci, David Warner, Bill Paxton')
,('Spider-Man: Far From Home','In Ixtenco, Mexico, Nick Fury and Maria Hill investigate an unnatural storm and encounter the Earth Elemental. Quentin Beck, a super-powered individual, arrives to defeat the creature. Beck subsequently defeats the Elemental and is recruited by Fury and Hill. In New York City, the Midtown School of Science and Technology completes its year, which was restarted to accommodate the students who previously disintegrated five years earlier as a result of Thanos'' actions','Parental Guidance (PG)','7/2/2019','Jon Watts','Tom Holland, Samuel L. Jackson, Zendaya, Cobie Smulders, Jon Favreau')
,('Aladin','Aladdin, a street urchin in the Arabian city of Agrabah, and his monkey Abu meet Princess Jasmine, who has snuck away from her sheltered life in the palace. Jasmine wishes to succeed her father as Sultan, but is instead expected to marry one of her royal suitors, including the charming yet dimwitted Prince Anders. Jafar, the grand vizier, schemes to overthrow the Sultan and seeks a magic lamp hidden in the Cave of Wonders, but only "the diamond in the rough" can enter the cave.','Mature (M)','5/24/2019','Guy Ritchie','Will Smith, Mena Massoud, Naomi Scott, Marwan Kenzari, Navid Negahban, Nasim Pedrad, Billy Magnussen')
,('Wolf of wall street','In 1987, Jordan Belfort lands a job as a Wall Street stockbroker for L.F. Rothschild, employed under Mark Hanna. He is quickly enticed into the drug-fueled stockbroker culture and Hanna''s belief that a broker''s only goal is to make money for himself. Jordan loses his job following Black Monday, the largest one-day stock market drop in history, and takes a job at a boiler room brokerage firm on Long Island that specializes in penny stocks. Thanks to his aggressive pitching style and the high commissions, Jordan makes a small fortune.','Restricted (R18+)','12/7/21','Martin Scorsese','Leonardo DiCaprio, Jonah Hill, Margot Robbie, Matthew McConaughey, Kyle Chandler')
,('Octopus Game','Movie adaption of everyone favorite TV-Serie. Seong Gi-hun, a divorced and indebted chauffeur, is invited to play a series of children''s games for a chance at a large cash prize. Accepting the offer, he is taken to an unknown location where he finds himself among 456 players who are all deeply in debt. The players are made to wear green tracksuits and are kept under watch at all times by masked guards in pink jumpsuits, with the games overseen by the Front Man, who wears a black mask and black uniform.','Restricted (R18+)', '10/10/2021','Hwang Dong-hyuk','Lee Jung-jae, Park Hae-soo, Wi Ha-joon, Jung Ho-yeon, O Yeong-su, Heo Sung-tae')
,('Doraemon: Nobita and the Haunts of Evil','One day, Nobita has stuck in the school again, his teacher not letting him to go (He hadn''t completed his homework). While his teacher had allowed him to go, Nobita rushed to his house, fearing that his mother would scold him. On his way, he found a dog, dirty and hungry, after fighting with a dog the previous night. Nobita wanted to help it, but he couldn''t.','Mature Accompanies (MA15+)','13/3/1982','Hideo Nishimaki','Nobuyo Ōyama, Noriko Ohara, Michiko Nomura, Kaneta Kimotsuki, Kazuya Tatekabe');

create table Cinema(
    cid SERIAL primary key,
    clocation VARCHAR(50) not null
);

INSERT INTO Cinema (clocation)
VALUES ('Broadway')
,('Darling Habour')
,('China town')
,('Bondi Junction')
,('Parramatta')
,('Rhodes');

create table Upcoming_week(
    sid serial primary key,
    mid int references Movies(mid),
    cid int references Cinema(id),
    screen_type VARCHAR(100) not null,
    movie_time datetime not null,
    booked_front_seat int default 0,
    booked_middle_seat int default 0,
    booked_back_seat int default 0
);

create table Current_week(
    sid serial primary key,
    mid int references Movies(mid),
    cid int references Cinema(id),
    screen_type VARCHAR(100) not null,
    movie_time datetime not null,
    booked_front_seat int default 0,
    booked_middle_seat int default 0,
    booked_back_seat int default 0
);

INSERT INTO Current_week (mid,cid,screen_type,movie_time)
VALUES (1,1,'bronze','2021-10-17 10:30:00')
,(1,1,'gold','2021-10-17 10:30:00')
,(2,2,'silver','2021-10-17 10:30:00')
,(3,3,'bronze','2021-10-17 10:30:00')
,(1,4,'gold','2021-10-17 10:30:00')
,(2,1,'silver','2021-10-17 13:30:00')
,(3,1,'bronze','2021-10-17 13:30:00')
,(4,2,'gold','2021-10-17 13:30:00')
,(3,3,'silver','2021-10-17 13:30:00')
,(4,4,'bronze','2021-10-17 13:30:00')
,(4,1,'gold','2021-10-17 17:30:00')
,(2,1,'silver','2021-10-17 17:30:00')
,(1,2,'bronze','2021-10-17 17:30:00')
,(3,3,'gold','2021-10-17 17:30:00')
,(1,4,'bronze','2021-10-17 17:30:00');


create table Transaction(
    tid serial primary key,
    status VARCHAR(50) not null,
    detail VARCHAR(1000),
    date_time DATETIME,
    user_id VARCHAR(100)
);

INSERT INTO Transaction (status,detail)
VALUES ('Successful','test');