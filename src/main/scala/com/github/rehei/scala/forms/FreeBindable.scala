package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.binding.AbstractBinding

case class UndeterminedBindable(val modelClazz: Class[_], val query: String) {

  def bindUsing(binding: AbstractBinding[_]) = {
    new Bindable(modelClazz, query, binding)
  }
  
}