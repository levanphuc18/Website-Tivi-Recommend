package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Config")
public class ConfigEntity {
	@Id
	@Column(name = "id", updatable = false)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "image")
	private String image;
	

	@Column(name = "screentype")
	private String screentype;
	
	@Column(name = "screensize")
	private String screensize;
	
	@Column(name = "scanningfrequency")
	private String scanningfrequency;
	
	@Column(name = "resolution")
	private String resolution;
	
	
	@Column(name = "utilities")
	private String utilities;
	
	@Column(name = "operatingsystem")
	private String operatingsystem;
	
	@Column(name = "date_added", updatable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateAdded;

	@OneToMany(mappedBy = "config", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<ProductEntity> products;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getScreentype() {
		return screentype;
	}

	public void setScreentype(String screentype) {
		this.screentype = screentype;
	}

	public String getScreensize() {
		return screensize;
	}

	public void setScreensize(String screensize) {
		this.screensize = screensize;
	}

	public String getScanningfrequency() {
		return scanningfrequency;
	}

	public void setScanningfrequency(String scanningfrequency) {
		this.scanningfrequency = scanningfrequency;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getUtilities() {
		return utilities;
	}

	public void setUtilities(String utilities) {
		this.utilities = utilities;
	}

	public String getOperatingsystem() {
		return operatingsystem;
	}

	public void setOperatingsystem(String operatingsystem) {
		this.operatingsystem = operatingsystem;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
