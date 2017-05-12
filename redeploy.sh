#!/bin/bash
mvn clean install
echo "=> Redeploying application..."
docker cp ifa-webapp/target/ROOT.war webapp:/opt/jboss/wildfly/standalone/deployments/ROOT.war
echo "=> Application redeployed with SUCCESS."
