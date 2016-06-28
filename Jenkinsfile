node {
    timestamps {
        timeout(time: 15, unit: 'MINUTES') {
            deleteDir()
            stage 'SCM'
            //git credentialsId: 'd9defbd7-29da-469c-aa64-fd5dd0041cfd', url: 'https://github.com/joostvdg/json-helper-jenkins-pipeline-plugin.git'
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

                stage 'SonarQube'
                def pom = readMavenPom()
                writeFile encoding: 'UTF-8', file: 'sonar-project.properties', text: """
                # must be unique in a given SonarQube instance
                sonar.projectKey=$pom.groupId:$pom.artifactId
                sonar.projectName=$pom.name
                sonar.projectVersion=$pom.version
                sonar.genericcoverage.reportPaths=target/surefire-reports/TEST-jenkins.plugins.jsonHelper.FromJsonStepTest.xml

                # Path is relative to the sonar-project.properties file. Replace "\\" by "/" on Windows.
                # Since SonarQube 4.2, this property is optional if sonar.modules is set.
                # If not set, SonarQube starts looking for source code from the directory containing
                # the sonar-project.properties file.
                sonar.sources=src/main/java"""
                archive 'sonar-project.properties'

                def sonarHome = tool name: 'SonarQube Runner Latest', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                env.PATH = "${sonarHome}/bin:${env.PATH}"
                sh "sonar-runner -D sonar.host.url=http://sonarqube5-instance:9000"

            }  catch (err) {
                echo "Caught: ${err}"
                currentBuild.result = 'FAILURE'

            }
        }
    }
}