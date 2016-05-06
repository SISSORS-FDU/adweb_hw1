package adweb.dao;

import adweb.bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 张亚中 on 2016-05-05.
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user(account, name, password, age)VALUES(?, ?, ?, ?)",
                new Object[]{user.getAccount(), user.getName(), user.getPassword(), user.getAge()},
                new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
                        java.sql.Types.INTEGER});
    }

    public User query(String account) {
        try {
            User user = (User) jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE account=?", new Object[]{account},
                    new int[]{java.sql.Types.VARCHAR}, new UserRowMapper());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public void update(User user) {
        jdbcTemplate.update("update user set account=?, name=?, password=?, age=? where id=? ",
               user.getAccount(), user.getName(), user.getPassword(), user.getAge(), user.getId());
    }

}

class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet set, int index) throws SQLException {
        User user = new User(set.getInt("id"), set.getString("account"), set.getString("name"), set.getString("password"),
                set.getInt("age"));
        return user;
    }
}