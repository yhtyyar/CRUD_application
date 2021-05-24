package repository.io;

import model.Region;
import repository.RegionRepository;
import repository.util.IOUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class IORegionRepository implements RegionRepository {

    private final String FILE_NAME = "src/main/resources/json_files/regions.json";
    private final File repositoryFile = new File(FILE_NAME);

    public IORegionRepository() {

        if (!repositoryFile.exists()) {
            IOUtil.createNewFile(repositoryFile);
        }
    }

    @Override
    public Region getById(Long id) {

        List <Region> regionList = getAll();

        Optional <Region> region = regionList.stream()
                .filter((r) -> r.getId() == id)
                .findFirst();
        return region.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id));
    }

    @Override
    public Region save(Region region) {
        updateRegionId(region);

        String recording = region.getId() + " | " + region.getNameRegion() + ".";
        IOUtil.writeRecording(repositoryFile, recording);

        return  region;
    }

    @Override
    public Region update(Region region) {
        List <Region> regionList = getAll();

        Optional <Region> resultRegion = regionList.stream()
                .filter((r) -> r.getId() == region.getId())
                .findFirst();

        Region foundRegion = resultRegion.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит обновленного элемента"));

        foundRegion.setNameRegion(region.getNameRegion());

        saveAll(regionList);

        return (Region) regionList;
    }

    @Override
    public void deleteById(Long id) {
        List <Region> regionList = getAll();

        Optional <Region> region = regionList.stream()
                .filter((r) ->r.getId() == id)
                .findFirst();

        regionList.remove(region.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id)));
        saveAll(regionList);
    }

    @Override
    public List<Region> getAll() {

        String fileString = IOUtil.fileToString(repositoryFile);

        if (fileString.length() == 0 || !fileString.contains(".")) {
            return new ArrayList<>();
        }

        String [] regions = fileString.split(".");

        return Arrays.stream(regions).map((s) -> {
            String[] parts = s.split(" | ");
            return new Region(Long.valueOf(parts[0]), parts[1]);
        }).collect(Collectors.toList());
    }

    private void updateRegionId(Region region) {
        List<Region> regionList = getAll();

        Long id = regionList.size() == 0 ? 1 : regionList.get(regionList.size() - 1).getId() + 1;
        region.setId(id);
    }

    private void saveAll(List<Region> list) {
        StringBuilder sb = new StringBuilder();

        list.forEach(r -> sb.append(r.getId()).append(" | ").append(r.getNameRegion()).append("."));

        IOUtil.rewriteAllRecordings(repositoryFile, sb.toString());
    }
}
