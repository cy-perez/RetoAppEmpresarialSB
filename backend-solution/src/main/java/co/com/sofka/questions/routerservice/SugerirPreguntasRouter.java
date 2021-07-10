package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecaseservice.UseCaseSugerirPreguntas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SugerirPreguntasRouter {
    @Bean
    public RouterFunction<ServerResponse> suggestQuestions(UseCaseSugerirPreguntas useCaseSugerirPreguntas) {
        return route(
                GET("/sugerirpreguntas/{pregunta}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseSugerirPreguntas.suggest(request.pathVariable("pregunta")), QuestionDTO.class))
        );
    }
}
