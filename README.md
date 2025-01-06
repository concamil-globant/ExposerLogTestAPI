# Log Analyzer Lambda with Spring Boot

This project demonstrates an AWS Lambda function built with **Spring Boot** to process logs from microservices, analyze them, and return meaningful insights. The project is designed to run on AWS Free Tier, utilizing various AWS services such as Lambda, API Gateway, CloudWatch Logs, and optionally Amazon Comprehend.

## **Features**
- **Log Processing**: Processes logs received from microservices.
- **Error Detection**: Identifies critical errors in logs based on specific keywords.
- **Scalable Architecture**: Designed to work seamlessly with AWS services.

---

## **Technologies Used**
- **Spring Boot**: For ease of development and robust application structure.
- **AWS Lambda**: For serverless execution of the log analysis logic.
- **CloudWatch Logs**: To store and query logs.
- **Optional**: Amazon Comprehend for AI-driven log analysis.

---

## **Getting Started**

### Prerequisites
- Java 17
- Maven
- AWS CLI configured with appropriate credentials
- IntelliJ IDEA or any Java IDE

### Project Setup
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd log-analyzer
   ```

2. **Build the Project**:
   ```bash
   mvn clean package
   ```

3. **Deploy to AWS Lambda**:
    - Navigate to the AWS Management Console.
    - Create a new Lambda function with runtime **Java 11/17**.
    - Upload the generated JAR file from the `target` directory.
    - Set the handler as:
      ```
      com.example.loganalyzer.LogAnalyzerHandler::handleRequest
      ```

---

## **Project Structure**
```plaintext
src
├── main
│   ├── java
│   │   └── com.example.loganalyzer
│   │       ├── LogAnalyzerApplication.java  # Main Spring Boot application
│   │       ├── LogAnalyzerHandler.java      # AWS Lambda handler
│   │       └── LogProcessor.java            # Core log processing logic
│   └── resources
│       └── application.properties           # Configuration files
├── test                                     # Unit tests (if applicable)
└── pom.xml                                  # Maven dependencies and build plugins
```

---

## **Usage**
1. **Send Logs for Processing**:
    - Use AWS API Gateway or a test event in AWS Lambda to send logs.

2. **Sample Event**:
   ```json
   {
     "logEntry": "ERROR: Database connection failed"
   }
   ```

3. **Expected Response**:
   ```json
   "Critical Error Detected: ERROR: Database connection failed"
   ```

---

## **Future Enhancements**
- **Amazon Comprehend Integration**: AI-driven sentiment and keyword analysis for advanced error reporting.
- **SNS Notifications**: Notify DevOps teams about critical errors.
- **CloudWatch Insights**: Advanced querying and monitoring of logs.

---

## **Contributing**
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes and open a pull request.

---

## **License**
This project is licensed under the MIT License.

