package com.proyecto.dev.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.dev.Vo.AlumnosAge;
import com.proyecto.dev.dto.AlumnoDto;
import com.proyecto.dev.dto.EstadoDto;
import com.proyecto.dev.repository.IAlumnoRepository;
import com.proyecto.dev.services.IAlumnoService;
import com.proyecto.dev.utils.Utilils;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	private static final Logger logger = LogManager.getLogger(AlumnoServiceImpl.class);

	@Autowired
	IAlumnoRepository iAlumnoRepository;

	@Autowired
	Utilils utilidad;

	@Autowired
	EstadoDto gestor;

	@Override
	public AlumnoDto getAlumnoById(Long id) {
		return (id != null) ? iAlumnoRepository.findById(id).orElse(null) : null;
	}

	@Override
	public List<AlumnoDto> getListAlumno() {
		return iAlumnoRepository.findAll();
	}

	@Override
	public AlumnoDto updateAlumno(Long id, AlumnoDto alumno) {
		AlumnoDto alumnoResponse = new AlumnoDto();
		try {
			alumnoResponse = iAlumnoRepository.findById(id).orElse(null);
			if (alumnoResponse != null) {
				alumnoResponse.setApellido(alumno.getApellido());
				alumnoResponse.setNombre(alumno.getNombre());
				alumnoResponse.setFechaNacimiento(alumno.getFechaNacimiento());
				alumnoResponse.setEstado(alumno.getEstado());
				return iAlumnoRepository.save(alumnoResponse);
			}
		} catch (Exception e) {
			logger.error("Se ha presentado un error al actualizar alumno " + e.getMessage());
		}
		return null;
	}

	public AlumnoDto saveAlumno(AlumnoDto alumno) {
		try {
			if (alumno.getId() == 0) {
				return iAlumnoRepository.save(alumno);
			}
		} catch (Exception e) {
			logger.error("Se ha presentado un error al guardar alumno " + e.getMessage());
		}
		return null;
	}

	public List<AlumnosAge> getListAlumnoAge() {
		List<AlumnoDto> lista = iAlumnoRepository.findAll();
		return lista.stream().map(alumno -> {
			return new AlumnosAge(alumno.getNombre(), alumno.getApellido(),
					utilidad.calculateAge(alumno.getFechaNacimiento()));
		}).collect(Collectors.toList());
	}

	public String getCadena() {
		int[] numeros = { 2, 3, 4, 5, 6, 7, 8, 9 };
		int salidas = Arrays.stream(numeros).reduce(0, (a, b) -> a + b);
		return "" + salidas;
	}

	public List<Integer> getOrdenar() {
		Integer[] numeros = { 4, 5, 4, 10, 6, 2, 8, 7 };

		List<Integer> salidas = Arrays.stream(numeros).sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
		return salidas;
	}

	public String salida = " ";

	public String convertir() {
		salida = "";
		getOrdenar().forEach(x -> {
			salida += x + " ";
		});
		return salida;
	}

	public Integer temporal;

	public static int contarRepeticiones(int[] lista, int numeroBuscado) {
		int contador = 0;
		for (int numero : lista) {
			if (numero == numeroBuscado) {
				contador++;
			}
		}
		return contador;
	}

	public static int contarRepeticionesNoConsecutivas(int[] lista, int numeroBuscado) {
		int contador = 0;
		for (int numero : lista) {
			if (numero == numeroBuscado) {
				contador++;
			} else {
				return contador;
			}
		}
		return contador;
	}

	boolean controlCambioNumero = true;
	Integer cantidadNumeroIguales = 0;

	public String contar(int[] listaNumeros) {
		boolean numeroRepetido = false;
		for (int i = 0; i < listaNumeros.length; i++) {
			cantidadNumeroIguales = contarRepeticiones(listaNumeros, listaNumeros[i]);
			String asteriscos = asterisco(cantidadNumeroIguales);
			int elementoEvaluado;
			if (i >= 1) {
				elementoEvaluado = listaNumeros[i - 1];
				numeroRepetido = elementoEvaluado == listaNumeros[i] ? true : false;
			} else {
				elementoEvaluado = listaNumeros[i];
			}

			if (cantidadNumeroIguales > 1 && numeroRepetido) {
				if (controlCambioNumero) {
					controlCambioNumero = false;
				}
			} else {
				System.out.println(listaNumeros[i] + ": " + asteriscos);
				controlCambioNumero = true;
			}
		}
		return "Salidas";
	}

	public String mensaje() {
		return utilidad.Mensaje();
	}

	public String salidaOtra() {
		return gestor.getNombreEstado();
	}

	public String getFizzBuzz() {
		List<String> listaNumero = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				listaNumero.add("Fizz-Buzz");
			} else if (i % 3 == 0) {
				listaNumero.add("Fizz");
			} else if (i % 5 == 0) {
				listaNumero.add("Buzz");
			} else {
				listaNumero.add(i + "");
			}
		}
		return listaNumero.toString();
	}

	public static int[] copiarArreglo(int[] arregloOriginal) {
		int tamano = arregloOriginal.length;
		int[] resultado = new int[tamano];
		for (int i = 0; i < tamano; i++) {
			resultado[i] = arregloOriginal[i];
		}
		return resultado;
	}

	public static int[] ordenarAscendente(int[] array) {
		int n = array.length;
		int[] resultado = copiarArreglo(array);

		for (int i = 0; i < n - 1; i++) {
			int indiceMinimo = i;
			for (int j = i + 1; j < n; j++) {
				if (resultado[j] < resultado[indiceMinimo]) {
					indiceMinimo = j;
				}
			}
			int temp = resultado[indiceMinimo];
			resultado[indiceMinimo] = resultado[i];
			resultado[i] = temp;
		}
		return resultado;
	}

	public static int[] ordenarDescendente(int[] array) {
		int n = array.length;
		int[] resultado = copiarArreglo(array);

		for (int i = 0; i < n - 1; i++) {
			int indiceMaximo = i;
			for (int j = i + 1; j < n; j++) {
				if (resultado[j] > resultado[indiceMaximo]) {
					indiceMaximo = j;
				}
			}
			int temp = resultado[indiceMaximo];
			resultado[indiceMaximo] = resultado[i];
			resultado[i] = temp;
		}
		return resultado;
	}

	public int[] getIntegration() {
		int[] array = { 5, 3, 1, 5, 2, 5, 2, 1, 1, 1, 1, 3, 1, 5, 2, 4, 0, 72, 0 };
		int[] salida = ordenarDescendente(array);
		contar(salida);
		return salida;
	}

	public String asterisco(int cantidad) {
		String asterisco = "";
		for (int i = 1; i <= cantidad; i++) {
			asterisco = asterisco + "*";
		}
		return asterisco;
	}

	public AlumnoDto getNombre(String nombre) {
		AlumnoDto alumno = iAlumnoRepository.findByNombreNativo(nombre);
		return alumno;
	}

	public String getExecpcion() {

		/*
		 * try { int a = 5; int b = 0; int c = 5 / 0; return "hello " + c; } catch
		 * (ArithmeticException e) { System.out.println(e.getMessage()); return
		 * e.getMessage(); }
		 */

		/*
		 * try { String[] myString = { "j", "a", "i", "r", "o" }; String salida =
		 * myString[10]; return salida; } catch (ArrayIndexOutOfBoundsException e) {
		 * return e.getMessage(); }
		 */

		/*
		 * try { A objA = new A(); B objB = (B) new A(); return "hello"; } catch
		 * (Exception e) { return e.getMessage(); }
		 */
		/*
		 * try { String salida = validarDato(-1); return salida; } catch (Exception e) {
		 * return e.getMessage(); }
		 */

		try {
			int num = Integer.parseInt("12,389");
			System.out.println(num);
			return "salida: " + num;
		} catch (Exception e) {
			System.out.println(e);
			return "Se ha presentado un error: " + e.getMessage();
		}

	}

	public static String validarDato(int dato) {
		if (dato < 0) {
			throw new IllegalArgumentException("¡No se permiten valores negativos!");
		}
		return "hola" + dato;
	}

	public static void funcional(int[] listadoNumeros) {
		int numeroInicial = listadoNumeros[0];
		int repeticiones = 1;
		int numeroRepeticiones = listadoNumeros[0];
		int contadorRepeticiones = 1;

		for (int i = 1; i < listadoNumeros.length; i++) {
			if (listadoNumeros[i] == listadoNumeros[i - 1]) {
				repeticiones++;
				if (repeticiones > contadorRepeticiones) {
					contadorRepeticiones = repeticiones;
					numeroRepeticiones = numeroInicial;
				}
			} else {
				repeticiones = 1;
				numeroInicial = listadoNumeros[i];
			}
		}

		System.out.println("Numero repeticiones: " + numeroRepeticiones);
		System.out.println("Repeticiones: " + contadorRepeticiones);
	}

	public static String integracion2() {
		int[] numeros = { 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 8, 9, 7, 8, 7, 7, 7, 7 };
		funcional(numeros);
		int sa = 10 % 4;
		return "integracion" + sa;
	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	class A {
		public void display() {
			System.out.println("Esta es la clase A");
		}
	}

	class B extends A {
		public void display() {
			System.out.println("Esta es la clase B, que es una subclase de A");
		}
	}

	class C extends B {
		public void display() {
			System.out.println("Esta es la clase C que es una subclase de B");
		}
	}

	public List<AlumnoDto> getAlumnoEstado(Long idEstado) {
		EstadoDto estado = new EstadoDto();
		estado.setId(idEstado);
		return iAlumnoRepository.findByEstado(estado);
	}

	public List<AlumnoDto> getAlumnoFlecha() {

		Long valorExcluir = 1L;
		String nombre = "jairo";
		String letra = "ia";

		Integer cantidad = (int) iAlumnoRepository.findAll().stream().count();

		List<String> listaNombreEsados = iAlumnoRepository.findAll().stream().map(x -> {
			return x.getEstado().getNombreEstado();
		}).collect(Collectors.toList());

		List<String> listaRespuesta = iAlumnoRepository.findAll().stream().filter(x -> {
			return !x.getEstado().getId().equals(valorExcluir);
		}).map(y -> {
			return y.getNombre() + " " + y.getApellido();
		}).collect(Collectors.toList());

		AlumnoDto alumno = iAlumnoRepository.findByNombreNativo(nombre);

		List<AlumnoDto> listadoAlumnos = iAlumnoRepository.findByNombreContaining(letra);

		listadoAlumnos.forEach(x -> {
			System.out.println(x.getApellido() + " " + x.getNombre());
		});
		int pageNumber = 0;
		int pageSize = 10;

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nombre").ascending());

		Page<AlumnoDto> page = iAlumnoRepository.findAll(pageable);

		List<AlumnoDto> listado = page.getContent();

		System.out.println("Objeto page: " + page.getTotalElements());

		System.out.println("Desde el objeto devuelto: " + listado.toString());

		System.out.println("Nombres de estados: " + listaNombreEsados);

		System.out.println("Cantidad de registros: " + cantidad);

		System.out.println("Nombres filtrados id, superior al dado: " + listaRespuesta);

		System.out.println("Nombre Nativo:" + alumno.getNombre() + " " + alumno.getApellido());

		return null;
	}

}
