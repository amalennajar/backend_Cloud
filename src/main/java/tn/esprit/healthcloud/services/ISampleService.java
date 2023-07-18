package tn.esprit.healthcloud.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Sample;

import java.util.List;

public interface ISampleService {
    Sample addSample(Sample s);
    Sample updateSample(Sample sample , int id_sample);

    void deleteSample(@PathVariable int id);

    List<Sample> getAllSample();

    Sample getSample(int id);
}

