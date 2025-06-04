package repository;

import model.Library;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryRepository {
    private static List<Library> libraries = new ArrayList<>();

    // CREATE - Agregar biblioteca
    public static void addLibrary(Library library) {
        libraries.add(library);
    }

    // READ - Obtener todas las bibliotecas
    public static List<Library> getAllLibraries() {
        return libraries;
    }

    // READ - Obtener biblioteca por ID
    public static Optional<Library> getLibraryById(int id) {
        return libraries.stream().filter(l -> l.getId() == id).findFirst();
    }

    // UPDATE - Actualizar biblioteca
    public static boolean updateLibrary(int id, String newName, String newAddress, int newCityId) {
        Optional<Library> library = getLibraryById(id);
        if (library.isPresent()) {
            library.get().setName(newName);
            library.get().setAddress(newAddress);
            library.get().setCityId(newCityId);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar biblioteca por ID
    public static boolean deleteLibrary(int id) {
        return libraries.removeIf(l -> l.getId() == id);
    }
}
