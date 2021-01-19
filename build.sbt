name := "fathom"

version := "0.1"

scalaVersion := "2.12.10"


// libraryDependencies ++= Seq(
//   "org.apache.spark" %% "spark-core" % "3.0.1",
//   "org.apache.spark" %% "spark-streaming" % "3.0.1",
//   "org.apache.spark" %% "spark-sql" % "3.0.1"
// )

//The environment variable DATABRICKS_JAR_PATH must be set to databricks connect jar path that can be returned
lazy val sparkJarPath = sys.env("DATABRICKS_JAR_PATH")
unmanagedBase := new java.io.File(sparkJarPath)


