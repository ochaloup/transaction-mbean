transaction-mbean
=================

Simple servlet to show data from Narayana BeanPopulator class

You can compile it by

 mvn clean compile war:war
 
and the result could be deployed like

 cp target/transaction-mbean-*.war $JBOSS_HOME/standalone/deployments/
