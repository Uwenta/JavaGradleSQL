package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    private static Connection getConnections() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getCode() {
        var dataSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnections()) {
            String result = runner.query(conn, dataSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(result);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDB(){
        var conn = getConnections();
        runner.execute(conn,"DELETE FROM auth_codes");
        runner.execute(conn,"DELETE FROM card_transactions");
        runner.execute(conn,"DELETE FROM cards");
        runner.execute(conn,"DELETE FROM users");

    }
}
