package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterControler {
	@Autowired
	private IProductFilteringServise filterService;
	
	
	@GetMapping("/quantity/{param}")  //localhost:8080/product/filter/quantity/2
	public String get/productFilterByQuantity(@PathVariable("param") int threshold) {
		
	}
	

}
