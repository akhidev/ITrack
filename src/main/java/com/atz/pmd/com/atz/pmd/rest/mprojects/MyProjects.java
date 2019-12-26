package com.atz.pmd.com.atz.pmd.rest.mprojects;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
//@Entity
//@Table(name = "MY_PROJECTS")
//@EntityListeners(AuditingEntityListener.class)
public class MyProjects {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;
    private String projectName;
    private String projectDesc;
    private Date startDate;
    private Date endDate;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MyProjects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    protected MyProjects(String s) {
    }

    public MyProjects(long projectId, String projectName, String projectDesc, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyProjects)) return false;
        MyProjects that = (MyProjects) o;
        return getProjectId() == that.getProjectId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId());
    }
}
