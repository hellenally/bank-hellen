FROM jtl-tkgiharbor.hq.bni.co.id/wss-dev/ubi9/openjdk-21:1.22-1 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

COPY . $HOME
RUN mvn -Dhttps.proxyHost=192.168.98.199 -Dhttps.proxyPort=8080 -Dserver.address=0.0.0.0 -Dmaven.test.skip=true clean package

FROM jtl-tkgiharbor.hq.bni.co.id/wss-dev/ubi9/openjdk-21-runtime
WORKDIR /usr/app

COPY --from=build /usr/app/target/*.jar /usr/app/*.jar
#COPY --from=build /usr/app/src/main/resources/templates/wsdl/* /usr/app/src/main/resources/templates/wsdl/


EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/*.jar"]
