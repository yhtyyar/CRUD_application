package repository.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class UtilsAbstract<T> implements Utils<T> {

    private File fileRepository;

    public UtilsAbstract(File fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public void inputRepository() {

        try{
            if( !fileRepository.exists()) {
                fileRepository.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }

    }

    public File getFileRepository() {
        return fileRepository;
    }

}
