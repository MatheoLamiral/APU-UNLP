package org.example.blogdemensajes.persistence.emf;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class MyDataSource {
    private static MysqlDataSource dataSource = null;

    private MyDataSource(){}

    public static DataSource getDataSource() {
        if(dataSource == null){
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/db_blog_mensajes?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("admin");
            dataSource.setPassword("admin");
        }
        return dataSource;
    }
}
