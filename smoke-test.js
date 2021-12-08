import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 1, // 1 user looping for 1 minute
  duration: '1m',

  thresholds: {
    http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
  },
};

export default () => {
  const res = http.get('https://product-perf.c-94c5d0c.kyma.shoot.live.k8s-hana.ondemand.com/api/products/');
  sleep(1);
};
