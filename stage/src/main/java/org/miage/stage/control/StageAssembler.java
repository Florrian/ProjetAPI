package org.miage.stage.control;

import org.miage.stage.boundary.OffreStageRepresentation;
import org.miage.stage.entity.OffreStage;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StageAssembler implements RepresentationModelAssembler<OffreStage, EntityModel<OffreStage>> {

    @Override
    public EntityModel<OffreStage> toModel(OffreStage OffreStage) {
        return EntityModel.of(OffreStage,
                linkTo(methodOn(OffreStageRepresentation.class).getOneOffreStage(offreStage.getId())).withSelfRel(),
                linkTo(methodOn(OffreStageRepresentation.class).getOffreStage()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<OffreStage>> toCollectionModel(Iterable<? extends OffreStage> entities) {
        var OffreStageModel = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .toList();
        return CollectionModel.of(OffreStageModel,
                linkTo(methodOn(OffreStageRepresentation.class).getAllOffreStage()).withSelfRel());
    }
}
