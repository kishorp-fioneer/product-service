# Performance Test PoC

This project is used by Performance Test PoC for CWP project.

PoC Components:
* Product Service - Spring Boot-Java application providing REST APIs, deployed in perf-test namespace
* Postgres Database - For persistence, deployed in perf-test namespace
* k6 Perf test tool
* Influx DB - For monitoring, deployed in monitoring namespace
* Grafana - For monitoring, deployed in monitoring namespace

### Deploying InfluxDB
```
helm repo add influxdata https://helm.influxdata.com/
helm upgrade --install my-release influxdata/influxdb -n monitoring
```
Create API Rule in Kyma console to expose influx db service, port:8086


### Deploying Grafana
```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install my-grafana-release bitnami/grafana -n monitoring
```
Create API Rule in Kyma console to expose grafana service, port:3000

