import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.*;

public class Acceso implements ActionListener{

    JFrame f;
    JButton enter,imgusr,close;
    JLabel user,contra;
    TextField us;
    JPasswordField pass;
    FileInputStream fis=null; 
    ObjectInputStream ois=null; 
    Vector <GuardaUsuario>v=new Vector<GuardaUsuario>();
    GuardaUsuario tmp=null;
    String usuario,contraseña;
    int nivel,recorrido=0;
    boolean exist=false;
    Administrador adm;
    Usuario usr;
    
	public Acceso(){
		Usuarios();
		f=new JFrame("Inicie Sesion");
	    ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\icon.png")).getImage());
		ImageIcon usr = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\user.png")).getImage());
		imgusr= new JButton(usr);  
		ImageIcon cer = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\close.png")).getImage());
		close= new JButton(cer);  
		ImageIcon usur = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\usuario.png")).getImage());
		user = new JLabel(usur);
		us = new TextField();
		ImageIcon contraseña = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\contraseña.png")).getImage());
		contra = new JLabel(contraseña);
		pass = new JPasswordField(10);
		ImageIcon btnentrar = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\btnentrar.png")).getImage());
		enter=new JButton(btnentrar);
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		f.setLocation(dim.width/2-f.getSize().width/2-300, dim.height/2-f.getSize().height/2-300);
		
 		f.setUndecorated(true);
 		
	   f.setLayout(null);
	   
	   imgusr.setOpaque(false);
  	   imgusr.setContentAreaFilled(false);
  	   imgusr.setBorder(null);
  	   close.setOpaque(false);
	   close.setContentAreaFilled(false);
	   close.setBorder(null);
	   
	   f.add(user);
	   f.add(close);
	   f.add(us);
	   f.add(imgusr);
	   f.add(contra);
	   f.add(pass);
 	   f.add(enter);

 	   

		user.setBounds(250, 125, 150,50);
		close.setBounds(495, 10, 29, 29);
		imgusr.setBounds(50,145,150,150);
		us.setBounds(260, 190, 100, 20);
		contra.setBounds(240, 200, 190, 120);
		pass.setBounds(260, 300, 100, 20);
		enter.setBounds(260, 380, 150, 26);
 	   
 	   enter.addActionListener(this);
 	   close.addActionListener(this);
 	   
 	   f.setIconImage(icon.getImage());
 	   f.setSize(550,500);
       f.getContentPane().setBackground(new Color(78,181,206));
 	   f.setVisible(true);


		
}
	public void Usuarios(){
		try{
			  fis = new FileInputStream("Usuarios.dat");
			  ois = new ObjectInputStream(fis);
			  v = (Vector)ois.readObject();
			  fis.close();
		  	}catch(IOException E){ }
		  	catch(ClassNotFoundException E){ }
	}
	
	public void evalua(String us3 , String pass){
		String contra="root";
		String pass3="1234";
		boolean error=true;
		if(us3.equals(contra) && pass.equals(pass3) ) {
			Administrador admin=new Administrador();
			f.dispose();
		}
		else{
			recorrido=0;
			System.out.println(v.size());
			do{
				 tmp = (GuardaUsuario)v.get(recorrido);
				if(us3.equals(tmp.Usuario()) && pass.equals(tmp.Contrasenia())){
					if(tmp.Nivel()==1)
						usr = new Usuario();
					else
						adm =new Administrador();
					exist=true;
					f.dispose();
					recorrido=v.size();
					}
					recorrido++;
					
			}while(recorrido<v.size());
			if(exist=false)
			 JOptionPane.showMessageDialog(f, "Contraseña incorrecta ");
			
		}
	}
	
	
	public static void main(String[] args) {
		Acceso obj = new Acceso();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(enter)){
		String us2 = (String)us.getText();
		String pas;
		pas= String.valueOf(pass.getPassword());
		evalua(us2,pas);
		}else if(e.getSource().equals(close)){
			System.exit(0);
		}
		
	}
}
