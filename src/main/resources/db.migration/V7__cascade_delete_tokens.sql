alter table tokens drop constraint tokens_pkey;
alter table tokens add foreign key (user_id) references users(id) on delete cascade;