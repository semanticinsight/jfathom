package com.sibytes.fathom.configuration

object ConnectStorage 
    extends SparkLogger 
    with SparkSessionWrapper
{
  def apply() = {
    connectStorage()
  }
  
  private def connectStorage() =
  { 
      Configuration.getDataLakeStorageType() match 
      {
        case "AzureDataLakeGen1" =>
        {
          spark.conf.set(s"dfs.adls.oauth2.access.token.provider.type", "ClientCredential")
          spark.conf.set(s"dfs.adls.oauth2.client.id", Configuration.getServicePrincipalId())
          spark.conf.set(s"dfs.adls.oauth2.credential", Configuration.getServiceCredential())
          spark.conf.set(s"dfs.adls.oauth2.refresh.url", Configuration.getOAuthRefreshUrl())
        }
        case "AzureDataLakeGen2" =>
        {
          spark.conf.set(s"fs.azure.account.auth.type", "OAuth")
          spark.conf.set(s"fs.azure.account.oauth.provider.type", "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider")
          spark.conf.set(s"fs.azure.account.oauth2.client.id", Configuration.getServicePrincipalId())
          spark.conf.set(s"fs.azure.account.oauth2.client.secret", Configuration.getServiceCredential())
          spark.conf.set(s"fs.azure.account.oauth2.client.endpoint", Configuration.getOAuthRefreshUrl())
        }
        case _ => {
          logError(s"Unknown storage type ${Configuration.getDataLakeStorageType()} in enviornment variable DATALAKESTORAGE")
          throw new Exception(s"Unknown storage type ${Configuration.getDataLakeStorageType()} in enviornment variable DATALAKESTORAGE")
        }
      }

      logInfo(s"""
                |Connected:
                |-----------------------------------------------
                | environment = ${Configuration.getEnvironment()}
                | storage account = ${Configuration.getStorageAccount()} 
          """.stripMargin)
    }
}