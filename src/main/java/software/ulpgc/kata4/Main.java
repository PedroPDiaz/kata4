package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:dataset.db")){
            OrganizationLoader loader = new SqliteOrganizationLoader(connection);
            List<Organization> organizations = loader.loadAll();
            for (Organization organization : organizations) {
                System.out.println(organization);
            }
        }


    }
}
