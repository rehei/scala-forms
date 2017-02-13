package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.UndeterminedBindable

case class Assertion(val modelClazz: Class[_], val query: String, validations: Validation*) {

  def assert(validation: Validation) = {
    Assertion(modelClazz, query, (validations :+ validation): _*)
  }

  def field = {
    new UndeterminedBindable(modelClazz, query)
  }

}