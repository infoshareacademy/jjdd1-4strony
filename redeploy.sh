#!/bin/bash
mvn clean install
echo "=> Redeploying application..."
docker cp ifa-reports/target/ROOT.war 4strony-reports-api:/opt/jboss/wildfly/standalone/deployments/ROOT.war
docker cp ifa-webapp/target/ROOT.war 4strony-webapp:/opt/jboss/wildfly/standalone/deployments/ROOT.war
echo "=> Application redeployed."
