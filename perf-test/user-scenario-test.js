import http from "k6/http";
import { check, group, sleep } from "k6";
import { Trend } from "k6/metrics";
var req_resp_time = new Trend("req_resp_time");

export const options = {
  vus: 40,
  duration: "5m",
  setupTimeout: '2m',
  teardownTimeout: '3m',
};
const SLEEP_DURATION = 0.1;

export function setup() {
  const params = {
    headers: {
      "Content-Type": "application/json",
    },
    tags: {
      name: "setup",
    },
  };
  const product =
    '{"name": "Bhagavad Gita", "description": "This is a spiritual book", "type": "Book", "category": "philosophy"}';

  for (let i = 0; i < 100; i++) {
    let res = http.post(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/",
      product,
      params
    );
    sleep(SLEEP_DURATION);
  }
}

export default function () {
  let body = JSON.stringify({
    username: "user_" + __ITER,
    password: "PASSWORD",
  });
  const params = {
    headers: {
      "Content-Type": "application/json",
    },
    tags: {
      name: "login", // first request
    },
  };

  group("user journey", (_) => {
    // Login request
    const login_response = http.post(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/login",
      body,
      params
    );
    req_resp_time.add(login_response.timings.duration, { test_type: "login" });
    check(login_response, {
      "is status 200": (r) => r.status === 200,
      "is access token present": (r) => r.json().hasOwnProperty("access_token"),
    });
    console.log("access_token========" + login_response.json()["access_token"]);
    params.headers["Authorization"] =
      "Bearer " + login_response.json()["access_token"];
    sleep(SLEEP_DURATION);

    // Get products request
    params.tags.name = "get-products";
    let get_products_response = http.get(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/",
      params
    );
    let products = get_products_response.json();
    req_resp_time.add(get_products_response.timings.duration, {
      test_type: "getProducts",
    });
    let product = products[Math.floor(Math.random() * products.length)];
    console.log("product id = " + product["id"]);
    product["description"] =
      product["description"] + " UPDATED by " + "user_" + __ITER;
    sleep(1);

    // Update product request
    body = JSON.stringify(product);
    params.tags.name = "update-product";
    const update_product_response = http.put(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/" +
        product["id"],
      body,
      params
    );
    req_resp_time.add(update_product_response.timings.duration, { 
      test_type: "modifyProductById",
    });
    sleep(1);

    // Logout request
    params.tags.name = "logout";
    const logout_response = http.get(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/logout",
      params
    );
    req_resp_time.add(logout_response.timings.duration),
      { test_type: "logout" };
    sleep(SLEEP_DURATION);
  });
}

export function teardown(data) {
  const params = {
    headers: {
      "Content-Type": "application/json",
    },
    tags: {
      name: "delete",
    },
  };
  let products = http
    .get(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/",
      params
    )
    .json();
  for (let i = 0; i < products.length; i++) {
    http.del(
      "https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/" +
        products[i]["id"],
      params
    );
    sleep(SLEEP_DURATION);
  }
}
