package com.sibytes.fathom.configuration
import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper{
  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("Fathom.Configuration")
      .getOrCreate()
  }

  sys.env.get("LOGGINGLEVELOVERRIDE") match
  {
    case Some(s) =>  spark.sparkContext.setLogLevel(s)
    case None => // do nothing
  }

  
}