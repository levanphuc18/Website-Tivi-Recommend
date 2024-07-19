package entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Supplier")
public class SupplierEntity {
	@Id
	@Column(name = "id", updatable = false)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "tax")
	private String tax;
	

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<InvoiceEntity> invoices;


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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTax() {
		return tax;
	}


	public void setTax(String tax) {
		this.tax = tax;
	}


	public List<InvoiceEntity> getInvoices() {
		return invoices;
	}


	public void setInvoices(List<InvoiceEntity> invoices) {
		this.invoices = invoices;
	}


	public SupplierEntity(String id, String name, String phone, String email, String address, String tax,
			List<InvoiceEntity> invoices) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.tax = tax;
		this.invoices = invoices;
	}


	public SupplierEntity() {
		super();
	}

}