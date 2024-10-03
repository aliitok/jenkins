FROM jenkins/jenkins:lts

# Switch to root user to install plugins and dependencies
USER root

# Install git to enable cloning the Groovy scripts repository
RUN apt-get update && apt-get install -y git

# Install Jenkins essential plugins
RUN jenkins-plugin-cli --plugins \
    configuration-as-code \
    git \
    job-dsl \
    pipeline-utility-steps \
    blueocean \
    credentials \
    workflow-aggregator

# Declare ARG for GitHub username and token
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# Clone the GitHub repository using the passed arguments (private repo)
RUN git clone https://${GITHUB_USERNAME}:${GITHUB_TOKEN}@github.com/aliitok/jenkins.git 
# Copy the Groovy init scripts from the cloned repository into Jenkins' init.groovy.d folder
COPY /jenkins/JCasC/* /usr/share/jenkins/ref/init.groovy.d/
RUN mkdir -p /var/jenkins_home/casc_configs
RUN chown -R jenkins:jenkins /var/jenkins_home/casc_configs
# Switch back to Jenkins user
USER jenkins

# Copy the JCasC YAML configuration file into the Jenkins image
COPY jenkins-config.yaml /var/jenkins_home/casc_configs/jenkins-config.yaml

# Set environment variable to point Jenkins to the configuration file
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs/jenkins-config.yaml

# Expose Jenkins port
EXPOSE 8080

# Set default Jenkins startup command
CMD ["/usr/local/bin/jenkins.sh"]
