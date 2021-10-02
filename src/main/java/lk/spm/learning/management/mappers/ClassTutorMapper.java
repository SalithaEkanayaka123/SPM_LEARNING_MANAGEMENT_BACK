package lk.spm.learning.management.mappers;

import lk.spm.learning.management.model.ImageModel;
import lk.spm.learning.management.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassTutorMapper implements RowMapper<ImageModel> {

    @Override
    public ImageModel mapRow(ResultSet resultSet, int i) throws SQLException {
        ImageModel imageModel = new ImageModel();
        //imageModel.setId(resultSet.getString("id"));
        imageModel.setName(resultSet.getString("name"));
//        imageModel.setDescription(resultSet.getString("description"));
//        imageModel.setTutorName(resultSet.getString("tutor_name"));
//        imageModel.setImage(resultSet.getString("image"));
        return imageModel;
    }
}
