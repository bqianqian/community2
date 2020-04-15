##社区交流平台

##资料
[spring 文档](https://spring.io/guides )
[spring web](https://spring.io/guides/gs/serving-web-content/)
[es 社区](https://elasticsearch.cn/)
[github oauth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[github okhttp获取用户信息]https://square.github.io/okhttp/
[mvn 库](http://ww1.mvnrespository.com/)
[spring boot连接池官方文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
##工具
git<br>
https://www.visual-paradigm.com/cn/


##sql
'''
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(50),
    TOKEN varchar(36),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint
)


CREATE USER IF NOT EXISTS sa PASSWORD '123';

ALTER USER sa admin true ;
'''