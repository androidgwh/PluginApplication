package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *
 * 密封类，更类似枚举
 * 一个抽象类
 * 密封类的所有子类要不在密封类中，要不在密封类的同一文件中
 * 密封类子类的子类可在任何位置
 * 跟when配合使用，如果覆盖了全部情况，不用加else，去掉sealed需要加else
 **/
sealed class SealedClass(val name:String) {

  class Cat(dogName:String,val eat:String):SealedClass(dogName){

  }
  fun  greetSealed(sealed:SealedClass)=when(sealed){
    is Dog ->"Hello ${sealed.name}"
    is Cat ->"Hello ${sealed.name} eat ${sealed.eat}"
  }
}
class Dog(dogName:String):SealedClass(dogName)
fun main() {
  val dog= Dog(dogName = "旺旺")
  val cat=SealedClass.Cat(dogName = "mimi",eat = "fish")
  println(dog.greetSealed(dog))
  println(cat.greetSealed(cat))
}