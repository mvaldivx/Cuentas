import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class Mostrar implements ActionListener, ItemListener {
JFrame f1;
JLabel titulo,lb2,lb3,lb4,lb5,pre1,rs1,rs2,rs3,corr;
JButton accept,modify,volver;
ImageIcon acept;
CheckboxGroup cbg1;
Checkbox cb1,cb2,cb3;
String preg;
String[]pregunta;
Guardar tmp=null;
FileInputStream fis=null; 
FileOutputStream fos=null;
ObjectInputStream ois=null; 
ObjectOutputStream oos=null;
Vector <Guardar>v=new Vector<Guardar>();
int e=0,opc; double d=0.0; String cad=null;
int fin;
Choice preguntan;
int react;
boolean delet;
ImageIcon tit;
	
	
	public Mostrar(String titu, String lab, Boolean eliminar){
		f1 = new JFrame(titu);
		ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Atras.png")).getImage());
		volver= new JButton(atras); 
		delet=eliminar;
		if(eliminar==true){
		      tit = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\eliminarReact.png")).getImage());
			  acept = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\aceptar.png")).getImage());

		}
		else{
			if(titu.equals("Modificar")){
				 tit = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\modifiReact.png")).getImage());
				 acept = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\aceptar.png")).getImage());
			}else{
		     tit = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\mostrarReact.png")).getImage());     
		     acept = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\modificar.png")).getImage());
			}
		}
			
		
	    modify= new JButton(acept);
	    titulo= new JLabel(tit);
		preguntan = new Choice();
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
		accept= new JButton(ace);
		pre1=new JLabel();
		rs1=new JLabel();
		rs2= new JLabel();
		rs3=new JLabel();
		
		cbg1= new CheckboxGroup();
		cb1 = new Checkbox("",false,cbg1);
		cb2 = new Checkbox("",false,cbg1);
		cb3 = new Checkbox("",false,cbg1);
		  
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		f1.setLocation(dim.width/2-f1.getSize().width/2-300, dim.height/2-f1.getSize().height/2-300);
		
 		f1.setUndecorated(true);
		f1.setLayout(null);
		
		volver.setOpaque(false);
	  	volver.setContentAreaFilled(false);
	  	volver.setBorder(null);
		   
		preguntan.add(" ");
		f1.add(volver);
		f1.add(titulo);
		f1.add(lb2);
		f1.add(preguntan);
		f1.add(pre1);
		f1.add(cb1);
		f1.add(lb3);
		f1.add(rs1);
		f1.add(cb2);
		f1.add(lb4);
		f1.add(rs2);
		f1.add(cb3);
		f1.add(lb5);
		f1.add(rs3);
		f1.add(modify);
		f1.add(accept);

		volver.setBounds(10,10,100,25);
		preguntan.setBounds(250,100,50,50);
		titulo.setBounds(110, 20, 300, 80);
		lb2.setBounds(70, 100, 140, 80);
		pre1.setBounds(260, 130, 100, 20);
		lb3.setBounds(70, 180, 180, 80);
		rs1.setBounds(260, 200, 100, 20);
		cb1.setBounds(400, 200, 100, 20);
		lb4.setBounds(70, 230, 180, 80);
		rs2.setBounds(260, 260, 100, 20);
		cb2.setBounds(400, 260, 100, 20);
		lb5.setBounds(70, 280, 180, 80);
		rs3.setBounds(260, 310, 100, 20);
		cb3.setBounds(400, 310, 100, 20);
		modify.setBounds(350,380,155,24);
	   
		f1.setIconImage(icon.getImage());
		f1.setSize(550,500);
	    f1.getContentPane().setBackground(new Color(78,181,206));
	    f1.setVisible(true);
	    accept.addActionListener(this);
	    modify.addActionListener(this);
	    preguntan.addItemListener(this);
	    volver.addActionListener(this);
	    leer();
		
	}
	
	
	
	
	public void leer(){
		
	try{
         fis = new FileInputStream("objetos.dat");
         ois = new ObjectInputStream(fis);
         v = (Vector)ois.readObject();
        fis.close();
      }catch(IOException E){ }
       catch(ClassNotFoundException E){ }  
	 do{
              tmp=(Guardar)v.get(e);
              preg=preg + tmp.muestra();
              e++; 
	  }while(e<v.size());
	  e= v.size();
	 numberOfChoice();
	}

	public void muestrar(){
		pre1.setText(pregunta[0]);
		rs1.setText(pregunta[1]);
		rs2.setText(pregunta[2]);
		rs3.setText(pregunta[3]);
		if(pregunta[4].equals("cb1"))
			cb1.setState(true);
		else if(pregunta[4].equals("cb2"))
			cb2.setState(true);
		else if(pregunta[4].equals("cb3"))
			cb3.setState(true);
	}
	public void clean(){
		pre1.setText("");
		rs1.setText("");
		rs2.setText("");
		rs3.setText("");
		cb1.setState(false);
		cb2.setState(false);
		cb3.setState(false);
	}

	public void numberOfChoice(){
		String g;
		int o=0;
		 for(int i=1; i <= e; i++){
			 o=i;
			g=String.valueOf(o);
			
			 preguntan.add(g);
		 }
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(modify)){
			if(delet){
				try{
					  fis = new FileInputStream("objetos.dat");
					  ois = new ObjectInputStream(fis);
					  v = (Vector)ois.readObject();
					  fis.close();
				  	}catch(IOException E){ }
				  	catch(ClassNotFoundException E){ }
			  
				  	v.remove(preguntan.getSelectedIndex()-1);
				    System.out.println(v.get(v.size()-1));
				  	try{ 
				  		fos=new FileOutputStream("objetos.dat");
				  		oos=new ObjectOutputStream(fos);
				  		oos.writeObject(v); //guarda el objeto en el archivo
				  		fos.close();
						JOptionPane.showMessageDialog(f1, "Se elimino correctamente");
				  	}catch(IOException E){ }  
				  	f1.dispose();
				  	Administrador admin= new Administrador();
			}else{
			String titulo= "Modificar";
			String lab= "Modificar Reactivo";
			NuevaPregunta np;
			np= new NuevaPregunta(titulo,lab,preg,react);
			f1.dispose();
			}
		}else if(e.getSource().equals(volver)){
			f1.dispose();
			Administrador admin= new Administrador();
		}
			
	}


	public void itemStateChanged(ItemEvent e) {
		preg="";
		if(preguntan.getSelectedIndex()==0)
			clean();
		else{
		tmp=(Guardar)v.get(preguntan.getSelectedIndex()-1);
        preg=preg + tmp.muestra();
    	pregunta=preg.split("°");
    	react=(preguntan.getSelectedIndex()-1);
		 muestrar();
		}
	}



}
