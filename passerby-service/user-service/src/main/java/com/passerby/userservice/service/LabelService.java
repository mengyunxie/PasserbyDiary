package com.passerby.userservice.service;

import com.passerby.userservice.model.Label;
import com.passerby.userservice.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public Label getLabel(String key) {
        return labelRepository.findById(key).orElse(null);
    }

    public Map<String, Label> getAllLabels() {
        List<Label> labelList = labelRepository.findAll();
        return labelList.stream().collect(Collectors.toMap(Label::getLabel_key, label -> label));
    }

}
