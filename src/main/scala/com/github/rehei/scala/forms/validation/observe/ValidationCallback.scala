package com.github.rehei.scala.forms.validation.observe

case class ValidationCallback(query: String, callback: (String) => Unit)