FROM amazoncorretto:17.0.9-alpine3.18
ADD ./exec/cliente-0.0.1-SNAPSHOT.jar /
ADD ./exec/application.yml /
ADD ./entrypoint/entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
EXPOSE 8080
