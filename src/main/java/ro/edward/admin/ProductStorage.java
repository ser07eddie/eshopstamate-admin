package ro.edward.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductStorage {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Product getById(Integer id) {
        ProductMapper productMapper = new ProductMapper();
        List<Product> productList = jdbcTemplate.query("select * from product where id ="+id, productMapper);
        return productList.get(0);
    }

    public void deleteProductById(Integer id) {
        jdbcTemplate.execute("delete from product where id =" +id);
    }

    public void updateProduct(Integer id, String newName, String newDescription, BigDecimal newPrice, int newQuantity, String newPhoto, int newCatId){
        Object[] params = new Object[7];

        params[0] = newName;
        params[1] = newDescription;
        params[2] = newPrice;
        params[3] = newQuantity;
        params[4] = newPhoto;
        params[5] = newCatId;
        params[6] = id;
        jdbcTemplate.update("update product set name=?, description=?, price=?, quantity=?, photo=?, category_id=? where id=?",params);
    }

    public void saveProduct(Product product) {

        Object[] params = new Object[6];
        params[0] = product.getName();
        params[1] = product.getDescription();
        params[2] = product.getPrice();
        params[3] = product.getQuantity();
        params[4] = product.getPhoto();
        params[5] = product.getCatId();

        jdbcTemplate.update("insert into product values(null,?,?,?,?,?,?);",params);
    }

    public List<Product> getAllProducts(){
        return jdbcTemplate.query("select * from product;",new ProductMapper());
    }

}
