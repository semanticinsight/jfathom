package com.sibytes.utils

trait Using {
  def using[A <: {def close(): Unit}, B](resource: A)(f: A => B): B = {
    try {
      f(resource)
    } finally {
      resource.close()
    }
  }
}
