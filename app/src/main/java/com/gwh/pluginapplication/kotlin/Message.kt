package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *
 * 数据类型对于属于同一超类型的多个数据类之前如何共享属性、方法
 **/
abstract class Message {
  abstract var action:String
}

data class PingMsg(override var action:String="ping"):Message(){

}
data class PingMsg1(override var action:String="ping",val body:Map<String,String>):Message(){

}

fun main() {
println(PingMsg())

  val map= mutableMapOf<String, String>()
  map.put("param","aaa")
  map.put("param","bbb")
  println(PingMsg1(body = map))
}