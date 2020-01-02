package com.atz.pmd.itrack.myproject;

import com.atz.pmd.itrack.ItrackJpaRepository;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

import org.json.*;


import static ch.qos.logback.core.CoreConstants.SYSOUT;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class MyprojectResource {

//    @Autowired
//    private MyprojectService myprojectService;

    @Autowired
    private ItrackJpaRepository itrackJpaRepository;

    @GetMapping(path ="/users/myproject")
    public List<MyProjects> retiveAllProject(@PathVariable String username){
        System.out.println("Akhilesh 001");

        return itrackJpaRepository.findByUsername("USER");
        //return myprojectService.getProjects();
    }

    @GetMapping(path ="/users/{username}/myprojects")
    public List<MyProjects> retiveAllProjects(@PathVariable String username){
        System.out.println("Akhilesh 005"+itrackJpaRepository.findByUsername(username));
        return itrackJpaRepository.findByUsername(username);
       // return myprojectService.getProjects();
    }

    @GetMapping(path = "/users/{username}/myprojects/{id}")
    public MyProjects getProject(@PathVariable String username, @PathVariable Long id) {
        System.out.println("Akhilesh 003");
        return itrackJpaRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/myprojects/{id}")
    public ResponseEntity<Void> deleteMyproject(@PathVariable String username, @PathVariable Long id){
        System.out.println("Akhilesh 002");
        itrackJpaRepository.deleteById(id);
//        MyProjects myProject=myprojectService.deleteById(id);
//        if(myProject!=null){
//            return ResponseEntity.noContent().build();
//        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}/myprojects/{id}")
    public ResponseEntity<MyProjects> updateMyproject(@PathVariable String username, @PathVariable Long id, @RequestBody MyProjects myProjects){
    myProjects.setUsername(username);
    MyProjects myProjectsUpdated = itrackJpaRepository.save(myProjects);
    return new ResponseEntity<MyProjects>(myProjects, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/myprojects")
    public ResponseEntity<Void> updateMyproject(@PathVariable String username,  @RequestBody MyProjects myProjects){
        System.out.println("Akhilesh in pageName"+myProjects);

        myProjects.setUsername(username);
        MyProjects myProjectsUpdated = itrackJpaRepository.save(myProjects);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(myProjectsUpdated.getProjectId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/users/{username}/uploadproject")
    public ResponseEntity<Void> uploadMyproject(@PathVariable String username,  @RequestBody JSONObject myProjects) throws ParseException {

          Object obj = null;
//
        List<MyProjects> projectList =(ArrayList)myProjects.get("MyProject");
        List<MyProjects> myprojectList =new ArrayList<MyProjects>();

        System.out.println("Akhilesh in pageName02"+projectList.size());
        LinkedHashMap projectMap = new LinkedHashMap();
        MyProjects myProjectsUpdated = new MyProjects();
        MyProjects myProject=new MyProjects();
        for(int i=0;i<projectList.size();i++){
            obj = projectList.get(i);
            projectMap =  (LinkedHashMap)obj;
            myProject=new MyProjects();
            myProject.setUsername((String) projectMap.get("username"));
            myProject.setProjectName((String) projectMap.get("projectName"));
            myProject.setProjectDesc((String) projectMap.get("projectDesc"));
            myProject.setStartDate(strTodate((String) projectMap.get("startDate")));
            myProject.setEndDate(strTodate((String) projectMap.get("endDate")));
            myprojectList.add(myProject);

        }
        itrackJpaRepository.saveAll(myprojectList);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(myProjectsUpdated.getProjectId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    public Date strTodate(String date){
        System.out.println("Akhiesh inside strTodate 01 :"+date);

        String sdate= date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
        System.out.println("Akhiesh inside strTodate 02 :"+sdate);

        Date rdate = new Date();
        try {
            rdate = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
            System.out.println("Akhiesh inside strTodate 03 :"+rdate);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return rdate;
    }

}

