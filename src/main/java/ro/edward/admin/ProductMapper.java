package ro.edward.admin;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setPhoto(resultSet.getString("photo"));
        product.setCatId(resultSet.getInt("category_id"));

        return product;
    }
}
