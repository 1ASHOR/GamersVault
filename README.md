# Gamers Vault

Gamers Vault is a personal game library manager built with JavaFX. It helps users track all their video games across multiple platforms in one centralized application. With Gamers Vault, users no longer have to rely on memory or jump between gaming platforms to remember what they own or have played. The app stores essential information like game titles, playtime, progress, and even personal notes or reviews — making it easier to decide what to play next or revisit.

---

## Features

- Add, view, and delete games from your personal vault  
- Track playtime, progression, platform, developer, and genre  
- Store personal opinions and ratings  
- Edit and save your user profile (gamertag & Discord name)  
- JavaFX-based desktop application  
- Uses a MySQL database for persistent data storage

---

## Installation Instructions

### Requirements

- Java Development Kit (JDK) 17 or higher  
- JavaFX SDK  
- MySQL (Recommended: XAMPP for local setup)  
- Git (optional, for cloning the repository)  
- An IDE like IntelliJ (recommended)

> ⚠️ The app runs on all platforms that support Java.  
> Ensure MySQL is up and running before launching the application.

### 🛠 Setup Steps

1. **Clone the Repository**  
   ```sh
   git clone https://github.com/1ASHOR/GamersVault
   cd GamersVault

2. **Import the Database**
   Open [http://localhost/phpmyadmin](http://localhost/phpmyadmin) via XAMPP and import the `database.sql` file found in the repository.

3. **Run the Application**

   * Open the project in IntelliJ or another IDE
   * Build the project using Maven
   * Run the `Main.java` file
     Or download and run the provided `.jar` file from the GitHub Releases section.

---

## Configuration

Gamers Vault requires a working MySQL server.

* The database schema is included in `database.sql`
* Update database connection settings in the `DatabaseConnector.java` file if needed:

  ```java
  private static final String URL = "jdbc:mysql://localhost:3306/gamersvault";
  private static final String USER = "root";
  private static final String PASSWORD = "";
  ```

If you're using a different host or port, update those fields accordingly.

---

## Usage Guide

Once the application starts, the Vault screen (game library) is shown immediately.

### Main Actions

* **Add a Game:**
  Click the "Add Game" button and fill in the required fields.

* **Delete a Game:**
  Use the selector next to the game entry and press "Delete". *In a future update, confirmation will be required before deletion.*

* **Edit User Info:**
  Click "Edit Profile" to enter your gamertag and Discord name.

* **View Games:**
  All your saved games are listed with their main attributes visible.

---

## Roadmap / Timeline

### Current Version (v1.0)

* Core features: game tracking, editing user info, add/delete functionality

### Planned Updates

| Update                      | Description                                                             | Status      |
| --------------------------- | ----------------------------------------------------------------------- | ----------- |
| Input Validation            | Handle incorrect or incomplete user inputs with friendly error messages | In Progress |
| Game Detail Page            | Ability to view complete details of a selected game in a new screen     | Planned     |
| Deletion Confirmation       | Add a pop-up confirmation before deleting a game                        | Planned     |
| Discord / Steam Integration | Automatically fetch game data from linked accounts                      | Planned     |
| Suggestions & Feedback      | Users can propose features via GitHub issues                            | Always Open |

---

## Communication & Evaluation Strategy

We encourage user feedback for continuous improvement.
If you encounter issues or have ideas for features:

* **Report Bugs:** Use the [Issues](https://github.com/1ASHOR/GamersVault/issues) tab on GitHub
* **Suggest Features:** Open a feature request on GitHub
* **Evaluate the App:** Provide usability or design feedback via email or GitHub discussions
* **Community Involvement:** Users are invited to contribute to development by forking the repository

---

## Developer Contact

Developed by [Tim](https://github.com/1ASHOR)
Email: [t.vanvliet7@ad-academie.nl](mailto:t.vanvliet7@ad-academie.nl)

---

## License

This project is open source and available under the [MIT License](LICENSE).

```
