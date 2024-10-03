folder("AZ360-Build-Sales-Tools") {
    displayName('Az360 Build Sales Tools')
    description('This folder contains the jobs for Sales tools')}
folder('AZ360-Build-Sales-Tools/az360-illustration-be') {
    displayName('Az360 Illustration be')
    description('This folder for illustration be')}
    
pipelineJob('AZ360-Build-Sales-Tools/az360-illustration-be/dev') {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url('https://github.developer.allianz.io/azap-az360/az360-illustration-be.git') // Replace with your repository URL
                                credentials('git-token-credentials') // Optional: specify credentials if needed
                            }
                            branches('*/main') // Specify the branch containing your Jenkinsfile
                        }
                    }
                    scriptPath('Jenkinsfile') // Path to Jenkinsfile in the repository
                }
            }
            keepDependencies(false)
            logRotator {
                daysToKeep(7)
                numToKeep(7)
                artifactDaysToKeep(-1)
                artifactNumToKeep(-1)
            }
        }