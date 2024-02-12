package com.BootCampexample.kelompok4.backend.controller;


import com.BootCampexample.kelompok4.backend.dto.KnowledgeDto;
import com.BootCampexample.kelompok4.backend.entity.Knowledge;
import com.BootCampexample.kelompok4.backend.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/knowledge")
@Slf4j
public class KnowledgeController {


    @Autowired
    KnowledgeService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable Integer id) {
        try {
            Knowledge knowledge = service.findId(id);
            return ResponseEntity.ok(knowledge);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().body("Data Tidak Ditemukan");
        }
    }


    @GetMapping("")
    public List<Knowledge> findAll() { return service.findAll();}

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid KnowledgeDto.Create knowledge, BindingResult result){
        Map<String, Object> output = new HashMap<>();
        if(result.hasErrors()){
            output.put("id", null);
            output.put("status", "Create date gagal");
            output.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);

        } else {
            output.put("id", service.create(knowledge));
            output.put("status", "Create date berhasil");
            return ResponseEntity.ok(output);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody @Valid KnowledgeDto.Update knowledge, BindingResult result) {
        Map<String, Object> output = new HashMap<>();
        if(result.hasErrors()){
            output.put("status", "update data gagal");
            output.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);

        } else {
            try {
                service.findId(knowledge.getId());
                service.update(knowledge);
                output.put("status", "Berhasil update data");
                return ResponseEntity.ok().body(output);
            } catch (EmptyResultDataAccessException e) {
                output.put("status", "Id tidak ditemukan");
                return ResponseEntity.badRequest().body(output);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        Map<String, Object> output = new HashMap<>();
        try {
            service.findId(id);
            service.delete(id);
            output.put("status", "Berhasil hapus data");
            return ResponseEntity.ok(output);
        } catch (EmptyResultDataAccessException e) {
            output.put("status", "Id tidak ditemukan");
            return ResponseEntity.badRequest().body(output);
        }
    }
}
