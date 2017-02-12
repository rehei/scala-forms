package com.github.rehei.scala.forms.validation.observe

case class ValidationCallback(query: String, reset: () => Unit, validate: (String) => Unit)