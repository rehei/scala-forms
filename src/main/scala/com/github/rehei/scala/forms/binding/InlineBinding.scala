package com.github.rehei.scala.forms.binding

import scala.collection.JavaConversions._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Renderable
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.markup.AbstractInlineMarkupFactory

class InlineBinding(val form: Form) extends AbstractBinding {

  override def bind[T](context: Bindable, model: AnyRef, markupFactory: MarkupFactory[T]) = {
    val collection = context.getter(model).asInstanceOf[java.util.Collection[AnyRef]]
    val inlineMarkupFactory = markupFactory.getInlineMarkupFactory()
    
    def insertFunc = {
      val value = ReflectUtil.create(getSubModelClazz(context)).asInstanceOf[AnyRef]
      collection.add(value)
      renderInlineElement(markupFactory, inlineMarkupFactory, value, () => removeFunc(value))
    }

    def removeFunc(value: AnyRef) = {
      collection.remove(value)
    }

    renderInlineFrame(markupFactory, inlineMarkupFactory, collection, insertFunc _, removeFunc _)
  }

  def renderInlineFrame[T](markupFactory: MarkupFactory[T], inlineMarkupFactory: AbstractInlineMarkupFactory[T], collection: java.util.Collection[AnyRef], addFunc: () => T, removeFunc: (AnyRef) => Unit) = {
    
    inlineMarkupFactory.renderInlineFrame(
      markupFactory.reduce(collection.map
          (value => renderInlineElement(markupFactory, inlineMarkupFactory, value, () => removeFunc(value)))),
      inlineMarkupFactory.renderInsertButton(addFunc))
  }

  def renderInlineElement[T](markupFactory: MarkupFactory[T], inlineMarkupFactory: AbstractInlineMarkupFactory[T], value: AnyRef, removeFunc: () => Unit) = {
    inlineMarkupFactory.renderInlineElement(form.render(value, null, markupFactory, () => { println("test") }), inlineMarkupFactory.renderRemoveButton(removeFunc))
  }

  def getSubModelClazz(context: Bindable) = {
    context.getFirstParameterizedTypeArgument()
  }

}