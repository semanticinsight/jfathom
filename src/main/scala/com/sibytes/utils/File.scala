package com.sibytes.utils

import java.io.{File=>JFile, FileOutputStream, IOException, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.io.Source
import java.nio.file.Paths

object File extends Using {

  
  def getAbsPath(path:String) = {
    Paths.get(".").toAbsolutePath().resolve(path)
  }

  def put(
               path: String,
               value: String,
               lineSeparator: String = "",
               deleteIfExists: Boolean = false) = {

    if (deleteIfExists)
      Files.deleteIfExists(Paths.get(path))

    val file = new JFile(path)

    using(
      if (file.exists() && !file.isDirectory) {
        val outStream = new FileOutputStream(file, true)
        new PrintWriter(outStream, true)
      }
      else {
        new PrintWriter(path)
      }
    ) {
      out =>
        out.append(s"$value$lineSeparator")
        if (out.checkError()) {
          val msg = s"An error occurred writing to the file ${path}"
          throw new IOException(msg)
        }
    }
  }

  def read(path: String) = {

    if (!Files.exists((Paths.get(path)))) {
      val msg = s"File doesn't not exist ${path}"
      throw new IOException(msg)
    }

    using(Source.fromFile(path)) {
      source => {
        source.getLines().mkString
      }
    }
  }

  def readToList(path: String) = {

    if (!Files.exists((Paths.get(path)))) {
      val msg = s"File doesn't not exist ${path}"
      throw new IOException(msg)
    }

    using(Source.fromFile(path)) {
      source => {
        source.getLines().toList
      }
    }
  }

  def read(path: String, fn:((String) => String)) = {

    if (!Files.exists((Paths.get(path)))) {
      val msg = s"File doesn't not exist ${path}"
      throw new IOException(msg)
    }

    using(Source.fromFile(path)) {
      source => {
        source.getLines().map(l => fn(l)).mkString
      }
    }
  }

  def parse(path: String, fn:((String) => String)) = {

    if (!Files.exists((Paths.get(path)))) {
      val msg = s"File doesn't not exist ${path}"
      throw new IOException(msg)
    }

    using(Source.fromFile(path)) {
      source => {
        source.getLines().flatMap(l => fn(l)).mkString
      }
    }
  }

}
