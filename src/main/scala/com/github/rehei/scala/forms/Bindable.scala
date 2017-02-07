package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.binding.AbstractBinding

case class Bindable(val modelClazz: Class[_], val query: String) {

  def bindUsing(binding: AbstractBinding[_]) = {
    new Field(modelClazz, query, binding)
  }
  
}