FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/v2ex-clojure.jar /v2ex-clojure/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/v2ex-clojure/app.jar"]
