import http from 'k6/http';
import { group } from "k6";
import { loadOptions,loadData, parseResponse, loadServiceConfig, withHeaders } from "./lib/k6_extensions.js";

// TODO :: call loadOptions() with the name of the json options that you want to load, eg: loadOptions('default-soak-test')
//         This parameter must be supplied with a value. The directory './options/' will be searched and '.json' appended
export let options = null;

switch(__ENV.K6_TEST_TYPE){
  case 'load': options = loadOptions('default-load-test');
               break;
 case 'soak': options = loadOptions('default-soak-test');
              break;
 case 'spike': options = loadOptions('default-spike-test');
               break;
 case 'stress': options = loadOptions('default-stress-test');
              break;
  default:    options = loadOptions('default-load-test');
               break;
}


export let user = loadData('user');
export let product = loadData('product');

// TODO :: call loadServiceConfig with the name of the service that you want to load, eg: loadServiceConfig('product')
//         This parameter must be supplied with a value. The directory './services/' will be searched and '.json' appended
export let serviceConfig = Object.assign({}, loadServiceConfig('product'), {
  params: withHeaders({ "Content-Type": "application/json"})
});

export function setup() {

//  const addUserRes=http.post(serviceConfig.addUserUrl, user, serviceConfig.params)
  //parseResponse(addUserRes);
  /*
  const loginRes=http.post(serviceConfig.loginUrl, user, {});
 // console.log("loginRes  "+JSON.stringify(loginRes));
  parseResponse(loginRes);
   let authToken = loginRes.json('access');
   serviceConfig.params=withHeaders({'Content-Type': 'application/json',
                              'Authorization': 'Bearer '+authToken});
 // console.log("serviceConfig  "+JSON.stringify(serviceConfig.params));
  */

  let productsResp=http.get(serviceConfig.getAllProductsUrl, serviceConfig.params);
  parseResponse(productsResp);
  let body=JSON.parse(productsResp.body);
//  console.log("Products  "+JSON.stringify(body));
  let results = body.filter(item => item.type=='Book');
  serviceConfig.filterEntity=results;
//  console.log("Filter  "+JSON.stringify(results))
  return serviceConfig;
   }


export  function getProducts(data) {
  group("GET /getProducts/", function() {
  parseResponse(http.get(data.getAllProductsUrl, data.params));
  });
};


export  function getProductById(data) {
  group("GET /getProductById/", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
  //console.log("item  "+JSON.stringify(item));
  parseResponse(http.get(data.getProductByIdUrl.replace('$id',item.id), data.params));
  }
  });
};

export  function addProduct(data) {
  group("POST /addProduct/", function() {
    parseResponse(http.post(data.addProductUrl, JSON.stringify(product), data.params));
  });
};

export  function deleteProductById(data) {
  group("DELETE /deleteProductById/", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
//  console.log("item  "+JSON.stringify(item));
  parseResponse(http.del(data.deleteProductByIdUrl.replace('$id',item.id), data.params));
  }
});
};

  export  function modifyProductById(data) {
    group("MODIFY /modifyProductById/", function() {
    let items=data.filterEntity;
    let item=items[Math.floor(Math.random() * items.length)]
    if(item){
  //  console.log("item  "+JSON.stringify(item));
    parseResponse(http.put(data.deleteProductByIdUrl.replace('$id',item.id),JSON.stringify(product), data.params));
    }
    });
};

export function teardown(data) {

//    parseResponse(http.post(serviceConfig.logoutUrl, user, data.params));
  };
