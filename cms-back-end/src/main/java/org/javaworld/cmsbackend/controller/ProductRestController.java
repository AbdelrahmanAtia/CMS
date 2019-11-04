package org.javaworld.cmsbackend.controller;

import java.util.List;
import org.javaworld.cmsbackend.constants.Constants;
import org.javaworld.cmsbackend.entity.Product;
import org.javaworld.cmsbackend.model.Response;
import org.javaworld.cmsbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/products")
	public List<Product> getProducts(@RequestParam String searchTerm,
									 @RequestParam int categoryId,
									 @RequestParam int pageNumber, 
									 @RequestParam int pageSize) {

		
		return productService.getProducts(searchTerm, categoryId, pageNumber, pageSize);
	}



	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		Product product = productService.findById(productId);
		if (product == null) {
			throw new RuntimeException("Product id not found - " + productId);
		}
		return product;
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		product.setId(0); // force creating a new entity
		productService.save(product);
		return product;
	}

	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		productService.save(product);
		return product;
	}

	@DeleteMapping("/products/{productId}")
	public Response deleteProduct(@PathVariable int productId) {
		Product tempProduct = productService.findById(productId);

		if (tempProduct == null) {
			return new Response(Constants.NOT_FOUND_STATUS, "Product id not found - " + productId);
			// throw new RuntimeException("Product id not found - " + productId);
		}

		productService.deleteById(productId);
		return new Response(Constants.OK_STATUS, "Deleted product id - " + productId);
	}

}
