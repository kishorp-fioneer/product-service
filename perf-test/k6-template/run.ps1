# Assumes that you've set the K6_HOSTENV , K6_SCRIPT & K6_TEST_TYPE  variables in powershell

docker build -t test .
docker run -it -e K6_HOSTENV=$K6_HOSTENV -e K6_SCRIPT=$K6_SCRIPT -e K6_TEST_TYPE=load test
