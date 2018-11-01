package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelDatos extends JPanel
{
	private JTextArea textArea;
	
	public PanelDatos(InterfazSuperAndesApp interfa)
	{
		setSize(interfa.getWidth()/2, interfa.getHeight()/2);
		setBorder ( new TitledBorder("Panel de información"));
		setLayout( new BorderLayout());
		
		textArea = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
		textArea.setEditable(false);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void actualizarInterfaz(String texto)
	{
		textArea.setText(texto);
	}
}
