package com.soccerconnect.controllers;


import com.soccerconnect.DBConnectionApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController extends MasterController{
    
    DBConnectionApp db = new DBConnectionApp();

    @GetMapping(value = "/addGround")
    public String addGround() 
    // @RequestParam(value = "stadiumName") String stadiumName,
    //                               @RequestParam(value = "location") String location,
    //                               @RequestParam(value = "managerName") String manageName,
    //                               @RequestParam(value = "capacity") String capacity)
                    
    {
        // db.groundQuery(stadiumName, location, manageName, capacity);
        return "addGround";
    }

    
}