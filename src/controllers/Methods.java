package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.AccountEntity;
import entities.AdminEntity;
import entities.CartDetailEntity;
import entities.CategoryEntity;
import entities.CustomerEntity;
import entities.FavoriteProductEntity;
import entities.OrderDetailEntity;
import entities.OrderEntity;
import entities.ProductEntity;
import entities.RatingProductEntity;
import models.RatingModel;


public class Methods {
	
	SessionFactory factory;

	public Methods(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	// LƯU PRODUCT
	public String saveProductRecord(String productId) {
	    String s = null;
	    String str = null;
	    try {
	        // Chạy lệnh Python với mã sản phẩm
	    	String cmd = "python D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\add-product-to-csv.py "  + productId;
			Process p = Runtime.getRuntime().exec(cmd);

	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

	        // Đọc kết quả đầu ra của lệnh
	        System.out.println("Here is the standard output of the command:\n");
	        while ((s = stdInput.readLine()) != null) {
	            System.out.println(s);
	            str = s;
	        }

	    } catch (IOException e) {
	        System.out.println("exception happened - here's what I know: ");
	        e.printStackTrace();
	    }
	    return str;
	}
	
	// LƯU ĐÁNH GIÁ 
	public String saveRatingRecord(String rate) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			String cmd = "python D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\add-rating-to-csv.py "  + rate;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);

