package controller;

import java.util.Scanner;

import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	private Modelo modelo;
	private View view;
	public final static String RUTAGEOJASON = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";
	public final static String JUEGUEMOS = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";

	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		Comparendo[] arreglo = null;

		while( !fin )
		{
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
			case 1:

				//Cargar el archivo

				modelo.leerGeoJson(JUEGUEMOS);

				view.printMessage("Archivo GeoJSon Cargado");
				view.printMessage("Numero actual de comparendos " + modelo.darTamanio() + "\n----------");

				//Primer Comparendo

				view.printMessage("La información del primer Object Id es: ");
				view.printMessage("Object Id: " + modelo.darPrimerComparendo().darObjectid());
				view.printMessage("Fecha Hora: " + modelo.darPrimerComparendo().darFecha_Hora().toString());
				view.printMessage("Infracción: " + modelo.darPrimerComparendo().darInfraccion());
				view.printMessage("Clase Vehiculo: " + modelo.darPrimerComparendo().darClase_Vehi());
				view.printMessage("Tipo Servicio: " + modelo.darPrimerComparendo().darTipo_Servicio());
				view.printMessage("Localidad: " + modelo.darPrimerComparendo().darLocalidad());
				view.printMessage("Municipio: " + modelo.darPrimerComparendo().darMunicipio() + "\n----------");

				//Último Comparendo

				view.printMessage("La información del último Object Id es: ");
				view.printMessage("Object Id: " + modelo.darUltimoComparendo().darObjectid());
				view.printMessage("Fecha Hora: " + modelo.darUltimoComparendo().darFecha_Hora().toString());
				view.printMessage("Infracción: " + modelo.darUltimoComparendo().darInfraccion());
				view.printMessage("Clase Vehiculo: " + modelo.darUltimoComparendo().darClase_Vehi());
				view.printMessage("Tipo Servicio: " + modelo.darUltimoComparendo().darTipo_Servicio());
				view.printMessage("Localidad: " + modelo.darUltimoComparendo().darLocalidad());
				view.printMessage("Municipio: " + modelo.darUltimoComparendo().darMunicipio() + "\n----------");
				
			break;

			case 5:
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opción Invalida !! \n---------");
				break;
			}
		}
		
	}	
}
