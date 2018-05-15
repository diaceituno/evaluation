#Deleting Existing Database
#Comment out if database doesnt exist -> generates error
drop database eval;

#Creating and selecting database eval;
create database eval;
use eval;

#Creating Entities
#Branches
create table branches(
	branchID smallint not null auto_increment,
	branchName varchar(100),
	
	primary key(branchID)
)
character set=utf8
comment='Stores all Branches in MIQR, uniquely identified by branchID';

#Groups
create table `groups`(
	groupID smallint not null auto_increment,
	branchID smallint,
	groupName varchar(100),
	
	foreign key(branchID) references branches(branchID),
	primary key(groupID)
)
character set=utf8
comment='Stores groups in MIQR with reference to the branch they belong to';

#Polls
create table polls(
	pollID smallint not null auto_increment,
	branchID smallint,
	pollName varchar(100) not null,
	fxml text,
	
	foreign key(branchID) references branches(branchID),
	primary key(pollID)
)
character set=utf8
Comment='Stores all polls and their respective fxml code';

#Users
create table `users`(
	userID smallint not null auto_increment,
	groupID smallint,
	userName varchar(100) not null unique,
	
	foreign key(groupID) references `groups`(groupID),
	primary key(userID)
)
character set=utf8
Comment='Stores all users in MIQR and their respective group';

#pollgroup
create table pollsgroups(
	groupID smallint,
	pollID smallint,
	
	foreign key(groupID) references `groups`(groupID),
	foreign key(pollID) references polls(pollID)
)
character set=utf8
Comment='Stores the relationship between the polls and the groups they have been assigned to';

#polltable
create table polltables(
	tableID smallint not null auto_increment,
	pollID smallint,
	tableTitle varchar(300),
	
	foreign key(pollID) references polls(pollID),
	primary key(tableID,pollID)
)
character set=utf8
Comment='Stores the different multiple choice tables in a poll';

#pollinput
create table pollinputs(
	inputID smallint not null auto_increment,
	pollID smallint,
	inputQuestion varchar(300),
	
	foreign key(pollID) references polls(pollID),
	primary key(inputID, pollID)
)
character set=utf8
Comment='Stores the different text input fields in a poll';

#tablequestions
create table tablequestions(
	questionID smallint not null auto_increment,
	tableID smallint,
	question varchar(300),
	
	foreign key(tableID) references polltables(tableID),
	primary key(questionID)
)
character set=utf8
Comment='Stores the different question within a multiple choice table';

#tableanswers
create table tableanswers(
	answerID smallint not null auto_increment,
	tableID smallint,
	answer varchar(300),
	value smallint,
	
	foreign key(tableID) references polltables(tableID),
	primary key(answerID)
)
character set=utf8
Comment='Stores the different answers within a multiple choice question and their given values';

#USER INPUTS
#useranswers
create table useranswers(
	userID smallint,
	questionID smallint,
	answerID smallint,
	
	foreign key(userID) references users(userID),
	foreign key(questionID) references tablequestions(questionID),
	foreign key(answerID) references tableanswers(answerID)
)
character set=utf8
Comment='Stores the users answer to a question within a multiple choice table';

#userinputs
create table userinputs(
	userID smallint,
	inputID smallint,
	input text
)

character set=utf8
Comment='Stores the users textual inputs';










