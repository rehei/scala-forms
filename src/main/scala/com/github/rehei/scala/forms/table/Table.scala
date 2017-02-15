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
    val repo: TableRepository[_ <: TableRowModel],
    val onCreateList: List[OnCreate],
    val onUpdateList: List[OnUpdate],
    val onDeleteList: List[OnDelete]) {

  protected lazy val list = repo.list()
  
  def this() = {
    this(List.empty, NullTableRepository, List.empty, List.empty, List.empty)
  }

  def attach(tableHead: TableHead) = {
    new Table(fields :+ tableHead, repo, onCreateList, onUpdateList, onDeleteList)
  }

  def action(onCreate: OnCreate) = {
    new Table(fields, repo, onCreateList :+ onCreate, onUpdateList, onDeleteList)
  }

  def action(onUpdate: OnUpdate) = {
    new Table(fields, repo, onCreateList, onUpdateList :+ onUpdate, onDeleteList)
  }
  
  def action(onDelete: OnDelete) = {
    new Table(fields, repo, onCreateList, onUpdateList, onDeleteList :+ onDelete)
  }
  
  def on(newRepo: TableRepository[_ <: TableRowModel]) = {
    new Table(fields, newRepo, onCreateList, onUpdateList, onDeleteList)
  }

  def columnCount = fields.size

  def columnName(index: Int) = fields(index).label()

  def rowCount = list.size

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
  
  def delete(modelID: String) = {
    repo.delete(modelID)
  }

  def modelAt(rowIndex: Int) = {
    list(rowIndex)
  }

  def render[X](tableMarkupFactory: AbstractTableMarkupFactory[X]) = {
    tableMarkupFactory.render(this)
  }

}