package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Field

case class Assertion(validations: Validation*) {

  def assert(validation: Validation) = {
    Assertion((validations :+ validation): _*)
  }

}