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
import com.google.gson.Gson;

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
            Game current_game = new Game(tempId);
            current_game.setHostType(request.body()); //this is RED or BLUE
            games.put(tempId, current_game);
            response.status(201);
            return current_game.getHost();
        }, new JsonTransformer());

        put("/dots/api/games/:gameid", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
                return Collections.EMPTY_MAP;
            }
            games.get(id).setStatus("IN_PROGRESS");
            response.status(200);
            return games.get(id).getGuest();
        }, new JsonTransformer());

        post("/dots/api/games/:gameId/hmove", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
            }
            CreateGameParams params = new Gson().fromJson(request.body(), CreateGameParams.class);
            if(!games.get(id).updateHBoard(params.getRow(), params.getCol(), params.getPlayerId())) {
                logger.error(String.format("Illegal move; line already filled %s", request.body()));
                response.status(422);
            }
            return Collections.EMPTY_MAP;

        }, new JsonTransformer());

        post("/dots/api/games/:gameId/vmove", "application/json", (request, response) -> {
            String id = request.params(":gameId");
            if (!games.containsKey(id)) {
                logger.error(String.format("Failed to find object with id: %s", id));
                response.status(404);
            }
            CreateGameParams params = new Gson().fromJson(request.body(), CreateGameParams.class);
            if ((games.get(id).getHost().getPlayerId().compareTo(params.getPlayerId()) != 0 && (games.get(id).getGuest().getPlayerId().compareTo(params.getPlayerId())) != 0)) {
                logger.error(String.format("Failed to find player with id: %s", params.getPlayerId()));
                response.status(404);
            }
            if(!games.get(id).updateVBoard(params.getRow(), params.getCol(), params.getPlayerId())) {
                logger.error(String.format("Illegal move; line already filled %s", request.body()));
                response.status(422);
            }

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
            return games.get(id).getCurrent_board();
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

