package com.github.rehei.scala.forms.table

import scala.xml.NodeSeq

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.table.action.OnEntity
import com.github.rehei.scala.forms.table.action.OnEntity
import com.github.rehei.scala.forms.table.action.OnInstance
import scala.collection.mutable.ListBuffer

object Table extends Table()

class Table protected (
    val fields: List[TableHead],
    val repo: TableRepository[_ <: TableRowModel],
    val entityActionList: List[OnEntity],
    val instanceActionList: List[OnInstance],
    val listeners: ListBuffer[TableChangedListener]) {

  protected lazy val list = repo.list()

  def this() = {
    this(List.empty, NullTableRepository, List.empty, List.empty, ListBuffer())
  }

  def attach(tableHead: TableHead) = {
    new Table(fields :+ tableHead, repo, entityActionList, instanceActionList, listeners)
  }

  def action(onCreate: OnEntity) = {
    new Table(fields, repo, entityActionList :+ onCreate, instanceActionList, listeners)
  }

  def action(onDelete: OnInstance) = {
    new Table(fields, repo, entityActionList, instanceActionList :+ onDelete, listeners)
  }

  def on(newRepo: TableRepository[_ <: TableRowModel]) = {
    new Table(fields, newRepo, entityActionList, instanceActionList, listeners)
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

  def save(model: TableRowModel) = {
    val id = model.identify()
    model.save()
    listeners.foreach(_.changed(model))
  }

  def delete(model: TableRowModel) = {
    val id = model.identify()
    model.delete()
    listeners.foreach(_.removed(model))
  }

  def modelAt(rowIndex: Int) = {
    list(rowIndex)
  }

  def render[X](tableMarkupFactory: AbstractTableMarkupFactory[X]) = {
    tableMarkupFactory.render(this)
  }

  def addTableChangedListener(listener: TableChangedListener) = {
    listeners.append(listener)
  }

}