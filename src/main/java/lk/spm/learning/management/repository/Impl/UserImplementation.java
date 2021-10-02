package lk.spm.learning.management.repository.Impl;
import lk.spm.learning.management.model.*;
import lk.spm.learning.management.mappers.ClassTutorMapper;
import lk.spm.learning.management.mappers.PersonMapper;
import lk.spm.learning.management.repository.loginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Permission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserImplementation implements loginUserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String validateUser(User user) {
        String sql = "SELECT type FROM users WHERE username=? AND password=?";
        try {
            String type = jdbcTemplate.queryForObject(sql, new Object[] {
                    user.getUsername(), user.getPassword() }, String.class);
            return type;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getStudentList() {
        String sql = "SELECT * FROM users WHERE type = 'student' order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }

    @Override
    public List<User> getTeacherList() {
        String sql = "SELECT * FROM users WHERE type = 'teacher' order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }


    @Override
    public List<User> getValidTeacherList() {
        String sql = "SELECT * FROM users WHERE type = 'teacher' and status = 'valid' order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }

    @Override
    public List<User> getInvalidTeacherList() {
        String sql = "SELECT * FROM users WHERE type = 'teacher' and status = 'invalid' order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }

    @Override
    public List<User> getPendingTeacherList() {
        String sql = "SELECT * FROM users WHERE type = 'teacher' and status = 'pending' order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }

    //get Tutor count
    public List<TutorCountData> getTutorListFromClasses() {
        String sql = "SELECT name,COUNT(tutor_name) AS tutorCount FROM images GROUP BY name";
        return namedParameterJdbcTemplate.query(sql, (rs, i) -> permissionMapperNew(rs));
    }

    private TutorCountData permissionMapperNew(ResultSet rs) throws SQLException {
       TutorCountData data = new TutorCountData();
       data.setClassName(rs.getString("name"));
       data.setCount(rs.getString("tutorCount"));
        return data;
    }

    //get Announcement count
    public List<AnnouncementCountData> getAnnListFromClasses() {
        String sql = "SELECT name,COUNT(header) AS count FROM announcements GROUP BY name";
        return namedParameterJdbcTemplate.query(sql, (rs, i) -> permissionMapperAnn(rs));
    }

    private AnnouncementCountData permissionMapperAnn(ResultSet rs) throws SQLException {
        AnnouncementCountData data = new AnnouncementCountData();
        data.setClassName(rs.getString("name"));
        data.setCount(rs.getString("count"));
        return data;
    }


    @Override
    public List<User> getUserList() {
        String sql = "SELECT * FROM users order by id";
        List<User> users = jdbcTemplate.query(sql, new PersonMapper());
        System.out.println(users);
        return users;
    }

    @Override
    public String getTeacherStatus(User user) {
        String sql = "SELECT status FROM users WHERE username=? AND password=?";
        try {
            String status = jdbcTemplate.queryForObject(sql, new Object[] {
                    user.getUsername(), user.getPassword() }, String.class);
            return status;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getUserID(User user) {
        String sql = "SELECT id FROM users WHERE username=? AND password=?";
        try {
            String id = jdbcTemplate.queryForObject(sql, new Object[] {
                    user.getUsername(), user.getPassword() }, String.class);
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * Tutor functionality.
     *
     **/

    public List<ChartData> getGraphData () {
        String sql = "SELECT COUNT(DISTINCT id) AS count, course AS category\n" +
                "FROM files\n" +
                "GROUP BY course";
           return namedParameterJdbcTemplate.query(sql, (rs, i) -> permissionMapper(rs));
    }

    private ChartData permissionMapper(ResultSet rs) throws SQLException {
        ChartData data = new ChartData();
        data.setCategory(rs.getString("category"));
        data.setCount(rs.getString("count"));
        return data;
    }
}
