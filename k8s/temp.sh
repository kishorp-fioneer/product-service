# API server URL is api.KYMA_CLUSTER_DOMAIN
API_SERVER_URL=$(kubectl config current-context)

# the name of the secret containing the service account token goes here
SECRET_NAME=$(kubectl get sa -n perf-test perf-test-service-account -ojsonpath='{.secrets[0].name}')

CA=$(kubectl get secret/${SECRET_NAME} -n perf-test -o jsonpath='{.data.ca\.crt}')
TOKEN=$(kubectl get secret/${SECRET_NAME} -n perf-test -o jsonpath='{.data.token}' | base64 --decode)

echo "
apiVersion: v1
kind: Config
clusters:
- name: default-cluster
  cluster:
    certificate-authority-data: ${CA}
    server: https://api.${API_SERVER_URL}
users:
- name: default-user
  user:
    token: ${TOKEN}
contexts:
- name: default-context
  context:
    cluster: default-cluster
    namespace: default
    user: default-user
current-context: default-context
"