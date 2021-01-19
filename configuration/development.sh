#!/bin/bash
sudo echo export ENVIRONMENT=DEV >> /databricks/spark/conf/spark-env.sh 

# Transparent Environment Vars 
sudo echo export AZUREADID=a69c8df4-e648-4b0a-beb9-b3716a01f60e >> /databricks/spark/conf/spark-env.sh
sudo echo export LOGAPPALERTEMAIL=shaun_chibru@hotmail.com >> /databricks/spark/conf/spark-env.sh
sudo echo export AUTOMATIONSCOPE=azure-key-vault-scope >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESTORAGEREDACTED=AzureDataLakeGen2 >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESTORAGEACCOUNTREDACTED=abfss://datalake@datalakegeneva.dfs.core.windows.net/ >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKEREDACTEDROOT=databricks/delta_dev_redacted >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESTORAGE=AzureDataLakeGen2 >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESTORAGEACCOUNT=abfss://datalake@datalakegeneva.dfs.core.windows.net/ >> /databricks/spark/conf/spark-env.sh
sudo echo export DATAENGINEERINGCONTROLDB=DataEngineeringControl >> /databricks/spark/conf/spark-env.sh
sudo echo export DATAENGINEERINGCONTROLHOST=dataplatform-sql.database.windows.net >> /databricks/spark/conf/spark-env.sh
sudo echo export DATAENGINEERINGCONTROLUSER=svc_DPUser >> /databricks/spark/conf/spark-env.sh
sudo echo export RESOURCEGROUP=DataPlatform >> /databricks/spark/conf/spark-env.sh
sudo echo export SUBSCRIPTIONID=e95203c2-64a0-43f9-bfc5-a4fdc588571a >> /databricks/spark/conf/spark-env.sh

# Secret Environment Vars 
sudo echo export LOGAPPALERTENDPOINT=LOGAPP-ALERT-ENDPOINT >> /databricks/spark/conf/spark-env.sh
sudo echo export SQLDATAENGEERINGCONTROLPASSWORD=SQL-DATAENGEERINGCONTROL-PASSWORD >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESPNAPPID=DATALAKE-SPN-APPID >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESPNOBJECTID=DATALAKE-SPN-OBJECTID >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKESPNCREDENTIAL=DATALAKE-SPN-CREDENTIAL >> /databricks/spark/conf/spark-env.sh
sudo echo export DATALAKECONNECTIONSTRING=DATALAKE-CONNECTION-STRING >> /databricks/spark/conf/spark-env.sh

