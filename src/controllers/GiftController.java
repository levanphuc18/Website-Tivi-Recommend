package controllers;

import java.io.BufferedReader;

import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entities.CartDetailEntity;
import entities.CustomerEntity;
import entities.FavoriteProductEntity;
import entities.OrderEntity;
import entities.ProductEntity;
import entities.RatingProductEntity;
import models.BcryptEncryption;
import models.ChangePasswordModel;
import models.CustomerForgotPasswordModel;
import models.CustomerLoginAccountModel;
import models.CustomerValidateModel;
import models.Mailer;

import models.OrderModel;
import models.RatingModel;
import vnpay.Config;

@Transactional
@Controller
@RequestMapping("/store")
public class GiftController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	
	// thực tập mã hóa Bcrypt
	BcryptEncryption encryption = new BcryptEncryption();
	
	
	@RequestMapping("")
	public String store(ModelMap model, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("list", method.getListProduct());
		if ((httpSession.getAttribute("customerUsername") != null)) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		}

		return "store/index";
	}

	@RequestMapping("/")
	public String store2(ModelMap model, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (httpSession.getAttribute("customerUsername") != null) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		}
		model.addAttribute("list", method.getListProduct());
		
		
		
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (httpSession.getAttribute("customerUsername") != null) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		}
//		model.addAttribute("listlx", method.getTop4ProductsWithTheMostViews());
		
