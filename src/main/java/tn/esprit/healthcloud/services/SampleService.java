package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Sample;

import tn.esprit.healthcloud.repositories.SampleRepository;

import java.util.List;

@Service
public class SampleService implements ISampleService {
    @Autowired
    SampleRepository sampleRepository;

    @Override
    public Sample addSample(Sample s) {
        Sample sample =sampleRepository.save(s);
        return sample;
    }

    @Override
    public Sample updateSample(Sample sample, int id_sample) {
        Sample samp = sampleRepository.findById(id_sample).get();
        samp=sample;
        sampleRepository.save(samp);
        return sample;
    }

    @Override
    public void deleteSample(int id) {
        sampleRepository.deleteById(id);
    }

    @Override
    public List<Sample> getAllSample() {
        return (List<Sample>) sampleRepository.findAll();
    }

    @Override
    public Sample getSample(int id) {
        Sample sample = sampleRepository.findById(id).orElse(null);
        return sample;
    }


}
