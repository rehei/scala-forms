package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.binding.AbstractBinding
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.FieldDecorator
import com.github.rehei.scala.forms.decorators.LabelDecorator

class Field protected (val modelClazz: Class[_],
                       val query: String,
                       val binding: AbstractBinding,
                       private val decorators: Map[Type, FieldDecorator]) extends Renderable {

  def this(modelClazz: Class[_], query: String, binding: AbstractBinding) = {
    this(modelClazz, query, binding, Map[Type, FieldDecorator]())
  }

  private val rawMethod = ReflectUtil.getGetterMethod(modelClazz, query)
  val getter = (model: AnyRef) => ReflectUtil.get(model, query)
  val setter = (model: AnyRef, value: Any) => {

    val postProcessedValue = {
      if (value != null && value.isInstanceOf[String]) {
        value.asInstanceOf[String].trim()
      } else {
        value
      }
    }

    ReflectUtil.set(model, query, postProcessedValue)
  }

  def label() = {
    getDecorator[LabelDecorator].map(_.label)
  }
  
  def label(label: String) = {
    this.decorateWith(LabelDecorator(label))
  }
  
  def decorateWith[T <: FieldDecorator](decorator: T)(implicit tag: TypeTag[T]): Field = {
    val keyValue = (tag.tpe, decorator)
    val newDecorators = decorators + keyValue
    new Field(modelClazz, query, binding, newDecorators)
  }

  def getDecorator[T <: FieldDecorator](implicit tag: TypeTag[T]): Option[T] = {
    decorators.get(tag.tpe).map { _.asInstanceOf[T] }
  }

  def render[T](model: AnyRef, markupFactory: MarkupFactory[T]) = {
    binding.bind(this, model, markupFactory)
  }

  def getFirstParameterizedTypeArgument(): Class[_] = {
    ReflectUtil.getFirstParameterizedTypeArgument(rawMethod)
  }

}
  