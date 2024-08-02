package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entities.AdminEntity;
import entities.ConfigEntity;
import entities.DisscountEntity;
import models.Generate;
import models.Pagination;
import models.UploadFile;
import models.List.Configs;
import models.List.Disscounts;
import models.dao.ConfigDAO;
import models.dao.DisscountDAO;
import models.validator.DateTimeValidator;

@Controller
@Transactional
@RequestMapping("/admin/disscounts")
public class DisscountController {

	@Autowired
	ServletContext context;

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory factory;
	
	DisscountDAO disscountDAO;
	
	String viewsDirectory = "admin/pages/disscount/";
	
	@RequestMapping("")
	public String renderConfigPage(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "search", required = false) String search) throws IOException {
		
		Disscounts disscounts = new Disscounts();
		disscountDAO = new DisscountDAO(factory);
		
		if (search != null) {
			disscounts = disscountDAO.searchForDisscounts(search);
			model.addAttribute("pagedLink", "/admin/disscounts?search=" + search);

		} else {
			disscounts = disscountDAO.getDisscounts();
			model.addAttribute("pagedLink", "/admin/disscounts");
		}
		
		PagedListHolder pagedListHolder = Pagination.disscountPagination(request, disscounts.getList(), 10, 5);

		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("type", "khuyến mãi");
		model.addAttribute("title", "Quản lý khuyến mãi");

		return viewsDirectory + "disscount";
	}
	
	// VIEW DETAILS OF CONFIG
			@RequestMapping(value = "/{id}", method = RequestMethod.GET)
			public String disscountDetail(ModelMap model, HttpServletRequest request, @PathVariable(value = "id") String id) {
				System.out.println("Id: " + id);

				disscountDAO = new DisscountDAO(factory);
				DisscountEntity disscount = disscountDAO.getDisscount(id);
				model.addAttribute("disscount", disscount);
				model.addAttribute("title", "Khuyến mãi " + disscount.getName());

				PagedListHolder pagedListHolder = Pagination.productPagination(request, disscount.getProducts(), 5, 5);
				model.addAttribute("pagedLink", "/admin/categories/" + id);
				model.addAttribute("pagedListHolder", pagedListHolder);
				model.addAttribute("type", "sản phẩm");

				return viewsDirectory + "viewDisscount";
			}
			
			// ADD CONFIG
			@RequestMapping(value = "/add", method = RequestMethod.GET)
			public String renderAddDisscountPage(ModelMap model) {
				DisscountEntity disscount = new DisscountEntity();
				model.addAttribute("disscount", disscount);
				model.addAttribute("title", "Thêm khuyến mãi");
				return viewsDirectory + "addDisscount";
			}

			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public String addDisscount(HttpSession session,ModelMap model, @ModelAttribute(value = "disscount") DisscountEntity disscount,
					BindingResult errors,
					RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, InterruptedException {

				if (disscount.getName().isEmpty()) {
					errors.rejectValue("name", "disscount", "Nhập tên khuyến mãi");
					model.addAttribute("nameValid", "is-invalid");
				}

				if (errors.hasFieldErrors("name")) {
					model.addAttribute("disscount", disscount);
					model.addAttribute("title", "Thêm khuyến mãi");
					return viewsDirectory + "addDisscount";

				} else {
					disscountDAO = new DisscountDAO(factory);
					Disscounts disscounts = disscountDAO.getDisscounts();
					String disscountId = Generate.generateDisscountId(disscounts.getList());

					disscount.setId(disscountId);
					
					AdminEntity admin = new AdminEntity();
					admin.setId(session.getAttribute("adminId").toString());
					disscount.setAdmin(admin);


					// Sử dụng ngày bắt đầu và ngày kết thúc từ đối tượng disscount
			        Date startDate = disscount.getStartDate();
			        Date endDate = disscount.getEndDate();
			        
//			        // Kiểm tra và xử lý ngày bắt đầu và ngày kết thúc
//			        if (startDate == null || endDate == null) {
//			            errors.rejectValue("startDate", "disscount", "Ngày bắt đầu và ngày kết thúc không được để trống");
//			            model.addAttribute("disscount", disscount);
//			            model.addAttribute("title", "Thêm khuyến mãi");
//			            return viewsDirectory + "addDisscount";
//			        }

			        if (startDate.after(endDate)) {
			            errors.rejectValue("endDate", "disscount", "Ngày kết thúc phải sau ngày bắt đầu");
			            model.addAttribute("disscount", disscount);
			            model.addAttribute("title", "Thêm khuyến mãi");
			            return viewsDirectory + "addDisscount";
			        }


					if (disscountDAO.addDisscountInDB(disscount)) {
						redirectAttributes.addFlashAttribute("message", "Thêm khuyến mãi thành công!");
						redirectAttributes.addFlashAttribute("messageType", "success");
					} else {
						redirectAttributes.addFlashAttribute("message", "Không thể thêm khuyến mãi!");
						redirectAttributes.addFlashAttribute("messageType", "error");
					}

					return "redirect:/admin/disscounts/add";
				}
			}
}
