create table question
(
	id int auto_increment
	primary key,
	title varchar(200) null,
	description text null,
	gmt_create datetime null,
	gmt_modified datetime null,
	creator bigint null,
	comment_count int null,
	view_count int null,
	like_count int null,
	tag varchar(100) null
);

create unique index question_id_uindex
	on question (id);

