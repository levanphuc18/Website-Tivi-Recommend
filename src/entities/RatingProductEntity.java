package entities;

import javax.persistence.*;

@Entity
@Table(name = "RatingProduct")
public class RatingProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

    // Mối quan hệ nhiều-1 đến CustomerEntity
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    // Mối quan hệ nhiều-1 đến OrderDetailEntity, chỉ định cột không được thêm hoặc cập nhật
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false),
        @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    })
    private OrderDetailEntity orderDetail;

    @Column(name = "rating")
    private int rating;

    @Column(name = "order_id")
    private String orderId;
    
    @Column(name = "product_id")
    private String productId;

    // Các phương thức getter và setter cho orderId và productId
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    // Constructor mặc định
    public RatingProductEntity() {
        // Không có tham số, có thể để trống hoặc thực hiện các khởi tạo khác nếu cần
    }

    // Các phương thức getter và setter cho các thuộc tính khác
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
}
