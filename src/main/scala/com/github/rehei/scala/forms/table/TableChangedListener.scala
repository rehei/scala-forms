package com.github.rehei.scala.forms.table

trait TableChangedListener {
  
  def changed(model: TableRowModel)
  
  def removed(model: TableRowModel)
  
}