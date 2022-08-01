FROM rockylinux/rockylinux
#CMD ["/bin/dnf", "install", "java-17-openjdk","y"]
RUN /bin/dnf install java-17-openjdk -y
WORKDIR /application
#EXPOSE 8080
COPY target/dd-0-ui-bootable.jar ./
#CMD ["java", "-jar", "dd-0-ui-bootable.jar"]
#EXPOSE 8080


ENV JAVA_OPTS="-Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0 -Djboss.http.port=8080 -Djboss.management.http.port=9990"

EXPOSE 8080 9990
ENTRYPOINT exec java -jar $JAVA_OPTS /application/dd-0-ui-bootable.jar