				str = s;
			}

		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return str;

	}
	
	// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT

	public String getRecommendation(String idkH) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			String cmd = "python D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\CollaborativeFilteringUser.py " + idkH;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);

				str = s;
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s +"\n");
			}

			// System.exit(0);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return str;

	}
	
	// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
	
	
	
	public String createTheNextCustomerId() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.id FROM CustomerEntity c WHERE c.id=(SELECT MAX(cc.id) FROM CustomerEntity cc)";
		Query query = session.createQuery(hql);

		String id = (String) query.uniqueResult();
		if (id == null || id.equals("")) {
			return "U0001";
		}
		int x = Integer.parseInt(id.substring(1)) + 1;
		String base = "U0000";
		String base2 = base.substring(0, base.length() - String.valueOf(x).length());
		String newId = base2 + String.valueOf(x);
		return newId;
	}
	
	
	

	public String createTheNextOrderId() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT o.id FROM OrderEntity o WHERE o.id=(SELECT MAX(oo.id) FROM OrderEntity oo)";
		Query query = session.createQuery(hql);

		String id = (String) query.uniqueResult();
		if (id == null || id.equals("")) {
			return "O000000001";
		}
		int x = Integer.parseInt(id.substring(1)) + 1;
		String base = "O000000000";
		String base2 = base.substring(0, base.length() - String.valueOf(x).length());
		String newId = base2 + String.valueOf(x);
		return newId;
	}

	// lấy danh sách category
	public List<CategoryEntity> getListCategory() {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
	
	// lấy danh sách 16 sản phẩm được thêm gần đây
	public List<ProductEntity> getTop16RecentProduct() {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p ORDER BY p.dateAdded DESC";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.setMaxResults(16).list();
		return list;
	}
	// lấy danh sách 4 sản phẩm có lượt xem nhiều
	public List<ProductEntity> getTop4ProductsWithTheMostViews() {
//		 System.out.println("getTop4ProductsWithTheMostViews"); 
		Session session = factory.getCurrentSession();
		String hql = "SELECT p FROM ProductEntity p ORDER BY p.views DESC\n"
				+ "";
		Query query = session.createQuery(hql);
		List<ProductEntity> listlx = query.setMaxResults(4).list();
		return listlx;
	}

	public List<OrderEntity> getCustomerOrder(String customerId) {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity o WHERE o.customerId=:customerId ORDER BY o.orderDate DESC";
		Query query = session.createQuery(hql).setParameter("customerId", customerId);
		List<OrderEntity> list = query.list();
		return list;
	}
	

	public List<ProductEntity> getAllProducts() {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p ORDER BY p.dateAdded DESC";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		return list;
	}

		// tìm sản phẩm
	public List<ProductEntity> searchForProduct(String keyword) {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.name LIKE :keyword OR p.category.name LIKE:keyword ORDER BY p.dateAdded DESC";
		Query query = session.createQuery(hql).setParameter("keyword", "%" + keyword + "%");
		List<ProductEntity> list = query.setParameter("keyword", "%" + keyword + "%").list();
		System.out.println(query.toString());
		return list;
	}
	
	 // Cập nhập thông tin khách hàng
	public boolean updateCustomerDetail(CustomerEntity customer, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CustomerEntity c SET c.firstname=:firstname, c.lastname=:lastname,"
				+ "c.phone=:phone, c.address=:address, c.email=:email   WHERE c.id=:id";
		Query query = session.createQuery(hql).setParameter("id",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("firstname", customer.getFirstname());
		query.setParameter("lastname", customer.getLastname());
		query.setParameter("phone", customer.getPhone());
		query.setParameter("address", customer.getAddress());
		query.setParameter("email", customer.getEmail());
		return query.executeUpdate() > 0;
	}
	

	// Cập nhập mật khẩu khách hàng
	public boolean updateCustomerPassword(CustomerEntity customer, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		
		
		String hql = "UPDATE CustomerEntity c SET c.password=:password WHERE c.id=:id";
		Query query = session.createQuery(hql).setParameter("id",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("password", customer.getPassword());
		return query.executeUpdate() > 0;
	}
	
	// cập nhật thay đổi mật khẩu
	public boolean updateCustomerForgotPassword(CustomerEntity customer, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CustomerEntity c SET c.password=:password WHERE c.id=:id";
		Query query = session.createQuery(hql).setParameter("id",
				customer.getId());
		query.setParameter("password", customer.getPassword());
		return query.executeUpdate() > 0;
	}
	
	// create random recovery
	public boolean updateCustomerRecoveryCode(CustomerEntity customer, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CustomerEntity c SET c.recoveryCode=:recoveryCode WHERE c.id=:id";
		Query query = session.createQuery(hql).setParameter("id",
				customer.getId());
		query.setParameter("recoveryCode", AdminAuthController.generateRecoveryCode(5));
		return query.executeUpdate() > 0;
	}
	
	public boolean updateProductQuantityFromCartDetail(String productId, String quantity, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CartDetailEntity c SET c.quantity=:quantity WHERE c.id.customer_id=:customerId and c.id.product_id=:productId";
		Query query = session.createQuery(hql).setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", productId);
		query.setParameter("quantity", Integer.parseInt(quantity));
		return query.executeUpdate() > 0;
	}

	public boolean updateProductQuantity(String productId, int quantity) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ProductEntity p SET p.quantity=:quantity WHERE p.id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		query.setParameter("quantity", quantity);
		return query.executeUpdate() > 0;
	}
	public boolean updateProductViews(String productId, int views) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ProductEntity p SET p.views=:views WHERE p.id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		query.setParameter("views", views);
		return query.executeUpdate() > 0;
	}
	
	// xóa sp khỏi giỏ hàng
	public boolean deleteProductFromCartDetail(String productId, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM CartDetailEntity c WHERE c.id.customer_id=:customerId and c.id.product_id=:productId";
		Query query = session.createQuery(hql).setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", productId);

		return query.executeUpdate() > 0;
	}

	// lấy tất cả sản phảm trong giỏ hàng của khách hàng
	public List<CartDetailEntity> getListCartDetail(String customerId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CartDetailEntity c WHERE c.customer.id =:customerId";
		Query query = session.createQuery(hql);

		List<CartDetailEntity> list = query.setParameter("customerId", customerId).list();
		return list;
	}

	public String getCustomerIdByUserName(String userName) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT id FROM CustomerEntity c WHERE c.username =:userName";
		Query query = session.createQuery(hql);
		String customerId = (String) query.setParameter("userName", userName).uniqueResult();
		return customerId;
	}

	public CustomerEntity getCustomerByUsername(String userName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity c WHERE c.username =:userName";
		Query query = session.createQuery(hql);
		CustomerEntity customer = (CustomerEntity) query.setParameter("userName", userName).uniqueResult();
		return customer;
	}

	// lấy danh sách sản phẩm
	public List<ProductEntity> getListProduct() {
		/* System.out.println("getListCartDetail"); */
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		return list;
	}

	public boolean checkOutHibernate(OrderEntity order, List<CartDetailEntity> listCartDetail) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		List<ProductEntity> listProduct = this.getListProduct();

		List<ProductEntity> listProductToBeUpdate = new ArrayList<ProductEntity>();

		for (ProductEntity pe : listProduct) {
			for (CartDetailEntity cde : listCartDetail) {
				if (cde.getProduct().getId().trim().equals(pe.getId())) {
//					System.out.println(pe.getQuantity() +"old quan: "+ pe.getName());
					pe.setQuantity(pe.getQuantity() - cde.getQuantity());// new quantity after update
//					System.out.println(pe.getQuantity() +"new quan: "+ pe.getName());
					listProductToBeUpdate.add(pe);
				}
			}
		}
		List<OrderDetailEntity> listODE = new ArrayList<OrderDetailEntity>();
		for (CartDetailEntity cartDetailEntity : listCartDetail) {
			OrderDetailEntity ode = new OrderDetailEntity(order.getId(), cartDetailEntity.getProduct().getId());
			ode.setQuantity(cartDetailEntity.getQuantity());
			listODE.add(ode);
		}
		boolean updateFlag = false;
		try {
			session.save(order);
			for (CartDetailEntity cartDetailEntity : listCartDetail) {
				session.delete(cartDetailEntity);
			}
			for (OrderDetailEntity ode : listODE) {
				session.save(ode);
			}

			t.commit();
		} catch (Exception ex) {
			t.rollback();
			return false;
		} finally {
			session.close();
			updateFlag = true;
		}
		if (updateFlag == true) {
			for (ProductEntity p : listProductToBeUpdate) {
				if (!updateProductQuantity(p.getId(), p.getQuantity())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean cartItemIsExit(String product_id, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.id.product_id FROM CartDetailEntity c WHERE c.id.customer_id =:customerId and c.id.product_id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", product_id);
		String productId = (String) query.uniqueResult();
		return product_id.equals(productId);
	}

	public int getQuantityOfCartItem(String product_id, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.quantity FROM CartDetailEntity c WHERE c.id.customer_id =:customerId and c.id.product_id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", product_id);
		int quantity = (int) query.uniqueResult();
		return quantity;
	}

	public boolean isProductOutOfStock(String product_id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT p.quantity FROM ProductEntity p WHERE p.id =:productId";
		Query query = session.createQuery(hql);
		int product = (int) query.setParameter("productId", product_id).uniqueResult();
		return product <= 0;
	}

	public boolean isCartItemMaximumAmountOfProduct(String product_id, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.quantity FROM CartDetailEntity c WHERE c.id.customer_id =:customerId and c.id.product_id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", product_id);
		int quantityOfCart = (int) query.uniqueResult();
		System.out.println("quantity from cart: " + quantityOfCart);
		hql = "SELECT c.product.quantity FROM CartDetailEntity c WHERE c.id.customer_id =:customerId and c.id.product_id=:productId";
		query = session.createQuery(hql);
		query.setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", product_id);
		int quantityOfProduct = (int) query.uniqueResult();
		System.out.println("quantity from product: " + quantityOfProduct);
		return quantityOfCart >= quantityOfProduct;
	}
	// lấy thông tin sản  phẩm thông qua id 
	public ProductEntity getProduct(String productId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.id =:productId";
		Query query = session.createQuery(hql);

		ProductEntity product = (ProductEntity) query.setParameter("productId", productId).uniqueResult();
		return product;
	}
	
	
	
	// lấy tất cả sản phẩm có id category
	public List<ProductEntity> getListProductBycategory(String categoryId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.category.id=:categoryId";
		Query query = session.createQuery(hql);

		List<ProductEntity> list = query.setParameter("categoryId", categoryId).list();
		return list;
	}

	public boolean insertCustomer(CustomerEntity customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(customer);
			t.commit();
			System.out.println("Insert successful!");

		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			System.out.println("Insert unsuccessful!");
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean isCustomerWithUsernameExit(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity c WHERE c.username=:username";
		Query query = session.createQuery(hql);

		CustomerEntity customer = (CustomerEntity) query.setParameter("username", username).uniqueResult();
		return customer != null;
	}
	public boolean isAdminWithUsernameExit(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity a WHERE a.username=:username";
		Query query = session.createQuery(hql);

		AccountEntity admin = (AccountEntity) query.setParameter("username", username).uniqueResult();
		return admin != null;
	}

//	public String getPasswordOfCustomerWithUsername(String username) {
//		Session session = factory.getCurrentSession();
//		String hql = "SELECT c.password FROM CustomerEntity c WHERE c.username=:username";
//		Query query = session.createQuery(hql);
//		String password = (String) query.setParameter("username", username).uniqueResult();
//		return password;
//	}
	
	// thực tập
	public String getPasswordOfCustomerWithUsername(String username) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.password FROM CustomerEntity c WHERE c.username=:username";
		Query query = session.createQuery(hql);
		String password = (String) query.setParameter("username", username).uniqueResult();
		return password;
	}
	
	
	public String getEmailOfCustomerWithUsername(String username) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.email FROM CustomerEntity c WHERE c.username=:username";
		Query query = session.createQuery(hql);
		String email = (String) query.setParameter("username", username).uniqueResult();
		return email;
	}
	public String getRecoveryCodeOfCustomerWithUsername(String username) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.recoveryCode FROM CustomerEntity c WHERE c.username=:username";
		Query query = session.createQuery(hql);
		String recoveryCode = (String) query.setParameter("username", username).uniqueResult();
		return recoveryCode.trim();
	}
	//testing
	public List<FavoriteProductEntity> getListFavourite(String customerId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM FavoriteProductEntity f WHERE f.customer.id =:customerId";
		Query query = session.createQuery(hql);

		List<FavoriteProductEntity> list = query.setParameter("customerId", customerId).list();
//		for(FavoriteProductEntity f: list) {
//			System.out.println(f.getProduct().getId());
//		}
		return list;
	}
	
	public boolean favItemIsExit(String product_id, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT f.product.id FROM FavoriteProductEntity f WHERE f.customer.id =:customerId and f.product.id=:productId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", product_id);
		String productId = (String) query.uniqueResult();
		return product_id.equals(productId);
	}
	
	public boolean deleteProductFromFavourite(String productId, HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM FavoriteProductEntity f WHERE f.customer.id=:customerId and f.product.id=:productId";
		Query query = session.createQuery(hql).setParameter("customerId",
				this.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		query.setParameter("productId", productId);

		return query.executeUpdate() > 0;
	}

	// Phương thức lấy danh sách các giá trị duy nhất của cột screenTypes từ bảng ConfigEntity
		public List<String> getUniqueCategories() {
//	        System.out.println("getUnique");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.name FROM CategoryEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueCategories = query.list();
	        return uniqueCategories;
	    }


	    public List<String> getUniqueScreenTypes() {
//	        System.out.println("getUniqueScreenTypes");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.screentype FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueScreenTypes = query.list();
	        return uniqueScreenTypes;
	    }

		public List<String> getUniqueScreenSizes() {
//	        System.out.println("getUniqueScreenSizes + dnsajldhkashdashk");
	        Session session = factory.getCurrentSession();
//	        session.beginTransaction();
	        String hql = "SELECT DISTINCT c.screensize FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueScreenSizes = query.list();
//	        session.getTransaction().commit();
	        return uniqueScreenSizes;
	    }


	    public List<String> getUniqueScanningFrequencies() {
//	        System.out.println("getUniqueScanningFrequencies");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.scanningfrequency FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueScanningFrequencies = query.list();
	        return uniqueScanningFrequencies;
	    }

	    public List<String> getUniqueResolutions() {
//	        System.out.println("getUniqueResolutions");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.resolution FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueResolutions = query.list();
	        return uniqueResolutions;
	    }

	    public List<String> getUniqueUtilities() {
//	        System.out.println("getUniqueUtilities");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.utilities FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueUtilities = query.list();
	        return uniqueUtilities;
	    }

	    public List<String> getUniqueOperatingSystems() {
//	        System.out.println("getUniqueOperatingSystems");
	        Session session = factory.getCurrentSession();
	        String hql = "SELECT DISTINCT c.operatingsystem FROM ConfigEntity c";
	        Query query = session.createQuery(hql);
	        List<String> uniqueOperatingSystems = query.list();
	        return uniqueOperatingSystems;
	    }
	    
	 // Filter
	    public List<ProductEntity> filterProducts(String nameCategory, String screenType, String screenSize, String scanningFrequency,
	            String resolution, String utilities, String operatingSystem) {
	        Session session = factory.getCurrentSession();
	        StringBuilder hql = new StringBuilder("SELECT DISTINCT p FROM ProductEntity p " +
	                                               "JOIN FETCH p.category cat " +
	                                               "JOIN FETCH p.config cfg " +
	                                               "WHERE 1=1");

	        if (nameCategory != null && !nameCategory.isEmpty()) {
	            hql.append(" AND cat.name = :nameCategory");
	        }
	        if (screenType != null && !screenType.isEmpty()) {
	            hql.append(" AND cfg.screentype = :screenType");
	        }
	        if (screenSize != null && !screenSize.isEmpty()) {
	            hql.append(" AND cfg.screensize = :screenSize");
	        }
	        if (scanningFrequency != null && !scanningFrequency.isEmpty()) {
	            hql.append(" AND cfg.scanningfrequency = :scanningFrequency");
	        }
	        if (resolution != null && !resolution.isEmpty()) {
	            hql.append(" AND cfg.resolution = :resolution");
	        }
	        if (utilities != null && !utilities.isEmpty()) {
	            hql.append(" AND cfg.utilities = :utilities");
	        }
	        if (operatingSystem != null && !operatingSystem.isEmpty()) {
	            hql.append(" AND cfg.operatingsystem = :operatingSystem");
	        }

	        Query query = session.createQuery(hql.toString());

	        if (nameCategory != null && !nameCategory.isEmpty()) {
	            query.setParameter("nameCategory", nameCategory);
	        }
	        if (screenType != null && !screenType.isEmpty()) {
	            query.setParameter("screenType", screenType);
	        }
	        if (screenSize != null && !screenSize.isEmpty()) {
	            query.setParameter("screenSize", screenSize);
	        }
	        if (scanningFrequency != null && !scanningFrequency.isEmpty()) {
	            query.setParameter("scanningFrequency", scanningFrequency);
	        }
	        if (resolution != null && !resolution.isEmpty()) {
	            query.setParameter("resolution", resolution);
	        }
	        if (utilities != null && !utilities.isEmpty()) {
	            query.setParameter("utilities", utilities);
	        }
	        if (operatingSystem != null && !operatingSystem.isEmpty()) {
	            query.setParameter("operatingSystem", operatingSystem);
	        }

	        List<ProductEntity> productList = query.list();
	        System.out.println(productList.size() + " PhucFilter \n");
	        return productList;
	    }
	    
	    
	    public boolean insertRating(RatingProductEntity rating) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(rating);
				t.commit();
				System.out.println("Insert successful!");

			} catch (Exception ex) {
				ex.printStackTrace();
				t.rollback();
				System.out.println("Insert unsuccessful!");
				return false;
			} finally {
				session.close();
			}
			return true;
		}

}
