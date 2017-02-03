package com.github.rehei.scala.forms.validation

object Validator extends Validator()

case class Validator protected (val assertions: Assertion*) {

  def this() = {
    this(List(): _*)
  }

  def attach(assertion: Assertion) = {
    Validator((assertions :+ assertion): _*)
  }

  def validate(model: AnyRef) = {

    for (assertion <- assertions; validation <- assertion.validations) {
      println(validation.isValid(model, assertion.field))
    }

  }

}