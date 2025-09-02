# Matrix Chat App - Copilot Instructions

## Project Overview
This is a Jetpack Compose Android application that implements a Matrix protocol client for chat functionality. The app is designed to start small and gradually expand Matrix API integration.

## Project Structure

### Core Directories
- `app/src/main/java/com/matrixchat/` - Main application source code
- `app/src/main/res/` - Android resources (layouts, strings, themes)
- `.github/workflows/` - CI/CD pipeline configuration
- `.github/copilot-instructions.md` - This documentation file

### Key Components

#### Application Entry Points
- `MainActivity.kt` - Main activity that sets up Jetpack Compose
- `MatrixChatApp.kt` - Root composable with navigation setup

#### UI Structure
- `ui/screens/LoginScreen.kt` - User authentication interface
- `ui/screens/ChatScreen.kt` - Main chat interface with message list and input
- `ui/components/MessageItem.kt` - Individual message bubble component
- `ui/theme/` - Material Design 3 theming (Color.kt, Theme.kt, Type.kt)

#### Data Layer (Future)
- `data/` - Directory for Matrix API integration, repositories, and data models
- Note: Currently using mock data for demonstration

### Build Configuration
- `build.gradle.kts` (root) - Project-level build configuration
- `app/build.gradle.kts` - App module configuration with dependencies
- `settings.gradle.kts` - Project settings
- `gradle.properties` - Gradle configuration properties

## Current Implementation Status

### ✅ Completed Features
1. **Basic Project Setup**
   - Android project with Jetpack Compose
   - Material Design 3 theming with Matrix-inspired colors
   - Navigation between login and chat screens

2. **UI Components**
   - Login screen with homeserver, username, password fields
   - Chat screen with message list and input field
   - Message bubbles with sender differentiation
   - Responsive layout design

3. **CI/CD Pipeline**
   - GitHub Actions workflow for building and testing
   - Automatic APK generation on commits
   - Caching for faster builds

### 🚧 In Progress / TODO
1. **Matrix API Integration**
   - Currently using mock login (always succeeds after 2-second delay)
   - Need to integrate actual Matrix Android SDK
   - Implement real authentication against Matrix homeserver

2. **Real Chat Functionality**
   - Currently shows demo messages
   - Need to connect to actual Matrix rooms
   - Implement message sending/receiving via Matrix API

3. **Enhanced Features** (Future)
   - Room list and room selection
   - User avatars and profiles
   - File/image sharing
   - Push notifications
   - End-to-end encryption support

## Key Dependencies

### Core Android/Compose
- `androidx.compose.ui` - Jetpack Compose UI toolkit
- `androidx.compose.material3` - Material Design 3 components
- `androidx.navigation:navigation-compose` - Navigation for Compose

### Matrix Integration
- `org.matrix.android:matrix-android-sdk2` - Official Matrix Android SDK
- `com.squareup.okhttp3:okhttp` - HTTP client for API calls
- `com.squareup.moshi:moshi` - JSON parsing

### Development Tools
- Kotlin 1.9.10
- Android Gradle Plugin 8.1.2
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

## Development Guidelines

### Making Changes
1. **Small Incremental Changes**: Always make minimal modifications
2. **Test Early**: Run `./gradlew build` and `./gradlew test` frequently
3. **Follow Material Design**: Use existing theme and component patterns
4. **Matrix Protocol**: Refer to Matrix.org specification for API implementation

### Code Organization
- Keep UI components in `ui/` packages
- Data layer logic goes in `data/` packages
- Use ViewModels for state management (to be added)
- Follow Android architecture guidelines

### Testing Strategy
- Unit tests in `app/src/test/`
- Instrumentation tests in `app/src/androidTest/`
- Focus on testing business logic and Matrix integration

## Matrix API Integration Notes

### Authentication Flow
1. User enters homeserver URL, username, password
2. App discovers homeserver capabilities via `.well-known/matrix/client`
3. Authenticate using `/login` endpoint
4. Store access token securely
5. Initialize sync with Matrix homeserver

### Chat Functionality
1. Get joined rooms via `/sync` endpoint
2. Load room history via `/messages` endpoint
3. Send messages via `/send` endpoint
4. Listen for new messages via long-polling or WebSocket

### Key Matrix Endpoints
- `GET /.well-known/matrix/client` - Server discovery
- `POST /login` - User authentication
- `GET /sync` - Get room updates
- `GET /rooms/{roomId}/messages` - Load message history
- `PUT /rooms/{roomId}/send/{eventType}/{txnId}` - Send messages

## Build and Deploy

### Local Development
```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Install debug APK
./gradlew installDebug
```

### CI/CD Pipeline
- Automatic builds on push to main/develop branches
- APK artifacts uploaded for each successful build
- Located in `.github/workflows/android.yml`

## Future Enhancements
1. **Advanced UI**: Room list, user profiles, settings
2. **Media Support**: Image/file sharing, voice messages
3. **Notifications**: Push notifications for new messages
4. **Security**: End-to-end encryption, device verification
5. **Performance**: Message caching, offline support

## Quick Start for New Contributors
1. Clone the repository
2. Open in Android Studio
3. Let Gradle sync complete
4. Run the app - it should show login screen
5. Enter any credentials to see the demo chat screen
6. Start implementing real Matrix integration in the `data/` package

---
*Last updated: Initial project setup - Replace mock authentication and chat with real Matrix API integration*