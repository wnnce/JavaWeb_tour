create table t_scenic
(
    id          int auto_increment comment '景点id'
        primary key,
    title       varchar(50)   null comment '景点标题',
    content     longtext      null comment '景点详情',
    scenic_img  varchar(100)  null comment '景点图片',
    scenic_date datetime      null comment '景点时间',
    click_num   int default 0 null
)
    comment '景点表';

create table t_user
(
    id          int auto_increment comment '用户ID'
        primary key,
    username    varchar(20) null comment 'username',
    password    varchar(20) null comment '密码',
    create_date datetime    null comment '创建时间'
)
    comment '用户表';

create table t_comment
(
    id           int auto_increment comment '评论id'
        primary key,
    content      text     null comment '评论详情',
    comment_date datetime null comment '评论时间',
    user_id      int      null comment '用户id',
    scenic_id    int      null comment '景点id',
    constraint t_comment_ibfk_1
        foreign key (user_id) references t_user (id),
    constraint t_comment_ibfk_2
        foreign key (scenic_id) references t_scenic (id)
)
    comment '评论表';

create index scenic_id
    on t_comment (scenic_id);

create index user_id
    on t_comment (user_id);
