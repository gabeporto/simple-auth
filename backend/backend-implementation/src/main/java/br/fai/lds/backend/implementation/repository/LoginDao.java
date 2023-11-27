package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;
import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.domain.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao implements LoginRepository {
    @Override
    public UserModel login(String email, String password) {

        final UserModel userModel = new UserModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM account WHERE email = ? AND password = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));

                return userModel;
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
