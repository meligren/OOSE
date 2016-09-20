//-------------------------------------------------------------------------------------------------------------//
// Code based on a tutorial by Shekhar Gulati of SparkJava at
// https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/
//-------------------------------------------------------------------------------------------------------------//

package com.dots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

public class DotsController {

    private static final String API_CONTEXT = "/api/v1";

    private Map<String, Game> games;

    private final Logger logger = LoggerFactory.getLogger(com.dots.DotsController.class);

    public DotsController() {
        games = new HashMap<String, Game>();
        setupEndpoints();
    }

    private void setupEndpoints() {
        post("/dots/api/games", "application/json", (request, response) -> {
            String tempId = UUID.randomUUID().toString();
            Game temp_game = new Game(tempId);
            temp_game.setHostType(request.body()); //this is RED or BLUE
            response.status(201);
            temp_game.setStatus("IN_PROGRESS");
            games.put(tempId, temp_game);
            return temp_game.getHost();
        }, new JsonTransformer());

        put("/dots/api/games:gameid", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
                return Collections.EMPTY_MAP;
            }
            response.status(200);
            return games.get(id).getGuest();
        }, new JsonTransformer());

        post("/dots/api/games/:gameId/hmove", "application/json", (request, response) -> {

            return Collections.EMPTY_MAP;

        }, new JsonTransformer());

        post("/dots/api/games/:gameId/vmove", "application/json", (request, response) -> {

            return Collections.EMPTY_MAP;

        }, new JsonTransformer());

        get("/dots/api/games/:gameId/board", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
                return Collections.EMPTY_MAP;
            }
            response.status(200);
            return games.get(id).current_board;
        }, new JsonTransformer());

        get("/dots/api/games/:gameId/state", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
                return Collections.EMPTY_MAP;
            }
            response.status(200);
            return games.get(id).getCurrent_state();
        }, new JsonTransformer());


    }
}

