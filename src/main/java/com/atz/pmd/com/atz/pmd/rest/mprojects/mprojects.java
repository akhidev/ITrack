package com.atz.pmd.com.atz.pmd.rest.mprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class mprojects {

    @Autowired
    private MyprojectService myprojectService;

    @GetMapping(path ="/users/myproject")
    public List<MyProjects> retiveAllProject(){
        System.out.println("Akhilesh 001");
        return myprojectService.getProjects();

    }

    @GetMapping(path ="/users/{username}/myprojects")
    public List<MyProjects> retiveAllProjects(@PathVariable String username){

        return myprojectService.getProjects();
    }

    @GetMapping(path = "/users/{username}/myprojects/{id}")
    public MyProjects getProject(@PathVariable String username, @PathVariable Long id) {
        System.out.println("Akhilesh 003");
        return myprojectService.findById(id);
    }

    @DeleteMapping("/users/{username}/myprojects/{id}")
    public ResponseEntity<Void> deleteMyproject(@PathVariable String username, @PathVariable Long id){
        System.out.println("Akhilesh 002");

    

        MyProjects myProject=myprojectService.deleteById(id);
        if(myProject!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}/myprojects/{id}")
    public ResponseEntity<MyProjects> updateMyproject(@PathVariable String username, @PathVariable Long id, @RequestBody MyProjects myProjects){

    MyProjects myProjectsUpdated = myprojectService.saveProject(myProjects);
    return new ResponseEntity<MyProjects>(myProjects, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/myprojects")
    public ResponseEntity<Void> updateMyproject(@PathVariable String username,  @RequestBody MyProjects myProjects){

        MyProjects myProjectsUpdated = myprojectService.saveProject(myProjects);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(myProjectsUpdated.getProjectId()).toUri();

        return ResponseEntity.created(uri).build();
    }




}

