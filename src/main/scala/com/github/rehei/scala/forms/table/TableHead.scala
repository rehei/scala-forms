package com.github.rehei.scala.forms.table

import com.github.rehei.scala.forms.UndeterminedBindable

class TableHead protected (val modelClazz: Class[_], val query: String, val labelOption: Option[String]) {

  protected val bindable = new UndeterminedBindable(modelClazz, query)

  def this(modelClazz: Class[_], query: String) {
    this(modelClazz, query, None)
  }

  def label(label: String) = {
    new TableHead(modelClazz, query, Some(label))
  }

  def label() = {
    labelOption.getOrElse(query)
  }

  def getter(model: AnyRef) = bindable.getter(model)

}