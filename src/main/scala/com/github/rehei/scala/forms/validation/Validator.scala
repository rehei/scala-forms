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
    assertions.flatMap(_.validations).map(_.isValid(model, null))
  }

}