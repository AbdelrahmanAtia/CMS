package org.javaworld.cmsbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.javaworld.cmsbackend.validator.OnCreate;
import org.javaworld.cmsbackend.validator.OnUpdate;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = { OnUpdate.class })
	@Column(name = "id")
	private Integer id;

	@NotNull(groups = { OnCreate.class, OnUpdate.class })
	@NotBlank(groups = { OnCreate.class, OnUpdate.class })
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "product_count")
	private int productCount;

	@Column(name = "image_name")
	private String imageName;

	@Transient
	private String base64Image;

	public Category() {

	}

	public Category(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", productCount=");
		builder.append(productCount);
		builder.append(", imageName=");
		builder.append(imageName);
		builder.append(", base64Image=");
		builder.append(base64Image);
		builder.append("]");
		return builder.toString();
	}

}