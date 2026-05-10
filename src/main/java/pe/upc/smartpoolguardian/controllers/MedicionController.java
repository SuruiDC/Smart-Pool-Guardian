package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.schema.dtos.MedicionDTO;
import pe.upc.smartpoolguardian.schema.dtos.PrediccionAlgasDTO;
import pe.upc.smartpoolguardian.schema.response.MedPorTipoResponseDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

    @Autowired
    private IMedicionService mS;

    @Autowired
    private IPiscinaService pS;

    ModelMapper m = new ModelMapper();

    @PostMapping("/registrar/")
    public ResponseEntity<?> crearMedicion(@RequestBody @Valid MedicionDTO dto) {
        //ENCONTRAR PISCINA
        var piscinaOpt = pS.buscarPiscinaPorId(dto.getIdPiscina());

        //DE DTO A ENTITY
        Medicion medicion = new Medicion();
        medicion.setFechaMedicion(dto.getFechaMedicion());
        medicion.setPiscina(piscinaOpt);

        //CREAR MEDICION
        Medicion registro = mS.crearMedicion(medicion);

        //DE ENTITY A DTO
        MedicionDTO response = new MedicionDTO();
        response.setFechaMedicion(registro.getFechaMedicion());
        response.setIdPiscina(registro.getPiscina().getPiscinaId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar/{idPiscina}")
    public ResponseEntity<?> listarPorPiscina(@PathVariable int idPiscina) {

        Piscina piscina = pS.buscarPiscinaPorId(idPiscina);

        if (piscina.isEliminado()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La piscina esta eliminado no puedes ver sus mediciones.");

        List<MedicionDTO> mediciones = mS.listarMedicionesPorPiscina(idPiscina).stream()
                .map( x -> m.map(x, MedicionDTO.class)).toList();

        if (mediciones.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron mediciones de esa piscina o no existe esa piscina.");

        } else {

            return ResponseEntity.ok(mediciones);
        }
    }

    @GetMapping("/prediccion-algas/{idUsuario}")
    public ResponseEntity<?> verRiesgoAlgas(@PathVariable("idUsuario") Integer idUsuario) {
        List<PrediccionAlgasDTO> alertas = mS.obtenerPrediccionesAlgas(idUsuario);

        if (alertas.isEmpty()) {
            return new ResponseEntity<>("No se detectó riesgo de algas para las piscinas de este usuario. Los niveles están bajo control.", HttpStatus.OK);
        }

        return new ResponseEntity<>(alertas, HttpStatus.OK);
    }

    @GetMapping("/obtener-tipo-mediciones-por-piscina/{idPiscina}/{tipo}")
    public ResponseEntity<?> medicionesPorTipoYPiscina(
            @PathVariable("idPiscina") Integer idPiscina,
            @PathVariable("tipo") String tipo
    ) {
        List<Object[]> lista = mS.ObtenerMedicionesDeUnTipoPorPiscina(idPiscina, tipo);

        if(lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay mediciones existentes");
        }

        List<MedPorTipoResponseDTO> response = new ArrayList<>();
        for(Object[] fila : lista){
            MedPorTipoResponseDTO dto = new MedPorTipoResponseDTO();
            dto.setPiscina_id(((Number)fila[0]).intValue());
            dto.setNombre_piscina(((String)fila[1]));
            dto.setFecha_meicion(((LocalDate)fila[2]));
            dto.setTipo_medicion(((String)fila[3]));
            response.add(dto);
        }

        return ResponseEntity.ok(response);
    }
}
