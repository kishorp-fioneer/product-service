# K6 Performance Test Suite Template
This is a simple a K6 performance test suite template. Some of the common functionality that's required for tracking errors has already been taken care of for you, allowing you to concentrate on building your performance tests.

This repository assumes that you have a basic knowledge of docker, javascript and k6.

## Build & Run

If you're on Windows, simply set the variables for `K6_HOSTENV` , `K6_SCRIPT` & `K6_TEST_TYPE` to your target values and run `.\run.ps1`. Otherwise, see the instructions below for a manual build and run.
K6_TEST_TYPE possible values(`load`,`soak`,`spike`,`stress`,`smoke`)
### Build

```bash
docker build -t test .
```

### Run

```bash
docker run -it -e K6_HOSTENV=qa -e K6_SCRIPT=TEMPLATE.js -e K6_TEST_TYPE=load test
```
