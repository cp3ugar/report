## 启动APM

mvn package

java -javaagent:elastic-apm-agent-1.6.1.jar \
     -Delastic.apm.service_name=my-application \
     -Delastic.apm.server_url=http://localhost:8200 \
     -Delastic.apm.secret_token= \
     -Delastic.apm.application_packages=indi.xk.esmysqldata \
     -jar ./target/es-mysql-data-0.0.1-SNAPSHOT.jar