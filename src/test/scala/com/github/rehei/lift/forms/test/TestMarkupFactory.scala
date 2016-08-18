package com.github.rehei.lift.forms.test

import com.github.rehei.scala.forms.SubRendering
import com.github.rehei.scala.forms.markup.MarkupFactory

class TestXMarkupFactory extends MarkupFactory {
  def renderForm(subRendering: SubRendering) = {
    <div>
      { subRendering.render() }
    </div>
  }

  def renderSection(subRendering: SubRendering) = {
    <div>
      { subRendering.render() }
    </div>
  }

  def renderTextbox() = {
    <input type="text"></input>
  }

}