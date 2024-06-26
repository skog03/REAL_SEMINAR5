package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

//TODO
//finiish Servise implementation: filterByQuantityThreshold afn filterByPriceBetween
//create controller class with 2 get mapping controllers and call both service functions
//test it

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService productCRUDservice;

	@GetMapping("/all") // localhost:8080/product/crud/all
	public String getProductCrudAll(Model model) {

		try {
			ArrayList<Product> result = productCRUDservice.retrieveAll();
			model.addAttribute("mypackage", result);
			return "show-all-product-page";// will show this html page in the web browser with mypackage data
		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}

	}

	@GetMapping("/all/{id}") // localhost:8080/product/crud/all/1
	public String getProductCrudById(@PathVariable("id") int id, Model model) {

		try {
			Product result = productCRUDservice.retrieveById(id);
			model.addAttribute("mypackage", result);
			return "show-one-product-page";
		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}
	}

	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public String getProductCrudByIdWithQuestionmark(@RequestParam("id") int id, Model model) {

		try {
			Product result = productCRUDservice.retrieveById(id);
			model.addAttribute("mypackage", result);
			return "show-one-product-page";
		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}
	}

	@GetMapping("/create") // localhost:8080/product/crud/create
	public String getProductCRUDCreate(Model model) {
		model.addAttribute("product", new Product());
		return "create-product-page";// will show reate-product-page.html page with default product
	}

	@PostMapping("/create")
	public String postproductCRUDCreate(@Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "create-product-page";  //thos will show the same html page
		}
		else {
			
		}
		
		try {
			productCRUDservice.create(product.getTitle(), product.getDescription(), product.getPrice(),
					product.getQuantity());
			return "redirect:/product/crud/all";// the endpoint localhost:8080/product/crud/all will be called

		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}

	}

	@GetMapping("/update/{id}") // localhost:8080/product/crud/update/1
	public String getproductCRUDCUpdateById(@PathVariable("id") int id, Model model) {
		try {
			Product productForUpdating = productCRUDservice.retrieveById(id);
			model.addAttribute("product", productForUpdating);
			return "update-product-page";

		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}

	}

	@PostMapping("/update/{id}") // localhost:8080/product/crud/update/1
	public String postProductCRUDCUpdateById(@PathVariable("id") int id, @Valid Product product, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "create-product-page";  //thos will show the same html page
		}
		else {
			
		}
		try {
			productCRUDservice.updateById(id, product.getTitle(), product.getDescription(), product.getPrice(),
					product.getQuantity());
			return "redirect:/product/crud/all/" + id;
		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}

	}

	@GetMapping("/delete/{id}") // localhost:8080/product/crud/delete/2
	public String getproductCRUDDeleteByUpdate(@PathVariable("id") int id, Model model) {
		try {
			productCRUDservice.deleteById(id);

			ArrayList<Product> result = productCRUDservice.retrieveAll();
			model.addAttribute("mypackage", result);
			return "show-all-product-page";// will show this html page in the web browser with mypackage data
		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
		}

	}

}
