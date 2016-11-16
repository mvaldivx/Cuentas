import java.awt.*;
import java.io.*;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.*;

public class NuevaPregunta extends WindowAdapter implements ItemListener, ActionListener{
JFrame f;
JLabel lb2,lb3,lb4,lb5,titulo;
TextField tf1,tf2,tf3,tf4;
CheckboxGroup cbg1;
Checkbox cb1,cb2,cb3;
boolean modifica=false;
JButton agregar,volver;

FileInputStream fis=null; FileOutputStream fos=null;
ObjectInputStream ois=null; ObjectOutputStream oos=null;
Guardar tmp=null; //Clase serializable
Vector <Guardar>v=new Vector<Guardar>();
String pre=null,re1=null,re2=null,re3=null,cor=null;
int reactivo;

	public NuevaPregunta(String tit, String labe){
		f= new JFrame(tit);
		ImageIcon nuevoR = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\nuevoReact.png")).getImage());
		titulo= new JLabel(nuevoR);
		cbg1=new CheckboxGroup();
		cb1 = new Checkbox("",false,cbg1);
		cb2 = new Checkbox("",false,cbg1);
		cb3 = new Checkbox("",false,cbg1);
		
		tf1=new TextField();	
		tf2=new TextField();
		tf3=new TextField();	
		tf4=new TextField();
		
		dibujar();
		
		

	}
	public NuevaPregunta(String tit,String lab, String preg,int react){
		modifica=true;
		reactivo=react;
		String pregunta[];
		pregunta=preg.split("°");
		f= new JFrame(tit);
		ImageIcon modifiR = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\modifiReact.png")).getImage());
		titulo= new JLabel(modifiR);
		cbg1=new CheckboxGroup();
		cb1 = new Checkbox("",false,cbg1);
		cb2 = new Checkbox("",false,cbg1);
		cb3 = new Checkbox("",false,cbg1);
		
		
		tf1=new TextField(pregunta[0]);
		tf2=new TextField(pregunta[1]);
		tf3=new TextField(pregunta[2]);
		tf4=new TextField(pregunta[3]);
		if(pregunta[4].equals("cb1"))
			cb1.setState(true);
		else if(pregunta[4].equals("cb2"))
			cb2.setState(true);
		else if(pregunta[4].equals("cb3"))
			cb3.setState(true);
		
		dibujar();
	}
	
	public void dibujar(){
		ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Atras.png")).getImage());
		volver= new JButton(atras); 
	    ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\icon.png")).getImage());
		ImageIcon preg = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\pregunta.png")).getImage());
		lb2= new JLabel(preg);	
		ImageIcon res1 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta1.png")).getImage());
		lb3= new JLabel(res1);
		ImageIcon res2 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta2.png")).getImage());
		lb4= new JLabel(res2);
		ImageIcon res3 = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\respuesta3.png")).getImage());
		lb5= new JLabel(res3);
		ImageIcon ace = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\aceptar.png")).getImage());
		agregar= new JButton(ace);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		f.setLocation(dim.width/2-f.getSize().width/2-300, dim.height/2-f.getSize().height/2-300);
		
 		f.setUndecorated(true);
		
		f.setLayout(null);
		

	  	volver.setOpaque(false);
	  	volver.setContentAreaFilled(false);
	  	volver.setBorder(null);
		
	  	f.add(volver);
		f.add(titulo);
		f.add(lb2);
		f.add(lb3);
		f.add(lb4);
		f.add(lb5);
		f.add(tf1);
		f.add(tf2);
		f.add(cb1);
		f.add(tf3);
		f.add(cb2);
		f.add(tf4);
		f.add(cb3);
		f.add(agregar);
		
	   volver.setBounds(10,10,100,25);
	   titulo.setBounds(110, 20, 300, 80);
	   lb2.setBounds(73, 100, 140, 80);
	   tf1.setBounds(260, 130, 100, 20);
	   lb3.setBounds(70, 180, 180, 80);
	   tf2.setBounds(260, 200, 100, 20);
	   cb1.setBounds(400, 200, 100, 20);
	   lb4.setBounds(70, 230, 180, 80);
	   tf3.setBounds(260, 260, 100, 20);
	   cb2.setBounds(400, 260, 100, 20);
	   lb5.setBounds(70, 280, 180, 80);
	   tf4.setBounds(260, 310, 100, 20);
	   cb3.setBounds(400, 310, 100, 20);
	   agregar.setBounds(350,380,155,24);
		   
	   f.setIconImage(icon.getImage());
	   f.setSize(550,500);
       f.getContentPane().setBackground(new Color(78,181,206));
       f.setVisible(true);
       
       volver.addActionListener(this);
	   f.addWindowListener(this);
	   agregar.addActionListener(this);
		
		
	}
	
	
	public void windowClosing(WindowEvent e){
	     f.dispose();
	     f=null;
	   } 
	
	   public void itemStateChanged(ItemEvent e){
	   }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(agregar)){
		  if(modifica==false){		  
		  
			  pre=tf1.getText();
			  re1=tf2.getText();
			  re2=tf3.getText();
			  re3=tf4.getText();
			  if(cb1.getState())
				  cor="cb1";
			  else if (cb2.getState())
				  cor="cb2";
			  else if (cb3.getState())
				  cor="cb3";
			  try{
				  fis = new FileInputStream("objetos.dat");
				  ois = new ObjectInputStream(fis);
				  v = (Vector)ois.readObject();
				  fis.close();
			  	}catch(IOException E){ }
			  	catch(ClassNotFoundException E){ }
		  
			  	v.add(new Guardar(pre,re1,re2,re3,cor));
			  	try{ 
			  		fos=new FileOutputStream("objetos.dat");
			  		oos=new ObjectOutputStream(fos);
			  		oos.writeObject(v); //guarda el objeto en el archivo
			  		fos.close();
			  		JOptionPane.showMessageDialog(f, "Se guardo correctamente");
			  	}catch(IOException E){ }  
			  	f.dispose();
		  }else{
			  pre=tf1.getText();
			  re1=tf2.getText();
			  re2=tf3.getText();
			  re3=tf4.getText();
			  if(cb1.getState())
				  cor="cb1";
			  else if (cb2.getState())
				  cor="cb2";
			  else if (cb3.getState())
				  cor="cb3";
			  try{
				  fis = new FileInputStream("objetos.dat");
				  ois = new ObjectInputStream(fis);
				  v = (Vector)ois.readObject();
				  fis.close();
			  	}catch(IOException E){ }
			  	catch(ClassNotFoundException E){ }
		  
			  	v.set(reactivo,new Guardar(pre,re1,re2,re3,cor));
			  	try{ 
			  		fos=new FileOutputStream("objetos.dat");
			  		oos=new ObjectOutputStream(fos);
			  		oos.writeObject(v); //guarda el objeto en el archivo
			  		fos.close();
			  		JOptionPane.showMessageDialog(f, "Se modifico correctamente");
		  }catch(IOException E){ }  
			  	f.dispose();
		  
		  
		
		  }
		  Administrador admin=new Administrador();
		  
		}else if(e.getSource().equals(volver)){
			f.dispose();
			f.setVisible(false);
			Administrador admin=new Administrador();
		}
	}
}
