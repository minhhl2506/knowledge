FROM openjdk:8-jdk-alpine

# Set the current working directory inside the image
WORKDIR /server

# Copy maven executable to the image
COPY . .


EXPOSE 8080

ENTRYPOINT ["java","-jar","./target/knowledge-0.0.1-SNAPSHOT.jar"]