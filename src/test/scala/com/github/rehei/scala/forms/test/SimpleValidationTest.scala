package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.test.model.Company
import org.junit.Test
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.macros.Reflection
import com.github.rehei.scala.forms.util.Conversions._
import com.github.rehei.scala.forms.rules.IsTrue
import com.github.rehei.scala.forms.rules.MinLength
import com.github.rehei.scala.forms.rules.MaxLength
import org.junit.Assert
import org.junit.Assert._

class SimpleValidationTest {

  @Test
  def test() {

    val company = new Reflection[Company]
    val employee = new Reflection[Employee]

    val model = new Company()
    model.employees.add(new Employee())
    model.employees.add(new Employee())

    model.name = "123"

    val validator = {
      Validator
        .attach(
          company(_.name)
            .assert(MinLength(5).message("Too short"))
            .assert(MaxLength(10).message("Too long")))
    }

    val results = validator.validate(model)

    val v0 = results(0)
    val v1 = results(1)

    assertEquals(v0.isValid, false)
    assertEquals(v0.ruleName, classOf[MinLength].getCanonicalName)

    assertEquals(v1.isValid, true)
    assertEquals(v1.ruleName, classOf[MaxLength].getCanonicalName)

    println(v0)
    println(v1)
  }

}