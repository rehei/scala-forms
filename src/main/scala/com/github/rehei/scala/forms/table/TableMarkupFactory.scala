package com.github.rehei.scala.forms.table

abstract class AbstractTableMarkupFactory[T] {

  def render(table: Table): T

}