# Student Exam Management System

The **Student Exam Management System** is a comprehensive application designed to manage and streamline the process of conducting exams for students. Built with Java and Spring Boot, this system provides a robust platform for handling various aspects of academic assessments, including student enrollment, exam creation, question management, and result tracking.

## Features

- **User Management**: Support for multiple roles including students, instructors, and administrators.
- **Course Management**: Instructors can create and manage courses.
- **Exam Creation**: Flexible exam setup with various question types (multiple-choice, true/false, essay).
- **Student Enrollment**: Students can enroll in courses and take exams.
- **Result Tracking**: Automated result calculation and storage.
- **Role-Based Access Control**: Secure access to different functionalities based on user roles.

## Technologies Used

- **Java**: The primary programming language for the application.
- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For database interactions.
- **H2 Database**: In-memory database for development and testing.
- **Lombok**: For reducing boilerplate code.
- **Spring Security**: For securing the application.

## Database Schema

The database schema for the system is designed to handle various entities and their relationships effectively. The main entities include users, roles, courses, exams, questions, answers, and exam results.

### Database Diagram

![online assessment](https://github.com/user-attachments/assets/4a09af2e-fd00-4a08-b8eb-174c444bcd61)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Git

### Installation

1. Clone the repository

    ```sh
    git clone https://github.com/your-username/student-exam-management-system.git
    ```

2. Navigate to the project directory

    ```sh
    cd student-exam-management-system
    ```

3. Install the dependencies

    ```sh
    mvn clean install
    ```

4. Run the application

    ```sh
    mvn spring-boot:run
    ```

### Usage

- Access the application at `http://localhost:8080`
- Use the provided endpoints to manage users, roles, courses, exams, and results.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Your Name - [fataiwasiu2@gmail.com](mailto:fataiwasiu2@gmail.com)

Project Link: [https://github.com/wastech/StudentExamManagementSystem](https://github.com/wastech/StudentExamManagementSystem)
