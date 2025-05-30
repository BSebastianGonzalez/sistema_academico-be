package co.ufps.edu.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {
    @GetMapping("/estudiantes/index_estudiantes")
    public String estudiantes() {
        return "index_estudiantes";
    }
    @GetMapping("/docentes/index_docentes")
    public String docentes() {
        return "index_docentes";
    }
}