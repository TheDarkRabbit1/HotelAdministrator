package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @GetMapping
    public String showRooms(Model model) {

        return "room/rooms";
    }
    @GetMapping("/roomInfo")
    public String getRoomInfo(@RequestParam("roomId") long roomId, Model model) {

//        model.addAttribute("room", room);
//        model.addAttribute("guests", guests);

        return "room/roomInfo";
    }
}

