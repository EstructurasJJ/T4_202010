package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

import model.data_structures.MaxHeapCP;
import model.data_structures.Node;


public class Modelo 
{
	private String parteDelComparendo; 
	private Comparendo compaAgregar;
	private boolean coordenadas = false;
	
	private double minLatitud = 1000000000;
	private double minLongitud = 1000000000;
	private double maxLatitud = -1000000000;
	private double maxLongitud = -1000000000;
	
	private MaxHeapCP<Comparendo> datosHeap;
	//JUANJO A�ADA AC� SU ESTRUCTURA
	
	public Modelo()
	{
		parteDelComparendo = "";
		datosHeap = new MaxHeapCP<Comparendo>();
	}
	
	
	public MaxHeapCP<Comparendo> darDatos()
	{
		return datosHeap;
	}
	
	public int darTamanio()
	{
		return datosHeap.darTama�o();
	}
	
	public Comparendo darPrimerComparendo()
	{
		return datosHeap.darHeap()[1];
	}
	
	public Comparendo darUltimoComparendo()
	{
		return datosHeap.darHeap()[datosHeap.darTama�o()];
	}

	public double darMinLatitud()
	{
		return minLatitud;
	}
	public double darMinLongitud()
	{
		return minLongitud;
	}
	public double darMaxLatitud()
	{
		return maxLatitud;
	}
	public double darMaxLongitud()
	{
		return maxLongitud;
	}
	
	public void leerGeoJson(String pRuta) 
	{	
        JsonParser parser = new JsonParser();
        FileReader fr = null;
        
		try 
		{
			fr = new FileReader(pRuta);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);

	}
	
	private void dumpJSONElement(JsonElement elemento) 
	{
		
		
		if (elemento.isJsonObject()) 
		{
			
	        JsonObject obj = elemento.getAsJsonObject();
	        
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        
	        while (iter.hasNext()) 
	        {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            componentesDelComparendo(entrada.getKey());	            

	            dumpJSONElement(entrada.getValue());
	        }

	    }
		else if (elemento.isJsonArray()) 
		{			
	        JsonArray array = elemento.getAsJsonArray();
	        java.util.Iterator<JsonElement> iter = array.iterator();
	        
	        while (iter.hasNext()) 
	        {
	            JsonElement entrada = iter.next();
	            dumpJSONElement(entrada);
	        }

	    } 
		else if (elemento.isJsonPrimitive()) 
		{
	        JsonPrimitive valor = elemento.getAsJsonPrimitive();
	        
	        if(compaAgregar == null)
	        {
	    		compaAgregar = new Comparendo();
	        }
	        
	        if(parteDelComparendo.equals("OBJECTID"))
	        {
	        	compaAgregar.asignarObjectid(valor.getAsInt());
	        	//System.out.println(valor);
	        	parteDelComparendo = "";
	        }
	        else if (parteDelComparendo.equals("FECHA_HORA"))
			{
	        	compaAgregar.asignarFecha_Hora(valor.getAsString());
				//System.out.println(compaAgregar.darFecha_Hora().toString());
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("MEDIO_DETECCION"))
			{
				compaAgregar.asignarMedio_Dete(valor.getAsString());
				//System.out.println(valor);
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("CLASE_VEHICULO"))
			{
				compaAgregar.asignarClase_Vehi(valor.getAsString());
				//System.out.println(valor);
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("TIPO_SERVICIO"))
			{
				compaAgregar.asignarTipo_Servicio(valor.getAsString());
				//System.out.println(valor);
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("INFRACCION"))
			{
				compaAgregar.asignarInfraccion(valor.getAsString());
				//System.out.println(valor);
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("DES_INFRACCION"))
			{
				compaAgregar.asignarDes_Infrac(valor.getAsString());
				//System.out.println(valor);
				parteDelComparendo = "";
				
			}
			else if (parteDelComparendo.equals("LOCALIDAD"))
			{				
				compaAgregar.asignarLocalidad(valor.getAsString());
				//System.out.println(valor);	
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("MUNICIPIO"))
			{				
				compaAgregar.asignarMunicipio(valor.getAsString());
				//System.out.println(valor);	
				parteDelComparendo = "";
			}
			else if (parteDelComparendo.equals("coordinates"))
			{
				agregarCoordenada(valor.getAsDouble());				
			}

	    } 
		else if (elemento.isJsonNull()) 
		{
	        System.out.println("Es NULL");
	    } 
		else 
		{
	        System.out.println("Es otra cosa");
	    }
		
	}
	
	public void componentesDelComparendo(String palabra)
	{
		if (palabra.equals("OBJECTID"))
		{
			parteDelComparendo = "OBJECTID";
		}
		else if (palabra.equals("FECHA_HORA"))
		{
			parteDelComparendo = "FECHA_HORA";
		}
		else if (palabra.equals("MEDIO_DETECCION"))
		{
			parteDelComparendo = "MEDIO_DETECCION";
		}
		else if (palabra.equals("CLASE_VEHICULO"))
		{
			parteDelComparendo = "CLASE_VEHICULO";
		}
		else if (palabra.equals("TIPO_SERVICIO"))
		{
			parteDelComparendo = "TIPO_SERVICIO";
		}
		else if (palabra.equals("INFRACCION"))
		{
			parteDelComparendo = "INFRACCION";
		}
		else if (palabra.equals("DES_INFRACCION"))
		{
			parteDelComparendo = "DES_INFRACCION";
		}
		else if (palabra.equals("LOCALIDAD"))
		{
			parteDelComparendo = "LOCALIDAD";
		}
		else if (palabra.equals("MUNICIPIO"))
		{
			parteDelComparendo = "MUNICIPIO";
		}
		else if (palabra.equals("coordinates"))
		{
			parteDelComparendo = "coordinates";
		}
	}
	
	public void agregarCoordenada(double pCor)
	{
		if(coordenadas == false)
		{
			compaAgregar.asignarLongitud(pCor);
			//System.out.println("Longitud: " + pCor);
			
			if (pCor < minLongitud)
			{
				minLongitud = pCor;
			}
			else if (pCor > maxLongitud)
			{
				maxLongitud = pCor;
			}
			
			coordenadas = true;
		}

		else
		{
			compaAgregar.asignarLatitud(pCor);
			//System.out.println("Latitud: " + pCor);
			
			if (pCor < minLatitud)
			{
				minLatitud = pCor;
			}
			else if (pCor > maxLatitud)
			{
				maxLatitud = pCor;
			}
			
			//AGREGAR//
			
			coordenadas = false;
			parteDelComparendo = "";
			
			datosHeap.a�adir(compaAgregar);
			compaAgregar = null;
			
			//System.out.println("///AGREGADO///");
			
		}
	}
	
	
	//TODO TALLER 4 NUEVO
	
	public MaxHeapCP<Comparendo> generarMuestraHeap(int N)
	{
		MaxHeapCP<Comparendo> heapProv = new MaxHeapCP<Comparendo>();
		
		Comparable[] arr = datosHeap.darHeap();
		
		for(int i=1; i<=N; i++)
		{
			int random = (int) (datosHeap.darTama�o() * Math.random());
			heapProv.a�adir((Comparendo) arr[random]);
		}
		return heapProv;
	}
	
	
	
}
