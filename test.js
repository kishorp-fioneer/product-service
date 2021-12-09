// ./test.js

import http from "k6/http";
import { Trend, Rate } from "k6/metrics";
import { group, sleep } from "k6";

export let options = {
    stages: [
        { target: 10, duration: "60s" }
    ]
};

var flow1RespTime = new Trend("flow_1_resp_time");

export default function() {
    group("Flow 1", function() {
        let res = http.get("https://test.loadimpact.com/public/crocodiles/1/");
        flow1RespTime.add(res.timings.duration);
    });
    group("Flow 2", function() {
        let res = http.get("https://test.loadimpact.com/public/crocodiles/2/");
        flow1RespTime.add(res.timings.duration);
    });
    group("Flow 3", function() {
        let res = http.get("https://test.loadimpact.com/public/crocodiles/3/");
        flow1RespTime.add(res.timings.duration);
     });
    sleep(1);
};

