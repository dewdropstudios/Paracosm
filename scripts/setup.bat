@echo Starting DewdropSetup Script for Paracosm (v 1.0.0) WIN32

call gradlew eclipse
call gradlew genEclipseRuns
call gradlew genIntellijRuns

call gradlew eclipse
call gradlew genEclipseRuns
call gradlew genIntellijRuns

@echo Setup complete. Closing script.