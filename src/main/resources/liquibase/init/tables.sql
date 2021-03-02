create table Book(
                     book_id serial primary key,
                     isbn bigint not null unique,
                     heading text not null,
                     author text not null,
                     release_year smallint not null,
                     publisher text not null,
                     genre integer not null,
                     description text not null,
                     cost float4 not null);

create table Genre(
                      genre_id integer primary key,
                      genre_name text not null);

create table Image(
                      book_id integer primary key,
                      name varchar(36) not null,
                      type varchar(36) not null,
                      image bytea not null);

create table Store_user(
                           user_id serial primary key,
                           username text not null,
                           email text not null,
                           password varchar(255) not null,
                           role text not null);

create table Shopping_list(
                              shopping_list_id serial primary key,
                              user_id integer not null,
                              book_id integer not null);

alter table Book
    add constraint book_genre_fk foreign key (genre)
        references Genre (genre_id)
        on update cascade on delete restrict;

alter table Image
    add constraint image_book_fk foreign key (book_id)
        references Book (book_id)
        on update cascade on delete cascade;

alter table Shopping_list
    add constraint shopping_list_store_user_fk foreign key (user_id)
        references Store_user (user_id)
        on update cascade on delete cascade;

alter table Shopping_list
    add constraint shopping_list_book_fk foreign key (book_id)
        references Book (book_id)
        on update cascade on delete cascade;
