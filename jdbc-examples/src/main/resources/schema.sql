create table if not exists `user`
(
    id          char(19) not null primary key,
    name        varchar(45),
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);
create table if not exists `address`(
    id char(19) not null primary key ,
    detail varchar(45),
    user_id char(19) ,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index (user_id)
);
create table if not exists `github_user`(
    id   char(19)not null primary key ,
    name  varchar(20),
    followers int default 0,
    stars int default 0,
    gender varchar(10),
    repos int default 0
);
create table if not exists `department`(
    id char(19)not null primary key ,
    name varchar(20) not null ,
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default  current_timestamp on update current_timestamp
);
/**
  将department字段json中depId属性按char(19) UTF8字符集以二进制存储建索引。因此区分大小写
  正确的声明决定是否命中索引
 */
 create  table if not exists `teacher`(
     id char(19)not null primary key ,
     name varchar(10) not null ,
     department json comment '{"depId", "name"}',
     /*-- 定义一个字段 `department`，类型为JSON。注释中提供了一个关于JSON结构的例子，
       表明这个JSON对象应该包含部门ID (`depId`) 和部门名称 (`name`)。*/
     insert_time datetime not null default current_timestamp,
     update_time datetime not null default  current_timestamp on update current_timestamp,
      index ((cast(department ->> '$.depId' as char(19)) collate utf8mb4_bin))
/* -- 创建一个索引，基于 `department` 字段中的 `depId` 值。这里使用了 JSON 提取运算符 `->>` 来获取 `department` 中 `depId` 的字符串值，
   并将其转换为定长字符串（CHAR）以便于创建索引。索引使用的排序规则是 `utf8mb4_bin`，这意味着它区分大小写并且对二进制数据敏感。*/
);

create table if not exists `account`(
    id    char(19) not null primary key ,
    name varchar(20),
    balance float,
    version int default 0
);