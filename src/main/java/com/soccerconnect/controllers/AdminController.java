package com.soccerconnect.controllers;


import com.soccerconnect.models.AddGround;
import com.soccerconnect.models.DBConnectionApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController extends MasterController{
    
    AddGround addg = new AddGround();

    @GetMapping(value = "/ground")
    public String addGround()
    {
        return "ground";
    }


    @RequestMapping(value = "/ground")
    public String addGround(        @RequestParam(value = "stadiumName") String stadiumName,
                                    @RequestParam(value = "stadiumId") String stadiumId,
                                    @RequestParam(value = "address") String address,
                                    @RequestParam(value = "managerName") String managerName,
                                    @RequestParam(value = "phone") String phone,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "capacity") String capacity){
        System.out.println(stadiumName +stadiumId + address + managerName +phone +email +capacity);
        addg.groundQuery(stadiumName, stadiumId, address, managerName, phone, email, capacity, capacity);
        return "welcomeAdmin";
    }

    
}