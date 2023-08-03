# Header Analyzer with Wicket

This project contains a sample Wicket web application that can be run on Apache Tomcat inside a Docker container or local.


## About the Application

This HeaderAnalyzer application is a simple example that demonstrates the usage of Wicket framework components to show HTTP request header information and verify a user's email address. The application includes the following:

- A Wicket page (HeaderInfoPage) that displays HTTP request header information.
- A form in HeaderInfoPage where users can enter their email addresses for verification.
- A FeedbackPanel to display success or error messages based on the email address verification result.
- A Service (MailService) to send HTTP request header information to entered E-Mail

## Prerequisites

To run this application, you need to have the following installed on your system:

- [Docker](https://www.docker.com/get-started)
- [Tomcar v9+](https://tomcat.apache.org/download-90.cgi) (If you want to run this project locally)

## Getting Started

1. Copy project to your local machine:

2. Configure the Email Properties inside `src/main/resources/mail.properties`:

       host=YOUR SMTP HOST URL
       port=YOUR SMTP PORT
       username=MAIL SERVER USERNAME
       password=MAIL SERVER PASSWORD
       from=SENDER EMAIL ADDRESS
       tls_enable=true|false

3. Build the Docker image for the HeaderAnalyzer application:

       docker build -t your-image-name

4. Replace `your-image-name` with a name for your Docker image (e.g., `my-header-analyzer-app`).


5. Run a Docker container from the image:

       docker run -d -p 8080:8080 your-image-name
 
6. Access the HeaderAnalyzer application: Visit the following URL in your web browser to access the running HeaderAnalyzer application:

       http://localhost:8080

## JavaDoc Documentation

To generate the JavaDoc documentation yourself, you can use the following command:

    javadoc -d docs -sourcepath src/main/java -subpackages com.meysamzamani.headeranalyzer -classpath path/to/dependencies

Replace `path/to/dependencies` with the actual classpath that includes the dependencies for your project.

## Project Structure

The project follows the standard Maven project structure:

- `src/main/java`: Contains the Java source code for the HeaderAnalyzer application.
- `src/main/resources`: Contains mail properties and configs.
- `pom.xml`: The Maven project configuration file.

## Dockerfile

The `Dockerfile` in the root directory is used to build the Docker image for the HeaderAnalyzer application. It uses two stages, one for building the application with Maven and the other for running the application on Tomcat.
