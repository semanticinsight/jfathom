package com.sibytes.fathom.configuration
import com.databricks.service.{DBUtils => dbutils}
import java.nio.file.Paths
import java.nio.file.Path

object Configuration extends SparkLogger
{

  def getEnv: ((String) => String) = 
  { 
    variable =>
      sys.env.get(variable)
      match{
        case Some(s) => s
        case None => {
          val msg = s"Environment variable '${variable}' not found"
          logError(msg)
          throw new EnvVarNotFoundException(msg)
        }
      }
  }
  
  def getOAuthRefreshUrl: (() => String) = () => 
    s"https://login.microsoftonline.com/${getAzureADId()}/oauth2/token"

  def getDbUtilsToken: (() => String) = () => getEnv("DBUTILSTOKEN")

  def getStorageAccount: (() => String) = () => getEnv("DATALAKESTORAGEACCOUNT")
  
  def getEnvironment: (() => String) = () => getEnv("ENVIRONMENT")
  
  def getAzureADId: (() => String) = () => getEnv("AZUREADID")

  def getSubscripionId: (() => String) = () => getEnv("SUBSCRIPTIONID")

  def getResourceGroup: (() => String) = () => getEnv("RESOURCEGROUP")
  
  def getAutomationScope: (() => String) = () => getEnv("AUTOMATIONSCOPE")
  
  def getDataLakeStorageType: (() => String) = () => getEnv("DATALAKESTORAGE")  

  def getDatalakeConnectionString: (() => String) = () =>
    getDbUtilsSecret("DATALAKECONNECTIONSTRING")

  def getServicePrincipalId: (() => String) = () =>
    getDbUtilsSecret("DATALAKESPNAPPID")
  
  def getServiceObjectId: (() => String) = () =>
    getDbUtilsSecret("DATALAKESPNOBJECTID")
  
  def getServiceCredential: (() => String) = () =>
    getDbUtilsSecret("DATALAKESPNCREDENTIAL")

  private def getDbUtilsSecret: ((String) => String) = { key =>

    if (getEnvironment() == "LOCALDEV")
      dbutils.secrets.setToken(getDbUtilsToken())

    dbutils.secrets.get(scope = getAutomationScope(), key = getEnv(key))

  }

  override def toString() = {
    s"""
      |Configuration:
      |-----------------------------------------------
      | Environment = ${Configuration.getEnvironment()}
      | Storage Account = ${Configuration.getStorageAccount()}
      | Storage Type = ${Configuration.getDataLakeStorageType()}
      | Automation Scope = ${Configuration.getAutomationScope()}
      | Resource Group = ${Configuration.getResourceGroup()}
      | Azure AD Id = ${Configuration.getAzureADId()}
      | Subscription Id = ${Configuration.getSubscriptionId()}
      | OAuth Refresh = ${Configuration.getOAuthRefreshUrl()}
      | Service Principal Id = ${Configuration.getServicePrincipalId()}
      | Service Object Id = ${Configuration.getServiceObjectId()}
      | Service Credential = ${Configuration.getServiceCredential()}
      | Datalake Connection String = ${Configuration.getDatalakeConnectionString()}
    """.stripMargin
  }
}