package ro.edward.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ProductStorage productStorage;

    @Autowired
    SecuritySession securitySession;


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView product(@RequestParam(value = "id", required = false)Integer id){
        ModelAndView modelAndView = new ModelAndView("add");
        if(id != null) {
            Product product = productStorage.getById(id);
            modelAndView.addObject("product", product);
        }else{
            Product product = new Product();
            modelAndView.addObject("product", product);
        }



        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showAll() {
        if (!securitySession.isUserLogged()) {
            return new ModelAndView("redirect:/index.html");
        }
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("logged",securitySession.isUserLogged());
        modelAndView.addObject("products", productStorage.getAllProducts());
        return modelAndView;
    }

    @GetMapping("/delete-action")
    public String delete(@RequestParam("id") int id) {
        productStorage.deleteProductById(id);
        return "redirect:/products";
    }



    @GetMapping("/create-action")
    public String create(
            @RequestParam(value = "id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("quantity") int quantity,
            @RequestParam("photo") String photo,
            @RequestParam("catId") int catId
    ) {

        if(id == 0) {

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setPhoto(photo);
            product.setCatId(catId);

            productStorage.saveProduct(product);
        }else{

            productStorage.updateProduct(id, name, description, price, quantity, photo, catId);
        }
        return "redirect:/products";
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        securitySession.userNotLogged();
        ModelAndView modelAndView = new ModelAndView("index.html");
        return modelAndView;
    }

}
