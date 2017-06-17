FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER "A.Odunlami <debo.odunlami@arca.network>"
# Define working directory.
WORKDIR /work
ADD target/kube-demo-gateway-0.0.1.jar /work/kube-demo-gateway-0.0.1.jar
ADD healthy /tmp/healthy
# Expose Ports
EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -jar /work/kube-demo-gateway-0.0.1.jar
#--spring.config.location=/properties/application.properties