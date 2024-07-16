create table users(

    id bigint not null auto_increment,
    username varchar(100) not null unique,
    email varchar(255) not null,
    password varchar(255) not null,
    active boolean not null,

    primary key(id)
 );
