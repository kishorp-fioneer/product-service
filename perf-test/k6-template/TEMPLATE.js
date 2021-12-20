import http from 'k6/http';
import { group,sleep } from "k6";
import { loadOptions,loadData, parseResponse, loadServiceConfig, withHeaders } from "./lib/k6_extensions.js";
import { Trend, Rate } from "k6/metrics";

var getProductsRespTime = new Trend("getProducts_resp_time");
var addProductRespTime = new Trend("addProduct_resp_time");
var deleteProductRespTime = new Trend("deleteProduct_resp_time");
var modifyProductRespTime = new Trend("modifyProduct_resp_time");
var getProductRespTime = new Trend("getProduct_resp_time");

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
  case 'smoke': options = loadOptions('default-smoke-test');
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
  group("getProducts", function() {
    let res =http.get(data.getAllProductsUrl, data.params);
    parseResponse(res);
    getProductsRespTime.add(res.timings.duration);
    sleep(1);
  });
};


export  function getProductById(data) {
  group("getProductById", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
  //console.log("item  "+JSON.stringify(item));
  let res =http.get(data.getProductByIdUrl.replace('$id',item.id), data.params);
  parseResponse(res);
  getProductRespTime.add(res.timings.duration);

  }
    sleep(1);
  });
};

export  function addProduct(data) {
  group("addProduct", function() {
    let res =http.post(data.addProductUrl, JSON.stringify(product), data.params);
    parseResponse(res);
      addProductRespTime.add(res.timings.duration);
    sleep(1);
  });
};

export  function deleteProductById(data) {
  group("deleteProductById", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
    //  console.log("item  "+JSON.stringify(item));
  let res =http.del(data.deleteProductByIdUrl.replace('$id',item.id), data.params);
  parseResponse(res);
  deleteProductRespTime.add(res.timings.duration);
  }
  sleep(1);
});
};

  export  function modifyProductById(data) {
    group("modifyProductById", function() {
    let items=data.filterEntity;
    let item=items[Math.floor(Math.random() * items.length)]
    if(item){
  //  console.log("item  "+JSON.stringify(item));
    let res =http.put(data.deleteProductByIdUrl.replace('$id',item.id),JSON.stringify(product), data.params);
    parseResponse(res);
    modifyProductRespTime.add(res.timings.duration);
    }
        sleep(1);
    });
};

export function teardown(data) {

//    parseResponse(http.post(serviceConfig.logoutUrl, user, data.params));
  };
