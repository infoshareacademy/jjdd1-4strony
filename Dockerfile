FROM jboss/wildfly:latest

ADD customization /opt/jboss/wildfly/customization/
COPY ifa-webapp/target/ROOT.war /opt/jboss/wildfly/customization/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]