// ./test.js

import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  duration: '10s',
  vus: 10,
  thresholds: {
    http_req_duration: ['p(95)<500'],
  },
};

export default function () {
  const res = http.get('https://product-perf.c-94c5d0c.kyma.shoot.live.k8s-hana.ondemand.com/api/products/');
  sleep(1);
}


