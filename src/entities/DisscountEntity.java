package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Disscount")
public class DisscountEntity {
	@Id
	@Column(name = "id", updatable = false)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "disscount_percent")
	private String percent;
	
	@Column(name = "start_date", updatable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	
	@Column(name = "end_date", updatable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	
	@ManyToOne()
	@JoinColumn(name = "admin_id")
	private AdminEntity admin;

	@OneToMany(mappedBy = "disscount", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public DisscountEntity(String id, String name, String reason, String percent, Date startDate, Date endDate,
			AdminEntity admin, List<ProductEntity> products) {
		super();
		this.id = id;
		this.name = name;
		this.reason = reason;
		this.percent = percent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.admin = admin;
		this.admin = new AdminEntity();
		this.products = products;
	}

	public DisscountEntity() {
		super();
	}
	

}
