import java.io.*;
public class Guardar implements Serializable{
String pre,re1,re2,re3,cor;
public Guardar(String pre,String re1,String re2,String re3,String cor){
this.pre=pre;
this.re1=re1;
this.re2=re2;
this.re3=re3;
this.cor=cor;
}
public String muestra(){
	String preg;
  preg=pre +"°"+ re1+ "°" + re2 +"°" +re3 +"°" +cor + "°";
return preg;

}
public String Pregunta(){
	return pre;
}
public String Respuesta1(){
	return re1;
}
public String Respuesta2(){
	return re2;
}
public String Respuesta3(){
	return re3;
}
public String Correcta(){
	return cor;
}
}