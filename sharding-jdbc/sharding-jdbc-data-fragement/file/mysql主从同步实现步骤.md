# 主库和从库的配置

## 主库
~~~ini
#开启日志
log-bin = mysql-bin
#设置服务id，主从不能一致
server-id = 1
#设置需要同步的数据库
binlog-do-db=store_db
binlog-do-db=product_db_1
binlog-do-db=product_db_2
#屏蔽系统库同步
binlog-ignore-db=mysql
binlog-ignore-db=information_schema
binlog-ignore-db=performance_schema
~~~

## 从库
> 如果从库是和主库在一台服务器上，要修改从库的端口号，如果不是一台机器上，那么就无所谓了
> 如果是多台从库，依次配置作为参考，依次对不同的从库进行配置即可

~~~ini
#设置3307端口 如果是和主库在同一台服务器上，那么就需要修改端口号
# port = 3307
# 设置mysql数据库的数据的存放目录(该目录不一定在mysql安装目录下)
datadir=xxx
#开启日志
log-bin = mysql-bin
#设置服务id，主从不能一样
server-id = 2
#设置需要同步的数据库
replicate_wild_do_table=store_db.%
replicate_wild_do_table=product_db_1.%
replicate_wild_do_table=product_db_2.%
#屏蔽系统库同步
replicate_wild_ignore_table=mysql.%
replicate_wild_ignore_table=information_schema.%
replicate_wild_ignore_table=performance_schema.%
~~~

# 授权主从复制专用账号
1. 登录主库
2. 执行如下命令
~~~shell script
#授权主从复制专用账号 %表示是允许从库对本机(主库当前所在的机器)进行复制
GRANT REPLICATION SLAVE ON *.* TO '用户名'@'%' IDENTIFIED BY '密码';
#刷新权限
FLUSH PRIVILEGES;
~~~

# 设置从库同步数据
0. 如果此前从库已经有主库指向，可以执行如下命令清空
~~~shell script
STOP SLAVE IO_THREAD FOR CHANNEL '';
reset slave all;
~~~
1. 查看主库的确认位点,记录Position返回的值和File返回的值
~~~shell script
# 需要登录主库执行如下命令
show master status;
~~~
2. 登录从库,设置位点
~~~shell script
CHANGE MASTER TO
master_host = 'localhost',
master_user = '授权账号时设置的用户名',
master_password = '授权账号时设置的密码',
master_log_file = 'File返回的值',
master_log_pos = Position返回的值;
~~~
3. 重启主库和从库
4. 登录从库,执行如下命令查询状态
~~~shell script
show slave status\G
#执行该命令后，确认Slave_IO_Runing以及Slave_SQL_Runing两个状态位是否为“Yes”，
# 如果不为 Yes，请检查error_log，然后排查相关异常。
~~~