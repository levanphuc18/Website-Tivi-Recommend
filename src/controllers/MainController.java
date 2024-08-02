package controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.CartDetailEntity;
import entities.CategoryEntity;
import entities.CustomerEntity;
import entities.ImageEntity;
import entities.ProductEntity;

@Transactional
@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	SessionFactory factory;
	@RequestMapping("")
	public String store(ModelMap model, HttpSession httpSession) {
		/* model.addAttribute("list", getListProduct()); */
		Methods method = new Methods(factory);
		
		// Hiển thị ngôi sao
		// Lấy danh sách tất cả sản phẩm
		List<ProductEntity> products = method.getAllProducts();

		// Tạo Map để lưu thông tin đánh giá cho mỗi sản phẩm
		Map<String, Map<String, Object>> productRatings = new HashMap<>();

		for (ProductEntity product : products) {
		    String productId = product.getId();
		    int reviewsCount = method.getProductReviewsCount(productId); // số lượng đánh giá
		    int totalRating = method.getTotalRating(productId); // tổng điểm đánh giá
		    double averageRating = reviewsCount > 0 ? (double) totalRating / reviewsCount : 0.0; // điểm trung bình

		    // Tạo Map để lưu thông tin đánh giá của sản phẩm
		    Map<String, Object> reviewInfo = new HashMap<>();
		    reviewInfo.put("averageRating", averageRating); // điểm trung bình đánh giá

		    // Lưu thông tin vào Map với productId là khóa
		    productRatings.put(productId, reviewInfo);
		}

		// Thêm danh sách sản phẩm và thông tin đánh giá vào ModelMap
		model.addAttribute("products", products);
		model.addAttribute("productRatings", productRatings);
		// Hiển thị ngôi sao
		
		model.addAttribute("list", method.getTop16RecentProduct());
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
		
		// TOP 4 SP CÓ LƯỢT XEM NHIỀU NHẤT	
//		model.addAttribute("listlx", method.getTop4ProductsWithTheMostViews());
		model.addAttribute("listCategories", method.getUniqueCategories());
		model.addAttribute("listScreenTypes", method.getUniqueScreenTypes());
		model.addAttribute("listScreenSizes", method.getUniqueScreenSizes());
		model.addAttribute("listScanningFrequencies", method.getUniqueScanningFrequencies());
		model.addAttribute("listResolutions", method.getUniqueResolutions());
		model.addAttribute("listUtilities", method.getUniqueUtilities());
		model.addAttribute("listOperatingSystems", method.getUniqueOperatingSystems());
		
		httpSession.setAttribute("listCategory", method.getListCategory());
		if(httpSession.getAttribute("customerUsername")!=null) {
			int sum = 0;
			for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
				sum = sum + c.getQuantity();
			}
			System.out.println(sum);
			httpSession.setAttribute("customerTotalQuantity", sum);
			model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
//		}
			
		
		// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
		// Lấy mã khách hàng từ phiên làm việc
		String customerId = method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"));
//	    System.out.println("mã khach hang đăng nhập "+ customerId);
	    
		String listMHStr = method.getRecommendation(customerId);
		String tmp = listMHStr.replace("'", "");
		tmp = tmp.replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace(" ", "");
		
		System.out.print(tmp);
		String[] tmp2= tmp.split(",");

		List<ProductEntity> listProductRecommend = new ArrayList<>();
		for(String s:tmp2) {
			
			ProductEntity prdtmp= method.getProduct(s);
			if (prdtmp != null) {
				listProductRecommend.add(prdtmp);
//		        System.out.println("\nCác sản phẩm được đề xuất: " + prdtmp.getId() + "\n");
		    } else {
		        System.out.println("\nSản phẩm với ID " + s + " không tồn tại.\n");
		    }
//			System.out.println("\n Các sản phẩm được đề xuất: "+ prdtmp.getId()+"\n");
		}
		model.addAttribute("listProductRecommend", listProductRecommend);
	}else {
		model.addAttribute("listProductRecommend", method.getTop4ProductsWithTheMostViews());
	}

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

		return "store/index";
	}
}
