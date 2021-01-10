package com.microservicio.app.familia.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.app.familia.entity.Personas;
import com.microservicio.app.familia.exceptions.ErrorDetails;
import com.microservicio.app.familia.exceptions.ResourceNotFoundException;
import com.microservicio.app.familia.repository.IFamiliaRepository;
import com.microservicio.app.familia.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

	private static final Logger logger = LogManager.getLogger(PersonaController.class);

	@Autowired
	private IFamiliaRepository repository;
	@Autowired
	private MongoOperations mongoOps;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// Metodo que sirve para poder llamar todos los registros de las personas
	// registradas en la base de datos.
	@GetMapping("/verTodasPersonas")
	public List<Personas> findAllPersonas() {
		List<Personas> personas0 = null;
		// Try-catch para poder hacer registro y manejo de las excepciones.
		try {
			// Función utilizada para poder hacer la busqueda de los registros en la base de
			// datos.
			personas0 = repository.findAll();
			// Registro de entrada y salida de datos en el momento de uso del método.
			logger.info("Consulta de personas");
			LogBD("200", "verTodasPersonas ha sido Exitoso");
		} catch (Exception e) {
			logger.info("Consulta de personas");
			LogBD("500", "El proceso ha fallado");
		}
		return personas0;
	}

	// Método implementado que realiza la busqueda de una persona en la base de
	// datos por su id.
	@GetMapping("/verPersonaId/{id}")
	public ResponseEntity<Personas> findPersonaById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		ResponseEntity<Personas> persona1 = null;
		try {
			// Función que permite la busqueda de la persona haciendo validación de si
			// existe o no el id.
			Personas persona = repository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("No se encuentra a esa persona con el id ::" + id));
			// Registro de entrada y salida de datos en el momento de uso del método.
			logger.info("Consulta de personas");
			LogBD("200", "getAllPersonal Exitoso");
			persona1 = ResponseEntity.ok().body(persona);
		} catch (Exception e) {
			logger.info("Consulta de personas");
			LogBD("500", "Error al encontrar la información buscada");
		}
		return persona1;
	}

	// Método que sirve para poder agregar personas a la base de datos.
	@PostMapping("/addPersona")
	public Personas savePersona(@RequestBody Personas personas) {
		Personas personas1 = new Personas();
		try {
			// Función que sirve para agregar personas a la base de datos.
			personas1 = repository.save(personas);
			logger.info("Consulta de personas");
			LogBD("200", "Persona añadida de manera correcta");
		} catch (Exception e) {
			logger.info("Consulta de personas");
			LogBD("500", "error al añadir a la persona");
		}
		return personas1;
	}

	// Método utilizado para poder eliminar personas de la base de datos teniendo en
	// cuenta su id.
	@DeleteMapping("/eliminarPersona/{id}")
	public String deletePersona(@PathVariable Long id) {
		// función que sirve para poder eliminar los registros de las personas segun su
		// id,
		repository.deleteById(id);
		logger.info("Consulta de personas");
		LogBD("200", "Registro de la persona con grupo familiar eliminado con exito");
		return "La persona con el id " + id + " ha sido eliminada";
	}

	// Método utilizado para poder hacer la modificación de registros previamente
	// guardados en la base de datos
	@PutMapping("/modificarPersonas/{id}")
	public ResponseEntity<Personas> updatePersonas(@PathVariable(value = "id") Long id, @RequestBody Personas personas)
			throws ResourceNotFoundException {
		ResponseEntity<Personas> persona1 = null;
		try {
			// Función que permite realizar la busqueda del id para poder hacer el reemplazo
			// de la información.
			Personas perso = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id :: " + id));

			// Una vez encuentra el id lo elimina para poder reemplazarle con la nueva
			// información.
			repository.deleteById(id);

			// Toma de los nuevos datos para poder hacer la actualización de la misma.
			perso.setId(personas.getId());
			perso.setNombre(personas.getNombre());
			perso.setCorreo(personas.getCorreo());
			perso.setTelefono(personas.getTelefono());
			perso.setFamiliares(personas.getFamiliares());

			// Una vez cargada la nueva información, se almacena en el espacio que antes
			// estaba registrado para la otra persona.
			final Personas updatePersonas = repository.save(perso);
			persona1 = ResponseEntity.ok(updatePersonas);

			logger.info("Consulta de Personas");
			LogBD("200", "Modificación de Persona exitosa");
		} catch (Exception e) {
			logger.info("Consulta de personas");
			LogBD("500", "Error al modificar la información");
		}
		return persona1;

	}

	public void LogBD(String message, String details) {
		Date fecha = new Date();
		ErrorDetails errordetail = new ErrorDetails();
		errordetail.setTimeStamp(fecha);
		errordetail.setDetails(details);
		errordetail.setMessage(message);
		mongoOps.insert(errordetail);
	}
}