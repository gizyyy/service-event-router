FROM amazoncorretto:17
ADD target/service-event-router-*.jar service-event-router.jar
ENTRYPOINT ["/bin/bash", "-c", "java $JAVA_OPTS -jar service-event-router.jar"]
