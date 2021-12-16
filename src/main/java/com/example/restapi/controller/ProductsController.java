package com.example.restapi.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.Product;
import com.example.restapi.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/products/")
@Api(value = "ProductsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

	private ProductsService productsService;

	private Logger LOG = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	public void setProductsService(ProductsService productsService) {
		this.productsService = productsService;
	}

	@GetMapping("/")
	@ApiOperation("Gets all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = List.class) })
	public List<Product> getProducts() {
		return productsService.getProducts();
	}

	@RequestMapping(path = "/static/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Gets all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getProductsStatic() {
		return SAMPLE_STATIC_RESPONSE;
	}

	@GetMapping("/listHeaders")
	public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers) {

		return new ResponseEntity<String>(headers.toString(), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	@ApiOperation("Gets the product with specific id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Product.class) })
	public Product getProduct(@PathVariable(name = "id") String id) {
		return productsService.getProduct(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product saveProduct(@RequestBody Product productToSave) {
		return productsService.saveProduct(productToSave);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
		return productsService.updateProduct(productToUpdate, id);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable(name = "id") String id) {
		productsService.deleteProduct(id);
	}

	private static final String SAMPLE_STATIC_RESPONSE = "[\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363130000\",\n" + "\"name\": \"Simple Product 1\",\n"
			+ "\"description\": \"This is a electornic product # 1\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363470001\",\n"
			+ "\"name\": \"Simple Product 2\",\n" + "\"description\": \"This is a electornic product # 2\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363480002\",\n" + "\"name\": \"Simple Product 3\",\n"
			+ "\"description\": \"This is a electornic product # 3\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3634a0003\",\n"
			+ "\"name\": \"Simple Product 4\",\n" + "\"description\": \"This is a electornic product # 4\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3634b0004\",\n" + "\"name\": \"Simple Product 5\",\n"
			+ "\"description\": \"This is a electornic product # 5\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3634c0005\",\n"
			+ "\"name\": \"Simple Product 6\",\n" + "\"description\": \"This is a electornic product # 6\",\n"
			+ "\"type\": \"AC\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3634d0006\",\n" + "\"name\": \"Simple Product 7\",\n"
			+ "\"description\": \"This is a electornic product # 7\",\n" + "\"type\": \"Mobile\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3634e0007\",\n"
			+ "\"name\": \"Simple Product 8\",\n" + "\"description\": \"This is a electornic product # 8\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363510008\",\n" + "\"name\": \"Simple Product 9\",\n"
			+ "\"description\": \"This is a electornic product # 9\",\n" + "\"type\": \"Mobile\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363520009\",\n"
			+ "\"name\": \"Simple Product 10\",\n" + "\"description\": \"This is a electornic product # 10\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36353000a\",\n" + "\"name\": \"Simple Product 11\",\n"
			+ "\"description\": \"This is a electornic product # 11\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36355000b\",\n"
			+ "\"name\": \"Simple Product 12\",\n" + "\"description\": \"This is a electornic product # 12\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36357000c\",\n" + "\"name\": \"Simple Product 13\",\n"
			+ "\"description\": \"This is a electornic product # 13\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36358000d\",\n"
			+ "\"name\": \"Simple Product 14\",\n" + "\"description\": \"This is a electornic product # 14\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36359000e\",\n" + "\"name\": \"Simple Product 15\",\n"
			+ "\"description\": \"This is a electornic product # 15\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3635c000f\",\n"
			+ "\"name\": \"Simple Product 16\",\n" + "\"description\": \"This is a electornic product # 16\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3635e0010\",\n" + "\"name\": \"Simple Product 17\",\n"
			+ "\"description\": \"This is a electornic product # 17\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3635f0011\",\n"
			+ "\"name\": \"Simple Product 18\",\n" + "\"description\": \"This is a electornic product # 18\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363600012\",\n" + "\"name\": \"Simple Product 19\",\n"
			+ "\"description\": \"This is a electornic product # 19\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363620013\",\n"
			+ "\"name\": \"Simple Product 20\",\n" + "\"description\": \"This is a electornic product # 20\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363640014\",\n" + "\"name\": \"Simple Product 21\",\n"
			+ "\"description\": \"This is a electornic product # 21\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363670015\",\n"
			+ "\"name\": \"Simple Product 22\",\n" + "\"description\": \"This is a electornic product # 22\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363680016\",\n" + "\"name\": \"Simple Product 23\",\n"
			+ "\"description\": \"This is a electornic product # 23\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3636c0017\",\n"
			+ "\"name\": \"Simple Product 24\",\n" + "\"description\": \"This is a electornic product # 24\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3636f0018\",\n" + "\"name\": \"Simple Product 25\",\n"
			+ "\"description\": \"This is a electornic product # 25\",\n" + "\"type\": \"Refrigerator\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363700019\",\n"
			+ "\"name\": \"Simple Product 26\",\n" + "\"description\": \"This is a electornic product # 26\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36372001a\",\n" + "\"name\": \"Simple Product 27\",\n"
			+ "\"description\": \"This is a electornic product # 27\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36373001b\",\n"
			+ "\"name\": \"Simple Product 28\",\n" + "\"description\": \"This is a electornic product # 28\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36375001c\",\n" + "\"name\": \"Simple Product 29\",\n"
			+ "\"description\": \"This is a electornic product # 29\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36377001d\",\n"
			+ "\"name\": \"Simple Product 30\",\n" + "\"description\": \"This is a electornic product # 30\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36378001e\",\n" + "\"name\": \"Simple Product 31\",\n"
			+ "\"description\": \"This is a electornic product # 31\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36379001f\",\n"
			+ "\"name\": \"Simple Product 32\",\n" + "\"description\": \"This is a electornic product # 32\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3637b0020\",\n" + "\"name\": \"Simple Product 33\",\n"
			+ "\"description\": \"This is a electornic product # 33\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3637c0021\",\n"
			+ "\"name\": \"Simple Product 34\",\n" + "\"description\": \"This is a electornic product # 34\",\n"
			+ "\"type\": \"AC\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3637e0022\",\n" + "\"name\": \"Simple Product 35\",\n"
			+ "\"description\": \"This is a electornic product # 35\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3637f0023\",\n"
			+ "\"name\": \"Simple Product 36\",\n" + "\"description\": \"This is a electornic product # 36\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363830024\",\n" + "\"name\": \"Simple Product 37\",\n"
			+ "\"description\": \"This is a electornic product # 37\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363840025\",\n"
			+ "\"name\": \"Simple Product 38\",\n" + "\"description\": \"This is a electornic product # 38\",\n"
			+ "\"type\": \"AC\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363860026\",\n" + "\"name\": \"Simple Product 39\",\n"
			+ "\"description\": \"This is a electornic product # 39\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363870027\",\n"
			+ "\"name\": \"Simple Product 40\",\n" + "\"description\": \"This is a electornic product # 40\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363880028\",\n" + "\"name\": \"Simple Product 41\",\n"
			+ "\"description\": \"This is a electornic product # 41\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3638a0029\",\n"
			+ "\"name\": \"Simple Product 42\",\n" + "\"description\": \"This is a electornic product # 42\",\n"
			+ "\"type\": \"Washing Machine\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3638b002a\",\n" + "\"name\": \"Simple Product 43\",\n"
			+ "\"description\": \"This is a electornic product # 43\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3638c002b\",\n"
			+ "\"name\": \"Simple Product 44\",\n" + "\"description\": \"This is a electornic product # 44\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3638e002c\",\n" + "\"name\": \"Simple Product 45\",\n"
			+ "\"description\": \"This is a electornic product # 45\",\n" + "\"type\": \"Mobile\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3638f002d\",\n"
			+ "\"name\": \"Simple Product 46\",\n" + "\"description\": \"This is a electornic product # 46\",\n"
			+ "\"type\": \"AC\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e36390002e\",\n" + "\"name\": \"Simple Product 47\",\n"
			+ "\"description\": \"This is a electornic product # 47\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e36392002f\",\n"
			+ "\"name\": \"Simple Product 48\",\n" + "\"description\": \"This is a electornic product # 48\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363930030\",\n" + "\"name\": \"Simple Product 49\",\n"
			+ "\"description\": \"This is a electornic product # 49\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363950031\",\n"
			+ "\"name\": \"Simple Product 50\",\n" + "\"description\": \"This is a electornic product # 50\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363960032\",\n" + "\"name\": \"Simple Product 51\",\n"
			+ "\"description\": \"This is a electornic product # 51\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363970033\",\n"
			+ "\"name\": \"Simple Product 52\",\n" + "\"description\": \"This is a electornic product # 52\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363990034\",\n" + "\"name\": \"Simple Product 53\",\n"
			+ "\"description\": \"This is a electornic product # 53\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3639a0035\",\n"
			+ "\"name\": \"Simple Product 54\",\n" + "\"description\": \"This is a electornic product # 54\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3639b0036\",\n" + "\"name\": \"Simple Product 55\",\n"
			+ "\"description\": \"This is a electornic product # 55\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3639c0037\",\n"
			+ "\"name\": \"Simple Product 56\",\n" + "\"description\": \"This is a electornic product # 56\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e3639e0038\",\n" + "\"name\": \"Simple Product 57\",\n"
			+ "\"description\": \"This is a electornic product # 57\",\n" + "\"type\": \"Mobile\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e3639f0039\",\n"
			+ "\"name\": \"Simple Product 58\",\n" + "\"description\": \"This is a electornic product # 58\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363a0003a\",\n" + "\"name\": \"Simple Product 59\",\n"
			+ "\"description\": \"This is a electornic product # 59\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363a1003b\",\n"
			+ "\"name\": \"Simple Product 60\",\n" + "\"description\": \"This is a electornic product # 60\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363a2003c\",\n" + "\"name\": \"Simple Product 61\",\n"
			+ "\"description\": \"This is a electornic product # 61\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363a3003d\",\n"
			+ "\"name\": \"Simple Product 62\",\n" + "\"description\": \"This is a electornic product # 62\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363a5003e\",\n" + "\"name\": \"Simple Product 63\",\n"
			+ "\"description\": \"This is a electornic product # 63\",\n" + "\"type\": \"Refrigerator\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363a7003f\",\n"
			+ "\"name\": \"Simple Product 64\",\n" + "\"description\": \"This is a electornic product # 64\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363a90040\",\n" + "\"name\": \"Simple Product 65\",\n"
			+ "\"description\": \"This is a electornic product # 65\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363ab0041\",\n"
			+ "\"name\": \"Simple Product 66\",\n" + "\"description\": \"This is a electornic product # 66\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363ac0042\",\n" + "\"name\": \"Simple Product 67\",\n"
			+ "\"description\": \"This is a electornic product # 67\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363ad0043\",\n"
			+ "\"name\": \"Simple Product 68\",\n" + "\"description\": \"This is a electornic product # 68\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363ae0044\",\n" + "\"name\": \"Simple Product 69\",\n"
			+ "\"description\": \"This is a electornic product # 69\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363af0045\",\n"
			+ "\"name\": \"Simple Product 70\",\n" + "\"description\": \"This is a electornic product # 70\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363b00046\",\n" + "\"name\": \"Simple Product 71\",\n"
			+ "\"description\": \"This is a electornic product # 71\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363b20047\",\n"
			+ "\"name\": \"Simple Product 72\",\n" + "\"description\": \"This is a electornic product # 72\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363b30048\",\n" + "\"name\": \"Simple Product 73\",\n"
			+ "\"description\": \"This is a electornic product # 73\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363b30049\",\n"
			+ "\"name\": \"Simple Product 74\",\n" + "\"description\": \"This is a electornic product # 74\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363b6004a\",\n" + "\"name\": \"Simple Product 75\",\n"
			+ "\"description\": \"This is a electornic product # 75\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363b8004b\",\n"
			+ "\"name\": \"Simple Product 76\",\n" + "\"description\": \"This is a electornic product # 76\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363b8004c\",\n" + "\"name\": \"Simple Product 77\",\n"
			+ "\"description\": \"This is a electornic product # 77\",\n" + "\"type\": \"Mobile\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363b9004d\",\n"
			+ "\"name\": \"Simple Product 78\",\n" + "\"description\": \"This is a electornic product # 78\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363ba004e\",\n" + "\"name\": \"Simple Product 79\",\n"
			+ "\"description\": \"This is a electornic product # 79\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363bb004f\",\n"
			+ "\"name\": \"Simple Product 80\",\n" + "\"description\": \"This is a electornic product # 80\",\n"
			+ "\"type\": \"Mobile\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363bc0050\",\n" + "\"name\": \"Simple Product 81\",\n"
			+ "\"description\": \"This is a electornic product # 81\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363be0051\",\n"
			+ "\"name\": \"Simple Product 82\",\n" + "\"description\": \"This is a electornic product # 82\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363bf0052\",\n" + "\"name\": \"Simple Product 83\",\n"
			+ "\"description\": \"This is a electornic product # 83\",\n" + "\"type\": \"AC\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c00053\",\n"
			+ "\"name\": \"Simple Product 84\",\n" + "\"description\": \"This is a electornic product # 84\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363c10054\",\n" + "\"name\": \"Simple Product 85\",\n"
			+ "\"description\": \"This is a electornic product # 85\",\n" + "\"type\": \"Microwave\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c20055\",\n"
			+ "\"name\": \"Simple Product 86\",\n" + "\"description\": \"This is a electornic product # 86\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363c30056\",\n" + "\"name\": \"Simple Product 87\",\n"
			+ "\"description\": \"This is a electornic product # 87\",\n" + "\"type\": \"Washing Machine\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c40057\",\n"
			+ "\"name\": \"Simple Product 88\",\n" + "\"description\": \"This is a electornic product # 88\",\n"
			+ "\"type\": \"TV\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363c50058\",\n" + "\"name\": \"Simple Product 89\",\n"
			+ "\"description\": \"This is a electornic product # 89\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c60059\",\n"
			+ "\"name\": \"Simple Product 90\",\n" + "\"description\": \"This is a electornic product # 90\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363c7005a\",\n" + "\"name\": \"Simple Product 91\",\n"
			+ "\"description\": \"This is a electornic product # 91\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c7005b\",\n"
			+ "\"name\": \"Simple Product 92\",\n" + "\"description\": \"This is a electornic product # 92\",\n"
			+ "\"type\": \"Washing Machine\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363c8005c\",\n" + "\"name\": \"Simple Product 93\",\n"
			+ "\"description\": \"This is a electornic product # 93\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363c9005d\",\n"
			+ "\"name\": \"Simple Product 94\",\n" + "\"description\": \"This is a electornic product # 94\",\n"
			+ "\"type\": \"AC\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363ca005e\",\n" + "\"name\": \"Simple Product 95\",\n"
			+ "\"description\": \"This is a electornic product # 95\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363cb005f\",\n"
			+ "\"name\": \"Simple Product 96\",\n" + "\"description\": \"This is a electornic product # 96\",\n"
			+ "\"type\": \"Refrigerator\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363cb0060\",\n" + "\"name\": \"Simple Product 97\",\n"
			+ "\"description\": \"This is a electornic product # 97\",\n" + "\"type\": \"Dishwasher\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363cc0061\",\n"
			+ "\"name\": \"Simple Product 98\",\n" + "\"description\": \"This is a electornic product # 98\",\n"
			+ "\"type\": \"Microwave\",\n" + "\"category\": \"Electronics\"\n" + "},\n" + "{\n"
			+ "\"id\": \"e4e081d47d93e34a017d93e363cd0062\",\n" + "\"name\": \"Simple Product 99\",\n"
			+ "\"description\": \"This is a electornic product # 99\",\n" + "\"type\": \"TV\",\n"
			+ "\"category\": \"Electronics\"\n" + "},\n" + "{\n" + "\"id\": \"e4e081d47d93e34a017d93e363ce0063\",\n"
			+ "\"name\": \"Simple Product 100\",\n" + "\"description\": \"This is a electornic product # 100\",\n"
			+ "\"type\": \"Dishwasher\",\n" + "\"category\": \"Electronics\"\n" + "}\n" + "]";
}
