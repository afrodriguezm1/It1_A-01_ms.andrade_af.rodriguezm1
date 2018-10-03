package uniandes.isis2304.superAndes.interfazApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());
	
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";
	
	private JsonObject tableConfig;
	
	private SuperAndes superAndes;
	
	private JsonObject guiConfig;

	private JMenuBar menuBar;
	
	public InterfazSuperAndesApp()
	{
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);
		configurarFrame();
		if(guiConfig != null)
		{
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}
	}
	
	public JsonObject openConfig(String tipo, String archConfig)
	{
		JsonObject config = null;
		try
		{
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		}
		catch(Exception e)
		{
			log.info("No se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	public static void main(String[] args) 
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp();
			interfaz.setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace( );
		}
	}
	
	private void configurarFrame()
	{
		int alto = 0;
		int ancho = 0;
		String titulo ="";
		if (guiConfig == null)
		{
			log.info("Se aplica configuracion por defecto");
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info("Se aplica configuraciín indicada en el archivo de configuración");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho =guiConfig.get("frameW").getAsInt();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);
		
		setTitle(titulo);
		setSize(ancho, alto);
	}
	
	private void crearMenu(JsonArray jsonMenu)
	{
		try
		{
		menuBar = new JMenuBar();
		for(JsonElement men : jsonMenu)
		{
			JsonObject jom = men.getAsJsonObject();
			
			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("option");
			
			JMenu menu = new JMenu(menuTitle);
			
			for(JsonElement op : opciones)
			{
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();
				
				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);
				
				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String evento = e.getActionCommand();
		try
		{
			Method req = InterfazSuperAndesApp.class.getMethod(evento);
			req.invoke(this);
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
	}

}
