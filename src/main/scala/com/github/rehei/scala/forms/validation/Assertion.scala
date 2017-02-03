package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Field

case class Assertion(val modelClazz: Class[_], val query: String, validations: Validation*) {

  def assert(validation: Validation) = {
    Assertion(modelClazz, query, (validations :+ validation): _*)
  }

  def field = {
    new Field(modelClazz, query, null)
  }

}