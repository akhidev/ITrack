package com.atz.pmd.com.atz.pmd.rest.mprojects;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class MyprojectService {

    private static List<MyProjects> myProjects = new ArrayList();
    private static int idCounter = 1;

    static {
        myProjects.add(new MyProjects(++idCounter,"PROJECT_001","First Project",new Date(01/01/2019),new Date(01/01/2019)));
        myProjects.add(new MyProjects(++idCounter,"PROJECT_002","Second Project",new Date(01/01/2019),new Date(01/01/2019)));
        myProjects.add(new MyProjects(++idCounter,"PROJECT_003","Third Project",new Date(01/01/2019),new Date(01/01/2019)));
    }

    public List<MyProjects> getProjects(){
        System.out.println("Akhilesh 001"+myProjects);
        return myProjects;
    }


    public MyProjects getProjects(long id){
        System.out.println("Akhilesh 001"+myProjects);
        MyProjects myProject = findById(id);
        return myProject;
        }

    public MyProjects deleteById(long id){
        MyProjects myProject = findById(id);
        if(myProjects == null)
            return null;
        if(myProjects.remove(myProject)){
            return myProject;
        }
        return null;
    }

    public MyProjects findById(long id) {
        for(MyProjects myprojects: myProjects ){
            if(myprojects.getProjectId()==id){
                return myprojects;
            }
        }
        return null;
    }

    public MyProjects saveProject(MyProjects myProject) {
        if(myProject.getProjectId()==-1||myProject.getProjectId()==0){
            myProject.setProjectId(++idCounter);
            this.myProjects.add(myProject);
        }else{
            deleteById(myProject.getProjectId());
            this.myProjects.add(myProject);
        }

        return myProject;

    }



}
