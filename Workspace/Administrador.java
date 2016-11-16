import java.awt.*;
import java.awt.event.*;
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
import javax.swing.JTable;
import javax.swing.JTextField;

public class Administrador extends WindowAdapter implements ActionListener {
JFrame f1,fNuevoUsu;
JButton lb1,lb2,lb3,lb4,cerrar,usumas,acept,volver,usuarioMas,addUser,dellUser;
JLabel adm,titu,Usu,contras,niv,encab;
JTextField Usua,contra;
Choice nive;
String titulo,boton,lab,preg;
boolean opt=false,users=false;
FileInputStream fis=null; 
FileOutputStream fos=null;
ObjectInputStream ois=null; 
ObjectOutputStream oos=null;
Vector <GuardaUsuario>v=new Vector<GuardaUsuario>();
String usuario,contraseña;
GuardaUsuario tmp=null; //Clase serializable
int nivel,e=0,index;
ImageIcon icon;
String[] encabezado={"ID","Usuario","Contraseña","Nivel","Calificacion"};
String [][] datos;
String pregunta[];
JTable tablausuarios;



   public Administrador(){
	   f1 = new JFrame("Administrador");
	   icon = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\icon.png")).getImage());
	   ImageIcon clo = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\cerrar.png")).getImage());
	   cerrar= new JButton(clo);
	   ImageIcon usuamas = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\ManejoUsuarios.png")).getImage());
	   usumas= new JButton(usuamas);
		ImageIcon nuevo = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\nuevoReactivo.png")).getImage());
	   lb1= new JButton(nuevo);
		ImageIcon modifi = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\modificarReactivo.png")).getImage());
	   lb2= new JButton(modifi);
		ImageIcon elimi = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\eliminarReactivo.png")).getImage());
	   lb3= new JButton(elimi);
		ImageIcon mos = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\mostrarReactivos.png")).getImage());
	   lb4= new JButton(mos);
	   ImageIcon dm = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\admin.png")).getImage());
	   adm= new JLabel(dm);
	   
	   
	   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f1.setLocation(dim.width/2-f1.getSize().width/2-300, dim.height/2-f1.getSize().height/2-300);
		
		f1.setUndecorated(true);
		
	   f1.setLayout(null);
	   
	   cerrar.setOpaque(false);
  	   cerrar.setContentAreaFilled(false);
  	   cerrar.setBorder(null);
  	   usumas.setOpaque(false);
	   usumas.setContentAreaFilled(false);
	   usumas.setBorder(null);
	   lb1.setOpaque(false);
  	   lb1.setContentAreaFilled(false);
  	   lb1.setBorder(null);
  	   lb2.setOpaque(false);
	   lb2.setContentAreaFilled(false);
	   lb2.setBorder(null);
	   lb3.setOpaque(false);
  	   lb3.setContentAreaFilled(false);
  	   lb3.setBorder(null);
  	   lb4.setOpaque(false);
	   lb4.setContentAreaFilled(false);
	   lb4.setBorder(null);
	   
	   f1.add(cerrar);
	   f1.add(usumas);
	   f1.add(adm);
	   f1.add(lb1);
	   f1.add(lb2);
	   f1.add(lb3);
	   f1.add(lb4);
	   
	   cerrar.setBounds(20,20,170,35);
	   usumas.setBounds(480,20,50,50);
	   adm.setBounds(130, 55, 290, 60);
	   lb1.setBounds(90, 140, 380,60);
	   lb2.setBounds(80, 220, 420,60);
	   lb3.setBounds(90, 300, 380,60);
	   lb4.setBounds(90, 380, 380,60);
	   
	   f1.setIconImage(icon.getImage());
	   f1.setSize(550,500);
       f1.getContentPane().setBackground(new Color(78,181,206));
	   f1.setVisible(true);
	   cerrar.addActionListener(this);
	   usumas.addActionListener(this);
	   lb1.addActionListener(this);
	   lb2.addActionListener(this);
	   lb3.addActionListener(this);	
	   lb4.addActionListener(this);
	   f1.addWindowListener(this);
   }

