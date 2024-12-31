# Connect Hub

## Description
Connect Hub is a Java-based social networking platform designed to provide core functionalities for users to interact and share content in a secure and user-friendly environment. Built using Swing for the frontend and core Java for the backend, the platform features account management, content sharing (posts and stories), friend and group management, and real-time notifications. A file-based JSON database ensures efficient and lightweight data handling.

## Features

### User Account Management
- User signup, login, and logout functionalities.
- Input validation (e.g., email format checks).
- Secure password storage using hashing (SHA-256 or bcrypt).
- Tracks user status (online/offline).

### Profile Management
- Update profile details, including profile photo, cover photo, bio, and password.
- View and edit own posts and friends list.

### Content Sharing
- Create and manage posts (permanent content) and stories (temporary content that disappears after 24 hours).
- Supports text and image content.

### Friend Management
- Send, accept, and decline friend requests.
- Block or remove friends.
- Suggest friends based on mutual connections.

### Group Management
- Create, join, and leave groups.
- Group permissions: Admin roles for managing members and posts.
- Display group-specific posts and updates in the news feed.

### Notifications
- Real-time notifications for:
  - New comments or likes on posts.
  - New friend requests.
  - Group activities.
- Threaded implementation for real-time updates.

### Chat Functionality
- One-on-one messaging between friends with a chat window displaying previous messages.

### Search Functionality
- Search for users and groups by name.
- Actions include viewing profiles, sending friend requests, and joining groups.

### News Feed
- Displays posts, stories, friend activities, and group updates.
- Friend suggestions and group recommendations.
- Interactive notifications.

## Technical Details

### Technology Stack
- **Programming Language:** Java
- **Frontend:** Swing
- **Backend:** Core Java
- **Database:** File-based JSON

### Design Principles
- Adheres to SOLID principles.
- Implements various design patterns, including creational, structural, and behavioral patterns.

## Project Setup

### Prerequisites
- JDK 11 or higher
- IDE for Java development (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

### Installation
1. Clone the repository:
   ```bash
   https://github.com/mohamedelhofy/Connect-Hub
   ```
2. Open the project in your preferred IDE.
3. Ensure all necessary dependencies are included.
4. Run the main class to start the application.

## Usage
1. **Signup/Login:** Create a new account or log in with existing credentials.
2. **Explore Features:**
   - Manage your profile and view your friends.
   - Share posts and stories.
   - Interact with friends and groups.
   - Respond to notifications.
3. **Chat:** Open the chat window to communicate with friends in real time.



## Contributors
- Dr. Layla Abou-Hadeed

## Acknowledgments
Special thanks to Alexandria University, Faculty of Engineering, Computer and Communication Engineering Department, for providing the foundation and guidance for this project.

