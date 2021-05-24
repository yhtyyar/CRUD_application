package controller;

import model.Writer;
import repository.WriterRepository;
import repository.json.JsonRepository;
import repository.json.JsonRepositoryDeserialization;

import java.util.List;

public class WriterController {

    private WriterRepository writerRepository;

    public WriterController() {
        writerRepository = JsonRepositoryDeserialization.getWriterRepository();
    }


    public Writer getById(Long id) {

        return writerRepository.getById(id);
    }


    public void save (Writer writer) {

        writerRepository.save(writer);
    }


    public void update (Writer writer) {

        writerRepository.update(writer);
    }


    public void deleteById (Long id) {

        writerRepository.deleteById(id);
    }


    public List<Writer> getAll() {

        return writerRepository.getAll();
    }


}
