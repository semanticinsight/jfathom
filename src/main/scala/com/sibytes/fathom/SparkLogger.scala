package com.sibytes.fathom.configuration

import org.apache.log4j.{Level, LogManager}

trait SparkLogger {
  
  val logger = LogManager.getRootLogger

  val printEnabled = sys.env.get("LOGGINGLEVELOVERRIDE") 
  match
  {
    case Some(s) =>  {
      logger.setLevel(Level.toLevel(s))
      true
    }
    case None => false
  }
  

  def logInfo(message:String) =
  {
    if (printEnabled) println(message)
    logger.info(message)
  }

  def logError(message:String, throwable:Throwable) =
  {
    if (printEnabled) println(message)
    logger.error(message, throwable)
    throw new Exception(message)
  }

  def logError(message:String) =
  {
    if (printEnabled) println(message)
    logger.error(message)
    throw new Exception(message)
  }
}