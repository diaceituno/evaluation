#Deleting Existing Database
#Comment out if database doesnt exist -> generates error
drop database eval;

#Creating and selecting database eval;
create database eval;
use eval;

#Creating Entities
#Branches
create table branches(
	branchID tinyint not null auto_increment,
	branchName varchar(100),
	
	primary key(branchID)
)
character set=utf8
comment='Stores all Branches in MIQR, uniquely identified by branchID';

#Groups
create table groups(
	groupID tinyint not null auto_increment,
	branchID tinyint,
	groupName varchar(100),
	
	foreign key(branchID) references branches(branchID),
	primary key(groupID)
)
character set=utf8
comment='Stores groups in MIQR with reference to the branch they belong to';

#Polls
create table polls(
	pollID tinyint not null auto_increment,
	pollName varchar(100) not null,
	fxml text,
	
	primary key(pollID)
)
character set=utf8
Comment='Stores all polls and their respective fxml code';

#Users
create table users(
	userID tinyint not null auto_increment,
	groupID tinyint,
	userName varchar(100) not null,
	
	foreign key(groupID) references groups(groupID),
	primary key(userID)
)
character set=utf8
Comment='Stores all users in MIQR and their respective group';

#pollgroup
create table pollsgroups(
	groupID tinyint,
	pollID tinyint,
	
	foreign key(groupID) references groups(groupID),
	foreign key(pollID) references polls(pollID)
)
character set=utf8
Comment='Stores the relationship between the polls and the groups they have been assigned to';

#polltable
create table polltables(
	tableID tinyint not null auto_increment,
	pollID tinyint,
	tableTitle varchar(300),
	
	foreign key(pollID) references polls(pollID),
	primary key(tableID,pollID)
)
character set=utf8
Comment='Stores the different multiple choice tables in a poll';

#pollinput
create table pollinputs(
	inputID tinyint not null auto_increment,
	pollID tinyint,
	inputQuestion varchar(300),
	
	foreign key(pollID) references polls(pollID),
	primary key(inputID, pollID)
)
character set=utf8
Comment='Stores the different text input fields in a poll';

#tablequestions
create table tablequestions(
	questionID tinyint not null auto_increment,
	tableID tinyint,
	question varchar(300),
	
	foreign key(tableID) references polltables(tableID),
	primary key(questionID)
)
character set=utf8
Comment='Stores the different question within a multiple choice table';

#tableanswers
create table tableanswers(
	answerID tinyint not null auto_increment,
	tableID tinyint,
	answer varchar(300),
	value tinyint,
	
	foreign key(tableID) references polltables(tableID),
	primary key(answerID)
)
character set=utf8
Comment='Stores the different answers within a multiple choice question and their given values';

#USER INPUTS
#useranswers
create table useranswers(
	userID tinyint,
	questionID tinyint,
	answerID tinyint,
	
	foreign key(userID) references users(userID),
	foreign key(questionID) references tablequestions(questionID),
	foreign key(answerID) references tableanswers(answerID)
)
character set=utf8
Comment='Stores the users answer to a question within a multiple choice table';

#userinputs
create table userinputs(
	userID tinyint,
	inputID tinyint,
	input text
)

character set=utf8
Comment='Stores the users textual inputs';










