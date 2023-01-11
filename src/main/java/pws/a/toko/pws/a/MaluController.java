/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.toko.pws.a;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pws.a.toko.pws.a.exceptions.NonexistentEntityException;

/**
 *
 * @author LENOVO
 */

@RestController
@CrossOrigin
@ResponseBody
public class MaluController {
    
    Barang data = new Barang();
    BarangJpaController control = new BarangJpaController();
    
    @GetMapping(value="/GET", produces = APPLICATION_JSON_VALUE)
    public List<Barang> getData(){
        List<Barang> buffer = new ArrayList<>();
        buffer = control.findBarangEntities();
        return buffer;
    }
    
    
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Barang.class);
        try {
            control.create(data);
            feedback = data.getNama() + "Saved";
        } catch (Exception error) {
            feedback = error.getMessage();
        }
            return feedback;
        
    }
    
    
    @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Barang.class);
        try {
            control.edit(data);
            feedback = data.getNama() + "Edited";
        } catch (Exception error) {
            feedback = error.getMessage();
        }
            return feedback;
        
    }
    
    
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Barang.class);
        try {
            control.destroy(data.getId());
            feedback = data.getNama() + "deleted";
        } catch (NonexistentEntityException error) {
            feedback = error.getMessage();
        }
            return feedback;
        
    }
    
}
