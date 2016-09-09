package com.github.rehei.scala.forms.binding

import scala.collection.JavaConversions._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Renderable
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil

class InlineBinding(form: Form) extends AbstractBinding {

  def bind[T](context: Field, model: AnyRef, markupFactory: MarkupFactory[T]) = {
    val collection = context.getter(model).asInstanceOf[java.util.Collection[AnyRef]]

    def insertFunc = {
      val value = ReflectUtil.create(getSubModelClazz(context)).asInstanceOf[AnyRef]
      collection.add(value)
      renderInlineElement(markupFactory, value, () => removeFunc(value))
    }

    def removeFunc(value: AnyRef) = {
      collection.remove(value)
    }

    renderInlineFrame(markupFactory, collection, insertFunc _, removeFunc _)
  }

  def renderInlineFrame[T](markupFactory: MarkupFactory[T], collection: java.util.Collection[AnyRef], addFunc: () => T, removeFunc: (AnyRef) => Unit) = {
    markupFactory.renderInlineFrame(
      markupFactory.reduce(collection.map(value => renderInlineElement(markupFactory, value, () => removeFunc(value)))),
      markupFactory.renderInsertButton(addFunc))
  }

  def renderInlineElement[T](markupFactory: MarkupFactory[T], value: AnyRef, removeFunc: () => Unit) = {
    markupFactory.renderInlineElement(form.render(value, markupFactory), markupFactory.renderRemoveButton(removeFunc))
  }

  def getSubModelClazz(context: Field) = {
    context.getFirstParameterizedTypeArgument()
  }

}