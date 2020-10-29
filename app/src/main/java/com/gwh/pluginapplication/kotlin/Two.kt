package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *
 **/
class Two {

  /**
   * kotlin没有static，在kotlin中也不能拥有静态属性和静态方法，使用伴生对象是
   * 解决这个问题的方法之一。
   *
   * 伴生对象
   * 伴生对象的成员类似java的静态成员
   * 可通过类名.方法名调用伴生对象中的方法
   * 在方法上添加    @JvmStatic
  后在java可以调用kotlin伴生对象的方法
   在public修饰的字段添加@JvnField可在jiava中调用kotlin伴生对象中的属性
   */
  companion object{
    @JvmField
    public  var name="tony"
    private var marks="A"
    @JvmStatic
    fun printMarks()="the ${name}'s mark is ${marks}"
    @JvmStatic
    fun changeMarks(marks:String){
      this.marks = marks
    }
    @JvmStatic
    fun changeMarks(name:String="默认值",marks:String){
      this.name=name
      this.marks = marks
    }
  }
}

fun main() {
  Two.changeMarks(marks = "A++")
  println(Two.printMarks())
}