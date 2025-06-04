package repository;

import model.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewRepository {
    private static List<Review> reviews = new ArrayList<>();

    // CREATE - Agregar reseña
    public static void addReview(Review review) {
        reviews.add(review);
    }

    // READ - Obtener todas las reseñas
    public static List<Review> getAllReviews() {
        return reviews;
    }

    // READ - Obtener reseña por ID
    public static Optional<Review> getReviewById(int id) {
        return reviews.stream().filter(r -> r.getId() == id).findFirst();
    }

    // UPDATE - Actualizar contenido y calificación de reseña
    public static boolean updateReview(int id, String newContent, int newRating) {
        Optional<Review> review = getReviewById(id);
        if (review.isPresent()) {
            review.get().setContent(newContent);
            review.get().setRating(newRating);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar reseña por ID
    public static boolean deleteReview(int id) {
        return reviews.removeIf(r -> r.getId() == id);
    }
}
