import java.io.Serializable;

public class GuardaUsuario implements Serializable{
	String usu,contra;
	int nivel,calificacion;
	public GuardaUsuario(String usu,String contra,int nivel,int calificacion){
	this.usu=usu;
	this.contra=contra;
	this.nivel=nivel;
	this.calificacion=calificacion;
	}
	public String Usuario(){
		return usu;
	}
	public String Contrasenia(){
		return contra;
	}
	public int Nivel(){
		return nivel;
	}
	public int Calificacion(){
		return calificacion;
	}
	public String muestra(){
		String preg;
	  preg=usu+"°"+ contra+ "°" + nivel+"°"+ calificacion+"°";
	return preg;

	}
	
}
