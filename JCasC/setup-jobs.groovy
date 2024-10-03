import jenkins.model.*
import javaposse.jobdsl.plugin.*
import hudson.model.*

def instance = Jenkins.getInstance()

println "--> Creating example pipeline job"

def jobName = "example-pipeline-job"
def job = instance.getItem(jobName)

if (job == null) {
    def pipelineScript = """
        pipeline {
            agent any
            stages {
                stage('Build') {
                    steps {
                        echo 'Building...'
                    }
                }
                stage('Test') {
                    steps {
                        echo 'Testing...'
                    }
                }
                stage('Deploy') {
                    steps {
                        echo 'Deploying...'
                    }
                }
            }
        }
    """.stripIndent()

    def pipelineJob = instance.createProject(org.jenkinsci.plugins.workflow.job.WorkflowJob, jobName)
    pipelineJob.setDefinition(new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(pipelineScript, true))
    pipelineJob.save()

    println "--> Job '${jobName}' created."
} else {
    println "--> Job '${jobName}' already exists."
}

instance.save()
