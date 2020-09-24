pipeline {
    agent {
        docker {
            image 'javiersantos/android-ci:28.0.3'
        }
    }
    stages {
        stage('Run UI tests') {
            steps {
                timeout(time: 15, unit: 'MINUTES') {
                 sh '$ANDROID_HOME/platform-tools/adb connect ${EMULATOR}:5555'
                 sh '$ANDROID_HOME/platform-tools/adb uninstall com.github.tarcv.orderme.app || true'
                 sh '$ANDROID_HOME/platform-tools/adb uninstall com.github.tarcv.orderme.app.test || true'
                 sh './gradlew clean connectedDebugAndroidTest'}
            }
        }
    }
}
