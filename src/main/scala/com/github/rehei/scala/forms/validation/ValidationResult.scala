package com.github.rehei.scala.forms.validation

case class ValidationResult(propertyPath: String, ruleName: String, isValid: Boolean, message: String)