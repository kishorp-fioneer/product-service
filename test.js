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

export function handleSummary(data) {
  console.log('Preparing the end-of-test summary...');
  console.log('>>>>data='+JSON.stringify(data));
  // Send the results to some remote server or trigger a hook
//   const resp = http.post('https://influxdb.c-94c5d0c.kyma.shoot.live.k8s-hana.ondemand.com/write?db=myk6db', JSON.stringify(data));
//   if (resp.status != 200) {
//     console.error('Could not send summary, got status ' + resp.status);
//   }

  return {
    'stdout': textSummary(data, { indent: ' ', enableColors: true }), // Show the text summary to stdout...
   // '../path/to/junit.xml': jUnit(data), // but also transform it and save it as a JUnit XML...
    'summary.json': JSON.stringify(data), // and a JSON with all the details...
    // And any other JS transformation of the data you can think of,
    // you can write your own JS helpers to transform the summary data however you like!
  };
}
