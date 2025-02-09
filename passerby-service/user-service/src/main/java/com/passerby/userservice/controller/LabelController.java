package com.passerby.userservice.controller;

import com.passerby.userservice.model.Label;
import com.passerby.userservice.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/session/labels")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/{labelKey}")
    public Label getLabel(@PathVariable String labelKey) {
        return labelService.getLabel(labelKey);
    }

    @GetMapping()
    public Map<String, Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    // For openFein
    @GetMapping("/valid/{labelKey}")
    public ResponseEntity<Boolean> isValid(@PathVariable String labelKey) {
        boolean isValid = labelService.isValid(labelKey);
        return ResponseEntity.ok(isValid);
    }
}
