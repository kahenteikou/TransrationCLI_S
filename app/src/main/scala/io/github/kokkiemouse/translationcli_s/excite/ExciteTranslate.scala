package io.github.kokkiemouse.translationcli_s.excite

import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.{URL, URLConnection}
import java.util.regex.{Matcher, Pattern}

class ExciteTranslate(direct:String){
  private var direction:String=direct;
  def this(){
    this("ENJA");
  }
  def getTranslatedText(source:String):String={
    var result_text:String=null
    try{
      var Excite:URL=new URL("https://www.excite.co.jp/world/english/")
      var connection:URLConnection=Excite.openConnection()
      var prntout=new PrintWriter(connection.getOutputStream);
      prntout.print("before={" + source + "}&wb_lp={" + direction + "}")
      prntout.close()
      var brin=new BufferedReader(new InputStreamReader(connection.getInputStream))
      var regex= "<textarea &#91;^>].*after.*>(.*)"
      val patternbuf = Pattern.compile(regex)
      brin.lines().forEach(line => {
        var matches=patternbuf.matcher(line)
        if(matches.matches()){
          result_text=matches.group(1)
        }
      })
      brin.close()
    }catch{
      case e:Exception => println(e)
    }
    return result_text
  }


}
