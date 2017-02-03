package com.github.rehei.scala.forms.validation

import scala.collection.mutable.ListBuffer

object Validator extends Validator()

case class Validator protected (val assertions: Assertion*) {

  def this() = {
    this(List(): _*)
  }

  def attach(assertion: Assertion) = {
    Validator((assertions :+ assertion): _*)
  }

  def validate(model: AnyRef) = {

    val buffer = ListBuffer[ValidationResult]()
    
    for (assertion <- assertions; validation <- assertion.validations) {
      buffer.append(validation.validate(model, assertion.field))
    }

    buffer.toList
  }

}