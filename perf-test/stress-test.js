import http from 'k6/http';
import { sleep } from 'k6';
import { Trend } from "k6/metrics";

var req_resp_time = new Trend("req_resp_time");
const SLEEP_DURATION = 0.1;

export const options = {
  stages: [
    { duration: '2m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.
    { duration: '2m', target: 200 },
    { duration: '2m', target: 300 },
    { duration: '2m', target: 400 },
    { duration: '2m', target: 0 }, // ramp-down to 0 users
  ],
  thresholds: {
    'http_req_duration': ['p(99)<1500'], // 99% of requests must complete below 1.5s
 },
};

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

export default () => {
  const res = http.get('https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/');
  req_resp_time.add(res.timings.duration, {
    test_type: "getProducts",
  });
  sleep(SLEEP_DURATION);
};


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
    sleep(0.1);
  }
}