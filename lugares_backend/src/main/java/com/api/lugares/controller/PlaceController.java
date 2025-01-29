package com.api.lugares.controller;

import com.api.lugares.api.PlaceRequest;
import com.api.lugares.api.PlaceResponse;
import com.api.lugares.domain.PlaceMapper;
import com.api.lugares.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/places")
@Tag(name = "Places", description = "Gerenciamento de lugares.")
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    @Operation(
            summary = "Criar um novo lugar",
            description = "Cria um novo lugar e salva no banco de dados.",
            tags = {"Places"},
            parameters = @Parameter(
                    name = "place",
                    description = "JSON enviado na requisição.",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Lugar criado com sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PlaceResponse.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(
            @NotBlank @Valid @RequestBody PlaceRequest request
    ){
        var placeResponse = placeService
                .create(request)
                .map(PlaceMapper::fromPlaceToResponse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(placeResponse);
    }

    @Operation(
            summary = "Listar e filtrar lugares",
            description = "Permite listar todos os lugares cadastrados no banco de dados podendo ser filtrado ou não pelo nome do lugar.",
            tags = {"Places"},
            parameters = @Parameter(
                    name = "name",
                    description = "Nome a ser filtrado.",
                    required = false
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de lugares.",
                            content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = PlaceResponse.class))
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Flux<PlaceResponse>> listAll(
            @RequestParam(value = "name", required = false)
            Optional<String> name){

        Flux<PlaceResponse> places = name
                .map(n -> placeService.listAllByName(n))
                .orElseGet(placeService::listAll);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(places);
    }

    @Operation(
            summary = "Detalhar lugar",
            description = "Obter mais detalhes de um lugar específico.",
            tags = {"Places"},
            parameters = @Parameter(
                    name = "id",
                    description = "ID do lugar a ser detalhado.",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lugar procurado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PlaceResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Mono<PlaceResponse>> detail(
            @PathVariable Long id
    ){
        var place = placeService.detail(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(place);
    }

    @Operation(
            summary = "Editar lugar",
            description = "Editar um objeto específico.",
            tags = {"Places"},
            parameters = {@Parameter(
                    name = "id",
                    description = "ID do lugar a ser editado.",
                    required = true
            ), @Parameter(
                    name="request",
                    description = "JSON com as informações que quer alterar.",
                    required = true
            )},
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Lugar editado com sucesso."
                    )
            }
    )
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> edit(
            @PathVariable Long id,
            @NotBlank @Valid @RequestBody PlaceRequest request
    ) {
        return placeService.edit(id, request)
                .then(Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build()));
    }

    @Operation(
            summary = "Deletar lugar",
            description = "Deletar um lugar específico.",
            tags = {"Places"},
            parameters = @Parameter(
                    name = "id",
                    description = "ID do lugar a ser deletado.",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Lugar deletado com sucesso."
                    )
            }
    )
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return placeService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
