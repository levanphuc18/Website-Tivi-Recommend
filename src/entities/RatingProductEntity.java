package entities;

import javax.persistence.*;

@Entity
@Table(name = "RatingProduct")
public class RatingProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
        @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    })
    private OrderDetailEntity orderDetail;

    @Column(name = "rating")
    private int rating;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

	public RatingProductEntity(int id, CustomerEntity customer, OrderDetailEntity orderDetail, int rating) {
		super();
		this.id = id;
		this.customer = customer;
		this.orderDetail = orderDetail;
		this.rating = rating;
	}
    
    
}
