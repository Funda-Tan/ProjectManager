package com.example.myapplication;

import java.util.ArrayList;

public class Project {

        //Variables

        private String project1;
        private String project2;
        private String project3;

        //Constructors

    public Project( String project1, String project2, String project3){

            this.project1 = project1;
            this.project2 = project2;
            this.project3 = project3;

        }

    public Project( String project1, String project2 ){

        this.project1 = project1;
        this.project2 = project2;


    }

    public Project( String project1){

        this.project1 = project1;


    }


    public Project()
        {

            this.project1 = "sadad";

        }

        //Methods

        public String getProject1()
        {
            return project1;
        }

    public String getProject2()
    {
        return project2;
    }

    public String getProject3()
    {
        return project3;
    }
    }

