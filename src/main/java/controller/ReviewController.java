package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Review;
import repository.ReviewRepository;
import java.util.List;
import java.util.Optional;

public class ReviewController {
    public static void configureRoutes(Javalin app) {
        app.get("/reviews", ReviewController::getAllReviews);
        app.get("/reviews/{id}", ReviewController::getReviewById);
        app.post("/reviews", ReviewController::createReview);
        app.put("/reviews/{id}", ReviewController::updateReview);
        app.delete("/reviews/{id}", ReviewController::deleteReview);
    }

    public static void getAllReviews(Context ctx) {
        List<Review> reviews = ReviewRepository.getAllReviews();
        ctx.json(reviews);
    }

    public static void getReviewById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Review> review = ReviewRepository.getReviewById(id);
        review.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Review not found"));
    }

    public static void createReview(Context ctx) {
        Review newReview = ctx.bodyAsClass(Review.class);
        ReviewRepository.addReview(newReview);
        ctx.status(201).json(newReview);
    }

    public static void updateReview(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Review reviewData = ctx.bodyAsClass(Review.class);
        boolean updated = ReviewRepository.updateReview(id, reviewData.getContent(), reviewData.getRating());
        if (updated) {
            ctx.result("Review successfully updated");
        } else {
            ctx.status(404).result("Review not found");
        }
    }

    public static void deleteReview(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = ReviewRepository.deleteReview(id);
        if (deleted) {
            ctx.result("Review successfully deleted");
        } else {
            ctx.status(404).result("Review not found");
        }
    }
}
