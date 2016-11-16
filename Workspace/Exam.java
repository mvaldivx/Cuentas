import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Exam implements ActionListener{
JFrame f;
JLabel pregunta,pregu,res1,re1,res2,re2,res3,re3;
JButton next;
CheckboxGroup cbg1;
Checkbox cb1,cb2,cb3;
FileInputStream fis=null; 
FileOutputStream fos=null;
ObjectInputStream ois=null; 
ObjectOutputStream oos=null;
Vector <Guardar>v=new Vector<Guardar>();
int e=0,rndm,contador,aux=0;
int[] numero=null;
Guardar tmp=null;
String preg;


	public Exam(){
		leer();
		f= new JFrame();
		ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\icon.png")).getImage());
		ImageIcon preg = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\pregunta.png")).getImage());
        pregunta= new JLabel(preg);
        pregu= new JLabel();
        ImageIcon resp1 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta1.png")).getImage());
        res1= new JLabel(resp1);
        re1= new JLabel();  
        ImageIcon resp2 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta2.png")).getImage());
        res2= new JLabel(resp2);
        re2= new JLabel(); 
        ImageIcon resp3 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta3.png")).getImage());
        res3= new JLabel(resp3);
        re3= new JLabel(); 
        cbg1= new CheckboxGroup();
		cb1 = new Checkbox("",false,cbg1);
		cb2 = new Checkbox("",false,cbg1);
		cb3 = new Checkbox("",false,cbg1);
		next = new JButton("Siguiente");
        
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		f.setLocation(dim.width/2-f.getSize().width/2-300, dim.height/2-f.getSize().height/2-300);
		
 		f.setUndecorated(true);
 		
	    f.setLayout(null);
	    
	    f.add(pregunta);
	    f.add(pregu);
	    f.add(res1);
	    f.add(re1);
	    f.add(cb1);
	    f.add(res2);
	    f.add(re2);
	    f.add(cb2);
	    f.add(res3);
	    f.add(re3);
	    f.add(cb3);
	    f.add(next);
	    
	    pregunta.setBounds(100, 100, 130, 30);
	    pregu.setBounds(240, 100, 150, 30);
	    res1.setBounds(100, 200, 150, 30);
	    re1.setBounds(270, 200, 100, 30);
	    cb1.setBounds(350, 200, 10, 10);
	    res2.setBounds(100, 300, 150, 30);
	    re2.setBounds(270, 300, 100, 30);
	    cb2.setBounds(350, 300, 10, 10);
	    res3.setBounds(100, 400, 150, 30);
	    re3.setBounds(270, 400, 100, 30);
	    cb3.setBounds(350, 400, 10, 10);
	    next.setBounds(250,450,50,50);
	    
	    next.addActionListener(this);
	    f.setIconImage(icon.getImage());
	 	f.setSize(550,500);
	    f.getContentPane().setBackground(new Color(78,181,206));
	 	f.setVisible(true);
	 	recuperaPreguntas();
	 	getPregunta();
	}
	public void getPregunta(){
		pregu.setText(tmp.Pregunta());
		re1.setText(tmp.Respuesta1());
		re2.setText(tmp.Respuesta2());
		re3.setText(tmp.Respuesta3());
	}
	public void iguales(){
		do{
			rndm = 1 + (int)(Math.random() * v.size()); 

		}while(numero[aux]== rndm);
	}
	public void recuperaPreguntas(){
		if(e<v.size()){
		aux=0;
		rndm = 1 + (int)(Math.random() * v.size()); 
		System.out.println(rndm);
		if(e>0){
			
		do{
			if( numero[aux]== rndm){
					iguales();
					aux=0;
								
			}
			else
				aux=v.size()+1;

			
		}while(aux <= v.size());
		}     
		numero[e]=rndm;
		tmp=(Guardar)v.get(rndm-1);
	    preg=preg + tmp.muestra();
	    e++;
		}else{
	  		JOptionPane.showMessageDialog(f, "Calificacion: ");
	  		f.dispose();
	  		Usuario usr= new Usuario();
		}
	}
	public void leer(){
		try{
	         fis = new FileInputStream("objetos.dat");
	         ois = new ObjectInputStream(fis);
	         v = (Vector)ois.readObject();
	        fis.close();
	      }catch(IOException E){ }
	       catch(ClassNotFoundException E){ }  
		numero= new int[v.size()];
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		recuperaPreguntas();
		getPregunta();
		
	}
}
