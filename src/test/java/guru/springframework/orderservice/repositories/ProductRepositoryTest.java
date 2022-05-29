package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1");

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }
    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setDescription("descr");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());

        Product fetchedProduct = productRepository.getById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getId());
        assertEquals(product.getDescription(), fetchedProduct.getDescription());
        assertEquals(product.getProductStatus(), fetchedProduct.getProductStatus());
    }
}
