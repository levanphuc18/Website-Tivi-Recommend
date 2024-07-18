package controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		model.addAttribute("listlx", method.getTop4ProductsWithTheMostViews());
		
		model.addAttribute("listCategories", method.getUniqueCategories());
		model.addAttribute("listlx", method.getTop4ProductsWithTheMostViews());
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
		}
		
		
		// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
		// Lấy mã khách hàng từ phiên làm việc
//	    String customerId = (String) httpSession.getAttribute("customerId");
//	    System.out.println("mã kahcsh hanbg đăng nhập "+ customerId);
//	    
//		String listMHStr = method.getRecommendation("U0013");
//		String tmp = listMHStr.replace("'", "");
//		tmp = tmp.replace("[", "");
//		tmp = tmp.replace("]", "");
//		tmp = tmp.replace(" ", "");
//		
//		System.out.print(tmp);
//		String[] tmp2= tmp.split(",");
//		
//		
//
//		List<ProductEntity> listProductDeXuat = new ArrayList<>();
//		for(String s:tmp2) {
//			
//			ProductEntity prdtmp= method.getProduct(s);
//			if (prdtmp != null) {
//		        listProductDeXuat.add(prdtmp);
//		        System.out.println("\nCác sản phẩm được đề xuất: " + prdtmp.getId() + "\n");
//		    } else {
//		        System.out.println("\nSản phẩm với ID " + s + " không tồn tại.\n");
//		    }
//			System.out.println("\n Các sản phẩm được đề xuất: "+ prdtmp.getId()+"\n");
//		}
//
//
//	model.addAttribute("listDXSP", listProductDeXuat);
//	httpSession.setAttribute("listCategory", method.getListCategory());
//	if(httpSession.getAttribute("customerUsername")!=null) {
//	int sum = 0;
//	for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
//		sum = sum + c.getQuantity();
//	}
//	System.out.println(sum);
//	httpSession.setAttribute("customerTotalQuantity", sum);
//	model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
//}

//		// ĐỀ XUẤT
//		// Lấy mã khách hàng từ phiên làm việc
//	    
//		String customerId = method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"));
//	    System.out.println("mã kahcsh hanbg đăng nhập "+ customerId);
//	    if(customerId != null) {
//	    	System.out.println("dhasjdsa");
//		String listMHStr = method.getRecommendation(customerId);
//		String tmp = listMHStr.replace("'", "");
//		tmp = tmp.replace("[", "");
//		tmp = tmp.replace("]", "");
//		tmp = tmp.replace(" ", "");
//		
//		System.out.print(tmp);
//		String[] tmp2= tmp.split(",");
//		
//		List<ProductEntity> listProductDeXuat = new ArrayList<>();
//		for(String s:tmp2) {
//			
//			ProductEntity prdtmp= method.getProduct(s);
//			if (prdtmp != null) {
//		        listProductDeXuat.add(prdtmp);
//		        System.out.println("\nCác sản phẩm được đề xuất: " + prdtmp.getId() + "\n");
//		    } else {
//		        System.out.println("\nSản phẩm với ID " + s + " không tồn tại.\n");
//		    }
//			System.out.println("\n Các sản phẩm được đề xuất: "+ prdtmp.getId()+"\n");
//		}
//
//
//		model.addAttribute("listDXSP", listProductDeXuat);
//		httpSession.setAttribute("listCategory", method.getListCategory());
//		if(httpSession.getAttribute("customerUsername")!=null) {
//			int sum = 0;
//		for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
//			sum = sum + c.getQuantity();
//		}
//		System.out.println(sum);
//		httpSession.setAttribute("customerTotalQuantity", sum);
//		model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
//}
//	
//		model.addAttribute("listDXSP", listProductDeXuat);
//		httpSession.setAttribute("listCategory", method.getListCategory());
//		if(httpSession.getAttribute("customerUsername")!=null) {
//			int sum = 0;
//		for (CartDetailEntity c : method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername")).getCartDetails()) {
//			sum = sum + c.getQuantity();
//		}
//		System.out.println(sum);
//		httpSession.setAttribute("customerTotalQuantity", sum);
//		model.addAttribute("listFavorite", method.getListFavourite(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))));
//	}			
//		}
//		
//		// DE XUAT
		return "store/index";
	}
	
	


}
