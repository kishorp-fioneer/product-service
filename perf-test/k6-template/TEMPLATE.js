

import http from 'k6/http';
import { group,sleep } from "k6";
import { loadOptions,loadData, parseResponse, loadServiceConfig, withHeaders } from "./lib/k6_extensions.js";
import { Trend, Rate,Gauge } from "k6/metrics";
import exec from 'k6/execution';
var req_resp_time = new Trend("req_resp_time");
var active_vu= new Gauge("active_vu");
var http_reqs_info= new Gauge("http_reqs_info");
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
  for(let i=0;i<500;i++){
  let res =http.post(serviceConfig.addProductUrl, JSON.stringify(product), serviceConfig.params);
  parseResponse(res);
  }
    sleep(1);

  let productsResp=http.get(serviceConfig.getAllProductsUrl, serviceConfig.params);
  parseResponse(productsResp);
  let body=JSON.parse(productsResp.body);

//  console.log("Products  "+JSON.stringify(body));
  let results = body.filter(item => item.type=='Book');
    sleep(1);
  serviceConfig.filterEntity=results;

  //  console.log("Filter  "+JSON.stringify(results))
        console.log("setup item  length "+results.length);
  return serviceConfig;
   }


export  function getProducts(data) {
  group("getProducts", function() {
    http_reqs_info.add(1);
    let res =http.get(data.getAllProductsUrl, data.params);
    parseResponse(res);
    req_resp_time.add(res.timings.duration);
    active_vu.add(exec.instance.vusActive);
    sleep(1);
  });
};


export  function getProductById(data) {
  group("getProductById", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
  //console.log("item  "+JSON.stringify(item));
  http_reqs_info.add(1);
  let res =http.get(data.getProductByIdUrl.replace('$id',item.id), data.params);
  parseResponse(res);
  req_resp_time.add(res.timings.duration);
  active_vu.add(exec.instance.vusActive);
  }
    sleep(1);
  });
};

export  function addProduct(data) {
  group("addProduct", function() {
    http_reqs_info.add(1);
    let res =http.post(data.addProductUrl, JSON.stringify(product), data.params);
    parseResponse(res);
    req_resp_time.add(res.timings.duration);
    active_vu.add(exec.instance.vusActive);
    sleep(1);
  });
};

export  function deleteProductById(data) {
  group("deleteProductById", function() {
  let items=data.filterEntity;
  let item=items[Math.floor(Math.random() * items.length)]
  if(item){
    //  console.log("item  "+JSON.stringify(item));
  http_reqs_info.add(1);
  let res =http.del(data.deleteProductByIdUrl.replace('$id',item.id), data.params);
  parseResponse(res);
  req_resp_time.add(res.timings.duration);
  active_vu.add(exec.instance.vusActive);
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
    http_reqs_info.add(1);
    let res =http.put(data.modifyProductByIdUrl.replace('$id',item.id),JSON.stringify(product), data.params);
    parseResponse(res);
    req_resp_time.add(res.timings.duration);
      active_vu.add(exec.instance.vusActive);
    }
        sleep(1);
    });
};

export function teardown(data) {

  let productsResp=http.get(serviceConfig.getAllProductsUrl, serviceConfig.params);
  parseResponse(productsResp);
  let body=JSON.parse(productsResp.body);

//  console.log("Products  "+JSON.stringify(body));
  let results = body.filter(item => item.type=='Book');
  sleep(1);
  console.log("teardown Total item  "+results.length);
  for(let i=0;i<results.length;i++){
  let res =http.del(data.deleteProductByIdUrl.replace('$id',results[i].id), data.params);
  parseResponse(res);
  }

//    parseResponse(http.post(serviceConfig.logoutUrl, user, data.params));
  };
