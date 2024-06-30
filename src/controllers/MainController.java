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
//		model.addAttribute("12332133", method.filterProducts("màn hình phẳng","46 inch","40HZ", "4K","Điều khiển thông minh","Smart TV"));
//		model.addAttribute("12332133", method.filterProducts("45 inch"));
		
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
		
		
		// Filter
		
		
		
		
		// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
//		String listMHStr = getRecommendation("P5610");
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

		/* addProductsForTesting(); */
		return "store/index";
	}
	
	
	// Filter
//	 @Autowired
//	    private ConfigService configService;
//	 Methods method = new Methods(factory);
//
//	    @RequestMapping("/filters")
//	    public String getFilters(Model model) {
//	        ConfigFiltersDTO filters = configService.getUniqueColumnValues();
//	        model.addAttribute("filters", filters);
//	        return "filters";
//	    }
//
//	    @PostMapping("/filterResults")
//	    public String getFilterResults(@RequestParam("color") String color,
//	                                   @RequestParam("screentype") String screentype,
//	                                   @RequestParam("screensize") String screensize,
//	                                   @RequestParam("scanningfrequency") String scanningfrequency,
//	                                   @RequestParam("resolution") String resolution,
//	                                   @RequestParam("utilities") String utilities,
//	                                   @RequestParam("operatingsystem") String operatingsystem,
//	                                   Model model) {
//	        FilterCriteria criteria = new FilterCriteria();
//	        criteria.setColor(color);
//	        criteria.setScreentype(screentype);
//	        criteria.setScreensize(screensize);
//	        criteria.setScanningfrequency(scanningfrequency);
//	        criteria.setResolution(resolution);
//	        criteria.setUtilities(utilities);
//	        criteria.setOperatingsystem(operatingsystem);
//
//	        List<ConfigEntity> filteredResults = configService.getFilteredConfigs(criteria);
//	        model.addAttribute("filteredResults", filteredResults);
//	        return "filterResults"; // Tên view hiển thị kết quả lọc
//	    }
	
	
//	public String getRecommendation(String maMH) {
//		String s = null;
//		String str = null;
//		try {
//
//			// run the Unix "ps -ef" command
//			// using the Runtime exec method:
//			String cmd = "python C:\\Users\\levan\\OneDrive\\Desktop\\TTCS\\webbantivi1\\webbantivi\\WebContent\\resources\\python\\test.py " + maMH;
//			Process p = Runtime.getRuntime().exec(cmd);
//
//			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//
//			// read the output from the command
//			System.out.println("Here is the standard output of the command:\n");
//			while ((s = stdInput.readLine()) != null) {
//				System.out.println(s);
//
//				str = s;
//			}
//
//			// read any errors from the attempted command
//			System.out.println("Here is the standard error of the command (if any):\n");
//			while ((s = stdError.readLine()) != null) {
//				System.out.println(s);
//			}
//
//			// System.exit(0);
//		} catch (IOException e) {
//			System.out.println("exception happened - here's what I know: ");
//			e.printStackTrace();
//			// System.exit(-1);
//		}
//		return str;
//
//	}
	// ĐỀ XUẤTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
	


	
}
