package com.github.rehei.scala.forms.table

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.Bindable

object Table extends Table

class Table[T <: AnyRef] protected (val fields: List[TableHead], val instances: List[T]) {

  def this() = {
    this(List.empty, List.empty)
  }
  
  def attach(tableHead: TableHead) = {
    val ext = fields :+ tableHead
    new Table[T](ext, instances)
  }
  
  def on[X <: AnyRef](instances: List[X]) = {
    new Table[X](fields, instances)
  }
  
  def columnCount = fields.size

  def columnName(index: Int) = fields(index).label()

  def rowCount = instances.size

  def valueAt(rowIndex: Int, columnIndex: Int) = {
    valueFor(modelAt(rowIndex), columnIndex)
  }

  def valueFor(model: T, columnIndex: Int) = {
    val value = fields(columnIndex).getter(model)
    val output = {
      if (value == null) {
        ""
      } else {
        if (value.isInstanceOf[NodeSeq]) {
          value
        } else {
          value.toString()
        }
      }
    }
    output
  }

  def modelAt(rowIndex: Int) = {
    instances(rowIndex)
  }
  
  def render[X](tableMarkupFactory: AbstractTableMarkupFactory[X]) = {
    tableMarkupFactory.render(this.asInstanceOf[Table[AnyRef]])
  }

}