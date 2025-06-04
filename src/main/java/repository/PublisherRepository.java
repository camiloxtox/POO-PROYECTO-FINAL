package repository;

import model.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherRepository {
    private static List<Publisher> publishers = new ArrayList<>();

    // CREATE - Agregar editorial
    public static void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    // READ - Obtener todas las editoriales
    public static List<Publisher> getAllPublishers() {
        return publishers;
    }

    // READ - Obtener editorial por ID
    public static Optional<Publisher> getPublisherById(int id) {
        return publishers.stream().filter(p -> p.getId() == id).findFirst();
    }

    // UPDATE - Actualizar información de la editorial
    public static boolean updatePublisher(int id, String newName, String newCountry) {
        Optional<Publisher> publisher = getPublisherById(id);
        if (publisher.isPresent()) {
            publisher.get().setName(newName);
            publisher.get().setCountry(newCountry);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar editorial por ID
    public static boolean deletePublisher(int id) {
        return publishers.removeIf(p -> p.getId() == id);
    }
}