	public void windowClosing(WindowEvent e){
		f1.dispose();
		f1=null;
	   }
	public void nuevoUsuario(){
		fNuevoUsu = new JFrame("Nuevo Usuario");
		e=0;
		mostrarUsuarios();
	}
	public void dibujaNuevoUsuario(){
		tablausuarios= new JTable(datos,encabezado);
		tablausuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					dellUser.setEnabled(true);
					dellUser.setVisible(true);
			}
		});
		ImageIcon agregar = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\UsuarioMas.png")).getImage());
        usuarioMas= new JButton(agregar);
		ImageIcon enc = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Usuario\\encabezado.png")).getImage());
        encab = new JLabel(enc);
        ImageIcon eliminarUser = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Usuario\\dell.png")).getImage());
        dellUser = new JButton(eliminarUser);
		ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Atras.png")).getImage());
		volver= new JButton(atras);	
		ImageIcon Nuevousur = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Usuario\\NuevoUsuario.png")).getImage());
		ImageIcon usuar = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Usuario\\Usuarios.png")).getImage());
		if(users)
			titu = new JLabel(Nuevousur);
		else
			titu = new JLabel(usuar);
		ImageIcon addUs = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\usuarioMas.png")).getImage());
        addUser=new JButton(addUs);
		ImageIcon usur = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\usuario.png")).getImage());
		 Usu= new JLabel(usur);
		Usua= new JTextField();
		ImageIcon contraseña = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\contraseña.png")).getImage());
		contras= new JLabel(contraseña);
		contra= new JTextField();
		ImageIcon level = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Usuario\\nivel.png")).getImage());
		niv = new JLabel(level);
		nive= new Choice();
		ImageIcon ace = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\NuevaPregunta\\aceptar.png")).getImage());
		acept = new JButton(ace);
		nive.add("1");
		nive.add("2");
		
		
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 fNuevoUsu.setLocation(dim.width/2-fNuevoUsu.getSize().width/2-300, dim.height/2-fNuevoUsu.getSize().height/2-300);
			
		 fNuevoUsu.setUndecorated(true);
			
		 fNuevoUsu.setLayout(null);
		
		 acept.setOpaque(false);
	  	 acept.setContentAreaFilled(false);
	  	 acept.setBorder(null);
	  	 volver.setOpaque(false);
	  	 volver.setContentAreaFilled(false);
	  	 volver.setBorder(null);
	  	 addUser.setOpaque(false);
	  	 addUser.setContentAreaFilled(false);
	  	 addUser.setBorder(null);
	  	 dellUser.setOpaque(false);
	  	 dellUser.setContentAreaFilled(false);
	  	 dellUser.setBorder(null);
	  	 
	  	 titu.setVisible(false);
	  	 Usu.setVisible(false);
	  	 Usua.setVisible(false);
	  	 contras.setVisible(false);
	  	 contra.setVisible(false);
	  	 nive.setVisible(false);
	  	 acept.setVisible(false);
	  	 niv.setVisible(false);
	  	 dellUser.setVisible(false);
	  	 dellUser.setEnabled(false);
		
	  	fNuevoUsu.add(addUser);
	  	fNuevoUsu.add(dellUser);
	  	fNuevoUsu.add(volver);
	  	fNuevoUsu.add(encab);
	  	fNuevoUsu.add(tablausuarios);
		fNuevoUsu.add(titu);
		fNuevoUsu.add(Usu);
		fNuevoUsu.add(Usua);
		fNuevoUsu.add(contras);
		fNuevoUsu.add(contra);
		fNuevoUsu.add(niv);
		fNuevoUsu.add(nive);
		fNuevoUsu.add(acept);
		
		
		 volver.setBounds(10,10,100,25);
		 addUser.setBounds(500,10,50,50);
		 dellUser.setBounds(270, 420, 70, 70);
		 encab.setBounds(90, 60, 420, 50);
		 tablausuarios.setBounds(90,100,410,300);
		 titu.setBounds(170,50,200,50);
		 Usu.setBounds(150,150,120,25);
		 Usua.setBounds(300, 150, 100, 25);
		 contras.setBounds(140, 210, 150,30);
		 contra.setBounds(300, 210, 100,25);
		 niv.setBounds(150, 270, 150,25);
		 nive.setBounds(300, 270, 100,25);
		 acept.setBounds(110, 380, 340,50);
		
		 volver.addActionListener(this);
		 acept.addActionListener(this);
		 addUser.addActionListener(this);
		 dellUser.addActionListener(this);
		 fNuevoUsu.setIconImage(icon.getImage());
		 fNuevoUsu.setSize(550,500);
		 fNuevoUsu.getContentPane().setBackground(new Color(78,181,206));
		 fNuevoUsu.setVisible(true);

	}
	public void mostrarUsuarios(){
		try{
			  fis = new FileInputStream("Usuarios.dat");
			  ois = new ObjectInputStream(fis);
			  v = (Vector)ois.readObject();
			  fis.close();
		  	}catch(IOException E){ }
		  	catch(ClassNotFoundException E){ }
		datos= new String[v.size()][5];
		if(v.size()<=0){
			users=true;
			dibujaNuevoUsuario();
			tablausuarios.setVisible(false);
			encab.setVisible(false);
			addUser.setVisible(false);
			
			titu.setVisible(true);
		  	 Usu.setVisible(true);
		  	 Usua.setVisible(true);
		  	 contras.setVisible(true);
		  	 contra.setVisible(true);
		  	 nive.setVisible(true);
		  	 acept.setVisible(true);
		  	 niv.setVisible(true);
		}else{
			users=false;
			do{
				preg=null;
				pregunta=null;
				tmp=(GuardaUsuario)v.get(e);
				preg= tmp.muestra();
				pregunta=preg.split("°");
				datos[e][0]=String.valueOf(e);
				for(int columnx=0;columnx<4;columnx++){
					datos[e][columnx+1]=pregunta[columnx];
				}
				e++; 
			}while(e<v.size());
			dibujaNuevoUsuario();
		}
	}
	public void Usuarios(){
		usuario=Usua.getText();
		contraseña= contra.getText();
		nivel=nive.getSelectedIndex();
		
		v.add(new GuardaUsuario(usuario,contraseña,nivel+1,0));
		try{ 
	  		fos=new FileOutputStream("Usuarios.dat");
	  		oos=new ObjectOutputStream(fos);
	  		oos.writeObject(v); //guarda el objeto en el archivo
	  		fos.close();
	  		JOptionPane.showMessageDialog(fNuevoUsu, "Se guardo correctamente");
	  	}catch(IOException E){ }  
	  fNuevoUsu.dispose();
	  f1.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource().equals(cerrar)){
			f1.dispose();
			Acceso access = new Acceso();
			}
		else if(ev.getSource().equals(lb1)){
			f1.dispose();
			NuevaPregunta preg = new NuevaPregunta(titulo,lab);
			}
		else if(ev.getSource().equals(lb2)){
			f1.dispose();
			titulo= "Modificar";
			lab= "Modificar Pregunta";
			opt=false;
			Mostrar preg = new Mostrar(titulo,lab,opt);
		}else if(ev.getSource().equals(lb3)){
			f1.dispose();
			titulo= "Eliminar";
			lab= "Eliminar Reactivos";
			boolean del= true;
			Mostrar preg = new Mostrar(titulo,lab,del);
		}
		else if(ev.getSource().equals(lb4)){
			f1.dispose();
			titulo= "Mostrar";
			lab= "Mostrar Reactivos";
			boolean del= false;
			Mostrar preg = new Mostrar(titulo,lab,del);
		}
		else if(ev.getSource().equals(usumas)){
			f1.dispose();
			nuevoUsuario();
		}
		else if(ev.getSource().equals(acept)){
			Usuarios();
		}else if(ev.getSource().equals(volver)){
			fNuevoUsu.dispose();
			fNuevoUsu.setVisible(false);
			f1.setVisible(true);
		}else if(ev.getSource().equals(addUser)){
			tablausuarios.setVisible(false);
			encab.setVisible(false);
			addUser.setVisible(false);
			dellUser.setVisible(false);
			
			titu.setVisible(true);
		  	 Usu.setVisible(true);
		  	 Usua.setVisible(true);
		  	 contras.setVisible(true);
		  	 contra.setVisible(true);
		  	 nive.setVisible(true);
		  	 acept.setVisible(true);
		  	 niv.setVisible(true);
		}else if(ev.getSource().equals(dellUser)){
			v.remove(tablausuarios.getSelectedRow());
			try{ 
		  		fos=new FileOutputStream("Usuarios.dat");
		  		oos=new ObjectOutputStream(fos);
		  		oos.writeObject(v); //guarda el objeto en el archivo
		  		fos.close();
				JOptionPane.showMessageDialog(f1, "Se elimino correctamente");
		  	}catch(IOException E){ }  
		  	fNuevoUsu.dispose();
		  	Administrador admin= new Administrador();
		}
		
	}

}
