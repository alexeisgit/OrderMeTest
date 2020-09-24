
pipeline {
    agent {
        docker {
            image 'javiersantos/android-ci:27.0.3'
        }
    }
    stages {
        stage('Run UI tests') {
            steps {
                timeout(time: 15, unit: 'MINUTES') {
                 sh '$ANDROID_HOME/platform-tools/adb connect ${EMULATOR}:5555'
                 sh '$ANDROID_HOME/platform-tools/adb uninstall com.github.tarcv.orderme.app || true'
                 sh '$ANDROID_HOME/platform-tools/adb uninstall com.github.tarcv.orderme.app.test || true'
                 sh './gradlew --stop'
                 sh './gradlew clean forkDebugAndroidTest'}
            }
        }
        }
        post {
          always {
          script {sh 'tar -czvf fork-report.tar.gz app/build/reports/fork/debugAndroidTest'}
              archiveArtifacts artifacts: 'fork-report.*', fingerprint: true
           junit 'app/build/reports/fork/debugAndroidTest/tests/**/*.xml'
           publishHTML(target: [
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: true,
            reportDir: 'app/build/reports/fork/debugAndroidTest/html',
            reportFiles: 'index.html',
            reportName: "HTML Report"
           ])
          }
    }
}
