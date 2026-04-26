package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.dtos.PiscinaDTO;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/piscinas")
public class PiscinaController {
    @Autowired
    private IPiscinaService pS;
    ModelMapper m = new ModelMapper();
//dante cambia los dtos por los dtos correspondientes en los endpoints ;)
    @PostMapping("/insertar-piscinas")
    public ResponseEntity<?>insertarPiscinas(@RequestBody @Valid PiscinaDTO dto){
        Piscina p = m.map(dto,Piscina.class);
        Piscina cur = pS.insert(p);
        PiscinaDTO respondeDTO = m.map(cur,PiscinaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(respondeDTO);

    }
    @GetMapping("/buscar-piscinas-id/{id}")
    public ResponseEntity<?>listarPiscinasPorId(@PathVariable int id){
        Optional<Piscina>piscina = pS.listId(id);
        if(piscina.isPresent()){
            PiscinaDTO dto = m.map(piscina.get(),PiscinaDTO.class);
            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Piscina no encontrada");
        }

    }
    @GetMapping("/listarPiscinasPorIdUsuarios/{idUsuario}")
    public ResponseEntity<?>listarPiscinasPorUsuario(@PathVariable int idUsuario){
        List<Piscina> piscinas = pS.listarPiscinasPorUsuarios(idUsuario);
        System.out.println(piscinas);
        if(piscinas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Piscina no encontrada");
        }
        else{
            List<PiscinaDTO> dto = piscinas.stream().map(y -> m.map(y,PiscinaDTO.class)).toList();
            return ResponseEntity.ok(dto);
        }
    }



    @PutMapping("/actualizar-piscinas")
    public ResponseEntity<String>actualizarPiscinas(@RequestBody @Valid PiscinaDTO dto){
        Optional<Piscina>existente = pS.listId(dto.getPiscina_id());
        Piscina p = new Piscina();
        p.setPiscina_id(dto.getPiscina_id());
        p.setNombre_piscina(dto.getNombre_piscina());
        p.setVolumen(dto.getVolumen());
        pS.update(p);
        return ResponseEntity.ok("Se ha actualizado correctamente");
    }
    @PutMapping("/eliminar-piscina")
    public ResponseEntity<String>eliminarPiscinas(@RequestBody @Valid PiscinaDTO dto){
        Optional<Piscina>existente = pS.listId(dto.getPiscina_id());
        Piscina p = new Piscina();
        p.setEliminado(true);
        pS.update(p);
        return ResponseEntity.ok("Se ha eliminado correctamente");
    }


}
