package org.javaworld.cmsbackend.controller;

import java.io.IOException;
import java.util.List;

import org.javaworld.cmsbackend.entity.Category;
import org.javaworld.cmsbackend.model.Response;
import org.javaworld.cmsbackend.service.AttachmentService;
import org.javaworld.cmsbackend.service.CategoryService;
import org.javaworld.cmsbackend.validator.OnCreate;
import org.javaworld.cmsbackend.validator.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(value = "/api")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttachmentService attachmentService;

	@GetMapping("/categories/all")
	public List<Category> getAllCategories() {
		return categoryService.findAll();
	}

	@GetMapping("/categories")
	public List<Category> getCategories(@RequestParam String searchTerm, 
										@RequestParam int pageNumber,
										@RequestParam int pageSize) {
		return categoryService.getCategories(searchTerm, pageNumber, pageSize);
	}

	@GetMapping("/categories/{categoryId}")
	public Category getCategory(@PathVariable int categoryId) {
		return categoryService.getCategoryById(categoryId);
	}

	@PostMapping("/categories")
	public Category addCategory(@Validated(value = {OnCreate.class}) 
			                    @RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	@PutMapping("/categories")
	public Category updateCategory(@Validated(value = {OnUpdate.class})
			                       @RequestBody Category category) {
		return categoryService.updateCategory(category);
	}

	@DeleteMapping("/categories/{categoryId}")
	public Response deleteCategory(@PathVariable int categoryId) {
		return categoryService.deleteCategoryById(categoryId);
	}
	
	@GetMapping("/categories/categories_images/{imageName}")
	public void getCategoryImage() throws IOException {
		attachmentService.getAttachment();
	}

	@DeleteMapping("/categories/deleteImage/{imageName}")
	public Response deleteCategoryImage(@PathVariable String imageName) {
		return categoryService.deleteCategoryImage(imageName);
	}
	
	@GetMapping("/categories/isNameExist/{categoryName}/{categoryId}")
	public boolean isUniqueEmail(@PathVariable String categoryName, @PathVariable int categoryId) {
		return categoryService.isUniqueCategoryName(categoryName, categoryId);
	}
	
}
