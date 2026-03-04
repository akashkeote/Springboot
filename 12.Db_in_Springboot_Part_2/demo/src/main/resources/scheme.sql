create table if not exists category(
    id int primary key,
    title varchar(100),
    description varchar(100)
);

create table if not exists course(
    courseId int primary key,
    courseTitle varchar(100),
    courseDescription varchar(255),
    coursePrice int,
    categoryId int,
    constraint fk_course_category
        foreign key (categoryId)
        references category(id)
        on update cascade
        on delete cascade
);