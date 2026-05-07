package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Evaluacion;
import pe.upc.smartpoolguardian.entities.Recomendacion;
import pe.upc.smartpoolguardian.schema.request.RecomendacionRequestDTO;
import pe.upc.smartpoolguardian.schema.response.RecomendacionResponseDTO;
import pe.upc.smartpoolguardian.servicesimplements.EvaluacionServiceImplement;
import pe.upc.smartpoolguardian.servicesimplements.RecomendacionServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionController {

    @Autowired
    private RecomendacionServiceImplement recomendacionService;

    @Autowired
    private EvaluacionServiceImplement evaluacionService;

    // ── CREATE ──────────────────────────────────────────────────────────────
    @PostMapping("/registrar")
    public ResponseEntity<RecomendacionResponseDTO> crearRecomendacion(
            @RequestBody @Valid RecomendacionRequestDTO dto) {

        // Verificar que la Evaluacion existe
        Evaluacion evaluacion = evaluacionService.buscarEvaluacionPorId(dto.getEvaluacionId());

        // DTO → Entity
        Recomendacion nueva = new Recomendacion();
        nueva.setMensaje(dto.getMensaje());
        nueva.setEvaluacion(evaluacion);

        // Guardar
        Recomendacion creada = recomendacionService.crearRecomendacion(nueva);

        // Entity → Response DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(creada));
    }
    // ── READ ALL ─────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<RecomendacionResponseDTO>> listarRecomendaciones() {
        List<RecomendacionResponseDTO> lista = recomendacionService.listarRecomendaciones()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // ── READ BY EVALUACION ───────────────────────────────────────────────────
    @GetMapping("/por-evaluacion/{idEvaluacion}")
    public ResponseEntity<List<RecomendacionResponseDTO>> listarPorEvaluacion(
            @PathVariable Integer idEvaluacion) {

        List<RecomendacionResponseDTO> lista = recomendacionService.listarPorEvaluacion(idEvaluacion)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // ── READ BY ID ───────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionResponseDTO> buscarPorId(@PathVariable Integer id) {
        Recomendacion recomendacion = recomendacionService.buscarRecomendacionPorId(id);
        return ResponseEntity.ok(toResponseDTO(recomendacion));
    }
    // ── UPDATE ───────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionResponseDTO> actualizarRecomendacion(
            @PathVariable Integer id,
            @RequestBody @Valid RecomendacionRequestDTO dto) {

        // Verificar que la Evaluacion existe
        Evaluacion evaluacion = evaluacionService.buscarEvaluacionPorId(dto.getEvaluacionId());

        // DTO → Entity
        Recomendacion actualizar = new Recomendacion();
        actualizar.setRecomendacionId(id);
        actualizar.setMensaje(dto.getMensaje());
        actualizar.setEvaluacion(evaluacion);

        // Actualizar
        Recomendacion actualizada = recomendacionService.actualizarRecomendacion(actualizar);

        return ResponseEntity.ok(toResponseDTO(actualizada));
    }
    // ── DELETE ───────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecomendacion(@PathVariable Integer id) {
        recomendacionService.eliminarRecomendacion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // ── Helper: Entity → ResponseDTO ─────────────────────────────────────────
    private RecomendacionResponseDTO toResponseDTO(Recomendacion r) {
        RecomendacionResponseDTO response = new RecomendacionResponseDTO();
        response.setRecomendacionId(r.getRecomendacionId());
        response.setMensaje(r.getMensaje());
        response.setEvaluacionId(r.getEvaluacion().getEvaluacionId());
        return response;
    }
}
