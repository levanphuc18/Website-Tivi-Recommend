package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entities.CartDetailEntity;
import entities.CustomerEntity;
import entities.OrderEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import vnpay.Config;
import models.Mailer;
import models.OrderModel;
import models.dao.OrderDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
@Transactional
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	
	@RequestMapping(value = "/vnpay", method = RequestMethod.GET)
	public String vnpay(HttpServletRequest request, HttpSession httpSession, ModelMap model
			, RedirectAttributes attributes) throws UnsupportedEncodingException {
		Methods method= new Methods(factory);
		System.out.println("VN-PAY");
		String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
//        String vnp_OrderInfo = orderId;
        String orderType = "other";
        String vnp_TxnRef = Config.getRandomNumber(8);
//        String vnp_IpAddr = "192.168.1.7";
        String vnp_IpAddr = Config.getIpAddress(request);
        String vnp_TmnCode = Config.vnp_TmnCode;
//        String bank_code= "NCB";
        
        List<CartDetailEntity> cartDetails = method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		float total = 0;
		for (CartDetailEntity c : cartDetails) {
			total = total + c.getQuantity() * c.getProduct().getPrice();
		}
		
		CustomerEntity cus = method.getCustomerByUsername((String) httpSession.getAttribute("customerUsername"));
//		System.out.println(cus.getAddress() + "123");

		int amount = (int) (total * 100);
		System.out.println(amount);
        
        Map vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");      
//        vnp_Params.put("vnp_BankCode", bank_code);
        String bank_code = request.getParameter("bankcode");
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        System.out.println("duong dan vnpay: " + paymentUrl);
        
        
        OrderEntity oe = new OrderEntity();
		oe.setCustomerId(method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername")));
		oe.setId(method.createTheNextOrderId());
		oe.setOrderDate(new Date());
		// oe.setOrderTotal(total);
		oe.setShipAddress(cus.getAddress());
		oe.setShipName(cus.getFirstname());
		oe.setShipPhone(cus.getPhone());
		oe.setShipNote("");
		oe.setState(0);
//		/
//		add new properties for order later
		if (method.checkOutHibernate(oe, method.getListCartDetail(
				method.getCustomerIdByUserName((String) httpSession.getAttribute("customerUsername"))))) {
			attributes.addFlashAttribute("message", "Đặt hàng thành công!");
			String body = "<h3  style='margin: 8px 0;'>Thông tin người nhận</h3><p  style='margin: 4px 0;'>Tên: "
					+ cus.getLastname() + "</p><p style='margin: 4px 0;'>SĐT: " + cus.getPhone()
					+ "</p><p  style='margin: 4px 0;'>Địa chỉ: " + cus.getAddress() + "</p>"
					+ "<p  style='margin: 4px 0;'>Phương thức thanh toán: VNPAY" + "</p>";
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
					+ amount + " đ</td></tr></table>";
			try {
				mailer.send("N20dccn128@student.ptithcm.edu.vn", cus.getEmail().trim(),
						"Thông báo hệ thống đã xác nhận đơn hàng của quý khách.", body);
				System.out.println("Gửi email thành công!");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Gửi email thất bại!");
			}
			return "redirect:" + paymentUrl;
		} else {
			model.addAttribute("message", "Đặt hàng thất bại!");
			model.addAttribute("cartDetails", cartDetails);
			model.addAttribute("cartDetailsTotal", total);
			return "store/index";
		}
	}
	
	
	@RequestMapping(value = "/vnpay-return", method = RequestMethod.GET)
	public String vnpay1(HttpServletRequest request, HttpSession httpSession) throws UnsupportedEncodingException {
		System.out.println("VN-PAY-return");
		return "store/vnpay-return";
	}
}
