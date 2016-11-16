import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Usuario implements ActionListener{
JFrame f;
JLabel titulo,texto;
JButton aceptar,cerrar;

	public Usuario(){
		f= new JFrame();
	    ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\icon.png")).getImage());
	    ImageIcon titu = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\User\\titulo.png")).getImage());
        titulo= new JLabel(titu);
	    ImageIcon text = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\User\\texto.png")).getImage());
        texto = new JLabel(text);
	    ImageIcon comenzar = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\User\\comenzar.png")).getImage());
        aceptar = new JButton(comenzar);
	    ImageIcon cerrarsesion = new ImageIcon(new ImageIcon(getClass().getResource("\\Images\\Admin\\cerrar.png")).getImage());
        cerrar = new JButton(cerrarsesion);
	    
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		f.setLocation(dim.width/2-f.getSize().width/2-300, dim.height/2-f.getSize().height/2-300);
		
 		f.setUndecorated(true);
 		
	    f.setLayout(null);
	    
	    cerrar.setOpaque(false);
	  	cerrar.setContentAreaFilled(false);
	  	cerrar.setBorder(null);
	  	aceptar.setOpaque(false);
	  	aceptar.setContentAreaFilled(false);
	  	aceptar.setBorder(null);
		
	    f.add(cerrar);
	    f.add(titulo);
	    f.add(texto);
	    f.add(aceptar);
	    
	    cerrar.setBounds(20,20,170,35);
	    titulo.setBounds(100,80,400,45);
	    texto.setBounds(150, 130, 300, 300);
	    aceptar.setBounds(350,430,155,24);
		
		
	    cerrar.addActionListener(this);
	    aceptar.addActionListener(this);
		f.setIconImage(icon.getImage());
	 	f.setSize(550,500);
	    f.getContentPane().setBackground(new Color(78,181,206));
	 	f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cerrar)){
			f.dispose();
			Acceso access= new Acceso();
		}else if(e.getSource().equals(aceptar)){
			f.dispose();
			Exam ex= new Exam();
		}
		
	}
}
