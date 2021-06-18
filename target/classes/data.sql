CREATE TABLE IF NOT EXISTS thread (
	id LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
	threadName VARCHAR(255) NOT NULL,
	employee VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS messages  (
	id LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
	threadId LONG NOT NULL,
	message VARCHAR (255) NOT NULL,
	dates DATE,
	times TIME,
	employee VARCHAR(255),
	FOREIGN KEY (threadId) REFERENCES thread (id)

);

insert into thread (threadName, employee) VALUES ('Work', 'Andrew');
insert into thread (threadName, employee) VALUES ('Meetings', 'Andrew');
insert into thread (threadName, employee) VALUES ('Help Desk', 'Nikolai');

insert into messages (threadId, message, dates, times, employee) VALUES ('1', 'Discuss work related issues here', '2020-10-28', '19:45:00', 'Andrew');
insert into messages (threadId, message, dates, times, employee) VALUES ('2', 'Plan meetings here', '2020-10-28', '20:00:00', 'Andrew' );
insert into messages (threadId, message, dates, times, employee) VALUES ('3', 'Need help? Ask here', '2020-10-28', '21:30:00', 'Nikolai');
insert into messages (threadId, message, dates, times, employee) VALUES ('3', 'Hey Nik, can you help me with issue on Eclipse?', '2020-10-29', '10:30:00', 'Robert');
insert into messages (threadId, message, dates, times, employee) VALUES ('1', 'Hey Andrew, should we start working on the design class diagram for the NORVS client?', '2020-10-28', '19:48:00', 'Michael');
insert into messages (threadId, message, dates, times, employee) VALUES ('1', 'Sounds good Michael, set up a meeting after with the others to discuss. Thanks', '2020-10-28', '19:50:00', 'Andrew');

