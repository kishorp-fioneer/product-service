// ./test.js

import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  duration: '10s',
  vus: 50,
  thresholds: {
    http_req_duration: ['p(95)<500'],
  },
};

export default function () {
  const res = http.get('https://product-perf.c-94c5d0c.kyma.shoot.live.k8s-hana.ondemand.com/api/products/');
  sleep(1);
}

export function teardown() {
  const http_req_duration = metrics.http_req_duration.results(); // Just a method to extract the statistics
  console.log('>>>>>> Response time was ' + String(res.timings.duration) + ' ms');
}
