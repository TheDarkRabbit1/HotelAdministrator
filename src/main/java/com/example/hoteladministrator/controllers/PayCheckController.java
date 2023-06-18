package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.PayCheck;
import com.example.hoteladministrator.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payChecks")
@AllArgsConstructor
public class PayCheckController {
    private final RoomService roomService;

    @GetMapping
    public String showAllPayChecks(Model model) {
        model.addAttribute("payChecks", roomService.getPayCheckList());
        return "payCheck/payChecks";
    }

    @GetMapping("/payCheckInfo")
    public String showPayCheck(@RequestParam("roomId") Long roomId, Model model) {
        PayCheck p = roomService.getLastPayCheckByRoomId(roomId);
        model.addAttribute("payCheck",p);
        return "payCheck/payCheckInfo";
    }
}
