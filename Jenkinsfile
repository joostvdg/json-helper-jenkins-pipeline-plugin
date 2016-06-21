node {
    timestamps {
        timeout(time: 15, unit: 'MINUTES') {
            deleteDir()
            stage 'SCM'
            checkout scm

            env.JAVA_HOME="${tool 'JDK 8 Latest'}"
            env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
            sh 'java -version'
            def mvnHome = tool 'Maven 3 Latest'
            env.PATH = "${mvnHome}/bin:${env.PATH}"

            try {
                stage 'Build'

                sh 'mvn clean package'
                step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/*.xml'])
                step([$class: 'JavadocArchiver', javadocDir: 'target/site/apidocs'])

                //stage 'SonarQube'
                //sh 'mvn sonar:sonar -Psonar'

            }  catch (err) {
                echo "Caught: ${err}"
                currentBuild.result = 'FAILURE'

            }
        }
    }
}