//		// ĐỀ XUẤT
		// Lấy mã khách hàng từ phiên làm việc
	    String customerId = (String) httpSession.getAttribute("customerUsername");
	    System.out.println("mã kahcsh hanbg đăng nhập "+ customerId);
		String listMHStr = method.getRecommendation(customerId);
		String tmp = listMHStr.replace("'", "");
		tmp = tmp.replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace(" ", "");
		
		System.out.print(tmp);
		String[] tmp2= tmp.split(",");
		
		List<ProductEntity> listProductDeXuat = new ArrayList<>();
		for(String s:tmp2) {
			
			ProductEntity prdtmp= method.getProduct(s);
			if (prdtmp != null) {
		        listProductDeXuat.add(prdtmp);
		        System.out.println("\nCác sản phẩm được đề xuất: " + prdtmp.getId() + "\n");
		    } else {
		        System.out.println("\nSản phẩm với ID " + s + " không tồn tại.\n");
		    }
			System.out.println("\n Các sản phẩm được đề xuất: "+ prdtmp.getId()+"\n");
		}


		model.addAttribute("listDXSP", listProductDeXuat);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if(httpSession.getAttribute("customerUsername")!=null) {
			int sum = 0;
		for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
			sum = sum + c.getQuantity();
		}
		System.out.println(sum);
		httpSession.setAttribute("customerTotalQuantity", sum);
		model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
}
	
		model.addAttribute("listDXSP", listProductDeXuat);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if(httpSession.getAttribute("customerUsername")!=null) {
			int sum = 0;
		for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
			sum = sum + c.getQuantity();
		}
		System.out.println(sum);
		httpSession.setAttribute("customerTotalQuantity", sum);
		model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
	}
		
		// DE XUAT
		
		
		return "store/index";
	}

	@RequestMapping("/product-detail/{productId}")
	public String productDetail2(@PathVariable("productId") String productId, ModelMap model, HttpSession httpSession) {
		ProductEntity productEntity = new ProductEntity();
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (httpSession.getAttribute("listRecentViewProducts") != null) {
			List<ProductEntity> lpe = (List<ProductEntity>) httpSession.getAttribute("listRecentViewProducts");
			boolean existed = false;
			for (ProductEntity pe : lpe) {
				if (pe.getId().equals(productId)) {
					existed = true;
				}
			}
			if (!existed) {
				lpe.add(0, method.getProduct(productId));
			}
			httpSession.setAttribute("listRecentViewProducts", lpe);
		} else {
			List<ProductEntity> lpe = new ArrayList<ProductEntity>();
			lpe.add(0, method.getProduct(productId));
			httpSession.setAttribute("listRecentViewProducts", lpe);
		}
		method.updateProductViews(productId, method.getProduct(productId).getViews() + 1);
		model.addAttribute("product", method.getProduct(productId));
		model.addAttribute("config", productEntity.getConfig()); // Đặt thông tin cấu hình vào model
		
		model.addAttribute("listFavorite", method.getListFavourite(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		System.out.println(productId + "; " + method.getProduct(productId).getName());

		
		return "store/product-detail";
	}
	
	@RequestMapping("/category/{categoryId}")
	public String category(ModelMap model, @PathVariable("categoryId") String categoryId, HttpServletRequest request,
			HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("list", method.getListProductBycategory(categoryId));
		PagedListHolder pagedListHolder = new PagedListHolder(method.getListProductBycategory(categoryId));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(3);
		pagedListHolder.setPageSize(8);

		model.addAttribute("listFavorite", method.getListFavourite(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "store/category-products";
	}

	@RequestMapping(value = "/sign-in")
	public String signIn(ModelMap model, HttpSession httpSession, RedirectAttributes attributes) {
		Methods method = new Methods(factory);

		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("account", new CustomerLoginAccountModel());
		if ((httpSession.getAttribute("customerUsername") != null)) {
			attributes.addFlashAttribute("message", "Vui lòng đăng xuất nếu muốn đăng nhập lại");
			return returnRedirectControl("index");
		}
		return "store/sign-in";
	}

	@RequestMapping(value = "/forgot-password")
	public String forgotPassword(ModelMap model, HttpSession httpSession, RedirectAttributes attributes) {
		if ((httpSession.getAttribute("customerUsername") != null)) {
			attributes.addFlashAttribute("message", "Bạn không thể khôi phục mật khẩu khi đã đăng nhập");
			return returnRedirectControl("index");
		}
		Methods method = new Methods(factory);

		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("account", new CustomerForgotPasswordModel());

		return "store/forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword2(@ModelAttribute("account") CustomerForgotPasswordModel account, ModelMap model,
			BindingResult errors, HttpSession httpSession, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (account.getUsername().trim().length() == 0) {
			errors.rejectValue("username", "account", "Vui lòng nhập tên đăng nhập!");
		} else if (!method.isCustomerWithUsernameExit(account.getUsername())) {
			errors.rejectValue("username", "account", "Tên đăng nhập không tồn tại!");
		}
		if (account.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "account", "Vui lòng nhập email!");
		} else if (!validateEmail(account.getEmail().trim())) {
			errors.rejectValue("email", "account", "Email không đúng định dạng!");
		} else if (!account.getEmail().equals(method.getEmailOfCustomerWithUsername(account.getUsername()))) {
			errors.rejectValue("email", "account", "Email không đúng với tài khoản!");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Thông tin bạn nhập không đúng!");

			return "store/forgot-password";
		} else {
			if (account.getRecoveryCode().trim().length() == 0) {
				String body = "<h3  style='margin: 8px 0;'>Mã khôi phục của bạn là: <span style='display: inline-block; padding: 4px 8px; background-color: yellow'>"
						+ method.getRecoveryCodeOfCustomerWithUsername(account.getUsername()) + "</span></h3>";
				body = body + "<p>Sau khi nhập mã khôi phục mật khẩu của bạn sẽ trở thành 12345</p>"
						+ "<p>Để tăng tính bảo mật hãy đổi mật khẩu ngay sau khi khôi phục</p>";
				try {
					mailer.send("n20dccn128@student.ptithcm.edu.vn", account.getEmail(), "Email về việc cấp lại mật khẩu.",
							body);
					model.addAttribute("message", "Đã gửi mail khôi phục mật khẩu!");

					System.out.println("Gửi email thành công!");
					return "store/forgot-password";
				} catch (Exception ex) {
					System.out.println("Gửi email thất bại!");
				}
			} else if (account.getRecoveryCode().trim().length() != 0) {
				if (account.getRecoveryCode().trim()
						.equals(method.getRecoveryCodeOfCustomerWithUsername(account.getUsername()))) {
					CustomerEntity ce = new CustomerEntity();
					
					// mã hóa pass Bcrypt
					String passwword = encryption.hashPassword("12345");
					ce.setPassword(passwword);
					ce.setId(method.getCustomerIdByUserName(account.getUsername().trim()));
					if (method.updateCustomerForgotPassword(ce, httpSession)) {
						if (method.updateCustomerRecoveryCode(ce, httpSession)) {
							attributes.addFlashAttribute("message", "Khôi phục mật khẩu thành công!");
							return "redirect:/store/sign-in";
						}
						System.out.println("Không thể tạo mã khôi phục mới");
						model.addAttribute("message", "Đã xảy ra lỗi!");
						return "store/forgot-password";
					}

				}
			} else {
				errors.rejectValue("recoveryCode", "account", "Sai mã xác nhận!");
				return "store/forgot-password";
			}
//			httpSession.setAttribute("customerUsername", account.getUsername());
//			int sum = 0;
//			for (CartDetailEntity c : method.getCustomerByUsername(account.getUsername()).getCartDetails()) {
//				sum = sum + c.getQuantity();
//			}
//			System.out.println(sum);
//			httpSession.setAttribute("customerTotalQuantity", sum);

		}
		return "store/forgot-password";
	}

	@RequestMapping(value = "/log-out")
	public String logOut(ModelMap model, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		httpSession.removeAttribute("customerUsername");
		return "redirect:/";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String signIn(@ModelAttribute("account") CustomerLoginAccountModel account, ModelMap model,
			BindingResult errors, HttpSession httpSession, RedirectAttributes attributes) throws NoSuchAlgorithmException {
		Methods method = new Methods(factory);
		
		// thực tập md5
//		Md5Encryption encryption = new Md5Encryption();
//		String password = encryption.convertHashToString(account.getPassword());
		
		// thực tập Bcrypt
		BcryptEncryption encryption = new BcryptEncryption();
		
		String password = account.getPassword();
		String storedHash = method.getPasswordOfCustomerWithUsername(account.getUsername());
		
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (!method.isCustomerWithUsernameExit(account.getUsername())) {
			errors.rejectValue("username", "account", "Tên đăng nhập không tồn tại!");
		} else if (!encryption.verifyPassword(password, storedHash)) {
			errors.rejectValue("password", "account", "Mật khẩu không đúng!");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Thông tin đăng nhập sai!");

			return "store/sign-in";
		} else {
			httpSession.setAttribute("customerUsername", account.getUsername());
			int sum = 0;
			for (CartDetailEntity c : method.getCustomerByUsername(account.getUsername()).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			attributes.addFlashAttribute("message", "Đăng nhập thành công!");
			return "redirect:/";
		}
	}

	@RequestMapping("/sign-up")
	public String signUp(ModelMap model, HttpSession httpSession, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		if ((httpSession.getAttribute("customerUsername") != null)) {
			attributes.addFlashAttribute("message", "Vui lòng đăng xuất nếu muốn đăng ký tài khoản mới");
			return returnRedirectControl("index");
		}
		httpSession.setAttribute("listCategory", method.getListCategory());
		System.out.println(method.createTheNextCustomerId());
		model.addAttribute("customer", new CustomerValidateModel());
		return "store/sign-up";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(ModelMap model, @ModelAttribute("customer") CustomerValidateModel customer,
			BindingResult errors, HttpSession httpSession, RedirectAttributes attributes) throws NoSuchAlgorithmException {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		
		// thực tập md5
//		Md5Encryption encryption = new Md5Encryption();
		
		// mã hóa Bcrypt
		String passwword = encryption.hashPassword(customer.getPassword());

		if (customer.getUsername().trim().length() == 0) {
			errors.rejectValue("username", "customer", "Vui lòng nhập tên đăng nhập!");
		} else if (customer.getUsername().trim().length() < 4) {
			errors.rejectValue("username", "customer", "Tên đăng nhập tối thiểu 4 ký tự!");
		} else if (method.isCustomerWithUsernameExit(customer.getUsername().trim())
				|| method.isAdminWithUsernameExit(customer.getUsername().trim())) {
			errors.rejectValue("username", "customer", "Tên đăng nhập đã tồn tại!");
		}
		if (customer.getFirstName().trim().length() == 0) {
			errors.rejectValue("firstName", "customer", "Vui lòng nhập họ!");
		}
		if (customer.getLastName().trim().length() == 0) {
			errors.rejectValue("lastName", "customer", "Vui lòng nhập tên!");
		}
		/*
		 * System.out.println(customer.getDateofbirth()); if (customer.getDateofbirth()
		 * == null || customer.getDateofbirth().equals("")) {
		 * errors.rejectValue("dateofbirth", "customer", "Vui lòng nhập ngày sinh!"); }
		 */
		if (customer.getTelephone().trim().length() == 0) {
			errors.rejectValue("telephone", "customer", "Vui lòng nhập số điện thoại!");
		} else if (!validatePhone(customer.getTelephone().trim())) {
			errors.rejectValue("telephone", "customer", "Số điện thoại không hợp lệ!");
		}
		if (customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "customer", "Vui lòng nhập email!");
		} else if (!validateEmail(customer.getEmail().trim())) {
			errors.rejectValue("email", "customer", "Email không đúng định dạng!");
		}
//		if (customer.getCity().trim().equals("0")) {
//			errors.rejectValue("city", "customer", "Vui lòng chọn Tỉnh/ Thành phố!");
//		}
//		if (customer.getDistrict().trim().equals("0")) {
//			errors.rejectValue("district", "customer", "Vui lòng chọn Quận/ Huyện!");
//		}
//		if (customer.getCommune().trim().equals("0")) {
//			errors.rejectValue("commune", "customer", "Vui lòng chọn Xã/ Phường!");
//		}
//		if (customer.getSpecificAddress().trim().length() == 0) {
//			errors.rejectValue("specificAddress", "customer", "Vui lòng nhập địa chỉ chi tiết!");
//		}
		if (customer.getFullAddress().trim().length() == 0) {
			errors.rejectValue("fullAddress", "customer", "Vui lòng nhập địa chỉ!");
		}
		if (customer.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "customer", "Vui lòng nhập mật khẩu!");
		}
		if (!customer.getPassword().trim().equals(customer.getConfirmPassword().trim())) {
			errors.rejectValue("confirmPassword", "customer", "Xác nhận mật khẩu không khớp!");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
			return "store/sign-up";
		} else {
			CustomerEntity ce = new CustomerEntity();
			ce.setId(method.createTheNextCustomerId().trim());
			ce.setUsername(customer.getUsername().trim());
//			ce.setPassword(customer.getPassword().trim());
			ce.setPassword(passwword);
			ce.setFirstname(customer.getFirstName().trim());
			ce.setLastname(customer.getLastName().trim());
			ce.setPhone(customer.getTelephone().trim());
			ce.setDateAdded(new Date());
			ce.setRecoveryCode(AdminAuthController.generateRecoveryCode(5));

			// ce.setAddress(customer.getSpecificAddress().trim() + ", " +
			// customer.getDistrict().trim() + ", "
//					+ customer.getCity().trim());
			ce.setAddress(customer.getFullAddress());
			ce.setEmail(customer.getEmail().trim());
//			model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng!");
			if (method.insertCustomer(ce)) {
				attributes.addFlashAttribute("message", "Đăng ký thành công!");
				httpSession.setAttribute("customerUsername", ce.getUsername());
				System.out.println("Thêm thành công");
				return "redirect:/";
			} else {
				model.addAttribute("message", "Đăng ký thất bại!");
				System.out.println("Thêm thất bại");
				return "store/sign-up";
			}
		}
	}


	@RequestMapping("/user-info")
	public String userInfo(HttpSession httpSession, ModelMap model) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("customer", new CustomerValidateModel());
		model.addAttribute("customerEntity",
				method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
		return "store/user-info";
	}

	@RequestMapping(value = "/user-info", method = RequestMethod.POST)
	public String userInfo2(ModelMap model, @ModelAttribute("customer") CustomerValidateModel customer,
			BindingResult errors, HttpSession httpSession, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		/*
		 * if (customer.getUsername().trim().length() == 0) {
		 * errors.rejectValue("username", "customer", "Vui lòng nhập tên đăng nhập!"); }
		 * else if (customer.getUsername().trim().length() < 4) {
		 * errors.rejectValue("username", "customer",
		 * "Tên đăng nhập tối thiểu 4 ký tự!"); } else if
		 * (isCustomerWithUsernameExit(customer.getUsername().trim())) {
		 * errors.rejectValue("username", "customer", "Tên đăng nhập đã tồn tại!"); }
		 */
		if (customer.getFirstName().trim().length() == 0) {
			errors.rejectValue("firstName", "customer", "Vui lòng nhập họ!");
		}
		if (customer.getLastName().trim().length() == 0) {
			errors.rejectValue("lastName", "customer", "Vui lòng nhập tên!");
		}
		/*
		 * System.out.println(customer.getDateofbirth()); if (customer.getDateofbirth()
		 * == null || customer.getDateofbirth().equals("")) {
		 * errors.rejectValue("dateofbirth", "customer", "Vui lòng nhập ngày sinh!"); }
		 */
		if (customer.getTelephone().trim().length() == 0) {
			errors.rejectValue("telephone", "customer", "Vui lòng nhập số điện thoại!");
		} else if (!validatePhone(customer.getTelephone().trim())) {
			errors.rejectValue("telephone", "customer", "Số điện thoại không hợp lệ!");
		}
		if (customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "customer", "Vui lòng nhập email!");
		} else if (!validateEmail(customer.getEmail().trim())) {
			errors.rejectValue("email", "customer", "Email không đúng định dạng!");
		}
//		if (customer.getCity().trim().length() == 0) {
//			errors.rejectValue("city", "customer", "Vui lòng chọn tỉnh, thành phố!");
//		}
//		if (customer.getDistrict().trim().length() == 0) {
//			errors.rejectValue("district", "customer", "Vui lòng chọn quận huyện!");
//		}
//		if (customer.getSpecificAddress().trim().length() == 0) {
//			errors.rejectValue("specificAddress", "customer", "Vui lòng nhập địa chỉ chi tiết!");
//		}
//		if (customer.getPassword().trim().length() == 0) {
//			errors.rejectValue("password", "customer", "Vui lòng nhập mật khẩu!");
//		}
//		if (!customer.getPassword().trim().equals(customer.getConfirmPassword().trim())) {
//			errors.rejectValue("confirmPassword", "customer", "Xác nhận mật khẩu không khớp!");
//		}
		if (customer.getFullAddress().trim().length() == 0) {
			errors.rejectValue("fullAddress", "customer", "Vui lòng nhập địa chỉ!");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
//			attributes.addFlashAttribute("message", "Vui lòng sửa các lỗi sau đây!");
			model.addAttribute("customerEntity",
					method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
			return "store/user-info";
		} else {
			CustomerEntity ce = new CustomerEntity();
			ce.setFirstname(customer.getFirstName().trim());
			ce.setLastname(customer.getLastName().trim());
			ce.setPhone(customer.getTelephone().trim());
//			ce.setAddress(customer.getSpecificAddress().trim() + ", " + customer.getDistrict().trim() + ", "
//					+ customer.getCity().trim());
			ce.setAddress(customer.getFullAddress());

			ce.setEmail(customer.getEmail().trim());
			System.out.println("still ok");
//			model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng!");
			if (method.updateCustomerDetail(ce, httpSession)) {
				System.out.println("Cập nhật thành công");
				model.addAttribute("message", "Cập nhật thông tin thành công!");
				return "store/user-info";
			} else {
				model.addAttribute("message", "Cập nhật thông tin thất bại!");
			}
		}
		return "store/user-info";
	}

	@RequestMapping(value = "/user-info/change-password")
	public String changePassword(HttpSession httpSession, ModelMap model) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("customer", new ChangePasswordModel());
		model.addAttribute("customerEntity",
				method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
		return "store/user-change-password";
	}

	@RequestMapping(value = "/user-info/order-history")
	public String orderHistory(HttpSession httpSession, ModelMap model) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("listOrder", method.getCustomerOrder(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		model.addAttribute("customerEntity",
				method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
		System.out.println("order-history");
		return "store/order-history";
	}

	
	@RequestMapping(value = "/user-info/order-history/submit-rating", method = RequestMethod.POST)
	public String submitRating(@RequestParam("orderId") String orderId,
								@RequestParam("customerId") String customerId,
	                           @RequestParam("productId") String productId,
	                           @RequestParam("rating") int rating,
	                           ModelMap model, HttpSession httpSession, RedirectAttributes attributes) {
	    
	    Methods method = new Methods(factory);
	    try {
	        // Lấy thông tin customer từ session và orderId, productId từ request
	        CustomerEntity customer = method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername"));
	        
	     // Tạo một đối tượng RatingProductEntity từ dữ liệu nhận được
	        RatingProductEntity ratingEntity = new RatingProductEntity();
	        ratingEntity.setOrderId(orderId);    // Thiết lập orderId
	        ratingEntity.setCustomer(customer);  // Thiết lập khách hàng
	        ratingEntity.setProductId(productId);  // Thiết lập productId
	        ratingEntity.setRating(rating);      // Thiết lập rating

	        // Gọi phương thức insertRating để lưu vào cơ sở dữ liệu
	        if (method.insertRating(ratingEntity)) {
	        	
	        	// Trong phương thức submitRating của controller
	        	List<OrderEntity> listOrder = method.getCustomerOrder(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
	        	model.addAttribute("listOrder", listOrder);
	            attributes.addFlashAttribute("message", "Đăng ký thành công!");
	            httpSession.setAttribute("customerUsername", customer.getUsername());
	            System.out.println("order-history- Rating success \n");
	            
	         // lưu đánh giá
	        String list = customer.getId() + "," + productId + "," + ratingEntity.getRating()  ;
			String tmp = method.saveRatingRecord(list);
//			String tmp = "";
			
			System.out.println("get danh gia");
			System.out.println("CustomerId: "+customer.getId() + " \nProductId: " + productId + " \nRating: " + ratingEntity.getRating());
//			System.out.println(" PHUC "+" "+ tmp);
			// lưu đánh giá
	            
	            return "store/order-history"; // Chuyển hướng về trang chủ sau khi đánh giá thành công
	        } else {
	            model.addAttribute("message", "Đánh giá thất bại!");
	            System.out.println("order-history- Rating fail");
	            return "store/order-history"; // Trở lại trang lịch sử đơn hàng nếu thất bại
	        }
	    } catch (Exception e) {
	        model.addAttribute("message", "Lỗi khi đánh giá: " + e.getMessage());
	        return "store/user-info"; // Xử lý ngoại lệ nếu có
	    }
	}

	
	// thực tập md5
	// thực tập Bcrypt
//	encryption.verifyPassword(password, storedHash
	@RequestMapping(value = "/user-info/change-password", method = RequestMethod.POST)
	public String changePassword2(ModelMap model, @ModelAttribute("customer") ChangePasswordModel customer,
			BindingResult errors, HttpSession httpSession, RedirectAttributes attributes) throws NoSuchAlgorithmException {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		
//		Md5Encryption encryption = new Md5Encryption();
		
		String oldPassword = customer.getOldPassword();
		String newPassword = customer.getNewPassword();
		String confirmNewPassword = customer.getConfirmNewPassword();
		
		String password = method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getPassword().trim();
		
		
		if (oldPassword.trim().length() == 0) {
			errors.rejectValue("oldPassword", "customer", "Vui lòng nhập mật khẩu cũ!");
		} else if (!encryption.verifyPassword(oldPassword, password)) {
			errors.rejectValue("oldPassword", "customer", "Mật khẩu cũ không đúng!");
		}
		if (newPassword.trim().length() == 0) {
			errors.rejectValue("newPassword", "customer", "Vui lòng nhập mật khẩu mới!");
		} else if (encryption.verifyPassword(newPassword, password)) {
			errors.rejectValue("newPassword", "customer", "Mật khẩu mới không được trùng với mật khẩu cũ!");
		}
		if (!customer.getConfirmNewPassword().trim().equals(customer.getNewPassword().trim())
				|| confirmNewPassword.trim().length() == 0) {
			errors.rejectValue("confirmNewPassword", "customer", "Xác nhận mật khẩu không khớp!");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
			model.addAttribute("customerEntity",
					method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
//			attributes.addFlashAttribute("message", "Vui lòng sửa các lỗi sau đây!");
			return "store/user-change-password";
		} else {
			CustomerEntity ce = new CustomerEntity();
			String passwordHash = encryption.hashPassword(customer.getConfirmNewPassword());
			ce.setPassword(passwordHash);
//			model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng!");
			if (method.updateCustomerPassword(ce, httpSession)) {
				System.out.println("Cập nhật mật khẩu thành công");
				attributes.addFlashAttribute("message", "Cập nhật mật khẩu thành công!");
			} else {
				attributes.addFlashAttribute("message", "Cập nhật mật khẩu thất bại!");
			}
		}
		return "redirect:/store/user-info";
	}

	@RequestMapping(value = "/shopping-cart/check-out")
	public String checkOut(HttpSession httpSession, ModelMap model, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		List<CartDetailEntity> listCart = method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		if (listCart.size() == 0) {
			attributes.addFlashAttribute("message", "Không có sản phẩm nào trong giỏ hàng!");
			return "redirect:/store/shopping-cart";
		}
		for (CartDetailEntity c : listCart) {
			if (c.getQuantity() > c.getProduct().getQuantity()) {
				attributes.addFlashAttribute("message",
						"Vui lòng cập nhật số lượng hợp lệ và nhấn Thanh toán lần nữa");
				return "redirect:/store/shopping-cart";
			}
		}
		if (httpSession.getAttribute("customerUsername") != null) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
		}
		model.addAttribute("order", new OrderModel());
		model.addAttribute("customerEntity",
				method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
		List<CartDetailEntity> cartDetails = method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		model.addAttribute("cartDetails", cartDetails);
		float total = 0;
		for (CartDetailEntity c : cartDetails) {
			total = total + c.getQuantity() * c.getProduct().getPrice();
		}
//		System.out.println(total);
		model.addAttribute("cartDetailsTotal", total);
		return "store/check-out";
	}
	
	
	@RequestMapping(value = "/shopping-cart/check-out", method = RequestMethod.POST)
	public String checkOut2(HttpSession httpSession, ModelMap model, @ModelAttribute("order") OrderModel order,
			BindingResult errors, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		if (order.getShipName().trim().length() == 0) {
			errors.rejectValue("shipName", "order", "Vui lòng nhập họ!");
		}
		if (order.getShipPhone().trim().length() == 0) {
			errors.rejectValue("shipPhone", "order", "Vui lòng nhập số điện thoại!");
		} else if (!validatePhone(order.getShipPhone().trim())) {
			errors.rejectValue("shipPhone", "order", "Số điện thoại không hợp lệ!");
		}
		if (order.getShipEmail().trim().length() == 0) {
			errors.rejectValue("shipEmail", "order", "Vui lòng nhập email!");
		} else if (!validateEmail(order.getShipEmail().trim())) {
			errors.rejectValue("shipEmail", "order", "Email không đúng định dạng!");
		}
		if (order.getShipAddress().trim().length() == 0) {
			errors.rejectValue("shipAddress", "order", "Vui lòng nhập địa chỉ chi tiết!");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
//			attributes.addFlashAttribute("message", "Vui lòng sửa các lỗi sau đây!");
			List<CartDetailEntity> cartDetails = method.getListCartDetail(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
			model.addAttribute("cartDetails", cartDetails);
			float total = 0;
			for (CartDetailEntity c : cartDetails) {
				total = total + c.getQuantity() * c.getProduct().getPrice();
			}
			model.addAttribute("cartDetailsTotal", total);
			return "store/check-out";
		} else {
			OrderEntity oe = new OrderEntity();
			oe.setCustomerId(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
			oe.setId(method.createTheNextOrderId());
			oe.setOrderDate(new Date());
			List<CartDetailEntity> cartDetails = method.getListCartDetail(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
			float total = 0;
			for (CartDetailEntity c : cartDetails) {
				total = total + c.getQuantity() * c.getProduct().getPrice();
			}
			// oe.setOrderTotal(total);
			oe.setShipAddress(order.getShipAddress());
			oe.setShipName(order.getShipName());
			oe.setShipPhone(order.getShipPhone());
			oe.setShipNote(order.getShipNote());
			oe.setState(0);
//			/
//			add new properties for order later
			if (method.checkOutHibernate(oe, method.getListCartDetail(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))))) {
				attributes.addFlashAttribute("message", "Đặt hàng thành công!");
				String body = "<h3  style='margin: 8px 0;'>Thông tin người nhận</h3><p  style='margin: 4px 0;'>Tên: "
						+ order.getShipName() + "</p><p style='margin: 4px 0;'>SĐT: " + order.getShipPhone()
						+ "</p><p  style='margin: 4px 0;'>Địa chỉ: " + order.getShipAddress() + "</p>"
						+ "<p  style='margin: 4px 0;'>Phương thức thanh toán: Thanh toán khi nhận hàng" + "</p>";;
				body = body + "<h3  style='margin: 8px 0;'>Thông tin đơn hàng</h3>"
						+ "<table style='width: 400px;border: 1px solid black;"
						+ "  border-collapse: collapse;'> <thead>" + "						<tr>"
						+ "							<th style='border: 1px solid black;border-collapse: collapse; padding: 0 4px;'>Sản phẩm</th>"
						+ "							<th style='border: 1px solid black;border-collapse: collapse; padding: 0 4px;'>Số lượng</th>"
						+ "							<th style='border: 1px solid black;border-collapse: collapse; padding: 0 4px;'>Tổng</th>"
						+ "						</tr>" + "					</thead>";
				for (CartDetailEntity cde : cartDetails) {
					body = body + "<tr>"
							+ "<td style='border: 1px solid black;border-collapse: collapse;padding-left:4px;'><p style='margin: 4px 0 0 0;'>"
							+ cde.getProduct().getName() + "</p>" + "<p style='margin: 0 0 4px 0;'>"
							+ cde.getProduct().getPrice() + " đ</p></td>"
							+ "<td style='text-align: center;border: 1px solid black;border-collapse: collapse;'>"
							+ cde.getQuantity() + "</td>"
							+ "<td style='text-align: center;border: 1px solid black;border-collapse: collapse;'>"
							+ (cde.getQuantity() * cde.getProduct().getPrice()) + " đ</td>" + "</tr>";
				}
				body = body
						+ "<tr><td colspan='3' style='text-align: right;border: 1px solid black;border-collapse: collapse;'>"
						+ total + " đ</td></tr></table>";
				try {
					mailer.send("N20dccn128@student.ptithcm.edu.vn", order.getShipEmail().trim(),
							"Thông báo hệ thống đã xác nhận đơn hàng của quý khách.", body);
					System.out.println("Gửi email thành công!");
				} catch (Exception ex) {
					System.out.println("Gửi email thất bại!");
				}
				return "redirect:/";
			} else {
				model.addAttribute("message", "Đặt hàng thất bại!");
				model.addAttribute("cartDetails", cartDetails);
				model.addAttribute("cartDetailsTotal", total);
				return "store/check-out";
			}
//			model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng!");
		}
	}

	@RequestMapping("/all")
	public String allProduct(ModelMap model, HttpServletRequest request, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		PagedListHolder pagedListHolder = new PagedListHolder(method.getAllProducts());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(3);
		pagedListHolder.setPageSize(8);

		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("listFavorite", method.getListFavourite(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		return "store/all-products";
	}

	@RequestMapping("/shopping-cart")
	public String shoppingCart(ModelMap model, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("listCartDetail", method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));

		if ((httpSession.getAttribute("customerUsername") != null)) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		}

		return "store/shopping-cart";
	}

	@RequestMapping(value = "/shopping-cart/delete/{productId}", method = RequestMethod.GET)
	public String delete(@PathVariable("productId") String productId, HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		System.out.println(productId);
		if (method.deleteProductFromCartDetail(productId, httpSession)) {
			System.out.println("Delete successful");
		} else {
			System.out.println("Delete unsuccessful");
		}
		return "redirect:/store/shopping-cart";
	}

	@RequestMapping(value = "/shopping-cart/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, ModelMap model, HttpSession httpSession,
			RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		List<CartDetailEntity> listCart = method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		if (listCart.size() == 0) {
			attributes.addFlashAttribute("message", "Không có sản phẩm nào trong giỏ hàng!");
			return "redirect:/store/shopping-cart";
		}
		httpSession.setAttribute("listCategory", method.getListCategory());
		String[] product_ids = request.getParameterValues("product_id");
		String[] quantities = request.getParameterValues("quantity");
		for (int i = 0; i < product_ids.length; i++) {
			method.updateProductQuantityFromCartDetail(product_ids[i], quantities[i], httpSession);
		}
		attributes.addFlashAttribute("message", "Cập nhật số lượng thành công.");
		return "redirect:/store/shopping-cart";
	}

	public String returnRedirectControl(String redirectPath) {

		if (redirectPath.equals("index")) {
			return "redirect:/";
		}
		if (redirectPath.equals("user-fav")) {
			return "redirect:/store/user-info/favorite";
		}
		if (redirectPath.equals("user-cart")) {
			return "redirect:/store/shopping-cart";
		}
		if (redirectPath.contains("allseperator")) {
			String newPath = redirectPath.replaceAll("seperator", "/");
			newPath = newPath.replaceAll("questionmark", "?");
			return "redirect:/store/" + newPath;
		}
		if (redirectPath.contains("categoryseperator")) {
			String newPath = redirectPath.replaceAll("seperator", "/");
			newPath = newPath.replaceAll("questionmark", "?");
			return "redirect:/store/" + newPath;
		}
		if (redirectPath.contains("searchseperator")) {
			String newPath = redirectPath.replaceAll("seperator", "/");
			newPath = newPath.replaceAll("questionmark", "?");
			return "redirect:/store/" + newPath;
		}
		if (redirectPath.contains("product-detailseperator")) {
			String newPath = redirectPath.replaceAll("seperator", "/");
			return "redirect:/store/" + newPath;
		}
		return "redirect:/";
	}

	@RequestMapping("/shopping-cart/insert-into-cart/{productId}/{redirectPath}")
	public String insertToCart(String customerId, @PathVariable("productId") String productId,
			@PathVariable("redirectPath") String redirectPath, HttpSession httpSession, RedirectAttributes attributes,
			ModelMap model) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		CartDetailEntity cde = new CartDetailEntity(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")), (productId));
		if (method.isProductOutOfStock(productId)) {
			attributes.addFlashAttribute("message", "Sản phẩm hiện hết hàng");
//			return "redirect:/store/shopping-cart";
			return returnRedirectControl(redirectPath);
		}
		if (method.cartItemIsExit(productId, httpSession)) {
			System.out.println("Exist; increase quantity by 1!");
			if (!method.isCartItemMaximumAmountOfProduct(productId, httpSession)) { // nếu số lượng tồn của sản phẩm k
																					// bằng số
				// sản phẩm của
				// product đó trong cart

				if (method.updateProductQuantityFromCartDetail(productId,
						String.valueOf(method.getQuantityOfCartItem(productId, httpSession) + 1), httpSession)) {
					System.out.println("Exit; increase quantity by 1 successful!");
					attributes.addFlashAttribute("message", "Thêm thành công");
					if ((httpSession.getAttribute("customerUsername") != null)) {
						int sum = 0;
						for (CartDetailEntity c : method
								.getCustomerByUsername((String) httpSession.getAttribute("customerUsername"))
								.getCartDetails()) {
							sum = sum + c.getQuantity();
						}
						System.out.println(sum);
						httpSession.setAttribute("customerTotalQuantity", sum);
						model.addAttribute("listFavorite", method.getListFavourite(
								method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
					}
					return returnRedirectControl(redirectPath);
				} else {
					System.out.println("Exit; increase quantity by 1 unsuccessful!");
					return returnRedirectControl(redirectPath);
				}

			} else {
				System.out.println(
						"Exit; increase quantity by 1 unsuccessful \n because amount of that product incart is equal to its amount in product!");
				attributes.addFlashAttribute("message",
						"Số lượng của sản phẩm này trong giỏ đã bằng tồn kho");
				return returnRedirectControl(redirectPath);
			}

		} else {
			cde.setQuantity(1);
			try {
				session.save(cde);
				t.commit();
				System.out.println("Insert successful!");

			} catch (Exception ex) {
				t.rollback();
				System.out.println("Insert unsuccessful!");
			} finally {
				session.close();
			}
		}
		if ((httpSession.getAttribute("customerUsername") != null)) {
			int sum = 0;
			for (CartDetailEntity c : method
					.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(
					method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		}
		attributes.addFlashAttribute("message", "Thêm vào giỏ thành công");
		return returnRedirectControl(redirectPath);
//		return "redirect:/store/shopping-cart";
	}

	/*
	 * @RequestMapping(value = "/search", method = RequestMethod.GET) public String
	 * search2() {
	 * 
	 * return "store/search-result"; }
	 */
	@RequestMapping(value = "/search")
	public String search2() {
		return "redirect:/";
	}

	@RequestMapping(value = "/search/{keyword}")
	public String search(HttpServletRequest request, ModelMap model, @PathVariable("keyword") String keyword,
			HttpSession httpSession) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("keyword", keyword);
		System.out.println(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(method.searchForProduct(keyword));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(3);
		pagedListHolder.setPageSize(8);

		if (httpSession.getAttribute("listRecentSearch") != null) {
			List<String> lrs = (List<String>) httpSession.getAttribute("listRecentSearch");
			boolean existed = false;
			for (String keyw : lrs) {
				if (keyw.equals(keyword)) {
					existed = true;
				}
			}
			if (!existed) {
				lrs.add(0, keyword);
			}
			httpSession.setAttribute("listRecentSearch", lrs);
		} else {
			List<String> lrs = new ArrayList<String>();
			lrs.add(0, keyword);

			httpSession.setAttribute("listRecentSearch", lrs);
		}
		model.addAttribute("listFavorite", method.getListFavourite(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "store/search-result";
	}

	@RequestMapping("/user-info/favorite")
	public String favoritePage(HttpSession httpSession, ModelMap model) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		model.addAttribute("listFavorite", method.getListFavourite(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		model.addAttribute("customerEntity",
				method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
		

		return "store/user-favorite";
	}
	
	
	// Theem vaof danh sach yeu thich
	@RequestMapping("/insert-to-favlist/{productId}/{redirectPath}")
	public String insertToFavouriteList(String customerId, @PathVariable("productId") String productId,
			@PathVariable("redirectPath") String redirectPath, HttpSession httpSession, RedirectAttributes attributes) {
		Methods method = new Methods(factory);
		httpSession.setAttribute("listCategory", method.getListCategory());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		FavoriteProductEntity fp = new FavoriteProductEntity();
		if (method.favItemIsExit(productId, httpSession)) {
			if(method.deleteProductFromFavourite(productId, httpSession)) {
				attributes.addFlashAttribute("message", "Đã xóa khỏi danh sách yêu thích");
				System.out.println("remove from fav list successful!-------------");
			}
		} else {
			fp.setCustomer(method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")));
			fp.setProduct(method.getProduct(productId));
			try {
				session.save(fp);
				t.commit();
				System.out.println("Insert to fav list successful!-------------");
				attributes.addFlashAttribute("message", "Đã thêm vào danh sách yêu thích");

			} catch (Exception ex) {
				t.rollback();
				System.out.println("--------Insert to fav list unsuccessful!");
			} finally {
				session.close();
			}
		}

		return returnRedirectControl(redirectPath);
//		return "redirect:/store/user-info/favorite";
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static final Pattern VALID_VI_PHONE_NUMBER_REGEX = Pattern.compile("^0{1}[0-9]{9}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validatePhone(String phoneNumber) {
		Matcher matcher = VALID_VI_PHONE_NUMBER_REGEX.matcher(phoneNumber);
		return matcher.find();
	}

	@RequestMapping("/filter")
    public String filterProducts(ModelMap model,
    		@RequestParam(value = "nameCategory", required = false) String nameCategory,
            @RequestParam(value = "screenType", required = false) String screenType,
            @RequestParam(value = "screenSize", required = false) String screenSize,
            @RequestParam(value = "scanningFrequency", required = false) String scanningFrequency,
            @RequestParam(value = "resolution", required = false) String resolution,
            @RequestParam(value = "utilities", required = false) String utilities,
            @RequestParam(value = "operatingSystem", required = false) String operatingSystem,HttpServletRequest request,HttpSession httpSession
    ) {
	 Methods methods  = new Methods(factory);
        List<ProductEntity> productList =methods.filterProducts(nameCategory ,screenType, screenSize, scanningFrequency, resolution, utilities, operatingSystem);

        System.out.println(productList.size()  + "PHUCCSCSCS");
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("productsFilter", productList);


        PagedListHolder pagedListHolder = new PagedListHolder(methods.filterProducts(nameCategory, screenType, screenSize, scanningFrequency, resolution, utilities, operatingSystem));
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(3);
		pagedListHolder.setPageSize(8);

		model.addAttribute("listFavorite", methods.getListFavourite(
				methods.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
		model.addAttribute("pagedListHolder", pagedListHolder);


        return "store/filter-products";
    }
	
	
}
