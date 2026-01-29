# 4th Semester APS - PixelHaus

Supervised Practical Activity (APS) project for the 4th Semester, focused on creating a game sales management system in Java.

## 🎯 Project Objectives

This work was developed to apply and improve knowledge in:

* **MVC Design Pattern:** Code organization separating business logic (Model), interface (View), and control (Controller).
* **DAO Design Pattern:** Abstraction of data access (`JogoDAO`, `UsuarioDAO`) to facilitate database connection maintenance.
* **Java Swing:** Improvement in the use of Layouts and visual components for creating user interfaces (`TelaLogin`, `TelaCadastro`, etc.).
* **Database Connection:** Utilization of JDBC to connect the application to a MySQL database, with connection management (Singleton).

## 📁 Project Structure

The project follows the MVC and DAO architecture:

* `controller/`: Contains classes that bridge the View and the Model (e.g., `LoginController`, `CadastroController`).
* `model/bean/`: Contains entity classes (POJOs) representing the data (e.g., `Jogo`, `Usuario`).
* `model/dao/`: Contains Data Access Object classes responsible for communicating with the database (e.g., `UsuarioDAO`).
* `view/`: Contains the application screens (JFrames/JPanels) (e.g., `TelaLogin`).
* `database/`: Contains the MySQL connection management class.
* `resources/`: Contains non-Java files, such as images and the configuration file.

## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/gabrielpaloni/APS-4-Semestre.git](https://github.com/gabrielpaloni/APS-4-Semestre.git)
    ```
2.  **Configure the Database:**
    * Open your MySQL manager (Workbench, DBeaver, etc.).
    * Execute the script `database/pixelhaus_script.sql` to create the `pixelhaus` database and all necessary tables.
    * Go to the `/resources/` folder.
    * Rename the file `config.properties.example` to `config.properties`.
    * Open `config.properties` and fill it with your MySQL credentials.
3.  **Open in IDE:**
    * Open the project in IntelliJ IDEA.
    * Locate the `Main.java` file and run it.

## 👨‍💻 Authors

* Gabriel S. B. Paloni
* Ana Paula Garbin
* Graziela Lopes Romualdo
