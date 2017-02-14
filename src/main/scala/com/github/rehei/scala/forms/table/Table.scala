package com.github.rehei.scala.forms.table

import scala.xml.NodeSeq

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.table.action.OnCreate
import com.github.rehei.scala.forms.table.action.OnCreate
import com.github.rehei.scala.forms.table.action.OnDelete
import com.github.rehei.scala.forms.table.action.OnUpdate

object Table extends Table()

class Table protected (
    val fields: List[TableHead],
    val instances: List[_ <: TableRowModel],
    val onCreateList: List[OnCreate],
    val onUpdateList: List[OnUpdate],
    val onDeleteList: List[OnDelete]) {

  def this() = {
    this(List.empty, List.empty, List.empty, List.empty, List.empty)
  }

  def attach(tableHead: TableHead) = {
    new Table(fields :+ tableHead, instances, onCreateList, onUpdateList, onDeleteList)
  }

  def action(onCreate: OnCreate) = {
    new Table(fields, instances, onCreateList :+ onCreate, onUpdateList, onDeleteList)
  }

  def action(onUpdate: OnUpdate) = {
    new Table(fields, instances, onCreateList, onUpdateList :+ onUpdate, onDeleteList)
  }
  
  def action(onDelete: OnDelete) = {
    new Table(fields, instances, onCreateList, onUpdateList, onDeleteList :+ onDelete)
  }
  
  def on[X <: TableRowModel](instances: List[X]) = {
    new Table(fields, instances, onCreateList, onUpdateList, onDeleteList)
  }

  def columnCount = fields.size

  def columnName(index: Int) = fields(index).label()

  def rowCount = instances.size

  def valueAt(rowIndex: Int, columnIndex: Int) = {
    valueFor(modelAt(rowIndex), columnIndex)
  }

  def valueFor(model: AnyRef, columnIndex: Int) = {
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
    tableMarkupFactory.render(this)
  }

}