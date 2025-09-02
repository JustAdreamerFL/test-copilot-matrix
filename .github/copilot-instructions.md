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
1. **Complete Project Setup**
   - Android project with Jetpack Compose and Material Design 3
   - Gradle build configuration with all necessary dependencies
   - CI/CD pipeline with GitHub Actions for automated builds
   - Comprehensive project structure following Android best practices

2. **Full UI Implementation**
   - Login screen with homeserver, username, password fields and validation
   - Chat screen with scrollable message list and send functionality
   - Custom Material Design 3 theming with Matrix-inspired colors
   - Navigation between screens with proper state management
   - Loading states, error handling, and user feedback

3. **Architecture & State Management**
   - MVVM pattern with ViewModels for both Login and Chat screens
   - Reactive UI with Compose state management
   - Proper separation of concerns between UI, ViewModel, and Data layers

4. **Matrix API Foundation**
   - MatrixClient class with proper API structure
   - Data models for Matrix protocol (LoginRequest, LoginResponse, etc.)
   - Mock implementation showing the intended functionality
   - Ready for real Matrix SDK integration

5. **Testing & Quality**
   - Unit tests for core Matrix client functionality
   - Proper error handling and edge cases
   - Code organization following Android conventions

### 🚧 Next Steps for Production
1. **Real Matrix Integration**
   - Replace mock MatrixClient with official Matrix Android SDK
   - Implement actual authentication against Matrix homeserver
   - Add real message sending/receiving via Matrix protocol

2. **Enhanced Chat Features**
   - Room list and room selection interface
   - User avatars and profile management
   - Rich media support (images, files, voice messages)
   - Message history and proper pagination

3. **Production Features**
   - Push notifications for new messages
   - End-to-end encryption support
   - Offline message caching and sync
   - Advanced settings and preferences

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
*Last updated: Complete Android Jetpack Compose Matrix chat app implemented with ViewModels, proper state management, and mock Matrix API integration. Ready for real Matrix SDK integration.*