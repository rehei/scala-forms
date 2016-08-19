package com.github.rehei.scala.forms.binding

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.Field
import scala.collection.JavaConversions._
import com.github.rehei.scala.forms.SubRendering
import com.github.rehei.scala.forms.Form
import scala.collection.mutable.ListBuffer
import com.github.rehei.scala.forms.Renderable
import com.github.rehei.scala.forms.model.MyInlineBinding

class InlineBinding(form: Form) extends AbstractBinding {

  def bind[T](context: Field, model: AnyRef, markupFactory: MarkupFactory[T]) = {

    val collection = context.getter(model).asInstanceOf[java.util.Collection[AnyRef]]

    val buffer = ListBuffer[T]()
    for (x <- collection) {
      buffer.append(form.render(x, markupFactory))
    }

    markupFactory.renderInline(buffer.toList)
  }

}