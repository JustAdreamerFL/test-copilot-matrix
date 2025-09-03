# test-copilot-matrix
This repo is for my testing of github copilot for making PRs. Only copilot will make code changes.

## Automated Releases

This repository now supports automated release creation! 🚀

### How to Create a Release

1. **Create and push a version tag:**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. **GitHub Actions will automatically:**
   - Build a release APK (`app-release-unsigned.apk`)
   - Create a GitHub release with the tag name
   - Upload the APK as a release asset
   - Generate release notes automatically

### Tag Format
- Use semantic versioning: `v1.0.0`, `v1.2.3`, `v2.0.0-beta1`, etc.
- Tags must start with `v` to trigger the release workflow
- Examples: `v1.0.0`, `v1.1.0`, `v2.0.0-alpha1`

### Release Assets
Each release will include:
- **APK file**: `app-release-unsigned.apk` - Ready to install Android app
- **Automatic release notes**: Generated from commits since the last release

### Workflow Details
The release automation is handled by the GitHub Actions workflow in `.github/workflows/android.yml`.
