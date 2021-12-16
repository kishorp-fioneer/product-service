// ./test.js

import http from "k6/http";
import { Trend, Rate } from "k6/metrics";
import { group, sleep } from "k6";

export let options = {
    stages: [
        { target: 10, duration: "15m" }
    ]
};

var api1RespTime = new Trend("api_1_resp_time");
var api2RespTime = new Trend("api_2_resp_time");
var api3RespTime = new Trend("api_3_resp_time");


export default function() {
    group("Flow 1", function() {
        let res = http.get("https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/");
        api1RespTime.add(res.timings.duration);
    });
    group("Flow 2", function() {
        let res = http.get("https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/e4e080d57dc206af017dc2073aaf0000");
        api2RespTime.add(res.timings.duration);
    });
    group("Flow 3", function() {
        let res = http.get("https://product-perf.c-1da60bd.kyma.ondemand.com/api/products/e4e080d57dc206af017dc2073c9e0001");
        api3RespTime.add(res.timings.duration);
     });
    sleep(1);
};

