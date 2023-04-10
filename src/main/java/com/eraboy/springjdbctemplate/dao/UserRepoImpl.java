package com.eraboy.springjdbctemplate.dao;

import com.eraboy.springjdbctemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    private static final String INSERT_USER_QUERY = "INSERT INTO user VALUES(?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE USER SET fname=? WHERE id=?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE id=?";
    private static final String GET_USERS_QUERY = "SELECT * FROM user";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getFname(), user.getLname(), user.getEmail());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, user.getFname(), user.getId());
        return user;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowNum) -> {
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        });
    }

    @Override
    public String deleteById(int id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
        return "User got deleted with id" + id;
    }

    @Override
    public List<User> allUsers() {
        return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) -> {
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        });
    }
}
