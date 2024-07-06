package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import med.voll.api.domain.direction.DataAddres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<DataResponseDoctor> RegisterDoctor(@RequestBody @Valid DataRegisterDoctor dataRegisterDoctor, UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor = doctorRepository.save(new Doctor(dataRegisterDoctor));
        DataResponseDoctor dataResponseDoctor = new DataResponseDoctor(doctor.getId(),
                doctor.getName(), doctor.getEmail(), doctor.getNumberTelf(),doctor.getSpeciality(),
                new DataAddres(doctor.getDirection().getStreet(),
                        doctor.getDirection().getDistric(),
                        doctor.getDirection().getCity(),
                        doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        //Retornar 201 created
        return ResponseEntity.created(url).body(dataResponseDoctor);
        //Retornar URI de médico, por convención
    }

    @GetMapping
    public ResponseEntity<Page<DataListDoctor>> listDoctors(@PageableDefault(size = 2) Pageable pageable) {
//        return doctorRepository.findAll(pageable).map(DataListDoctor::new);
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(pageable).map(DataListDoctor::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataUpdateDoctor dataUpdateDoctor){
        Doctor doctor = doctorRepository.getReferenceById(dataUpdateDoctor.id());
        doctor.dataUpdate(dataUpdateDoctor);
        return ResponseEntity.ok(new DataResponseDoctor(doctor.getId(),
                doctor.getName(), doctor.getEmail(), doctor.getNumberTelf(),doctor.getSpeciality(),
                new DataAddres(doctor.getDirection().getStreet(),
                        doctor.getDirection().getDistric(),
                        doctor.getDirection().getCity(),
                        doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.desactiveDoctor();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseDoctor> getDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        var datos = new DataResponseDoctor(doctor.getId(),
                doctor.getName(), doctor.getEmail(), doctor.getNumberTelf(),doctor.getSpeciality(),
                new DataAddres(doctor.getDirection().getStreet(),
                        doctor.getDirection().getDistric(),
                        doctor.getDirection().getCity(),
                        doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement()));
        return ResponseEntity.ok(datos);

    }

}
