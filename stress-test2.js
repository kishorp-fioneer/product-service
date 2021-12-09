import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '2m', target: 100 }, // below normal load
    { duration: '1m', target: 100 },
    { duration: '2m', target: 200 }, // normal load
    { duration: '1m', target: 200 },
    { duration: '2m', target: 300 }, // around the breaking point
    { duration: '2m', target: 0 }, // scale down. Recovery stage.
  ],
};

export default function () {

  const res = http.get('https://product-perf.c-94c5d0c.kyma.shoot.live.k8s-hana.ondemand.com/api/products/static/');

  sleep(1);
}
