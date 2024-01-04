package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteOrganizationLoader implements OrganizationLoader {
    private final Connection connection;

    public SqliteOrganizationLoader(Connection connection) {
        this.connection = connection;
    }

    private static final String querryAllSql = "SELECT organization_id AS id, name, website, country, description, founded, industry, num_employees " +
            "FROM organization";

    @Override
    public List<Organization> loadAll() {
        try {
            return load(ResultSetOf(querryAllSql));
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    private List<Organization> load(ResultSet resultSet) throws SQLException {
        List<Organization> list = new ArrayList<>();
        while (resultSet.next()) list.add(OrganizationFrom(resultSet));
        return list;
    }

    private Organization OrganizationFrom(ResultSet resultSet) throws SQLException {
        return new Organization(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("website"),
                resultSet.getString("country"),
                resultSet.getString("description"),
                resultSet.getInt("founded"),
                resultSet.getString("industry"),
                resultSet.getInt("num_employees")
        );
    }

    private ResultSet ResultSetOf(String querryAllSql) throws SQLException {
        return connection.createStatement().executeQuery(querryAllSql);
    }
}
