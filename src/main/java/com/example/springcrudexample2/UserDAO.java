package com.example.springcrudexample2;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class UserDAO {
    JdbcTemplate template;

    @Autowired
    public UserDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int save(User user){
        String sql = "insert into userCRUD_table (name, password, email, sex, country) values('"+user.getName()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getSex()+"', '"+user.getCountry()+"')";
        return template.update(sql);
    }

    public List<User> getAll(){
        return template.query("select * from userCRUD_table", (rs, row) -> {
            User user=new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setSex(rs.getString(4));
            user.setCountry(rs.getString(5));
            return user;
        });
    }

    public User getOne(int id){
        String sql = "select * from userCRUD_table where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }

    public int edit(User user){
        String sql="update userCRUD_table set name='"+user.getName()+"', password='"+user.getPassword()+"' ,email='"+user.getEmail()+"', sex='"+user.getSex()+"', country='"+user.getCountry()+"' where id="+user.getId()+"";
        return template.update(sql);
    }

    public int delete(int id){
        String sql = "delete from userCRUD_table where id=" + id + "";
        return template.update(sql);
    }
}
