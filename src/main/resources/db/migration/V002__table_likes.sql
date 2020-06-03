create table if not exists likes
(
	id serial not null
		constraint likes_pk
			primary key,
	who_id integer not null,
	whom_id integer not null,
	action boolean not null
);