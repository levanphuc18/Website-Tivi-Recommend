package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import entities.ConfigEntity;
import entities.SupplierEntity;
import models.Generate;
import models.Pagination;
import models.UploadFile;
import models.List.Configs;
import models.List.Suppliers;
import models.dao.ConfigDAO;
import models.dao.SupplierDAO;

@Controller
@Transactional
@RequestMapping("/admin/suppliers")
public class SupplierController {
	@Autowired
	ServletContext context;

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory factory;

	SupplierDAO supplierDAO;
	
	String viewsDirectory = "admin/pages/supplier/";
	
	@RequestMapping("")
	public String renderSupplierPage(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "search", required = false) String search) throws IOException {
		
		Suppliers suppliers = new Suppliers();
		supplierDAO = new SupplierDAO(factory);
		
		if (search != null) {
			suppliers = supplierDAO.searchForSupplier(search);
			model.addAttribute("pagedLink", "/admin/suppliers?search=" + search);

		} else {
			suppliers = supplierDAO.getSuppliers();
			model.addAttribute("pagedLink", "/admin/suppliers");
		}
		
		PagedListHolder pagedListHolder = Pagination.supplierPagination(request, suppliers.getList(), 10, 5);

		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("type", "nhà cung cấp");
		model.addAttribute("title", "Quản lý Nhà cung cấp");

		return viewsDirectory + "supplier";
	}
	
	// VIEW DETAILS OF SUPPLIER
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String supplierDetail(ModelMap model, HttpServletRequest request, @PathVariable(value = "id") String id) {
		System.out.println("Id: " + id);

		supplierDAO = new SupplierDAO(factory);
		SupplierEntity supplier = supplierDAO.getSupplier(id);
		model.addAttribute("supplier", supplier);
		model.addAttribute("title", "Nhà cung cấp " + supplier.getName());

		PagedListHolder pagedListHolder = Pagination.invoicePagination(request, supplier.getInvoices(), 5, 5);
		model.addAttribute("pagedLink", "/admin/categories/" + id);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("type", "sản phẩm");

		return viewsDirectory + "viewSupplier";
	}
	
	// ADD SUPPLIER
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String renderAddSupplierPage(ModelMap model) {
		SupplierEntity supplier = new SupplierEntity();
		model.addAttribute("supplier", supplier);
		model.addAttribute("title", "Thêm Nhà cung cấp");
		return viewsDirectory + "addSupplier";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addConfig(ModelMap model, @ModelAttribute(value = "supplier") SupplierEntity supplier,
			BindingResult errors,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, InterruptedException {

		if (supplier.getName().isEmpty()) {
			errors.rejectValue("name", "supplier", "Nhập tên nhà cung cấp");
			model.addAttribute("nameValid", "is-invalid");
		}

		if (errors.hasFieldErrors("name")) {
			model.addAttribute("supplier", supplier);
			model.addAttribute("title", "Thêm Nhà cung cấp");
			return viewsDirectory + "addSupplier";

		} else {
			supplierDAO = new SupplierDAO(factory);
			Suppliers suppliers = supplierDAO.getSuppliers();
			String supplierId = Generate.generateSupplierId(suppliers.getList());

			supplier.setId(supplierId);

			if (supplierDAO.addSupplierInDB(supplier)) {
				redirectAttributes.addFlashAttribute("message", "Thêm nhà cung cấp thành công!");
				redirectAttributes.addFlashAttribute("messageType", "success");
			} else {
				redirectAttributes.addFlashAttribute("message", "Không thể thêm nhà cung cấp!");
				redirectAttributes.addFlashAttribute("messageType", "error");
			}

			return "redirect:/admin/suppliers/add";
		}
	}
	
	// EDIT SUPPLIER
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String renderEditSupplierPage(ModelMap model, @PathVariable(value = "id") String id) {
		supplierDAO = new SupplierDAO(factory);
		SupplierEntity supplier = supplierDAO.getSupplier(id);
		model.addAttribute("supplier", supplier);
		model.addAttribute("title", "Sửa nhà cung cấp");
		return viewsDirectory + "editSupplier";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editConfig(ModelMap model, HttpServletRequest request, @PathVariable(value = "id") String id,
			@ModelAttribute("supplier") SupplierEntity modifiedSupplier, BindingResult errors,
			RedirectAttributes redirectAttributes) throws IOException, InterruptedException {

		supplierDAO = new SupplierDAO(factory);
		if (modifiedSupplier.getName().isEmpty()) {
			errors.rejectValue("name", "supplier", "Nhập tên nhà cung cấp");
			model.addAttribute("nameValid", "is-invalid");
		}

		if (errors.hasFieldErrors("name")) {
			SupplierEntity supplier = supplierDAO.getSupplier(id);
			model.addAttribute("supplier", modifiedSupplier);
			model.addAttribute("title", "Thêm Nhà cung cấp");
			return viewsDirectory + "editSupplier";
		} else {
			SupplierEntity supplier = supplierDAO.getSupplier(id);
			supplier.setName(modifiedSupplier.getName());
			supplier.setPhone(modifiedSupplier.getPhone());
			supplier.setEmail(modifiedSupplier.getEmail());
			supplier.setAddress(modifiedSupplier.getAddress());
			supplier.setTax(modifiedSupplier.getTax());

			if (supplierDAO.updateSupplierInDB(supplier)) {
				redirectAttributes.addFlashAttribute("message", "Cập nhật nhà cung cấp thành công!");
				redirectAttributes.addFlashAttribute("messageType", "success");
			} else {
				redirectAttributes.addFlashAttribute("message", "Không thể cập nhật nhà cung cấp!");
				redirectAttributes.addFlashAttribute("messageType", "error");
			}
			return "redirect:/admin/suppliers/edit/" + supplier.getId();
		}
	}
	
	// DELETE CATEGORY
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(ModelMap model, @PathVariable("id") String id, RedirectAttributes redirectAttributes) {

		supplierDAO = new SupplierDAO(factory);
		SupplierEntity supplier = supplierDAO.getSupplier(id);

		if (supplierDAO.deleteSupplierInDB(supplier)) {
			redirectAttributes.addFlashAttribute("message", "Xoá nhà cung cấp thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		} else {
			redirectAttributes.addFlashAttribute("message", "Không thể xoá nhà cung cấp!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}

		return "redirect:/admin/suppliers";
	}
}
