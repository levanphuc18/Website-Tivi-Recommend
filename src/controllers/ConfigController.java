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

import entities.CategoryEntity;
import entities.ConfigEntity;
import models.Generate;
import models.Pagination;
import models.UploadFile;
import models.List.Categories;
import models.List.Configs;
import models.dao.CategoryDAO;
import models.dao.ConfigDAO;

@Controller
@Transactional
@RequestMapping("/admin/configs")
public class ConfigController {
	@Autowired
	ServletContext context;

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory factory;
	
	@Autowired
	@Qualifier("uploadFile")
	UploadFile uploadFile;
	
	ConfigDAO configDAO;
	
	String viewsDirectory = "admin/pages/config/";
	
	@RequestMapping("")
	public String renderConfigPage(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "search", required = false) String search) throws IOException {
		
		Configs configs = new Configs();
		configDAO = new ConfigDAO(factory);
		
		if (search != null) {
			configs = configDAO.searchForConfig(search);
			model.addAttribute("pagedLink", "/admin/configs?search=" + search);

		} else {
			configs = configDAO.getConfigs();
			model.addAttribute("pagedLink", "/admin/configs");
		}
		
		PagedListHolder pagedListHolder = Pagination.configPagination(request, configs.getList(), 10, 5);

		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("type", "danh mục");
		model.addAttribute("title", "Quản lý Cấu hình");

		return viewsDirectory + "config";
	}
	
	// VIEW DETAILS OF CONFIG
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public String configDetail(ModelMap model, HttpServletRequest request, @PathVariable(value = "id") String id) {
			System.out.println("Id: " + id);

			configDAO = new ConfigDAO(factory);
			ConfigEntity config = configDAO.getConfig(id);
			model.addAttribute("config", config);
			model.addAttribute("title", "Danh mục " + config.getName());

			PagedListHolder pagedListHolder = Pagination.productPagination(request, config.getProducts(), 5, 5);
			model.addAttribute("pagedLink", "/admin/categories/" + id);
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("type", "sản phẩm");

			return viewsDirectory + "viewConfig";
		}
		
		// EDIT CONFIG
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String renderEditConfigPage(ModelMap model, @PathVariable(value = "id") String id) {

			configDAO = new ConfigDAO(factory);
			ConfigEntity config = configDAO.getConfig(id);
			model.addAttribute("config", config);
			model.addAttribute("title", "Sửa cấu hình");
			return viewsDirectory + "editConfig";
		}

		@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
		public String editConfig(ModelMap model, HttpServletRequest request, @PathVariable(value = "id") String id,
				@RequestParam(value = "image", required = false) MultipartFile image,
				@ModelAttribute("config") ConfigEntity modifiedConfig, BindingResult errors,
				RedirectAttributes redirectAttributes) throws IOException, InterruptedException {

			configDAO = new ConfigDAO(factory);
			if (modifiedConfig.getName().isEmpty()) {
				errors.rejectValue("name", "config", "Nhập tên cấu hình");
				model.addAttribute("nameValid", "is-invalid");
			}

			if (errors.hasFieldErrors("name")) {
				ConfigEntity config = configDAO.getConfig(id);
				modifiedConfig.setImage(config.getImage());
				model.addAttribute("config", modifiedConfig);
				model.addAttribute("title", "Thêm Cấu hình");
				return viewsDirectory + "editConfig";
			} else {
				ConfigEntity config = configDAO.getConfig(id);
				config.setName(modifiedConfig.getName());
				config.setColor(modifiedConfig.getColor());
				config.setScreentype(modifiedConfig.getScreentype());
				config.setScreensize(modifiedConfig.getScreensize());
				config.setScanningfrequency(modifiedConfig.getScanningfrequency());
				config.setResolution(modifiedConfig.getResolution());
				config.setUtilities(modifiedConfig.getUtilities());
				config.setOperatingsystem(modifiedConfig.getOperatingsystem());
				
				

				if (image.getSize() != 0) {
					String oldImageFileName = config.getId() + UploadFile.getExtension(config.getImage());
					String imageFileName = config.getId() + UploadFile.getExtension(image.getOriginalFilename());
					String imagePath = uploadFile.getRelativeUploadPath() + "configs/" + imageFileName;
					config.setImage(imagePath);

					File oldFileInServer = new File(
							uploadFile.getUploadPathOnServer(context) + "configs/" + oldImageFileName);
					File oldFileInResource = new File(uploadFile.getUploadPath() + "configs/" + oldImageFileName);

					File fileInServer = new File(uploadFile.getUploadPathOnServer(context) + "configs/" + imageFileName);
					File fileInResource = new File(uploadFile.getUploadPath() + "configs/" + imageFileName);

					if (oldFileInResource.exists()) {
						oldFileInResource.delete();
					}

					if (oldFileInServer.exists()) {
						oldFileInServer.delete();
					}

					UploadFile.writeFile(fileInServer, image);
					UploadFile.writeFile(fileInResource, image);

					Thread.sleep(5000);
				}

				if (configDAO.updateConfigInDB(config)) {
					redirectAttributes.addFlashAttribute("message", "Cập nhật danh mục thành công!");
					redirectAttributes.addFlashAttribute("messageType", "success");
				} else {
					redirectAttributes.addFlashAttribute("message", "Không thể cập nhật danh mục!");
					redirectAttributes.addFlashAttribute("messageType", "error");
				}

				return "redirect:/admin/configs/edit/" + config.getId();
			}
		}
		
		
		// ADD CONFIG
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public String renderAddConfigPage(ModelMap model) {
			ConfigEntity config = new ConfigEntity();
			model.addAttribute("config", config);
			model.addAttribute("title", "Thêm Cấu Hình");
			return viewsDirectory + "addConfig";
		}

		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String addConfig(ModelMap model, @ModelAttribute(value = "config") ConfigEntity config,
				BindingResult errors, @RequestParam(value = "image", required = false) MultipartFile image,
				RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, InterruptedException {

			if (config.getName().isEmpty()) {
				errors.rejectValue("name", "config", "Nhập tên cấu hình");
				model.addAttribute("nameValid", "is-invalid");
			}

			if (errors.hasFieldErrors("name")) {
				model.addAttribute("config", config);
				model.addAttribute("title", "Thêm Danh Mục");
				return viewsDirectory + "addConfig";

			} else {
				configDAO = new ConfigDAO(factory);
				Configs configs = configDAO.getConfigs();
				String configId = Generate.generateConfigId(configs.getList());

				config.setId(configId);
				config.setDateAdded(new Date());

				if (image.getSize() != 0) {
					String imageFileName = configId + UploadFile.getExtension(image.getOriginalFilename());
					String imagePath = uploadFile.getRelativeUploadPath() + "configs/" + imageFileName;
					config.setImage(imagePath);

					File fileInServer = new File(uploadFile.getUploadPathOnServer(context) + "configs/" + imageFileName);
					File fileInResource = new File(uploadFile.getUploadPath() + "configs/" + imageFileName);
					if (!fileInServer.exists()) {
						UploadFile.writeFile(fileInServer, image);
					}
					if (!fileInResource.exists()) {
						UploadFile.writeFile(fileInResource, image);
					}
				}

				if (configDAO.addConfigInDB(config)) {
					redirectAttributes.addFlashAttribute("message", "Thêm cấu hình thành công!");
					redirectAttributes.addFlashAttribute("messageType", "success");
				} else {
					redirectAttributes.addFlashAttribute("message", "Không thể thêm cấu hình!");
					redirectAttributes.addFlashAttribute("messageType", "error");
				}

				return "redirect:/admin/configs/add";
			}
		}
		
		// DELETE CATEGORY
		@RequestMapping(value = "/delete/{id}")
		public String deleteConfig(ModelMap model, @PathVariable("id") String id, RedirectAttributes redirectAttributes) {

			configDAO = new ConfigDAO(factory);
			ConfigEntity config = configDAO.getConfig(id);

			if (configDAO.deleteConfigInDB(config)) {
				String imageFileName = config.getId() + UploadFile.getExtension(config.getImage());
				File fileInServer = new File(uploadFile.getUploadPathOnServer(context) + "configs/" + imageFileName);
				File fileInResource = new File(uploadFile.getUploadPath() + "configs/" + imageFileName);

				if (fileInServer.exists()) {
					fileInServer.delete();
				}

				if (fileInResource.exists()) {
					fileInResource.delete();
				}

				redirectAttributes.addFlashAttribute("message", "Xoá cấu hình thành công!");
				redirectAttributes.addFlashAttribute("messageType", "success");
			} else {
				redirectAttributes.addFlashAttribute("message", "Không thể xoá cấu hình!");
				redirectAttributes.addFlashAttribute("messageType", "error");
			}

			return "redirect:/admin/configs";
		}

}

